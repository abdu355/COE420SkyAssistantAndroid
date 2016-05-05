package objects;

import java.util.Date;

/**
 * Created by Administrator on 4/29/2016.
 */
public class Calendar {
    String eventDate;
    String reservationID;



    public Calendar (String eventDate, String reservationID)
    {

        this.eventDate = eventDate;
        this.reservationID=reservationID;

    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }
}
