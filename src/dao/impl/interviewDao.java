package dao.impl;

import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class interviewDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static void addInterviewOne(String interviewer, int studentId, double aspect1, double aspect2, double aspect3, double aspect4, String tag1, String tag2, String tag3, String tag4, String task, String comment) {
        if (!checkFirstExist(interviewer, studentId)) {
            String sql = "insert into firstInterview(studentId, interviewer, aspect1, aspect2, aspect3, aspect4, task, comment, tag1, tag2, tag3, tag4) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, studentId, interviewer, aspect1, aspect2, aspect3, aspect4, task, comment, tag1, tag2, tag3, tag4);
        }else{
            String sql = "UPDATE firstInterview SET aspect1 = ?, aspect2 = ?, aspect3 = ?, aspect4 = ?, task = ?, comment = ?, tag1 = ?, tag2 = ?, tag3 = ?, tag4 = ? WHERE studentId = ? AND interviewer = ?";
            jdbcTemplate.update(sql, aspect1, aspect2, aspect3, aspect4, task, comment, tag1, tag2, tag3, tag4, studentId, interviewer);
        }


    }

    public static void addInterviewTwo(String interviewer, int studentId, double aspect1, double aspect2, String tag1, String tag2, String task, String comment) {
        if(!checkSecondExist(interviewer,studentId)){
            String sql = "insert into secondInterview(studentId, interviewer, aspect1, aspect2, task, comment, tag1, tag2) values(?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, studentId, interviewer, aspect1, aspect2, task, comment, tag1, tag2);
        }else{
            String sql = "UPDATE secondInterview SET aspect1 = ?, aspect2 = ?, task = ?, comment = ?, tag1 = ?, tag2 = ? WHERE studentId = ? AND interviewer = ?";
            jdbcTemplate.update(sql, aspect1, aspect2, task, comment, tag1, tag2, studentId, interviewer);
        }


    }

    public static boolean checkFirstExist(String interviewer, int studentId) {
        String sql = "select * from firstInterview where studentId = ? AND interviewer = ?";
        try {
            Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, studentId, interviewer);
            if (stringObjectMap == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkSecondExist(String interviewer, int studentId) {
        String sql = "select * from secondInterview where studentId = ? AND interviewer = ?";
        try {
            Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, studentId, interviewer);
            if (stringObjectMap == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static Map<String, Object> getInterview(int studentId) {
        List<Map<String, Object>> firstView = null;
        List<Map<String, Object>> secondView = null;
        try {
            String sql1 = "select * from firstInterview where studentId = ?";
            firstView = jdbcTemplate.queryForList(sql1, studentId);
        } catch (DataAccessException ignored) {
            System.out.println("学号不正确");
        }
        try {
            String sql2 = "select * from secondInterview where studentId = ?";
            secondView = jdbcTemplate.queryForList(sql2, studentId);
        } catch (DataAccessException e) {
            System.out.println("学号不正确");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("firstView", firstView);
        map.put("secondView", secondView);
        return map;
    }
}
