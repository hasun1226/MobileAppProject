package team19.notes4u;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Profile screen that receives a notetaker and displays their profile picture,
 * username, and rating
 */

public class ProfileActivity extends AppCompatActivity {
    private String rating;
    private static String url;
    private Wrapper wrapper;
    private TextView userName;
    private RatingBar starRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button acceptButton = (Button) findViewById(R.id.AcceptNoteTaker);


        acceptButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(ProfileActivity.this).create(); //Read Update
                alertDialog.setTitle("Notetaker Accepted");
                alertDialog.setMessage("You will receive an alert when your notes are ready.");
                //alertDialog.setButton(0, "Ok", new DialogInterface.OnClickListener() {
                //public void onClick(DialogInterface dialog, int which) {
                // }
                //});
                alertDialog.show();  //<-- See This!
            }
        });

        Intent intent = getIntent();
        //String userName = intent.getStringExtra(MainActivity.USER_NAME);
        int userID = 1;//intent.getIntExtra(MainActivity.USER_ID);
        url = "users/" + Integer.toString(userID) + "/ratings";
        wrapper = new Wrapper(url);
        new fetchData().execute();
        userName.setText("Steve Johnson");
    }

    private class fetchData extends AsyncTask<Void, Void, List<JSONObject>> {
        @Override
        protected void onPreExecute() {
            userName = (TextView) findViewById(R.id.userID);
            starRating = (RatingBar) findViewById(R.id.starBar);
        }

        List<JSONObject> jsonObjects;
        @Override
        protected List<JSONObject> doInBackground(Void... args) {
            wrapper.openConnection();
            try {
                jsonObjects = wrapper.getJsonObjects();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObjects;
        }

        @Override
        protected void onPostExecute(List<JSONObject> jsonObjects) {
            try {
                JSONObject user = jsonObjects.get(0);
                rating = user.getString("rating");
                starRating.setNumStars(5);
                starRating.setRating(Integer.parseInt(rating)%7);
                wrapper.closeConnection();
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}
