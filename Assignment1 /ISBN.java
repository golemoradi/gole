public class ISBN {

 public static void main(String[] args) {
 
  /*
  Gole Moradi 260726494
  */
 
  //Declaring the variable to represent the ISBN number
        int n = Integer.parseInt(args[0]);
        
        
     //Your code Starts here
        //declaring the variables to represent each digit in the ISBN number 
        int digit2 = n%10;
        int digit3 = (n/10)%10;
        int digit4 = (n/100)%10;
        int digit5 = (n/1000)%10;
        // computing the product sum of the first 4 ISBN numbers
        int prodSum= 5*digit5 + 4*digit4 + 3*digit3 + 2*digit2;
        //determining which digit from 0-10 will complete the ISBN number 
        //chain of conditional statements is used
        if (prodSum%11 == 0) {
          System.out.println("0");
        }
        if ((prodSum+10)%11 == 0) {
          System.out.println("X");
        }
        if ((prodSum+9)%11 == 0) {
          System.out.println("9");
        }
        if ((prodSum+8)%11 == 0) {
          System.out.println("8");
        }
        if ((prodSum+7)%11 == 0) {
          System.out.println("7");
        }
        if ((prodSum+6)%11 == 0) {
          System.out.println("6");
        }
        if ((prodSum+5)%11 == 0) {
          System.out.println("5");
        }
        if ((prodSum+4)%11 == 0) {
          System.out.println("4");
        }
        if ((prodSum+3)%11 == 0) {
          System.out.println("3");
        }
        if ((prodSum+2)%11 == 0) {
          System.out.println("2");
        }
        if ((prodSum+1)%11 == 0) {
          System.out.println("1");
        } 
     //Your code Ends here
 }

}
