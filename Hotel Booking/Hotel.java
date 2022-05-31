import java.util.NoSuchElementException;
public class Hotel {
  private String name;
  private Room[] rooms;
  private Reservation[] reservations;
  
  //constructor
  public Hotel(String hotelName, Room[] roomsArray) {
    this.rooms = new Room[roomsArray.length];
    for (int i=0; i<rooms.length; i++) {
      rooms[i] = roomsArray[i];
    }
    this.name = hotelName;
  }
  
  //method that adds a reservation 
  private void addReservation(Reservation r1) {
    //checking if there are even any reservations made yet and initializing the array
    if (reservations == null) {
      this.reservations = new Reservation[]{r1};
    }
    else {
      //changing the size of the array
      Reservation[] newArrayReservations = new Reservation[this.reservations.length+1];
      //populating the new array
      for (int i=0; i<this.reservations.length; i++) {
        newArrayReservations[i] = this.reservations[i];
      }
      newArrayReservations[this.reservations.length] = r1;
      this.reservations = newArrayReservations;
    }
  }
  
  //method that removes a reservation
  private void removeReservation(String clientName, String roomType) {
    boolean isThereReservation = false;
    int index = 0;
      //checking if there is a room reserved 
      for (int i=0; i<this.reservations.length; i++) {
        Room r1 = reservations[i].getRoom();
        if ((reservations[i].getName().equals(clientName)) && (r1.getType().equals(roomType))) {
            index = i; 
            isThereReservation = true;
        }
      }
      if (isThereReservation == false) {
        throw new NoSuchElementException("No reservation has been made under " + clientName + " for the given type of room");
      }
      else {
        //changing the room availability
        Room openRoom = reservations[index].getRoom();
        openRoom.changeAvailability();
        //removing the reservation from the array
        Reservation[] reservationsLeft = new Reservation[reservations.length-1];
        for (int i=0; i<reservationsLeft.length; i++) {
          if (i<index) {
            reservationsLeft[i] = this.reservations[i];
          }
          else {
            reservationsLeft[i] = this.reservations[i+1];
          }
        }
        this.reservations = reservationsLeft;
      }
  }
  
  //method that creates a reservation
  public void createReservation(String name, String roomType) {
    Room roomAvailable = Room.findAvailableRoom(this.rooms, roomType);
    if (roomAvailable == null) {
      System.out.println("There are no available rooms of the requested type.");
    }
    else {
      Reservation newReservation = new Reservation(roomAvailable, name);
      roomAvailable.changeAvailability();
      addReservation(newReservation);
      System.out.println("You have successfully reserved a " + roomType + " room under the name of " + name + ". We look forward to having you at " + this.name + "!");
    }
  }
  
  //method to cancel a reservation 
  public void cancelReservation(String clientName, String roomType) {
    try {
      removeReservation(clientName, roomType);
      System.out.println(clientName + ", your reservation for a " + roomType + " has been successfully cancelled.");
    }
    catch (Exception e) {
      System.out.println("No reservation has been made under the name " + clientName + " for a " + roomType + " room.");
    }
  }
      
  
  //method that prints the customer's invoice
  public void printInvoice(String clientName) {
    double invoice = 0;
    if (reservations != null) {
      for (int i=0; i<this.reservations.length; i++) {
        if (reservations[i].getName().equals(clientName)) {
          Room reserved = reservations[i].getRoom();
          invoice = invoice + reserved.getPrice();
        }
      }
    }
    System.out.println(clientName + "'s invoice is of $" + invoice);
  }
  
  //toString method showing hotel name and available rooms of each type 
  public String toString() {
    String s1 = "Here is the hotel info \n";
    String s2 = "Hotel name: " + this.name + "\n";
    
    //finding the available rooms of each type
    int queen = 0;
    int doubleBed = 0;
    int king = 0;
    for (int i=0; i<rooms.length; i++) {
      if (rooms[i].getType().equals("queen") && rooms[i].getAvailability() ==true) {
        queen++;
      }
      if (rooms[i].getType().equals("double") && rooms[i].getAvailability() ==true) {
        doubleBed++;
      }
      if (rooms[i].getType().equals("king") && rooms[i].getAvailability() ==true) {
        king++;
      }
    }
    String s3 = "Available rooms: " + doubleBed + " double, " + queen + " queen, " + king + " king.";
    String s4 = s1 + s2 + s3;
    return s4;
  }
      

  
  
}
    
  
    
  