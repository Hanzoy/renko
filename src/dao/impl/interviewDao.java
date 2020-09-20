package dao.impl;

import dao.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Utils;

import java.util.List;

public class interviewDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static void addInterviewOne(String interviewer, int studentId, double aspect1, double aspect2, double aspect3, double aspect4, List<Double> tag1, List<Double> tag2, List<Double> tag3, List<Double> tag4, String task, String comment) {
        String sql = "insert into firstInterview(studentId,interviewer,aspect1,aspect2,aspect3,aspect4,task,comment) values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,studentId,interviewer,aspect1,aspect2,aspect3,aspect4,task,comment);
        String sql2 = "insert into tag(studentId,id,tags) values(?,?,?)";
        for(Double s: tag1){
            jdbcTemplate.update(sql2,studentId,s,1);
        }
        for(Double s: tag2){
            jdbcTemplate.update(sql2,studentId,s,2);
        }
        for(Double s: tag3){
            jdbcTemplate.update(sql2,studentId,s,3);
        }
        for(Double s: tag4){
            jdbcTemplate.update(sql2,studentId,s,4);
        }
    }
}
