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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import team19.notes4u.DB.Reply;
import team19.notes4u.DB.Request;
import team19.notes4u.DB.StatusIdToStatus;
import team19.notes4u.DB.Wrapper;
import team19.notes4u.adapter.ListViewRequestAdapter;

public class ViewPendingActivity extends AppCompatActivity {

    private static final int PICKFILE_RESULT_CODE = 1;
    private String reply_id;
    String user;

    //parallel arrays
    //requestString: arrayList of the requests in a humanreadable format
    //requests arrayList of the request objects
    List<Reply> replies = new ArrayList<Reply>();
    List<Request> requestList = new ArrayList<Request>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending);
        setTitle("View My Pending Requests");

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

        populateSlackRequests(user);
    }

    //        TODO: Find out how to get user id
    private void populateSlackRequests(String user_id){
        new DownloadRequestList().execute();
    }

    int index;

    // Download JSON file AsyncTask
    private class DownloadRequestList extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            //        http://notes4u.herokuapp.com/users/1/requests
//        format: 'users' + userid + '/requests'

            String connectionStringRequests = ("replies/");
            Wrapper wrapper = new Wrapper(connectionStringRequests);

//        Object[] request_array = wrapper.getJsonObjects();

            List<JSONObject> request_array = wrapper.getJsonObjects();
            for(JSONObject j : request_array){
                Reply r = new Reply();
                Request request = new Request();
                //System.out.println(j.toString());

                try {
                    String requestString = ("requests/"+j.getString("request_id"));
                    Wrapper requestWrapper = new Wrapper(requestString);
                    List<JSONObject> requests = requestWrapper.getJsonObjects();

                    r.setId(j.getString("id"));
                    r.setNoteTakerID(j.getString("notetaker_id"));
                    r.setRequestID(j.getString("request_id"));
                    r.setSlackerID(j.getString("slacker_id"));
                    r.setStatus(j.getString("status"));

                    String connectionStringCourse = ("courses/" + requests.get(0).getString("course_id"));
                    Wrapper coursesWrapper = new Wrapper(connectionStringCourse);
                    List<JSONObject> course = coursesWrapper.getJsonObjects();

                    request.setDatetime(requests.get(0).getString("when"));
                    request.setLocation(requests.get(0).getString("location"));
                    request.setId(requests.get(0).getString("id"));
                    request.setUser(requests.get(0).getString("user_id"));
                    request.setCourse(course.get(0).getString("course_code"));
                    request.setStatus(requests.get(0).getString("status"));

                } catch (JSONException e) {
                    System.out.println("HERE");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                if (r.getNoteTakerID().equals(user)){
                    replies.add(r);
                    requestList.add(request);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            ListView listView = (ListView)(findViewById(R.id.listviewslackrequests));

            ListViewRequestAdapter adapter = new ListViewRequestAdapter(
                    ViewPendingActivity.this,
                    requestList);
            listView.setAdapter(adapter);

            //overwrite the current onitemclicklistener so when listitem is selected you go to the
            //'Notetakers' profile
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position,
                                        long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewPendingActivity.this);
                    builder.setTitle("Accept Request to Take Notes?");

                    builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Set this request as Pending
                            // Toast: request has been sent
                            // Refresh Screen
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.setType("*/*");
                            index = position;
                            startActivityForResult(intent, PICKFILE_RESULT_CODE);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PICKFILE_RESULT_CODE:
                if (resultCode==RESULT_OK) {
                    String FilePath = data.getData().getPath();
                    System.out.println(FilePath);
                    //textFile.setText(FilePath);
                }
                break;
        }
        //Post Request stuff here
        new UploadRequest().execute(index);
    }

    private class UploadRequest extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... ints) {
            for(Integer i : ints) {
                try {
                    Wrapper wrapper = new Wrapper("replies/upload/" + replies.get(i).getId());
                    wrapper.getJsonObjects();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            System.out.println("Request has been posted");
        }
    }
}
