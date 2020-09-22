package test;

import dao.JDBCUtils;
import dao.impl.otherDao;
import dao.impl.studentDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class test {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    public static void main(String[] args) {
//        List<Map<String, Object>> studentsByRoom = studentDao.getStudentsByRoom("1", 0);
//        System.out.println(studentsByRoom);
//        String time = otherDao.queryForOther("time");
//        System.out.println(time);

            String sql5 = "select * from map where thekey = \"aspect1\" OR thekey = \"aspect2\" OR thekey = \"aspect3\" OR thekey = \"aspect4\"";
            List<Map<String, Object>> hashMap = jdbcTemplate.queryForList(sql5);
            System.out.println(hashMap);
    }
}
