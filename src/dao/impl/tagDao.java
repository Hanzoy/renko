package dao.impl;

import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class tagDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static int addTag(String msg, int tags){
        String sql1 = "select tagId from tagku where msg = ?";
        try{

            Integer tagId = jdbcTemplate.queryForObject(sql1,Integer.class,msg);
            if(tagId != null){
                return tagId;
            }
        }catch (DataAccessException e){

        }
        String sql = "insert into tagku( msg, tags) values(?,?)";
        jdbcTemplate.update(sql, msg, tags);
        Integer tagId = jdbcTemplate.queryForObject(sql1,Integer.class,msg);
        return tagId;
    }
}
