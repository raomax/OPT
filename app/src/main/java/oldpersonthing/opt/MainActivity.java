package oldpersonthing.opt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    Firebase ref;
    Firebase usersRef;
    NurseUser userTest1;
    Map<String, NurseUser> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        ref = new Firebase("https://dazzling-heat-1446.firebaseio.com/");
        usersRef = ref.child("users");
        userTest1 = new NurseUser("userTest1");
        users = new HashMap<String, NurseUser>();

        users.put("userTest1",userTest1);

        usersRef.setValue(users);
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
