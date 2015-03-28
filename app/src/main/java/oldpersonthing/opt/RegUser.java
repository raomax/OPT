package oldpersonthing.opt;

import java.util.ArrayList;

/**
 * Created by Robin Onsay on 3/27/2015.
 */
public class RegUser {
    private String fullName;
    private int patientNumber;
    private String nurseNameAndNumber;
    public ArrayList<Appointment> appointments;
    public RegUser(){

    }

    public RegUser(String fullName,String nurseNameAndNumber){
        this.fullName = fullName;
        this.nurseNameAndNumber = nurseNameAndNumber;
        appointments = new ArrayList();
    }
}
