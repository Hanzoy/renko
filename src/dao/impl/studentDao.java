package dao.impl;

import dao.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
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

    public static List<Map<String,Object>> getAllStudentsSomeAspects(){
        try{
            String sql = "select studentId, name, class, time1, time2, room  from students";
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
            return map;
        }catch (DataAccessException e){
            return null;
        }
    }

//    public static List<Map<String, Object>> getAllStudentsInformation(){
//        try{
//            String sql = "select * from students";
//            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
//            return map;
//        }catch (DataAccessException e){
//            return null;
//        }
//    }
    public static List<Map<String,Object>> getAllStudentsBySomeKeyWorld(String keyWorld){
        try{
            String sql = "select studentId, name, class, time1, time2  from students where studentId like ? OR name like ?";
            List<Map<String, Object>> map = jdbcTemplate.queryForList(sql, "%"+keyWorld+"%", "%"+keyWorld+"%");
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

    public static double getFirstScores(int studentId){
        try{
            String sql1 = "select AVG(aspect1) from firstInterview where studentId = ?";
            Double aspect1 = jdbcTemplate.queryForObject(sql1, Double.class, studentId);
            String sql2 = "select AVG(aspect2) from firstInterview where studentId = ?";
            Double aspect2 = jdbcTemplate.queryForObject(sql2, Double.class, studentId);
            String sql3 = "select AVG(aspect3) from firstInterview where studentId = ?";
            Double aspect3 = jdbcTemplate.queryForObject(sql3, Double.class, studentId);
            String sql4 = "select AVG(aspect4) from firstInterview where studentId = ?";
            Double aspect4 = jdbcTemplate.queryForObject(sql4, Double.class, studentId);

            String sql5 = "select * from map where thekey = \"aspect1\" OR thekey = \"aspect2\" OR thekey = \"aspect3\" OR thekey = \"aspect4\"";
            List<Map<String, Object>> hashMap = jdbcTemplate.queryForList(sql5);
            if(aspect1 == null || aspect2 == null || aspect3 == null || aspect4 == null || hashMap == null) return 0;
            return aspect1*(new Double(hashMap.get(0).get("thevalue").toString()))+aspect2*(new Double(hashMap.get(1).get("thevalue").toString()))+aspect3*(new Double(hashMap.get(2).get("thevalue").toString()))+aspect4*(new Double(hashMap.get(3).get("thevalue").toString()));
//            return aspect1;
        }catch (DataAccessException e){
            e.printStackTrace();
            System.out.println("错误");
        }
        return -1;
    }
    public static double getSecondScores(int studentId){
        try{
            String sql1 = "select AVG(aspect1) from secondInterview where studentId = ?";
            Double aspect1 = jdbcTemplate.queryForObject(sql1, Double.class, studentId);
            String sql2 = "select AVG(aspect2) from secondInterview where studentId = ?";
            Double aspect2 = jdbcTemplate.queryForObject(sql2, Double.class, studentId);

            String sql5 = "select * from map where thekey = \"aspect5\" OR thekey = \"aspect6\"";
            List<Map<String, Object>> hashMap = jdbcTemplate.queryForList(sql5);


            if(aspect1 == null || aspect2 == null || hashMap == null) return 0;

            return aspect1*(new Double(hashMap.get(0).get("thevalue").toString()))+aspect2*(new Double(hashMap.get(1).get("thevalue").toString()));
//            return aspect1;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void setFirstTime(List<Double> studentIds, String time){
        try{
            String sql = "UPDATE students SET time1 = ? WHERE studentId = ?";
            for(double id : studentIds){
                jdbcTemplate.update(sql,time,(int)id);
            }
        }catch (DataAccessException e){

        }
    }
    public static void setTSecondTime(List<Double> studentIds, String time){
        try{
            String sql = "UPDATE students SET time2 = ? WHERE studentId = ?";
            for(double id : studentIds){
                jdbcTemplate.update(sql,time,(int)id);
            }
        }catch (DataAccessException e){

        }
    }

    public static void setStudentsRoom(List<Double> studentIds, int room){
        try{
            String sql = "UPDATE students SET room = ? WHERE studentId = ?";
            for(double id : studentIds){
                jdbcTemplate.update(sql,room,(int)id);
            }
        }catch (DataAccessException e){

        }
    }

    public static void addStudent(int studentId, String name, String Class){
        try{
            String sql = "insert into students(studentId, name, Class, status) values(?,?,?,?)";
            jdbcTemplate.update(sql,studentId,name,Class,0);
        }catch (DataAccessException e){

        }
    }
}
