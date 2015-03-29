package oldpersonthing.opt;

/**
 * Created by Robin Onsay on 3/28/2015.
 */
public class Appointment extends RegUser {
    public String event = "";
    public String time = "";
    public Appointment(String event, String time){
        this.event = event;
        this.time = time;
    }

    @Override
    public String toString() {
        return event +"-"+time ;
    }
}
