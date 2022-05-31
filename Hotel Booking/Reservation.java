public class Reservation {
  private String name;
  private Room roomReserved;
  
  //constructor
  public Reservation(Room reserved, String clientName) {
    this.name = clientName;
    this.roomReserved = reserved;
  }
  
  //get methods for name and room
  public String getName() {
    return name;
  }
  
  public Room getRoom() {
    return roomReserved;
  }
}
