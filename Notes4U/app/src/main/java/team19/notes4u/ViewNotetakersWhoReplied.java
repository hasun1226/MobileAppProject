package team19.notes4u;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import team19.notes4u.DB.Wrapper;
import team19.notes4u.adapter.ProfileViewRequestAdapter;
import team19.notes4u.DB.User;

public class ViewNotetakersWhoReplied extends AppCompatActivity {

    String user;
    String request_id;

    List<User> replies = new ArrayList<User>();
    List<String> replies_id = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notetakers_who_replied);


        //allows access to passed on activity variables
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
            System.out.println("user is: " + user);

            request_id = extras.getString("request_id");
        }
        else{
            user = "1";
            System.out.println("Couldn't get user id defaulting to 1");
        }


        populateReplies(user);
    }

    //        TODO: Find out how to get user id
    private void populateReplies(String user_id){
        new DownloadRepliesList().execute();
    }

    // Download JSON file AsyncTask
    private class DownloadRepliesList extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            //        http://notes4u.herokuapp.com/users/1/requests
//        format: 'users' + userid + '/requests'
            // requests/:request_id/replies
            String connectionString = ("requests/" + request_id + "/replies");
            Wrapper wrapper = new Wrapper(connectionString);

//        Object[] request_array = wrapper.getJsonObjects();

            List<JSONObject> replies_array = wrapper.getJsonObjects();
            for(JSONObject j : replies_array){
                User u = new User();
                String reply_id = "";

                try {
                    String connectionStringCourse = ("users/" + j.getString("notetaker_id"));
                    Wrapper coursesWrapper = new Wrapper(connectionStringCourse);
                    List<JSONObject> users = coursesWrapper.getJsonObjects();

                    u.setId(users.get(0).getString("id"));
                    u.setEmail(users.get(0).getString("email"));
                    u.setProfile_picture(users.get(0).getString("profile_picture"));
                    u.setProfile_Image(loadBitmap(u.getProfile_picture()));

                    reply_id = j.getString("id");


                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                replies.add(u);
                replies_id.add(reply_id);
            }
            return null;
        }

        public Bitmap loadBitmap(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void v) {
            ListView listView = (ListView)(findViewById(R.id.listviewslackrequests));

            ProfileViewRequestAdapter adapter = new ProfileViewRequestAdapter(
                    ViewNotetakersWhoReplied.this,
                    replies);
            listView.setAdapter(adapter);

            //overwrite the current onitemclicklistener so when listitem is selected you go to the
            //'Notetakers' profile
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Intent intent = new Intent(ViewNotetakersWhoReplied.this, ProfileActivity.class);
                    intent.putExtra("user_id", user);
                    intent.putExtra("notetaker_id", replies.get(position).getId());

                    intent.putExtra("notetaker_id", replies.get(position).getId());
                    intent.putExtra("notetaker_email", replies.get(position).getEmail());
                    intent.putExtra("notetaker_profile_picture", replies.get(position).getProfile_image());

                    intent.putExtra("reply_id", replies_id.get(position));
                    startActivity(intent);

                }
            });
        }
    }
}
