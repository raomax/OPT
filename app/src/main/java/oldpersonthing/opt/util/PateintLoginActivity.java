package oldpersonthing.opt.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import oldpersonthing.opt.R;

public class PateintLoginActivity extends ActionBarActivity implements View.OnClickListener {
    EditText name;
    CheckBox newPatient;
    boolean isNewPatient = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pateint_login);
       name = (EditText) findViewById(R.id.editTextName);
        name.setOnClickListener(this);

        newPatient = (CheckBox) findViewById(R.id.checkBoxNewPatient);

        Button next = (Button)  findViewById(R.id.buttonNext);
        next.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pateint_login, menu);
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
    protected void onResume() {
        super.onResume();
        isNewPatient = newPatient.isChecked();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editTextName:
                name.setText("");
                break;
            case R.id.buttonNext:
                if(isNewPatient){
                    startActivity(new Intent(this, NewPatientActivity.class));
                }else{

                }



        }

    }

}
