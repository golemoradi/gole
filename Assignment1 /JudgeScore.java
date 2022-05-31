public class JudgeScore {
 public static void main(String[] args) {
 
  /*
  Gole Moradi 260726494
  */ 
 
  //Declaring the variables for storing the judges scores.
  int judge1, judge2, judge3, judge4;
  judge1 = Integer.valueOf(args[0]);
  judge2 = Integer.valueOf(args[1]);
  judge3 = Integer.valueOf(args[2]);
  judge4 = Integer.valueOf(args[3]);
  
  //Your code Starts here
  //determining the max value using conditional statements
  if (judge1>=judge2 && judge1>=judge3 && judge1>=judge4) {
    //after max value is determined, min value is found using conditional statements
     if (judge2>=judge4 && judge3>=judge4) {
       //after finding the min value, the average is calculated and printed 
      double avg = (double)(judge2+judge3)/2.0;
      System.out.println(avg);
      }
    else if (judge3>=judge2 && judge4>=judge2) {
      double avg = (double)(judge4+judge3)/2.0;
      System.out.println(avg);
      }
    else if (!((judge2>=judge4 && judge3>=judge4) || (judge3>=judge2 && judge4>=judge2))) { 
      double avg = (double)(judge2+judge4)/2.0;
      System.out.println(avg);
      }
    }
  
  else if (judge2>=judge3 && judge2>=judge1 && judge2>=judge4) {
    if (judge1>=judge4 && judge3>=judge4) {
      double avg = (double)(judge1+judge3)/2.0;
      System.out.println(avg);
      }
     else if (judge3>=judge1 && judge4>=judge1) {
      double avg = (double)(judge4+judge3)/2.0;
      System.out.println(avg);
      }
    else if (!((judge3>=judge1 && judge4>=judge1) || (judge1>=judge4 && judge3>=judge4))) {
      double avg = (double)(judge1+judge4)/2.0;
      System.out.println(avg);
      }
    }
  
  else if (judge3>=judge2 && judge3>=judge1 && judge3>=judge4) {
    if (judge2>=judge4 && judge1>=judge4) {
      double avg = (double)(judge2+judge1)/2.0;
      System.out.println(avg);
      }
    else if (judge1>=judge2 && judge4>=judge2) {
      double avg = (double)(judge4+judge1)/2.0;
      System.out.println(avg);
      }
    else if (!((judge1>=judge2 && judge4>=judge2) || (judge2>=judge4 && judge1>=judge4))) {
      double avg = (double)(judge2+judge4)/2.0;
      System.out.println(avg);
      }
    }
  
  else if (judge4>=judge2 && judge4>=judge3 && judge4>=judge1) {
    if (judge2>=judge1 && judge3>=judge1) {
      double avg = (double)(judge2+judge3)/2.0;
      System.out.println(avg);
      }
    else if (judge3>=judge2 && judge1>=judge2) {
      double avg = (double)(judge1+judge3)/2.0;
      System.out.println(avg);
      }
    else if (!((judge3>=judge2 && judge1>=judge2) || (judge2>=judge1 && judge3>=judge1))) {
      double avg = (double)(judge1+judge2)/2.0;
      System.out.println(avg);
      }
    }
  //Your code Ends here
  
 }
}
