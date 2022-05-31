/* FILE: A3_solutions.c is where you will code your answers for Assignment 3.
 * 
 * Each of the functions below can be considered a start for you. They have 
 * the correct specification and are set up correctly with the header file to
 * be run by the tester programs.  
 *
 * You should leave all of the code as is, especially making sure not to change
 * any return types, function name, or argument lists, as this will break
 * the automated testing. 
 *
 * Your code should only go within the sections surrounded by
 * comments like "REPLACE EVERTHING FROM HERE... TO HERE.
 *
 * The assignment document and the header A3_solutions.h should help
 * to find out how to complete and test the functions. Good luck!
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "A3_provided_functions.h"

unsigned char*
bmp_open( char* bmp_filename,        unsigned int *width, 
          unsigned int *height,      unsigned int *bits_per_pixel, 
          unsigned int *padding,     unsigned int *data_size, 
          unsigned int *data_offset                                  )
{
  unsigned char *img_data=NULL;
  
  //opening the file and checking if it is null
  FILE *filename = fopen(bmp_filename, "rb");
  if (filename==NULL) {
    printf("Unable to open the file. \n" );
    return NULL;
  }

  //checking if the file is a bmp file
  char b, m;
  fread (&b,1,1,filename);
  fread (&m,1,1,filename);
  if (b!=66 || m!=77) {
    printf("Not a bmp file. \n");
    return NULL;
  }

  //finding the size of the whole file 
  fread(data_size, 1, sizeof(unsigned int), filename );
  if (data_size==NULL) {
    return NULL;
  }

  rewind(filename);

  //allocating memory for bitmap file
  img_data =(unsigned char*)malloc(sizeof(unsigned char)*(*data_size));
  if (img_data==NULL) {
    printf("Failed to allocate memory for bmp file. \n");
    return NULL;
  }

  fread(img_data, 1, (*data_size), filename);

  //getting the width
  unsigned int *w= (unsigned int*)(img_data+18);
  *width = *w;

  //getting the height
  unsigned int *h = (unsigned int*)(img_data+22);
  *height = *h;

  //getting the bpp
  unsigned short int *temp;
  temp = (unsigned short int*)(img_data+28);
  *bits_per_pixel = *temp;

  //getting the padding value 
  unsigned int bytes = ((*width)*(*bits_per_pixel))/8;
  if (bytes%4 !=0) {
    *padding = 4-bytes%4;
  }

  //getting the header size (data offset)
  unsigned int *d = (unsigned int*)(img_data+10);
  *data_offset = *d;

  fclose(filename);

  return img_data;  
}

void 
bmp_close( unsigned char **img_data )
{
  free(*img_data);
  *img_data = NULL;
}

unsigned char***  
bmp_scale( unsigned char*** pixel_array, unsigned char* header_data, unsigned int header_size,
           unsigned int* width, unsigned int* height, unsigned int num_colors,
           float scale )
{
  unsigned char*** new_pixel_array = NULL; 

  //scaling height and width
  *height = (unsigned int) (*height)*scale;
  *width = (unsigned int) (*width)*scale;

  //allocating memory for the new pixel array
  new_pixel_array = (unsigned char***)malloc( sizeof(unsigned char**) * (*height));
  if ( new_pixel_array==NULL) {
    return NULL;
  }

  for (int row=0; row<*height; row++) {
    new_pixel_array[row] = (unsigned char**)malloc(sizeof(unsigned char*)*(*width));
    for (int col=0; col<*width; col++) {
      new_pixel_array[row][col] = (unsigned char*)malloc(sizeof(unsigned char)*num_colors);
    }
  }

  //populating the new array
  for( int row=0; row<*height; row++ ) {
    for( int col=0; col<*width; col++ ) {
      for( int color=0; color<num_colors; color++ ) {
        new_pixel_array[row][col][color] = pixel_array[(int)(row/scale)][(int)(col/scale)][color];
      }
    }
  }

  //modifying header
  unsigned int *new_height = (unsigned int*) (header_data+22);
  unsigned int *new_width = (unsigned int*) (header_data+18);
  *new_height = *width;
  *new_height = *height;

  unsigned int *new_pixel_size = (unsigned int*) (header_data+2);
  int new_padding = ( 4 - ((*width) * num_colors) % 4) % 4;
  int new_row_size = (*width)*num_colors+new_padding;
  *new_pixel_size = (unsigned int) (header_size + (*height)*new_row_size);
  
  return new_pixel_array;
}


int 
bmp_collage( char* background_image_filename,     char* foreground_image_filename, 
             char* output_collage_image_filename, int row_offset,                  
             int col_offset,                      float scale )
{
  //converting the bmp files to arrays 
  unsigned char **background_array = NULL;
  unsigned char **foreground_array = NULL;

  unsigned char* header_data;
  unsigned int header_size, width, height, num_colors;

  unsigned char* header_data1;
  unsigned int header_size1, width1, height1, num_colors1;

  background_array = bmp_to_3D_array(background_image_filename, &header_data, &header_size, &width, &height, &num_colors);
  if (background_array = NULL) {
    return -1;
  }
  foreground_array = bmp_to_3D_array(foreground_image_filename, &header_data1, &header_size1, &width1, &height1, &num_colors1);
  if (foreground_array = NULL) {
    return -1;
  }

  //scaling the foreground image
  unsigned char*** scaled_foreground_array = bmp_scale(foreground_array, header_data1, header_size1, &width1, &height1, num_colors1, scale);
  if (scaled_background_array = NULL) {
    return -1;
  }

  //creating the output array
  unsigned char **output_array = NULL;

  for ( int row=0; row<height; row++ ) {
    for( int col=0; col<width; col++ ) {
      for( int color=0; color<num_colors; color++ ) {
        if (row>=row_offset && row<=height1) {
          output_array[row][col][color] = foreground_array[row][col][color];
        output_array[row][col][color] = background_array[row][col][color];
      }
    }
  }

  bmp_from_3D_array( output_collage_image_filename, header_data, header_size, output_array, width, height, num_colors);

  return 0;
}              

