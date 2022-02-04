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
        reservationService.addRoom(rooms);
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
