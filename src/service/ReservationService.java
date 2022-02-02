package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService();
    Map<String, Reservation> reservations = new HashMap<>();
    Map<String, IRoom> rooms = new HashMap<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);

        try {
            reservations.put(customer.getEmail(), newReservation);
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
            for (String key : reservations.keySet()) {
                tempReservation = reservations.get(key);
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

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        try {
            return (Collection<Reservation>)reservations.get(customer.getEmail());
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
            for(Reservation reservation : reservations.values()) {
                System.out.println(reservation);
            }
        }
    }
}
