package oldpersonthing.opt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.firebase.client.Firebase;


public class NurseLoginActivity extends ActionBarActivity implements View.OnClickListener{
    CheckBox isNewNurse;
    Button next;
    EditText name;
    static String nurseName ="Joy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isNewNurse = (CheckBox) findViewById(R.id.checkBoxNewNurse);
        next = (Button) findViewById(R.id.buttonNextNurse);
        next.setOnClickListener(this);
        name = (EditText) findViewById(R.id.editTextNameNurse);
        name.setOnClickListener(this);
        Firebase.setAndroidContext(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
        switch(v.getId()){
            case R.id.buttonNextNurse:
                if(isNewNurse.isChecked()){
                    EditText nameNurse = (EditText) findViewById(R.id.editTextNameNurse);
                    nurseName = nameNurse.getText().toString();
                    new WriteNurse().execute(nurseName);
                    startActivity(new Intent(this, NewNurseActivity.class));
                }else{
                    EditText nameNurse = (EditText) findViewById(R.id.editTextNameNurse);
                    nurseName = nameNurse.getText().toString();
                    startActivity(new Intent(this, ReturningNurseActivity.class));
        }
                break;
            case R.id.editTextName:
                name.setText("");
                break;
        }
    }
    private class WriteNurse extends AsyncTask<String,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... nurse) {
            Firebase ref = new Firebase("https://dazzling-heat-1446.firebaseio.com");
            Firebase nnurse = ref.child(nurse[0]);
            nnurse.setValue(nurse[0]);
            return null;
        }
    }
}
