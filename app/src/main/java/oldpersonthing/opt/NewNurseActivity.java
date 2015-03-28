package oldpersonthing.opt;

import android.content.Intent;
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

import java.util.ArrayList;


public class NewNurseActivity extends ActionBarActivity implements View.OnClickListener{
    Button addOldPerson;
    ArrayList<RegUser> oldPeoples;
    EditText oldPersonName;
    final String NURSE_NAME = NurseLoginActivity.nurseName;
    ListView listView;
    static RegUser oldPersonsName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_nurse);
        addOldPerson = (Button) findViewById(R.id.buttonAddOldPerson);
        addOldPerson.setOnClickListener(this);
        oldPeoples = new ArrayList();
        oldPersonName = (EditText) findViewById(R.id.editTextOldPersonName);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oldPersonsName = (RegUser) listView.getItemAtPosition(position);
                startActivity(new Intent(getBaseContext(),PatientSchedueler.class));
                finish();
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
                    oldPeoples.add(new RegUser(oldPersonName.getText().toString(), NURSE_NAME));
                    Log.w("ARRAY LIST", oldPeoples.toString());
                    ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                            android.R.layout.simple_list_item_1, oldPeoples.toArray());

                    listView.setAdapter(adapter);


                }
        }
    }
}
