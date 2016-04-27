package com.example.b00047562.skyassistant;

import java.util.Date;

/**
 * Created by Kareem on 4/25/2016.
 */
public class Reservation {
    int ReservationID;
    Date ReservationDate;
    Flight flight;

    public int getReservationID() {
        return ReservationID;
    }

    public void setReservationID(int reservationID) {
        ReservationID = reservationID;
    }

    public Date getReservationDate() {
        return ReservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        ReservationDate = reservationDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
