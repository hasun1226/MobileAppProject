import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.*;

public class Wrapper {
	
	private final String staticConnectionString = "http://notes4u.herokuapp.com/";
	
	private String ObjectType; 
	private String connectionString = staticConnectionString;
	
	private Scanner connect;
	
	public Wrapper(String obj){
		this.ObjectType = obj;
		this.connectionString += ObjectType;
	}
	
	public List<JSONObject> getJsonObjects() throws JSONException{
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		Pattern p = Pattern.compile("\\{(.*?)\\}");
		Matcher m = p.matcher(getContent());
		while(m.find()){
			jsonObjects.add(new JSONObject(m.group()));
		}
		return jsonObjects;
	}
	
	private String getContent() {
		String out = connect.useDelimiter("\\A").next();
		return out;
	}
	
	private void openConnection(){
		try {
			this.connect = new Scanner(new URL(this.connectionString).openStream(), "UTF-8");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void closeConnection(){
		this.connect.close();
	}
	
	
    public static void main(String[] args) throws Exception {
    	//Wrapper wrapper = new Wrapper("users");
    	//wrapper.openConnection();
    	//System.out.println(wrapper.getJsonObjects());
    	//wrapper.closeConnection();
    }  
	
}
