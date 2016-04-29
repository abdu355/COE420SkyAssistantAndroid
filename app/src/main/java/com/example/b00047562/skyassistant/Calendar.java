package com.example.b00047562.skyassistant;

import java.util.Date;

/**
 * Created by Administrator on 4/29/2016.
 */
public class Calendar {
    Date eventDate;
    String reservationID;



    public Calendar (Date eventDate, String reservationID)
    {

        this.eventDate = eventDate;
        this.reservationID=reservationID;

    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }
}
