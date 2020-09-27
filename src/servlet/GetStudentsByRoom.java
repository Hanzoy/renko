package servlet;

import bean.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.adminDao;
import dao.impl.studentDao;
import org.json.JSONObject;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/getStudentsByRoom")
public class GetStudentsByRoom extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request,response);
        HashMap<String,Object> map = new HashMap<>();

        String jsonString = Utils.getJsonString(request);
        JSONObject jsonObject = new JSONObject(jsonString);
        String uuid = jsonObject.getString("uuid");
        String room = jsonObject.getString("room");

        System.out.println(uuid);

        admin ad = null;
        if(uuid == null || room == null){
            map.put("status", 2);
            map.put("msg", "参数不全");
        }else {
            ad = admin.cipherTextToUser(uuid);
        }

        System.out.println(ad);

        if(ad == null && room != null){
            map.put("status", 1);
            map.put("msg", "验证失败");
        }else if(adminDao.login(ad)){
            int interview = 2;
            if(Utils.isFirstInterview())interview = 1;
            map.put("status", 0);
            map.put("interview", interview);
            map.put("interviewed", studentDao.getStudentsByRoom(room, interview));
            map.put("noInterview", studentDao.getStudentsByRoom(room, interview-1));
        }else{
            map.put("status", 1);
            map.put("msg", "验证失败");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
