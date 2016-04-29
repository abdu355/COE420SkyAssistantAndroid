package com.example.b00047562.skyassistant;

import java.util.Date;

/**
 * Created by Kareem on 4/25/2016.
 */


public class Flight {
    int FlightID, Price;
    String Airline;
    String DepartureLoc, DestinationLoc;
    Date DepartureDate, ArrivalDate;

    public Flight(int flightID, int price, String airline, String departureLoc, String destinationLoc, Date departureDate, Date arrivalDate) {
        FlightID = flightID;
        Price = price;
        Airline = airline;
        DepartureLoc = departureLoc;
        DestinationLoc = destinationLoc;
        DepartureDate = departureDate;
        ArrivalDate = arrivalDate;
    }

    public int getFlightID() {
        return FlightID;
    }

    public void setFlightID(int flightID) {
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

    public Date getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(Date departureDate) {
        DepartureDate = departureDate;
    }

    public Date getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        ArrivalDate = arrivalDate;
    }
}
