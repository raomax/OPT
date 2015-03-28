package oldpersonthing.opt;

/**
 * Created by Robin Onsay on 3/27/2015.
 */
public class RegUser {
    private String fullName;
    private int patientNumber;
    private int nurseNumber;
    public RegUser(){

    }

    public RegUser(String fullName,int nurseNumber){
        this.fullName = fullName;
        this.nurseNumber = nurseNumber;
    }
}
