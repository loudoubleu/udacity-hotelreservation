package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class AdminResource {
    private static AdminResource adminResource = new AdminResource();
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    private AdminResource() {}

    public static AdminResource getInstance() {
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(HashMap<String, IRoom> rooms) {
        Room newRoom = new Room();
        Scanner scanner = new Scanner(System.in);
        String roomNumber = "";
        double roomPrice = 0.0;
        RoomType roomType;
        int roomTypeInput = 0;
        //boolean isFreeRoom = false;
        boolean anotherRoom = true;

        while(anotherRoom) {
            System.out.println("Enter room number");
            roomNumber = scanner.next();

            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            roomTypeInput = scanner.nextInt();
            if(roomTypeInput == 1) {
                roomType = RoomType.SINGLE;
            }
            else if (roomTypeInput == 2) {
                roomType = RoomType.DOUBLE;
            }
            else {
                System.out.println("Invalid room type");
            }

            System.out.println("Is this a free room? y/n");
            String freeRoomInput = scanner.next();
            if(freeRoomInput.equals("y")) {
                //isFreeRoom = true;
                newRoom = new FreeRoom(roomNumber, roomPrice, roomType = roomTypeInput == 1 ? RoomType.SINGLE : RoomType.DOUBLE);
            }
            else {
                System.out.println("Enter price per night");
                roomPrice = scanner.nextDouble();
                newRoom = new Room(roomNumber, roomPrice, roomType = roomTypeInput == 1 ? RoomType.SINGLE : RoomType.DOUBLE, false);
            }
            rooms.put(roomNumber, newRoom);

            System.out.println("Would you like to add another room? y/n");
            String anotherRoomInput = scanner.next();
            anotherRoom = anotherRoomInput.equals("y") ? true : false;
        }
    }

    public HashMap<String, IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public HashMap<String, Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
