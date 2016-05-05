package dbhandler;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import objects.Calendar;
import objects.Flight;
import broker.Reservation;
import objects.User;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
            //ob.put(params[1],params[2]);
            ob.put("username", user.getUsername());
            ob.put("createdBy", user);
            ob.put("flightID",flightob);
            ob.put("resDate",params[1]);
            ob.put("ticketno",params[2]);
            ob.put("gateno",params[3]);
            ob.put("class",params[4]);
            ob.put("seat",params[5]);
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

    public Flight getParseFlight(ParseUser user, int listpointer, String... params)
    {
        String ID;
        int price;
        String airline;
        String departureLoc;
        String destinationLoc;
        String departureDate;
        String arrivalDate;
        ParseObject ob;

        ParseQuery<ParseObject> query =  new ParseQuery<ParseObject>("Flight");
        query.orderByDescending("createdAt");
        try {
            List<ParseObject> results = query.find();
            ID = results.get(listpointer).getObjectId();
            price = results.get(listpointer).getInt("price");
            airline = results.get(listpointer).getString("airline");
            departureLoc = results.get(listpointer).getString("departureLoc");
            destinationLoc = results.get(listpointer).getString("destinationLoc");
            departureDate = results.get(listpointer).getString("departureDate");
            arrivalDate = results.get(listpointer).getString("arrivalDate");
            Log.d("ParseExc",ID+price+airline+departureLoc);
            retflight = new Flight(ID,price,airline,departureLoc,destinationLoc,departureDate,arrivalDate);
        } catch (ParseException e) {
            Log.d("ParseExc",e.getMessage());
        }catch(IndexOutOfBoundsException e) {
            Log.d("ParseExc",e.getMessage());
        }

        return retflight;
    }

    public Reservation getParseReservation(ParseUser user, String... params)
    {
        String ID;
        String flightID;
        String resDate;
        String ticketno;
        String gateno;
        String flightclass;
        String seatno;

        ParseQuery<ParseObject> query = ParseQuery.getQuery(params[0]);
        query.whereEqualTo("createdBy", user);
        query.orderByDescending("createdAt");
        try {
            List<ParseObject> results = query.find();
            ID = results.get(0).getObjectId();
            flightID = results.get(0).getString("flightID");
            resDate = results.get(0).getString("resDate");
            retreservation = new Reservation(ID,flightID,resDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retreservation;
    }

    public Calendar getParseCalendar(ParseUser user, String... params)
    {
        String resID;
        String eventDate;

        ParseQuery<ParseObject> query = ParseQuery.getQuery(params[0]);
        query.whereEqualTo("createdBy", user);
        query.orderByDescending("createdAt");
        try {
            List<ParseObject> results = query.find();
            resID = results.get(0).getString("resID");
            eventDate = results.get(0).getString("eventDate");
            usercal = new Calendar(eventDate,resID);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return usercal;
    }

    public User getParseUserProfile(ParseUser user, String... params)
    {
        String profilename;
        String profileemail;
        String profilephone;
        String password;

        return new User(null,null,null);
    }
    public Date returnDateformat(String date) {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate= new Date();
        try {
            startDate = df.parse(date);
            //String newDateString = df.format(startDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

}
