package bean;

public class student {
    private int studentId;
    private String name;
    private String Class;
    private String time1;
    private String time2;
    private double score1;
    private double score2;

    @Override
    public String toString() {
        return "student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", Class='" + Class + '\'' +
                ", time1='" + time1 + '\'' +
                ", time2='" + time2 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }

    public student(int studentId, String name, String aClass, String time1, String time2, double score1, double score2) {
        this.studentId = studentId;
        this.name = name;
        Class = aClass;
        this.time1 = time1;
        this.time2 = time2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getclass() {
        return Class;
    }

    public void setClass(String aClass) {
        Class = aClass;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }
}
