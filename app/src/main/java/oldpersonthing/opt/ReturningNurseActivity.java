package oldpersonthing.opt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;


public class ReturningNurseActivity extends ActionBarActivity implements View.OnClickListener{
    Button addOldPerson;
    ArrayList<String> oldPeoples;
    EditText oldPersonName;
    public final static String NURSE_NAME = NurseLoginActivity.nurseName;
    ListView listView;
    Firebase nnurse;
    static String oldPersonsName;
    LoadPatients patients;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_nurse);
        nnurse = new Firebase("https://dazzling-heat-1446.firebaseio.com").child(NURSE_NAME);
        patients = new LoadPatients(nnurse);

        addOldPerson = (Button) findViewById(R.id.buttonAddOldPerson);
        addOldPerson.setOnClickListener(this);
        oldPeoples = patients.getPatients();
        oldPersonName = (EditText) findViewById(R.id.editTextOldPersonName);
        listView = (ListView) findViewById(R.id.listView);
        listView.setItemsCanFocus(false);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, oldPeoples);
        listView.setAdapter(adapter);
        final Intent intent = new Intent(this,PatientSchedueler.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oldPersonsName = (String) listView.getItemAtPosition(position);
                Log.w("OLD PERSONS NAME", oldPersonsName);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_nurse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_refresh_New_nurse){
            adapter.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAddOldPerson:
                if(oldPersonName.getText().toString().length()>1){
                    String s = oldPersonName.getText().toString();
                    new WriteNurseUser().execute(s);
                    //oldPeoples.add(s);
                    adapter.add(s);
                    Log.w("ARRAY LIST", oldPeoples.toString());
                    adapter.notifyDataSetChanged();
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                            android.R.layout.simple_list_item_1, oldPeoples);
//                    listView.setAdapter(adapter);




                }
        }
    }
    private class WriteNurseUser extends AsyncTask<String,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... usr) {
            Firebase patient = nnurse.child(usr[0]);
            Firebase patientName = nnurse.child(NewNurseActivity.PATIENT_NAME).child(usr[0]);
            patientName.setValue(usr[0]);
            patient.setValue(usr[0]);
            return null;
        }
    }

}
