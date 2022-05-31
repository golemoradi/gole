public class Room {
  private String type;
  private double price;
  private boolean availability;
  
  //constructor with type as input 
  public Room(String type) {
    //checking if the type is valid
    if (type.equals("double") || type.equals("queen") || type.equals("king")) {
      this.type = type;
    }
    else {
      throw new IllegalArgumentException("No room of such type can be created");
    }
    this.availability = true;
    if (type.equals("double")) {
      this.price = 90;
    }
    if (type.equals("queen")) {
      this.price = 110;
    }
    if (type.equals("king")) {
      this.price = 150;
    }
  }
  
  //get methods for type, price and availability
  public String getType() {
    return this.type;
  }
  
  public double getPrice() {
    return this.price;
  }
  
  public boolean getAvailability() {
    return this.availability;
  }
  
  //method to change availability
  public void changeAvailability() {
    this.availability = !this.availability;
  }
  
  //method to find an available room
  public static Room findAvailableRoom(Room[] rooms, String roomType) {
    for (int i=0; i<rooms.length; i++) {
      if ((rooms[i].availability == true) && rooms[i].type.equals(roomType)) {
        return rooms[i];
      }
    }
    return null;
  }
}