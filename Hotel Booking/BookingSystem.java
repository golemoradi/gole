import java.util.Scanner; 
import java.util.Random; 
 
public class BookingSystem { 
     
    private static String[] typeOfRooms = {"double","queen","king"}; 
    private static Random r = new Random(123); 
     
    //returns a random String from the above array.  
    private static String getRandomType(){ 
        int index = r.nextInt(typeOfRooms.length); 
        return typeOfRooms[index]; 
    } 
    //returns a random number of rooms between 5 and 50. 
    private static int getRandomNumberOfRooms(){ 
        return r.nextInt(50)+1; 
    } 
    //End of provided code.  
     
    public static void main(String[] args){ 
      //Student Name: Gole Moradi
      //Student Number: 260726494
      
      Scanner read = new Scanner(System.in);
      //choosing the hotel name
      System.out.println("Welcome to the COMP 202 booking system");
      System.out.println("Please enter the name of the hotel you'd like to book");
      String hotelName = read.nextLine();
      
      //creating array of rooms
      Room[] hotelRooms = new Room[getRandomNumberOfRooms()];
      //populating new array with room types
      for (int i=0; i<hotelRooms.length; i++) {
        hotelRooms[i] = new Room(getRandomType());
      }
      //creating the new hotel
      Hotel newHotel = new Hotel(hotelName, hotelRooms);
      
      //hotel menu
      showMenu(hotelName);
      int menuOption = read.nextInt();
      if (menuOption>5 || menuOption<1) {
        showMenu(hotelName);
        menuOption = read.nextInt();
      }
      String clientName, buffer, roomType;
      //carrying out the menu operation
      while (menuOption !=5) {
        if (menuOption == 1) {
          buffer = read.nextLine();
          System.out.print("Please enter your name:");
          clientName = read.nextLine();
          System.out.print("What type of room would you like to reserve?");
          roomType = read.nextLine();
          newHotel.createReservation(clientName, roomType);
        }
        else if (menuOption == 2) {
         buffer = read.nextLine();
         System.out.print("Please enter the name you used to make the reservation:");
         clientName = read.nextLine();
         System.out.print("What type of room did you reserve?");
         roomType = read.nextLine();
         newHotel.cancelReservation(clientName, roomType);
        }
        else if (menuOption == 3) {
          buffer = read.nextLine();
          System.out.print("Please enter your name:");
          clientName = read.nextLine();
          newHotel.printInvoice(clientName);
        }
        else if (menuOption == 4) {
          System.out.println(newHotel);
        }
        showMenu(hotelName);
        menuOption = read.nextInt();
        if (menuOption>5 || menuOption<1) {
          showMenu(hotelName);
          menuOption = read.nextInt();
        }
      }
      System.out.println("It was a pleasure doing business with you!");
    }
    
    //helper method that displays the menu
    private static void showMenu(String hotelName) {
      int numberOfStars = hotelName.length() + 55;
      for (int i=0; i<numberOfStars; i++) {
         System.out.print("*");
      }
      System.out.println("");
      System.out.println("Welcome to the " + hotelName + ". Choose one of the following options:");
      System.out.println("1) Make a reservation \n2) Cancel a reservation \n3) See an invoice \n4) See hotel info \n5) Exit the Booking System");
      for (int i=0; i<numberOfStars; i++) {
         System.out.print("*");
      }
      System.out.println("");
    }
      
      
      
      
      
      
      
      
      
      
      
}