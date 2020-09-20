package dao.impl;

import dao.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Utils;

import java.util.List;

public class interviewDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static void addInterviewOne(String interviewer, String studentId, String aspect1, String aspect2, String aspect3, String aspect4, List<String> tag1, List<String> tag2, List<String> tag3, List<String> tag4, String task, String comment) {
        String sql = "insert into firstInterview(studentId,interviewer,aspect1,aspect2,aspect3,aspect4,task,comment) values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,studentId,interviewer,aspect1,aspect2,aspect3,aspect4,task,comment);
        String sql2 = "insert into tag(studentId,id,tags) values(studentId,id,tags)";
        for(String s: tag1){
            jdbcTemplate.update(sql2,)
        }
    }
}
