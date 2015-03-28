package oldpersonthing.opt;

import com.firebase.client.Firebase;

/**
 * Created by Robin Onsay on 3/28/2015.
 */
public class DataBaseVar {
    public static Firebase ref;
    public static Firebase  nurseUser;
    public static Firebase  patientUser;

    public static void init(){
        ref = new Firebase("https://dazzling-heat-1446.firebaseio.com/");
        nurseUser = ref.child("Admin");
        patientUser = ref.child("Patient");

    }

}
