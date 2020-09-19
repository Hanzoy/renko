package dao.impl;

import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class studentDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static List<Map<String,Object>> getAllStudents(){
        try{
            String sql = "select * from students";
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
            return map;
        }catch (DataAccessException e){
            return null;
        }
    }
}
