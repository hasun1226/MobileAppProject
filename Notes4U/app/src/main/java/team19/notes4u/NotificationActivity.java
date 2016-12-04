package team19.notes4u;

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
import team19.notes4u.DB.Wrapper;
import team19.notes4u.adapter.ListViewRequestAdapter;
import team19.notes4u.adapter.notificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    String user;

    //parallel arrays
    //requestString: arrayList of the requests in a humanreadable format
    //requests arrayList of the request objects
    List<Request> requests = new ArrayList<Request>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle("Notifications");

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

        populateNotifications(user);

    }

    //        TODO: Find out how to get user id
    private void populateNotifications(String user_id){
        new NotificationActivity.DownloadRequestList().execute();
    }

    // Download JSON file AsyncTask
    private class DownloadRequestList extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            //        http://notes4u.herokuapp.com/users/1/requests
//        format: 'users' + userid + '/requests'

            String connectionString = ("notifications/get_notifications/"+user);
            Wrapper wrapper = new Wrapper(connectionString);

//        Object[] request_array = wrapper.getJsonObjects();

            List<JSONObject> notification_array = wrapper.getJsonObjects();
            for(JSONObject j : notification_array){
                Request r = new Request();
                //System.out.println(j.toString());

                try {
                    //String connectionStringCourse = ("courses/" + j.getString("course_id"));
                    //Wrapper coursesWrapper = new Wrapper(connectionStringCourse);
                    //List<JSONObject> notification = wrapper.getJsonObjects();

                    r.setMessage(j.getString("message"));
                    r.setStatus(j.getString("status"));

                } catch (JSONException e) {
                    System.out.println("HERE");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                requests.add(r);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void v) {
            ListView listView = (ListView)(findViewById(R.id.listviewslackrequests));

            notificationAdapter adapter = new notificationAdapter(
                    NotificationActivity.this,
                    requests);
            listView.setAdapter(adapter);

            //overwrite the current onitemclicklistener so when listitem is selected you go to the
            //'Notetakers' profile
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {

                }
            });
        }
    }

}
