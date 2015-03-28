package oldpersonthing.opt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;


public class TestActivity extends ActionBarActivity {
    Firebase ref;
    Firebase usersRef;
    NurseUser userTest2;
    Map<String, NurseUser> users;
    ArrayList<String> s = new ArrayList();
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Firebase.setAndroidContext(this);
/*
        ref = new Firebase("https://dazzling-heat-1446.firebaseio.com/");
        usersRef = ref.child("Patients");
        userTest2 = new NurseUser("userTest1");
        users = new HashMap<String, NurseUser>();
        //String users = "test123";
        for(int i = 0;i<10;i++)
        users.put("patient"+i,new NurseUser(""+Math.random()));



        usersRef.setValue(users);
        s.add("test");

        String[] f = new String[s.size()];
        for(int i=0;i<f.length;i++){
            f[i] = s.get(i);
        }
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.w("DATA FROM FIRE",snapshot.getValue().toString());
                Map<String,Object> x = (Map<String,Object>) snapshot.getValue();
                Log.w("DATA FROM FIRE",x.toString());
                Map<String,Object> xx = (Map<String,Object>) x.get("users3");
                Log.w("DATA FROM FIRE",xx.toString());
                Map<String,Object> xxx = (Map<String,Object>) xx.get("userTest2");
                Log.w("DATA FROM FIRE",xxx.toString());
                String xxxx = xxx.get("userName").toString();

                Log.w("DATA FROM FIRE",xxxx);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.w("The read failed: ", firebaseError.getMessage());
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,f );
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);*/
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Firebase ref = new Firebase("https://dazzling-heat-1446.firebaseio.com/");
        ref.createUser("bobtony@firebase.com", "correcthorsebatterystaple", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.w("FIREBASE USER WORKED","Successfully created user account with uid: " + result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
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
}
