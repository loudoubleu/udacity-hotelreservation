package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Date;

public class Reservation {
    private Customer customer = null;
    private IRoom room = null;
    private Date checkInDate = null;
    private Date checkOutDate = null;
    private Calendar calendar = Calendar.getInstance();

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        StringBuilder reservationInfo = new StringBuilder("\nReservation Info");
        String datePattern = "EEE MMM dd yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

        reservationInfo.append("\n-----------------------------\n");
        reservationInfo.append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
        reservationInfo.append("Room: ").append(room.getRoomNumber()).append(" - ")
                .append(room.getRoomType() == RoomType.SINGLE ? "Single bed" : "Double bed")
                .append("\n");
        reservationInfo.append("Price: $").append(room.getRoomPrice()).append(" per night").append("\n");
        calendar.setTime(checkInDate);
        reservationInfo.append("Checkin Date: ")
                .append(dateFormat.format(calendar.getTime())).append("\n");
        calendar.setTime(checkOutDate);
        reservationInfo.append("CheckOut Date: ")
                .append(dateFormat.format(calendar.getTime())).append("\n");

        return reservationInfo.toString();
    }
}
