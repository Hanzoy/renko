package dao.impl;

import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class tagDao {
    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public static int addTag(String msg, int tags){
        String sql1 = "select tagId from tagku where msg = ? AND tags = ?";
        try{

            Integer tagId = jdbcTemplate.queryForObject(sql1, Integer.class, msg, tags);
            if(tagId != null){
                return tagId;
            }
        }catch (DataAccessException e){

        }
        String sql = "insert into tagku( msg, tags) values(?,?)";
        jdbcTemplate.update(sql, msg, tags);
        Integer tagId = jdbcTemplate.queryForObject(sql1, Integer.class, msg, tags);
        return tagId;
    }

    public static List<Map<String,Object>> getTags(String aspect){
        try{
            String sql = "select tagId, msg from tagku where tags = ?";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, aspect);
            return maps;
        }catch (DataAccessException e){
            return null;
        }
    }
}
