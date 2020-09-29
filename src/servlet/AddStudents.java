package servlet;

import bean.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.adminDao;
import dao.impl.interviewDao;
import dao.impl.studentDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/addStudents")
public class AddStudents extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request, response);
        HashMap<String, Object> map = new HashMap<>();

        String jsonString = Utils.getJsonString(request);
        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray stdDataArr = jsonObject.getJSONArray("stdDataArr");
        for(int i=0; i<stdDataArr.length(); i++){
            JSONObject json = stdDataArr.getJSONObject(i);
//            if(json.getString("organizationFirst").equals("学生创新实践中心科技发展部")) {
//                studentDao.addStudent(json.getInt("stdId"), json.getString("stdName"), json.getString("major") + json.getString("classNum"));
//            }else{
//                String second = null;
//                try{
//                    second = json.getString("organizationSecond");
//                }catch (JSONException e){
//
//                }
//                if("学生创新实践中心科技发展部".equals(second)){
//                    studentDao.addStudent(json.getInt("stdId"), json.getString("stdName"), json.getString("major") + json.getString("classNum"));
//                }
//
//            }
            if(json.getString("branchFirst").equals("技术组")){
                studentDao.addStudent(json.getInt("stdId"), json.getString("stdName"), json.getString("major") + json.getString("classNum"));
            }
        }

        map.put("status", 0);
        map.put("msg", "提交成功");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
