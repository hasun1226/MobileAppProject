package team19.notes4u;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import org.json.JSONException;
import org.json.JSONObject;
import team19.notes4u.DB.Wrapper;

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

    private String user_id;
    private String notetaker_name;
    private Bitmap notetaker_profile_pic;
    private String notetaker_id;

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

        ((ImageView)findViewById(R.id.noteTakerProfilePic)).setImageBitmap(notetaker_profile_pic);
        ((TextView)findViewById(R.id.userID)).setText(notetaker_name);
    }
}