package team19.notes4u.DB;

/**
 * Created by Charga on 11/11/2016.
 */

public class Course {

    private String id;
    private String course_code;
    private String semester;
    private String professor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }


    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString(){
        return this.course_code+"/"+this.semester+"/"+this.getProfessor();
    }
}
