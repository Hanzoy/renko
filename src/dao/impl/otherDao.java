package dao.impl;

import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class otherDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static String queryForOther(String key){
        try{
            String sql = "SELECT thevalue FROM map WHERE thekey = ?";
            String value = jdbcTemplate.queryForObject(sql,String.class,key);
            return value;
        }catch (DataAccessException e){
            return null;
        }
    }
}
