package objects;

import java.util.Date;

/**
 * Created by Kareem on 4/25/2016.
 */

public class Flight {
    int  Price;
    String FlightID;
    String Airline;
    String DepartureLoc, DestinationLoc;
    String DepartureDate, ArrivalDate;

    public Flight(String flightID, int price, String airline, String departureLoc, String destinationLoc, String departureDate, String arrivalDate) {
        FlightID = flightID;
        Price = price;
        Airline = airline;
        DepartureLoc = departureLoc;
        DestinationLoc = destinationLoc;
        DepartureDate = departureDate;
        ArrivalDate = arrivalDate;
    }

    public String getFlightID() {
        return FlightID;
    }

    public void setFlightID(String flightID) {
        FlightID = flightID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    public String getDepartureLoc() {
        return DepartureLoc;
    }

    public void setDepartureLoc(String departureLoc) {
        DepartureLoc = departureLoc;
    }

    public String getDestinationLoc() {
        return DestinationLoc;
    }

    public void setDestinationLoc(String destinationLoc) {
        DestinationLoc = destinationLoc;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }
}
