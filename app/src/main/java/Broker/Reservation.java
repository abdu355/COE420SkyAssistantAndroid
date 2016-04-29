package broker;

import com.example.b00047562.skyassistant.Flight;

import java.util.Date;

/**
 * Created by Kareem on 4/25/2016.
 */
public class Reservation {
    String ReservationID;
    Date ReservationDate;
    Flight flight;
    String flightID;

    public Reservation(String ID, String flightID, Date date)
    {
            ReservationID=ID;
            this.flightID=flightID;
            ReservationDate =date;
    }
    public String getReservationID() {
        return ReservationID;
    }

    public void setReservationID(String reservationID) {
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

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }
}
