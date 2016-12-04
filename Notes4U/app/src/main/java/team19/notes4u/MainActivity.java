package team19.notes4u;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;

import team19.notes4u.DB.User;
import team19.notes4u.model.ApiService;
import team19.notes4u.pollHelpers.PollAlarmManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private String user_name;
        private String user_id;
    Retrofit retrofit;
    ApiService apiService;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder().baseUrl("https://notes4u.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        textView = (TextView) findViewById(R.id.textView4);

        Intent intent = getIntent();
        user_name = intent.getExtras().getString("username");
        user_id = intent.getExtras().getString("user");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        TextView txt = (TextView) headerView.findViewById(R.id.greeting);
        txt.setText("Welcome, " + user_name + "!");
        navigationView.setNavigationItemSelectedListener(this);

        new PollTask().execute();

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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.post_request) {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            intent.putExtra("username", user_name);
            intent.putExtra("user", user_id);
            startActivity(intent);
        } else if (id == R.id.notetake) {
            Intent intent = new Intent(MainActivity.this, TakeNotesActivity.class);
            intent.putExtra("username", user_name);
            intent.putExtra("user", user_id);
            startActivity(intent);

        } else if (id == R.id.my_profile) {

        } else if (id == R.id.view_my_request) {
            Intent intent = new Intent(MainActivity.this, ViewSlackRequestsActivity.class);
            intent.putExtra("user", user_id);
            startActivity(intent);

        } else if (id == R.id.view_pending) {
            Intent intent = new Intent(MainActivity.this, ViewPendingActivity.class);
            intent.putExtra("user", user_id);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getUserId(){
        return user_id;
    }

    private class PollTask extends AsyncTask<String, Void, Void> {
        private AlarmManager alarmMgr;
        private PendingIntent alarmIntent;

        public Void doInBackground(String... strings) {
            long totalSize = 0;
            try {
                alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this, PollAlarmManager.class);
                for (String str: strings) {
                    intent.putExtra("user", str);
                }

                alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + 2000,
                        2000, alarmIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
