package oldpersonthing.opt;

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


public class PatientSchedueler extends ActionBarActivity implements View.OnClickListener {
    EditText event;
    EditText time;
    Button addEvent;
    ListView listViewEvents;
    final RegUser PATIENT = NewNurseActivity.oldPersonsName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_schedueler);
        addEvent = (Button) findViewById(R.id.buttonAddEvent);
        addEvent.setOnClickListener(this);
        event = (EditText) findViewById(R.id.editTextEvent);
        time = (EditText) findViewById(R.id.editTextTime);
        listViewEvents = (ListView) findViewById(R.id.listViewEvents);
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
                   PATIENT.appointments.add(new Appointment(event.getText().toString(),time.getText().toString()));
                    Log.w("PATIENT EVENTS", PATIENT.appointments.toString());
                    ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                            android.R.layout.simple_list_item_1, PATIENT.appointments.toArray());
                    listViewEvents.setAdapter(adapter);


                }
        }
    }
}
