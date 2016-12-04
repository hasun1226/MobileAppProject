package team19.notes4u.DB;

/**
 * Created by tyler on 04/12/16.
 */

public class Reply {

    private String status;
    private String id;
    private String noteTakerID;
    private String slackerID;
    private String requestID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoteTakerID() {
        return noteTakerID;
    }

    public void setNoteTakerID(String userID) {
        this.noteTakerID = userID;
    }

    public String getSlackerID() {
        return slackerID;
    }

    public void setSlackerID(String slackerID) {
        this.slackerID = slackerID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

}
