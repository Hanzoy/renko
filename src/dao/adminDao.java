package dao;

import bean.admin;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import utils.DESUtils;

import java.io.IOException;
import java.util.Base64;

import static utils.DESUtils.des3DecodeECB;

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
