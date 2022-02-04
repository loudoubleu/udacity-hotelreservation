package menu;

import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.*;

public class AdminMenu {
    private HotelResource hotelResource = HotelResource.getInstance();
    private AdminResource adminResource = AdminResource.getInstance();
    Scanner scanner = new Scanner(System.in);
    Calendar calendar = Calendar.getInstance();

    public AdminMenu() {}

    // 0. Display admin menu
    public void displayAdminMenu() {
        StringBuilder menuSB = new StringBuilder("\nAdmin Menu");
        menuSB.append("\n---------------------------------------------\n");
        menuSB.append("1. See all Customers\n");
        menuSB.append("2. See all Rooms\n");
        menuSB.append("3. See all Reservations\n");
        menuSB.append("4. Add a Room\n");
        menuSB.append("5. Back to Main Menu\n");
        menuSB.append("6. Populate with test data");
        menuSB.append("\n---------------------------------------------\n");
        menuSB.append("Please select a number for the menu option");

        System.out.println(menuSB);
    }

    // 0. Display and run admin menu
    public void initialize() {
        int menuSelection = 0;

        while (true) {
            displayAdminMenu();
            menuSelection = scanner.nextInt();
            if(!featureSelect(menuSelection)) {
                break;
            }
        }
    }

    public boolean featureSelect(int menuItem) {
        switch (menuItem){
            case 1:
                displayAllCustomers();
                break;
            case 2:
                displayAllRooms();
                break;
            case 3:
                displayAllReservations();
                break;
            case 4:
                addARoom();
                break;
            case 5:
                return returnToMainMenu();
            case 6:
                addTestData();
                break;
            default:
                System.out.println("Invalid menu selection.");
                break;
        }

        return true;
    }

    // 1. See all Customers
    public void displayAllCustomers() {
        HashMap<String, Customer> customers = adminResource.getAllCustomers();

        for(String email : customers.keySet()) {
            System.out.println(customers.get(email));
        }
    }

    // 2. See all Rooms.
    public void displayAllRooms() {
        HashMap<String, IRoom> rooms = adminResource.getAllRooms();

        if (rooms.size() < 1) {
            System.out.println("There are no rooms to display.");
        }

        for(String roomId : rooms.keySet()) {
            System.out.println(rooms.get(roomId));
        }
    }

    // 3. See all Reservations
    public void displayAllReservations() {
        adminResource.displayAllReservations();
    }

    // 4. Add a Room
    public void addARoom()
    {
        HashMap<String, IRoom> rooms = adminResource.getAllRooms();
        adminResource.addRoom(rooms);
    }

    // 5. Back to Main Menu
    public boolean returnToMainMenu() {
        return false;
    }

    // 6. Populate system with test data.
    public void addTestData() {
        // Add test Customers
        hotelResource.createACustomer("jb@theservice.com", "James", "Bond");
        hotelResource.createACustomer("simon@thesaint.com", "Simon", "Templar");
        hotelResource.createACustomer("ehunt@mif.com", "Ethan", "Hunt");
        hotelResource.createACustomer("xxx@xagents.com", "Xander", "Cage");
        hotelResource.createACustomer("dom@fastfurious.com", "Domenic", "Torreto");

        // Add test Rooms
        HashMap<String, IRoom> rooms = adminResource.getAllRooms();
        rooms.put("100", new Room("100", 135.00, RoomType.SINGLE, false));
        rooms.put("200", new Room("200", 145.00, RoomType.DOUBLE, false));
        rooms.put("301", new FreeRoom("301", 0.00, RoomType.SINGLE));
        rooms.put("302", new FreeRoom("302", 0.00, RoomType.DOUBLE));

        // Add test Reservations
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("01"));
        Date checkIn = calendar.getTime();
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("07"));
        Date checkOut = calendar.getTime();
        hotelResource.bookARoom("jb@theservice.com", hotelResource.getRoom("100"), checkIn, checkOut);
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("08"));
        checkIn = calendar.getTime();
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("13"));
        checkOut = calendar.getTime();
        hotelResource.bookARoom("simon@thesaint.com", hotelResource.getRoom("200"), checkIn, checkOut);
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("14"));
        checkIn = calendar.getTime();
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("19"));
        checkOut = calendar.getTime();
        hotelResource.bookARoom("ehunt@mif.com", hotelResource.getRoom("301"), checkIn, checkOut);
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("20"));
        checkIn = calendar.getTime();
        calendar.set(Integer.parseInt("2022"), Integer.parseInt("02") - 1, Integer.parseInt("25"));
        checkOut = calendar.getTime();
        hotelResource.bookARoom("xxx@xagents.com", hotelResource.getRoom("302"), checkIn, checkOut);
    }
}
