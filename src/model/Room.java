package model;

public class Room implements IRoom {
    private String roomNumber = "";
    private Double price = 0.0;
    private RoomType enumeration = RoomType.SINGLE;
    private boolean free = false;

    public Room(String roomNumber, Double price, RoomType enumeration, boolean free) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.free = free;
    }

    public Room(){}

    public String getRoomNumber(){

        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getRoomPrice(){

        return price;
    }

    public void setRoomPrice(double price) {
        this.price = price;
    }

    public RoomType getRoomType(){

        return enumeration;
    }

    public void setRoomType(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    public boolean isFree(){

        return free;
    }

    public void setIsFree(boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        StringBuilder roomSummary = new StringBuilder("\nRoom Info");

        roomSummary.append("\n----------------------------\n");
        roomSummary.append("Room Number: ").append(getRoomNumber()).append("\n");
        roomSummary.append("Room Type: ").append(getRoomType() == RoomType.SINGLE ? "Single Bed Room" : "Double Bed Room").append("\n");
        roomSummary.append("Price: $").append(!isFree() ? getRoomPrice() : "0").append("\n");

        return roomSummary.toString();
    }
}
