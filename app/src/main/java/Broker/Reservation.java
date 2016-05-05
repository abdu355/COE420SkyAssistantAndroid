package broker;

import objects.Flight;

import java.util.Date;

/**
 * Created by Kareem on 4/25/2016.
 */
public class Reservation {
    String ReservationID;
    String ReservationDate;
    Flight flight;
    String flightID;
    String ticketno;
    String gateno;
    String flightclass;
    String seatno;

    public Reservation(String ID, String flightID, String date)
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

    public String getReservationDate() {
        return ReservationDate;
    }

    public void setReservationDate(String reservationDate) {
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

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public String getGateno() {
        return gateno;
    }

    public void setGateno(String gateno) {
        this.gateno = gateno;
    }

    public String getFlightclass() {
        return flightclass;
    }

    public void setFlightclass(String flightclass) {
        this.flightclass = flightclass;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }
}
