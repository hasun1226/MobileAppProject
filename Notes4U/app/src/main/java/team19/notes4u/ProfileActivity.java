package team19.notes4u;

import android.content.Intent;
import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.RatingBar;
import android.widget.TextView;

import android.widget.Toast;

import team19.notes4u.DB.Wrapper;



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

    private String user_id;

    private String notetaker_name;
    private Bitmap notetaker_profile_pic;
    private String notetaker_id;

    private String reply_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        Button acceptButton = (Button) findViewById(R.id.AcceptNoteTaker);
        setTitle("NoteTaker Profile");

        Intent getting = getIntent();

        user_id = getting.getExtras().getString("user_id");

        notetaker_name = getting.getExtras().getString("notetaker_email");
        notetaker_id = getting.getExtras().getString("notetaker_id");
        notetaker_profile_pic = getting.getExtras().getParcelable("notetaker_profile_picture");

        reply_id = getting.getExtras().getString("reply_id");

        ((ImageView)findViewById(R.id.noteTakerProfilePic)).setImageBitmap(notetaker_profile_pic);
        ((TextView)findViewById(R.id.userID)).setText(notetaker_name);
    }

    public void reply(View view){
        new ReplyToThisNoteTaker().execute();
        Toast toast = Toast.makeText(this, "Accepted this Notetaker", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(ProfileActivity.this, ViewSlackRequestsActivity.class);
        intent.putExtra("user", user_id);
        startActivity(intent);
    }

    private class ReplyToThisNoteTaker extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Wrapper.CreateEmptyGetRequest("replies/"+reply_id+"/approve_request");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

        }
    }
}