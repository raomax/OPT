package oldpersonthing.opt;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;


public class PatientSchedueler extends ActionBarActivity implements View.OnClickListener {
    EditText event;
    EditText time;
    Button addEvent;
    ListView listViewEvents;
    ArrayList<Appointment> apps;
    final RegUser PATIENT = NewNurseActivity.oldPersonsName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_schedueler);
        addEvent = (Button) findViewById(R.id.buttonAddEvent);
        addEvent.setOnClickListener(this);
        event = (EditText) findViewById(R.id.editTextEvent);
        event.setOnClickListener(this);
        time = (EditText) findViewById(R.id.editTextTime);
        time.setOnClickListener(this);
        listViewEvents = (ListView) findViewById(R.id.listViewEvents);
        apps = new ArrayList<>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient_schedueler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAddEvent:
                if(event.getText().toString().length()>1){

                    Log.w("EVENT", event.getText().toString());
                    Log.w("TIME",time.getText().toString());

                   new WriteApps().execute(new Appointment
                           (event.getText().toString(),time.getText().toString()));

                    Log.w("PATIENT EVENTS", apps.toString());
                    ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                            android.R.layout.simple_list_item_1, apps.toArray());
                    listViewEvents.setAdapter(adapter);
                    event.setText("");
                    time.setText("");
                }
            case R.id.editTextEvent:
                event.setText("");
                break;
            case R.id.editTextTime:
                time.setText("");
                break;
        }
    }
    private class WriteApps extends AsyncTask<Appointment,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Appointment... app) {
            Firebase ref = new Firebase("https://dazzling-heat-1446.firebaseio.com");
            Firebase appointment = ref.child(PATIENT.getNurseNameAndNumber())
                    .child(PATIENT.getFullName()).child(app[0].toString());
            appointment.setValue(app[0].toString());
            return null;
        }
    }
}
