package oldpersonthing.opt;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Robin Onsay on 3/28/2015.
 */
public class LoadPatients {
    Firebase nurse;
    private ArrayList<String> rawNames;
    
    public LoadPatients(Firebase nurse){
        this.nurse = nurse.child(NewNurseActivity.PATIENT_NAME);
        rawNames = new ArrayList<>();
    }

    public ArrayList<String> getPatients(){
        nurse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ArrayList<Object>
                String rawName = dataSnapshot.getValue().toString();
                rawName =  rawName.replace("{","");
                rawName = rawName.replace("}","");
                Scanner scan = new Scanner(rawName);
                Log.w("TESTING GET", rawName);
                scan.useDelimiter(", ");
                while(scan.hasNext()){
                    rawNames.add(scan.next());
                }
                for(int i=0;i<rawNames.size();i++){
                    String s = rawNames.get(i);
                    s = s.substring((s.indexOf("=")+1));
                    rawNames.set(i,s);
                }
                Log.w("TESTING GET", rawNames.toString());

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.w("TESSTING GET", firebaseError.toString());
            }
        });
        return rawNames;
    }
}
