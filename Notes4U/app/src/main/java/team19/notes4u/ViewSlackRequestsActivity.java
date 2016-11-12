package team19.notes4u;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import team19.notes4u.DB.Wrapper;
import team19.notes4u.DB.Request;

/**
 * Created by tyler on 09/11/16.
 */

public class ViewSlackRequestsActivity extends AppCompatActivity {

    String user_id = "1";
    List<String> requestsString = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slack_requests);
        setTitle("Slack Requests");
        populateSlackRequests("1");

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

            String connectionString = ("users/" + user_id + "/requests");
            Wrapper wrapper = new Wrapper(connectionString);

//        Object[] request_array = wrapper.getJsonObjects();

            List<JSONObject> request_array = wrapper.getJsonObjects();
            List<Request> requests = new ArrayList<>();
            for(JSONObject j : request_array){
                Request r = new Request();
                System.out.println(j.toString());
                try {
                    r.setDatetime(j.getString("when"));
                    r.setLocation(j.getString("location"));
                    r.setId(j.getString("id"));
                } catch (JSONException e) {
                    System.out.println("HERE");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                requests.add(r);
                requestsString.add(r.toString());

            }
            System.out.println(requestsString);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            ListView listView = (ListView)(findViewById(R.id.listviewslackrequests));
            listView.setAdapter(new ArrayAdapter<String>(
                    ViewSlackRequestsActivity.this,
                    R.layout.listitem,
                    R.id.textview,
                    requestsString
            ));
        }
    }



}