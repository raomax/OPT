package oldpersonthing.opt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TestActivity extends ActionBarActivity {
    Firebase ref;
    Firebase usersRef;
    NurseUser userTest2;
    Map<String, NurseUser> users;
    ArrayList<String> s = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Firebase.setAndroidContext(this);

        ref = new Firebase("https://dazzling-heat-1446.firebaseio.com/");
        usersRef = ref.child("users3");
        userTest2 = new NurseUser("userTest1");
        users = new HashMap<String, NurseUser>();
        //String users = "test123";

        users.put("userTest2",userTest2);
        users.put("userTest3",userTest2);


        usersRef.push().setValue(users);
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
                Map<String,Object> xx = (Map<String,Object>) x.get("users");
                Log.w("DATA FROM FIRE",xx.toString());
                Map<String,Object> xxx = (Map<String,Object>) xx.get("userTest");
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
        listView.setAdapter(adapter);


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
