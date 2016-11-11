package team19.notes4u;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import team19.notes4u.DB.Request;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import team19.notes4u.DB.Wrapper;
import team19.notes4u.DB.Course;

import org.json.JSONException;
import org.json.JSONObject;

public class PostActivity extends AppCompatActivity {

    List<String> allCourses = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Create a Request");


        Spinner courses = ((Spinner)findViewById(R.id.courseDropDown));
        final List<JSONObject> courseList = new ArrayList<JSONObject>();
        new DownloadCourseList().execute();


        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void createRequest(View v){
        String course = ((Spinner)findViewById(R.id.courseDropDown)).getSelectedItem().toString();
        String courseDate = getDateFromDatePicker((DatePicker)findViewById(R.id.dateOfCourse));
        String courseTime = getTimeFromTimePicker((TimePicker)findViewById(R.id.timeOfCourse));
        String courseLocation = ((EditText)findViewById(R.id.locationOfCourse)).getText().toString().trim();
        String USER_ID = "1"; //Sample, Get USER ID
        if(courseLocation.equals("")) {
            ((EditText) findViewById(R.id.locationOfCourse)).setError("Location of course is required");
        }
        else{
            Request request = new Request();
            request.setCourse(course);
            request.setDatetime(courseDate + ":" + courseTime);
            request.setLocation(courseLocation);
            request.setUser(USER_ID);
            new InsertRequest().execute(request);
        }


    }

    public String getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        return Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static String getTimeFromTimePicker(TimePicker timePicker) {
        timePicker.clearFocus();
        String time = Integer.toString(timePicker.getHour()) + ":" + Integer.toString(timePicker.getMinute());

        return time;
    }

    // Download JSON file AsyncTask
    private class DownloadCourseList extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            List<JSONObject> courseList = new ArrayList<JSONObject>();
            List<Course> courses = new ArrayList<Course>();
            courseList = new Wrapper("courses").getJsonObjects();

            try {
                for (JSONObject jsonobject: courseList) {

                    Course course = new Course();

                    course.setId(jsonobject.optString("id"));
                    course.setSemester(jsonobject.optString("semester"));
                    course.setCourse_code(jsonobject.optString("course_code"));
                    course.setProfessor(jsonobject.optString("professor"));

                    // Populate spinner with country names
                    courses.add(course);
                    allCourses.add(course.toString());

                }
            } catch (Exception e) {
                System.out.println("JSON Loop");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the spinner in activity_main.xml
            Spinner mySpinner = (Spinner) findViewById(R.id.courseDropDown);
            Collections.sort(allCourses);
            // Spinner adapter
            mySpinner
                    .setAdapter(new ArrayAdapter<String>(PostActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            allCourses));

        }
    }

    // Download JSON file AsyncTask
    private class InsertRequest extends AsyncTask<Request, Void, Void> {

        @Override
        protected Void doInBackground(Request... request) {
            Wrapper wrapper = new Wrapper("requests");
            for(int i=0; i<request.length; i++) {
                try {
                    wrapper.InsertRequest(request[i]);
                } catch (JSONException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            System.out.println("I should probably move to the view requests screen");
        }
    }
}
