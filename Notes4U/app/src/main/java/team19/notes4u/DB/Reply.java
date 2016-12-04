package team19.notes4u.DB;

/**
 * Created by SUNNY on 2016-12-04.
 */

public class Reply {
    private String id;
    private String notetaker_id;
    private String slacker_id;
    private String status;
    private String request_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String id) {
        this.request_id = id;
    }

    public String getNotetaker_id() {
        return notetaker_id;
    }

    public void setNotetaker_id(String notetaker_id) { this.notetaker_id = notetaker_id; }

    public String getSlacker_id() { return slacker_id; }

    public void setSlacker_id(String slacker_id) { this.slacker_id = slacker_id; }

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
        return getId() + "\nNotetaker: " + getNotetaker_id() + "\nSlacker: " + getSlacker_id() + "\nStatus: " + getStatus();
    }

}
