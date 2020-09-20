package bean;

import java.util.List;

public class order1 {
    private String interviewer;
    private int studentId;
    private double aspect1;
    private double aspect2;
    private double aspect3;
    private double aspect4;
    private List<String> tag1;
    private List<String> tag2;
    private List<String> tag3;
    private List<String> tag4;
    private String task;
    private String comment;

    @Override
    public String toString() {
        return "order1{" +
                "interviewer='" + interviewer + '\'' +
                ", studentId=" + studentId +
                ", aspect1=" + aspect1 +
                ", aspect2=" + aspect2 +
                ", aspect3=" + aspect3 +
                ", aspect4=" + aspect4 +
                ", tag1=" + tag1 +
                ", tag2=" + tag2 +
                ", tag3=" + tag3 +
                ", tag4=" + tag4 +
                ", task='" + task + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public order1() {
    }

    public order1(String interviewer, int studentId, double aspect1, double aspect2, double aspect3, double aspect4, List<String> tag1, List<String> tag2, List<String> tag3, List<String> tag4, String task, String comment) {
        this.interviewer = interviewer;
        this.studentId = studentId;
        this.aspect1 = aspect1;
        this.aspect2 = aspect2;
        this.aspect3 = aspect3;
        this.aspect4 = aspect4;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.task = task;
        this.comment = comment;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getAspect1() {
        return aspect1;
    }

    public void setAspect1(double aspect1) {
        this.aspect1 = aspect1;
    }

    public double getAspect2() {
        return aspect2;
    }

    public void setAspect2(double aspect2) {
        this.aspect2 = aspect2;
    }

    public double getAspect3() {
        return aspect3;
    }

    public void setAspect3(double aspect3) {
        this.aspect3 = aspect3;
    }

    public double getAspect4() {
        return aspect4;
    }

    public void setAspect4(double aspect4) {
        this.aspect4 = aspect4;
    }

    public List<String> getTag1() {
        return tag1;
    }

    public void setTag1(List<String> tag1) {
        this.tag1 = tag1;
    }

    public List<String> getTag2() {
        return tag2;
    }

    public void setTag2(List<String> tag2) {
        this.tag2 = tag2;
    }

    public List<String> getTag3() {
        return tag3;
    }

    public void setTag3(List<String> tag3) {
        this.tag3 = tag3;
    }

    public List<String> getTag4() {
        return tag4;
    }

    public void setTag4(List<String> tag4) {
        this.tag4 = tag4;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    //            "uuid": "qwerty==asdfghjkl==",
//            "studentId": 20206666,
//            "aspect1": 98,
//            "aspect2": 92,
//            "aspect3": 98,
//            "aspect4": 92,
//            "tag1":[12,23,34,45],
//            "tag2":[12,45],
//            "tag3":[12,34,45],
//            "tag4":[],
//            "task": "完成task3",
//            "comment": "这小子可以"
}
