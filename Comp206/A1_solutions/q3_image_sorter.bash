#!/bin/bash

#replacing slash from path given as first argument 
echo $1 > pics_location.txt
cat pics_location.txt | tr '/' '_' > pics_name.txt


# ls -tr $(find $1 -type f -name '*.jpg') to recursively find the pictures in the directory given as first argument
# lists them sorted in reverse order (oldest at the top)
# tr '\n' ' ' replacing new line with space 
# pics.txt is the list of images sorted by date and time
ls -tr $(find $1 -type f -name '*.jpg') | tr '\n' ' ' > pics_list.txt

#creating the timeline image
convert `cat pics.txt` -append `cat pics_name.txt`.jpg