package dbhandler;

import android.content.Context;
import android.util.Log;

import com.example.b00047562.skyassistant.Calendar;
import com.example.b00047562.skyassistant.Flight;
import broker.Reservation;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 4/13/2016.
 */
public class ParseFunctions {

    public String result; // add more results as necessary
    private Type type;
    private Context context;
    private Flight retflight;
    private Reservation retreservation;
    private Calendar usercal;

    // String airline, String departureLoc, String destinationLoc, Date departureDate, Date arrivalDate (Flight)
    // String ID, String flightID, Date date (Reservation)
    // Date eventDate, String reservationID

    public void pushFlightData(ParseUser user,Date dep,Date arr, String ... params) //upload to Parse single row
    {
        try {

            ParseObject ob = new ParseObject(params[0]);
            ob.put(params[1],params[2]);
            ob.put("username", user.getUsername());
            ob.put("createdBy", user);
            ob.put("price",params[3]);
            ob.put("airline",params[4]);
            ob.put("departureLoc",params[5]);
            ob.put("destinationLoc",params[6]);
            ob.put("departureDate",dep);
            ob.put("arrivalDate",arr);
            ob.saveEventually();
        } catch (Exception e) {
            Log.d("ParseError", e.getMessage());
        }

    }
    public void pushReserveData(ParseUser user, Flight flight, String ... params) //upload to Parse single row
    {
        try {
            ParseObject flightob = ParseObject.createWithoutData("Flight", flight.getFlightID());
            ParseObject ob = new ParseObject(params[0]);
            ob.put(params[1],params[2]);
            ob.put("username", user.getUsername());
            ob.put("createdBy", user);
            ob.put("flightID",flightob);
            ob.put("resDate",params[3]);
            ob.saveEventually();
        } catch (Exception e) {
            Log.d("ParseError", e.getMessage());
        }

    }
    public void pushCalendarData(ParseUser user,Date date,String ... params) //upload to Parse single row
    {
        try {
            //ParseObject resob = ParseObject.createWithoutData("Reservation", reserve.getReservationID());
            ParseObject ob = new ParseObject(params[0]);
            ob.put("username",  user.getUsername());
            ob.put("createdBy", user);
            ob.put("eventDate",params[1]);
            ob.put("eventDatev2",date);
            ob.saveEventually();
        } catch (Exception e) {
            Log.d("ParseError", e.getMessage());
        }

    }

    public Flight getParseFlight(ParseUser user, String... params)
    {
        String ID;
        int price;
        String airline;
        String departureLoc;
        String destinationLoc;
        Date departureDate;
        Date arrivalDate;

        ParseQuery<ParseObject> query = ParseQuery.getQuery(params[0]);
        query.whereEqualTo("createdBy", user);
        query.orderByDescending("createdAt");
        try {
            List<ParseObject> results = query.find();
            ID = results.get(0).getObjectId();
            price = results.get(0).getInt("price");
            airline = results.get(0).getString("airline");
            departureLoc = results.get(0).getString("departureLoc");
            destinationLoc = results.get(0).getString("destinationLoc");
            departureDate = results.get(0).getDate("departureDate");
            arrivalDate = results.get(0).getDate("arrivalDate");

            retflight = new Flight(ID,price,airline,departureLoc,destinationLoc,departureDate,arrivalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retflight;
    }

    public Reservation getParseReservation(ParseUser user, String... params)
    {
        String ID;
        String flightID;
        Date resDate;

        ParseQuery<ParseObject> query = ParseQuery.getQuery(params[0]);
        query.whereEqualTo("createdBy", user);
        query.orderByDescending("createdAt");
        try {
            List<ParseObject> results = query.find();
            ID = results.get(0).getObjectId();
            flightID = results.get(0).getString("flightID");
            resDate = results.get(0).getDate("resDate");
            retreservation = new Reservation(ID,flightID,resDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retreservation;
    }

    public Calendar getParseCalendar(ParseUser user, String... params)
    {
        String resID;
        Date eventDate;

        ParseQuery<ParseObject> query = ParseQuery.getQuery(params[0]);
        query.whereEqualTo("createdBy", user);
        query.orderByDescending("createdAt");
        try {
            List<ParseObject> results = query.find();
            resID = results.get(0).getString("resID");
            eventDate = results.get(0).getDate("eventDate");
            usercal = new Calendar(eventDate,resID);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return usercal;
    }

}
