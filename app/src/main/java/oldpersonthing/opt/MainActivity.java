package oldpersonthing.opt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import oldpersonthing.opt.util.PateintLoginActivity;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button pill = (Button) findViewById(R.id.buttonPill);
//        pill.setOnClickListener(this);
        final Button patient = (Button) findViewById(R.id.patientButton);
        patient.setOnClickListener(this);
        final Button nurse = (Button) findViewById(R.id.nurseButton);
//        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/verdana.ttf");
//        nurse.setTypeface(face);
//        patient.setTypeface(face);
//        pill.setTypeface(face);
        nurse.setOnClickListener(this);
//        startActivity(new Intent(this,TestActivity.class));
//        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


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
            case R.id.nurseButton:
                startActivity(new Intent(this,NurseLoginActivity.class));
                break;
            case R.id.patientButton:
                startActivity(new Intent(this,PateintLoginActivity.class));
                break;
        }

//        if(v.getId() == R.id.buttonPill){
//            popPills();
//        }else{
//            unPopPills();
//        }
    }

    public void popPills(){
        String url = "https://www.google.com/";
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            Log.e(e.toString(),e.getMessage());
        }
    }
    public void unPopPills(){
        String url = "35.14.107.223:5000/close";
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            Log.e(e.toString(),e.getMessage());
        }
    }
}
