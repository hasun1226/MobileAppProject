package team19.notes4u;

import android.annotation.TargetApi;
import android.content.Intent;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team19.notes4u.DB.Rating;
import team19.notes4u.DB.Request;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import team19.notes4u.DB.Wrapper;
import team19.notes4u.DB.Course;
import team19.notes4u.model.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

public class PostActivity extends AppCompatActivity {

    List<String> allCourses = new ArrayList<String>();
    Retrofit retrofit;
    ApiService apiService;
    private String user_name;
    private String user_id;

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

        Intent intent = getIntent();
        user_name = intent.getExtras().getString("username");
        user_id = intent.getExtras().getString("user");

        retrofit = new Retrofit.Builder().baseUrl("https://notes4u.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
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
            Request in = new Request();
            in.setUser(user_id);
            String courseDate = getDateFromDatePicker((DatePicker)findViewById(R.id.dateOfCourse));
            String courseTime = getTimeFromTimePicker((TimePicker)findViewById(R.id.timeOfCourse));

                Request request = new Request();
                in.setCourse(((Spinner)findViewById(R.id.courseDropDown)).getSelectedItem().toString());
                request.setDatetime(courseDate + ":" + courseTime);
                request.setLocation(((EditText)findViewById(R.id.locationOfCourse)).getText().toString().trim());

            Call<String> createCall = apiService.create("Requests", in);
            createCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast pieceToast= Toast.makeText(getApplicationContext(), "The request has been posted!", Toast.LENGTH_SHORT);
                    pieceToast.show();

                    Intent intent = new Intent(PostActivity.this, MainActivity.class);
                    intent.putExtra("user", user_id);
                    intent.putExtra("username", user_name);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    System.out.println("Failed adding ratings");
                }
            });

            System.out.println("I should probably move to the view requests screen");
        }
    }
}
