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

import java.util.ArrayList;
import java.util.List;

import team19.notes4u.DB.Request;
import team19.notes4u.DB.StatusIdToStatus;
import team19.notes4u.DB.Wrapper;
import team19.notes4u.adapter.ListViewRequestAdapter;

public class ViewPendingActivity extends AppCompatActivity {


    String user;

    //parallel arrays
    //requestString: arrayList of the requests in a humanreadable format
    //requests arrayList of the request objects
    List<Request> requests = new ArrayList<Request>();

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

    // Download JSON file AsyncTask
    private class DownloadRequestList extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            //        http://notes4u.herokuapp.com/users/1/requests
//        format: 'users' + userid + '/requests'

            String connectionStringRequests = ("users/" + user + "/requests");
            Wrapper wrapper = new Wrapper(connectionStringRequests);

//        Object[] request_array = wrapper.getJsonObjects();

            List<JSONObject> request_array = wrapper.getJsonObjects();
            for(JSONObject j : request_array){
                Request r = new Request();
                //System.out.println(j.toString());

                try {
                    String connectionStringCourse = ("courses/" + j.getString("course_id"));
                    Wrapper coursesWrapper = new Wrapper(connectionStringCourse);
                    List<JSONObject> course = coursesWrapper.getJsonObjects();

                    r.setDatetime(j.getString("when"));
                    r.setLocation(j.getString("location"));
                    r.setId(j.getString("id"));
                    r.setUser(j.getString("user_id"));
                    r.setCourse(course.get(0).getString("course_code"));
                    r.setStatus(j.getString("status"));

                } catch (JSONException e) {
                    System.out.println("HERE");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                if(Integer.parseInt(r.getStatus()) == StatusIdToStatus.STATUS.ACCEPTED.ordinal() ||
                        Integer.parseInt(r.getStatus()) == StatusIdToStatus.STATUS.PENDING.ordinal())
                    requests.add(r);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            ListView listView = (ListView)(findViewById(R.id.listviewslackrequests));

            ListViewRequestAdapter adapter = new ListViewRequestAdapter(
                    ViewPendingActivity.this,
                    requests);
            listView.setAdapter(adapter);
        }
    }
}
