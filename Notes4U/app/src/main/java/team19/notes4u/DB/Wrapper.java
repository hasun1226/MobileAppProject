package team19.notes4u.DB;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;

import org.json.*;

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

	public JSONObject InsertRequest(Request request) throws JSONException, IOException {
		JSONObject JSONrequest = new JSONObject();
		JSONrequest.put("user_id", request.getUser());
		JSONrequest.put("course_id", request.getCourse());
		JSONrequest.put("when", request.getDatetime());
		JSONrequest.put("location", request.getLocation());
		this.connectionString = this.staticConnectionString + "requests";

		String urlParameters = "\\{request: " + JSONrequest.toString() + "}";
		byte[] postData = urlParameters.getBytes();
		int postDataLength = postData.length;
		URL url = new URL( this.connectionString );
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();
		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod( "POST" );
		conn.setRequestProperty( "Content-Type", "application/json");
		conn.setRequestProperty( "charset", "utf-8");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		conn.setUseCaches( false );
		try {
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write( postData );
			wr.close();
		}
		catch (Exception e){

		}
		return JSONrequest;
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
