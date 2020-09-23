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

    public static void setFirstWeight(int aspect1, int aspect2, int aspect3, int aspect4){
        try{
            String sql1 = "";
            String sql2 = "";
            String sql3 = "";
            String sql4 = "";
//todo 修改每一个
        }catch (DataAccessException e){

        }
    }
}
