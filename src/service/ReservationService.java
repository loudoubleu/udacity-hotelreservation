package service;

import model.*;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService();
    ArrayList<Reservation> reservations = new ArrayList<>();
    Map<String, IRoom> rooms = new HashMap<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return reservationService;
    }

    public void addRoom(HashMap<String, IRoom> rooms) {
        Room newRoom = new Room();
        Scanner scanner = new Scanner(System.in);
        String roomNumber = "";
        double roomPrice = 0.0;
        RoomType roomType;
        int roomTypeInput = 0;
        boolean anotherRoom = true;

        while(anotherRoom) {
            System.out.println("Enter room number");
            roomNumber = scanner.next();

            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            roomTypeInput = scanner.nextInt();
            roomType = getRoomType(roomTypeInput);
            if (roomType == null) {
                System.out.println("Invalid room type");
                break;
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

    RoomType getRoomType(int roomTypeAsInt) {
        RoomType roomType = null;

        if(roomTypeAsInt == 1) {
            roomType = RoomType.SINGLE;
        }
        else if (roomTypeAsInt == 2) {
            roomType = RoomType.DOUBLE;
        }

        return roomType;
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);

        try {
            reservations.add(newReservation);
        }
        catch (NullPointerException ex) {
            System.out.println("Cannot book reservation. No customer exists with that email address.");
            return null;
        }

        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> unavailableRooms = new ArrayList<>();
        List<IRoom> availableRooms = new ArrayList<>();
        Reservation tempReservation;
        Date tempCheckIn;
        Date tempCheckOut;

        if(reservations.size() == 0) {
            // If there are no reservations then all rooms are available.
            availableRooms = new ArrayList<IRoom>(rooms.values());
        }
        else {
            for (Reservation reservation : reservations) {
                tempReservation = reservation;
                tempCheckIn = tempReservation.getCheckInDate();
                tempCheckOut = tempReservation.getCheckOutDate();

                if (checkInDate.equals(tempCheckIn)) {
                    unavailableRooms.add(tempReservation.getRoom());
                }
                else if (checkInDate.after(tempCheckIn) && !checkInDate.after(tempCheckOut)) {
                    unavailableRooms.add(tempReservation.getRoom());
                }
                else if (checkInDate.before(tempCheckIn) && !checkOutDate.before(tempCheckIn)) {
                    unavailableRooms.add(tempReservation.getRoom());
                }
            }

            ArrayList<IRoom> listOfAllRooms = new ArrayList<>(rooms.values());
            final ArrayList<IRoom> tempRooms = new ArrayList<>();
            listOfAllRooms.forEach(room -> {
                if (!unavailableRooms.contains(room)) {
                    tempRooms.add(room);
                }
            });
            availableRooms = tempRooms;
        }

        return (Collection<IRoom>) availableRooms;
    }

    public HashMap<String, IRoom> getAllRooms() {
        return (HashMap<String, IRoom>) rooms;
    }

    public ArrayList<Reservation> getCustomerReservation(Customer customer) {
        try {
            ArrayList<Reservation> customerReservations = new ArrayList<>();

            reservations.forEach(reservation -> {
                if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                    customerReservations.add(reservation);
                }
            });

            return customerReservations;
        }
        catch (NullPointerException ex) {
            System.out.println("There are no reservations for this Customer");
        }

        return null;
    }

    public void printAllReservation() {
        if (reservations.size() == 0) {
            System.out.println("There are no reservations to display.");
        }
        else {
            for(Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}
