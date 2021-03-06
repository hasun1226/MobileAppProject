package team19.notes4u;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import team19.notes4u.DB.Reply;
import team19.notes4u.DB.Request;
import team19.notes4u.DB.Wrapper;
import team19.notes4u.adapter.ListViewRequestAdapter;

import team19.notes4u.DB.StatusIdToStatus.STATUS;

public class TakeNotesActivity extends AppCompatActivity {

    String user;

    List<Request> requests = new ArrayList<Request>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_notes);
        setTitle("Take Notes Requests");

        //allows access to passed on activity variables
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("user");
            System.out.println("user is: " + user);
        }
        else{
            user = "1";
            System.out.println("Couldn't get user id defaulting to 1");
        }

        populateTakeNotesRequests(user);
    }

    private void populateTakeNotesRequests(String user) {
        new DownloadRequestList().execute();
    }

    private void CreateReply(Request request) {
        System.out.println(request.toString());
        Reply reply = new Reply();
        reply.setRequestID(request.getId());
        reply.setNoteTakerID(user);
        reply.setSlackerID(request.getUser());
        System.out.println(reply.toString() + "request_id = " + request.getId());
        new InsertReply().execute(reply);
    }

    // Download JSON file AsyncTask
    private class DownloadRequestList extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            //        http://notes4u.herokuapp.com/users/1/requests
//        format: 'users' + userid + '/requests'


            String connectionStringRequests = ("/requests");
            Wrapper wrapper = new Wrapper(connectionStringRequests);

//        Object[] request_array = wrapper.getJsonObjects();

            List<JSONObject> request_array = wrapper.getJsonObjects();
            for(JSONObject j : request_array){
                Request r = new Request();
                int already_taking = 0;
                //System.out.println(j.toString());

                try {

                    String connectionNotetakers = connectionStringRequests + "/" + j.getString("id") + "/notetakers";
                    Wrapper noteTakerWrapper = new Wrapper(connectionNotetakers);
                    List<JSONObject> notetakers = noteTakerWrapper.getJsonObjects();
                    for(JSONObject taker : notetakers){
                        if (Integer.parseInt(taker.getString("id")) == Integer.parseInt(user)) {
                            System.out.println("HERE " + taker.getString("id"));
                            already_taking = 1;
                        }
                    }

                    String connectionStringCourse = ("courses/" + j.getString("course_id"));
                    Wrapper coursesWrapper = new Wrapper(connectionStringCourse);
                    List<JSONObject> course = coursesWrapper.getJsonObjects();

                    r.setDatetime(j.getString("when"));
                    r.setLocation(j.getString("location"));
                    r.setId(j.getString("id"));
                    r.setUser(j.getString("user_id"));
                    r.setCourse(course.get(0).getString("course_code"));
                    r.setStatus(j.getString("status"));

                } catch (JSONException e)  {
                    System.out.println("HERE");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                // Doesn't add the requests that are accepted or posted by the user
                if(Integer.parseInt(r.getStatus()) != STATUS.ACCEPTED.ordinal() &&
                        Integer.parseInt(r.getUser()) != Integer.parseInt(user) &&
                        Integer.parseInt(r.getStatus()) != STATUS.COMPLETED.ordinal() &&
                        already_taking == 0) {

                    requests.add(r);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            ListView listView = (ListView)(findViewById(R.id.listviewslackrequests));

            ListViewRequestAdapter adapter = new ListViewRequestAdapter(
                    TakeNotesActivity.this,
                    requests);
            listView.setAdapter(adapter);

            //overwrite the current onitemclicklistener so when listitem is selected you go to the
            //'Notetakers' profile
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent, View view, final int position,
                                        long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TakeNotesActivity.this);
                    builder.setTitle("Accept Request to Take Notes?");

                    builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            // Set this request as Pending
                            CreateReply((Request) parent.getAdapter().getItem(position));

                            Toast pieceToast= Toast.makeText(getApplicationContext(), "You have applied to be a notetaker!", Toast.LENGTH_SHORT);
                            pieceToast.show();
                            // Refresh Screen
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Return to take note request screen
                        }
                    });

                    builder.show();  //<-- See This!

                }
            });
        }
    }

    private class InsertReply extends AsyncTask<Reply, Void, Void> {

        @Override
        protected Void doInBackground(Reply... replies) {
            Wrapper wrapper = new Wrapper("replies");
            for(int i=0; i< replies.length; i++) {
                try {
                    System.out.println(replies[i]);
                    wrapper.InsertReply(replies[i]);
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            return null;
        }
    }
}
