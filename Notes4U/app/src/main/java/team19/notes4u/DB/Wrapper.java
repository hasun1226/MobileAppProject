package team19.notes4u.DB;

import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;

import org.json.*;

import team19.notes4u.R;

import static team19.notes4u.PostActivity.getTimeFromTimePicker;

public class Wrapper {

	private final String staticConnectionString = "http://notes4u.herokuapp.com/";

	private String ObjectType;
	private String connectionString = staticConnectionString;

	private URLConnection connect;

	public Wrapper(){

	}

	public Wrapper(String obj){
		this.ObjectType = obj;
		this.connectionString += ObjectType;
	}

	public List<JSONObject> getJsonObjects() {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		Pattern p = Pattern.compile("\\{(.*?)\\}");
		Matcher m = null;
		try {
			m = p.matcher(getContent());
		}
		catch (Exception e){
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
		while(m.find()){
			try{
			jsonObjects.add(new JSONObject(m.group()));
			}
			catch (Exception e){
				System.out.println(e.getCause());
				System.out.println(e.getMessage());
			}
		}
		return jsonObjects;
	}

	public String getDateFromDatePicker(DatePicker datePicker){
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year =  datePicker.getYear();

		return Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
	}

	public void InsertRequest(Request request) throws JSONException, IOException {
		URL object = new URL(connectionString);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		JSONObject params   = new JSONObject();
		JSONObject parent = new JSONObject();

		//String courseDate = getDateFromDatePicker((DatePicker)findViewById(R.id.dateOfCourse));
		//String courseTime = getTimeFromTimePicker((TimePicker)findViewById(R.id.timeOfCourse));

		params.put("user_id", request.getUser());
		params.put("course_id", "1");
		params.put("when", "Thu, 29 Feb 2016 22:45:10");
		params.put("location", request.getLocation());

		parent.put("request", params);

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(parent.toString());
		wr.flush();

		//display what returns the POST request

		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			System.out.println("" + sb.toString());
		} else {
			System.out.println(con.getResponseMessage());
		}

	}

	private String getContent() throws IOException {
		URL url = new URL(this.connectionString);
		Scanner s = new Scanner(url.openStream());
		String out = "";
		while (s.hasNext()) {
			out += s.next();
		}
		s.close();
		return out;
	}

	public void closeConnection(){
		connect.setDoOutput(false);
		connect.setDoInput(false);
	}


	public static void main(String[] args) throws Exception {
		//Wrapper wrapper = new Wrapper("requests");
		//System.out.println(wrapper.getJsonObjects());
		//wrapper.CreateRequest("3", "CSC384", "2016-09-13T10:", "00:00.000Z", "BA3200");
	}

}
