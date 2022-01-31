package model;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration, true);
    }

    @Override
    public String toString() {
        StringBuilder roomSummary = new StringBuilder("\nFree Room Info");

        roomSummary.append("\n----------------------------\n");
        roomSummary.append("Room Number: ").append(getRoomNumber()).append("\n");
        roomSummary.append("Room Type: ").append(getRoomType() == RoomType.SINGLE ? "Single Bed Room" : "Double Bed Room").append("\n");
        roomSummary.append("Price: $0.0").append("\n");

        return roomSummary.toString();
    }
}
