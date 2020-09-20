package test;

import dao.impl.otherDao;
import dao.impl.studentDao;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        List<Map<String, Object>> studentsByRoom = studentDao.getStudentsByRoom("1", 0);
        System.out.println(studentsByRoom);
        String time = otherDao.queryForOther("time");
        System.out.println(time);

    }
}
