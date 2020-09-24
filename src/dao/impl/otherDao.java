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

    public static void setFirstWeight(double aspect1, double aspect2, double aspect3, double aspect4){
        try{
            String sql1 = "UPDATE map SET thevalue = ? WHERE thekey = \'aspect1\'";
            String sql2 = "UPDATE map SET thevalue = ? WHERE thekey = \'aspect2\'";
            String sql3 = "UPDATE map SET thevalue = ? WHERE thekey = \'aspect3\'";
            String sql4 = "UPDATE map SET thevalue = ? WHERE thekey = \'aspect4\'";
            jdbcTemplate.update(sql1,aspect1);
            jdbcTemplate.update(sql2,aspect2);
            jdbcTemplate.update(sql3,aspect3);
            jdbcTemplate.update(sql4,aspect4);
        }catch (DataAccessException e){

        }
    }
    public static void setSecondWeight(double aspect1, double aspect2){
        try{
            String sql1 = "UPDATE map SET thevalue = ? WHERE thekey = \'aspect5\'";
            String sql2 = "UPDATE map SET thevalue = ? WHERE thekey = \'aspect6\'";
            jdbcTemplate.update(sql1,aspect1);
            jdbcTemplate.update(sql2,aspect2);
        }catch (DataAccessException e){

        }
    }
}
