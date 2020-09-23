package dao.impl;

import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class interviewDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static void addInterviewOne(String interviewer, int studentId, double aspect1, double aspect2, double aspect3, double aspect4, List<Double> tag1, List<Double> tag2, List<Double> tag3, List<Double> tag4, String task, String comment) {
        String sql = "insert into firstInterview(studentId,interviewer,aspect1,aspect2,aspect3,aspect4,task,comment) values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,studentId,interviewer,aspect1,aspect2,aspect3,aspect4,task,comment);
        String sql2 = "insert into tag(studentId,id) values(?,?)";
        for(Double s: tag1){
            jdbcTemplate.update(sql2,studentId,s);
        }
        for(Double s: tag2){
            jdbcTemplate.update(sql2,studentId,s);
        }
        for(Double s: tag3){
            jdbcTemplate.update(sql2,studentId,s);
        }
        for(Double s: tag4){
            jdbcTemplate.update(sql2,studentId,s);
        }
    }

    public static void addInterviewTwo(String interviewer, int studentId, double aspect1, double aspect2, List<Double> tag1, List<Double> tag2, String task, String comment) {
        String sql = "insert into secondInterview(studentId,interviewer,aspect1,aspect2,task,comment) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,studentId,interviewer,aspect1,aspect2,task,comment);
        String sql2 = "insert into tag(studentId,id) values(?,?)";
        for(Double s: tag1){
            jdbcTemplate.update(sql2,studentId,s);
        }
        for(Double s: tag2){
            jdbcTemplate.update(sql2,studentId,s);
        }
    }

    public static List<Map<String,Object>> getInterview(int studentId){
        List<Map<String, Object>> firstView = null;
        List<Map<String, Object>> secondView = null;
        List<Integer> tags = null;
        try {
            String sql1 = "select * from firstInterview where studentId = ?";
            firstView = jdbcTemplate.queryForList(sql1, studentId);
        }catch (DataAccessException ignored) {
        }try {
            String sql2 = "select * from secondInterview where studentId = ?";
            secondView = jdbcTemplate.queryForList(sql2, studentId);
        }catch (DataAccessException e){
        }try {
            String sql3 = "select id from tag where studentId = ?";
            tags = jdbcTemplate.queryForList(sql3, Integer.class, studentId);
        }catch (DataAccessException e) {
        }try{
            List<Integer> tag1 = new ArrayList<>();
            List<Integer> tag2 = new ArrayList<>();
            List<Integer> tag3 = new ArrayList<>();
            List<Integer> tag4 = new ArrayList<>();
            List<Integer> tag5 = new ArrayList<>();
            List<Integer> tag6 = new ArrayList<>();

            for(int s:tags){
                String sql4 = "select tags from tagku where tagId = ?";
                Integer tagKu = jdbcTemplate.queryForObject(sql4, Integer.class, s);
                switch (tagKu){
                    case 1:
                        tag1.add(s);
                        break;
                    case 2:
                        tag2.add(s);
                        break;
                    case 3:
                        tag3.add(s);
                        break;
                    case 4:
                        tag4.add(s);
                        break;
                    case 5:
                        tag5.add(s);
                        break;
                    case 6:
                        tag6.add(s);
                        break;
                }
            }
            firstView.get(0).put("tag1",tag1);
            firstView.get(1).put("tag2",tag2);
            firstView.get(2).put("tag3",tag3);
            firstView.get(3).put("tag4",tag4);
            secondView.get(0).put("tag1",tag5);
            secondView.get(1).put("tag2",tag6);
            return firstView;
        }catch (DataAccessException e){
            System.out.println("学号不正确");
        }
        return null;
    }
}
