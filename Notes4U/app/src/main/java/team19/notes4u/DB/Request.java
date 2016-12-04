package team19.notes4u.DB;

/**
 * Created by Charga on 11/11/2016.
 */

public class Request {

    private String id;
    private String user;
    private String course;
    private String datetime;
    private String location;
    private String status;

    public enum STATUS{
        UNACCEPTED, PENDING, ACCEPTED
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus(){
        return this.status;
    }

    public static String changeStatus(String status) {
        if(status.equals("0"))
            return "Unaccepted";
        if(status.equals("1"))
            return "Pending";
        if(status.equals("2"))
            return "Accepted";
        return "N/A";
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return "Location = " + getLocation() + ", Datetime = " + getDatetime() + ", Slacker = " + getUser();
    }
}
