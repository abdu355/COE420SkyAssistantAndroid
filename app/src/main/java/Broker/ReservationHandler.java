package broker;

import com.parse.ParseUser;

import java.util.Date;

import dbhandler.ParseFunctions;
import objects.Flight;

/**
 * Created by Kareem on 4/25/2016.
 */
public class ReservationHandler {
    //TODO:
    //Add any necessary fields/variables
    Reservation Resrv;
    ParseFunctions customParse;

    public ReservationHandler(Reservation resrv) {

        Resrv = resrv;
    }

    public boolean MakeReservation(Flight myflight,String TicketNum,String Gate,String Class,String Seat){
        customParse = new ParseFunctions();
        customParse.pushReserveData(ParseUser.getCurrentUser(),myflight,"Reservation",new Date().toString(),TicketNum,Gate,Class,Seat);
        //if success return true, else false
        return true;
    }

    public boolean CancelReservation(){

        //if success return true, else false
        return true;
    }

    public Reservation getResrv() {
        return Resrv;
    }

    public void setResrv(Reservation resrv) {
        Resrv = resrv;
    }
}
