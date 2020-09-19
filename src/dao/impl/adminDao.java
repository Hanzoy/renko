package dao.impl;

import bean.admin;
import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class adminDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static boolean login(admin ad){
        try{
            String sql = "select * from admin where name = ? AND password = ?";
            admin theAd = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(admin.class), ad.getName(), ad.getPassword());
            if(theAd != null)return true;
            else return false;
        }catch (DataAccessException e){
            return false;
        }
    }
}
