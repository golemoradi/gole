public class DayOfTheWeek {

 public static void main(String[] args) {
 
  /*
  Gole Moradi 260726494
  */
 
  //Declaring the variables for year(y), month(m), day(d)
  int y,m,d;
  //Initialisation of the variables y,m and d with the input arguments 
  y = Integer.valueOf(args[0]);
  m = Integer.valueOf(args[1]);
  d = Integer.valueOf(args[2]);
  //Declaring the auxiliary variables used in the formula.
  int y0, m0, d0, x;
  
  
  //Your code Starts here
  //formulas to compute day of the week 
  y0 = y - (14-m)/12;
  x = y0 + y0/4 - y0/100 + y0/400;
  m0 = m + 12*((14-m)/12) - 2;
  d0 = (d+x+(31*m0)/12)%7;
  // chain of conditional statements in order to display the proper day of the week
  if (d0 == 0) {
    System.out.println("SUNDAY");
  }
   if (d0 == 1) {
    System.out.println("MONDAY");
  }
   if (d0 == 2) {
    System.out.println("TUESDAY");
  }
   if (d0 == 3) {
    System.out.println("WEDNESDAY");
  }
   if (d0 == 4) {
    System.out.println("THURSDAY");
  }
   if (d0 == 5) {
    System.out.println("FRIDAY");
  }
   if (d0 == 6) {
    System.out.println("SATURDAY");
  }           
  //Your code Ends here
 }

}
