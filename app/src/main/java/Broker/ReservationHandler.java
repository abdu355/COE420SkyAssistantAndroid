package broker;

/**
 * Created by Kareem on 4/25/2016.
 */
public class ReservationHandler {
    //TODO:
    //Add any necessary fields/variables
    Reservation Resrv;

    public ReservationHandler(Reservation resrv) {
        Resrv = resrv;
    }

    public boolean MakeReservation(){

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
