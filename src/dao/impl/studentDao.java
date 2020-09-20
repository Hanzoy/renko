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

    public static List<Map<String,Object>> getStudentsByRoom(String room){
        try{
            String sql = "select * from students where room = ?";
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql,room);
            return map;
        }catch (DataAccessException e){
            return null;
        }
    }

    public static List<Map<String,Object>> getStudentsByRoom(String room,int status){
        try{
            String sql = "select * from students where room = ? AND status = ?";
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql,room,status);
            return map;
        }catch (DataAccessException e){
            return null;
        }
    }
}
