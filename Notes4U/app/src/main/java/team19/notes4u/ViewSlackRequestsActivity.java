package team19.notes4u;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;
import android.app.ProgressDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Environment;
import android.net.Uri;
import android.os.PowerManager;

import org.json.JSONException;
import org.json.JSONObject;

import team19.notes4u.DB.Wrapper;

import team19.notes4u.DB.Request;

import team19.notes4u.adapter.ListViewRequestAdapter;

/**
 * Created by tyler on 09/11/16.
 */

public class ViewSlackRequestsActivity extends AppCompatActivity {


    String user;

    //parallel arrays
    //requestString: arrayList of the requests in a humanreadable format
    //requests arrayList of the request objects
    List<Request> requests = new ArrayList<Request>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slack_requests);
        setTitle("Slack Requests");

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


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(ViewSlackRequestsActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ViewSlackRequestsActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(ViewSlackRequestsActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1
                        );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
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

            String connectionString = ("users/" + user + "/requests");
            Wrapper wrapper = new Wrapper(connectionString);

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
                    r.setDownload_url(j.getString("download_link"));

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

            ListViewRequestAdapter adapter = new ListViewRequestAdapter(
                    ViewSlackRequestsActivity.this,
                    requests);
            listView.setAdapter(adapter);

            //overwrite the current onitemclicklistener so when listitem is selected you go to the
            //'Notetakers' profile
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    if (requests.get(position).getStatus().equals("3")){

                        String url = requests.get(position).getDownload_url();
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        request.setDescription("Download Link");
                        request.setTitle(requests.get(position).getCourse()+" "+requests.get(position).getDatetime());
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

// get download service and enqueue file
                        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        AlertDialog alertDialog = new AlertDialog.Builder(ViewSlackRequestsActivity.this).create(); //Read Update
                        alertDialog.setTitle("File Downloaded");
                        alertDialog.setMessage("Your file is now accessible in downloads.");
                        //alertDialog.setButton(0, "Ok", new DialogInterface.OnClickListener() {
                        //public void onClick(DialogInterface dialog, int which) {
                        // }
                        //});
                        alertDialog.show();  //<-- See This!
                    }
                    else if (requests.get(position).getStatus().equals("0")) {
                        Intent intent = new Intent(ViewSlackRequestsActivity.this, ProfileActivity.class);
                    }
                    else if(requests.get(position).getStatus() == "1") {
                        Intent intent = new Intent(ViewSlackRequestsActivity.this, ViewNotetakersWhoReplied.class);
                        intent.putExtra("request_id", requests.get(position).getId());
                        intent.putExtra("user_id", requests.get(position).getUser());
                        startActivity(intent);
                    }
                    else {
                        AlertDialog alertDialog = new AlertDialog.Builder(ViewSlackRequestsActivity.this).create(); //Read Update
                        alertDialog.setTitle("No file available");
                        alertDialog.setMessage("Your notetaker has not yet uploaded any notes.");
                        //alertDialog.setButton(0, "Ok", new DialogInterface.OnClickListener() {
                        //public void onClick(DialogInterface dialog, int which) {
                        // }
                        //});
                        alertDialog.show();  //<-- See This!
                    }
                }
            });
        }
    }
}