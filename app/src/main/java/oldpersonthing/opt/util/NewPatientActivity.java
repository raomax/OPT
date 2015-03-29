package oldpersonthing.opt.util;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import oldpersonthing.opt.LoadEvents;
import oldpersonthing.opt.R;

public class NewPatientActivity extends ActionBarActivity implements View.OnClickListener {
    TextView eventsFor;
    final String Name =  PateintLoginActivity.Name;
    final String Nurse =  PateintLoginActivity.Nurse;
    ListView eventList;
    LoadEvents events;
     ArrayList<String>  daEvents;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        eventsFor = (TextView) findViewById(R.id.textViewEventsFor);
        eventsFor.setText(eventsFor.getText()+" "+Name );

        Firebase.setAndroidContext(this);
        Firebase patient = new Firebase("https://dazzling-heat-1446.firebaseio.com").child(Nurse).child(Name);
        events = new LoadEvents(patient);
        eventList = (ListView) findViewById(R.id.listViewEventList);
        events.getPatients();
        daEvents = new ArrayList();
        Log.e("ARRAY LIST", daEvents.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        daEvents = events.getPatients();
//        Log.e("ARRAY LIST", daEvents.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_refresh){
            Log.e("EVENT ARRAY", daEvents.toString());
            daEvents = events.getRawEvents();
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, daEvents);
            eventList.setAdapter(adapter);
            return true;
        }
        if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    private class GetEvents extends AsyncTask<LoadEvents, Void, Void> {


        @Override
        protected Void doInBackground(LoadEvents... params) {
            params[0].getPatients();
            return null;
        }


    }
}
