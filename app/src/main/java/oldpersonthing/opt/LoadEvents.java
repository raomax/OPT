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
public class LoadEvents {
    Firebase name;
    private ArrayList<String> rawEvents;

    public LoadEvents(Firebase name){
        this.name = name;
        rawEvents = new ArrayList<>();
    }

    public ArrayList<String> getPatients(){
        name.addValueEventListener(new ValueEventListener() {
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
                    rawEvents.add(scan.next());
                }
                for(int i=0;i< rawEvents.size();i++){
                    String s = rawEvents.get(i);
                    s = s.substring((s.indexOf("=")+1));
                    rawEvents.set(i,s);
                }
                Log.w("TESTING GET", rawEvents.toString());

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.w("TESSTING GET", firebaseError.toString());
            }
        });
        return rawEvents;
    }
}
