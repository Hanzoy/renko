package servlet;

import bean.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.adminDao;
import dao.impl.otherDao;
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

@WebServlet("/setRoom")
public class SetRoom extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request,response);
        HashMap<String,Object> map = new HashMap<>();

        String jsonString = Utils.getJsonString(request);
        JSONObject jsonObject = new JSONObject(jsonString);

        String uuid = jsonObject.getString("uuid");

        if(uuid!=null){
            admin ad = admin.cipherTextToUser(uuid);
            if(ad != null){
                if(adminDao.login(ad)){
                    List<Double> studentIds = Utils.jsonToList(jsonObject.getJSONArray("studentId"));
                    int room = new Integer(jsonObject.get("room").toString()) ;
                    studentDao.setStudentsRoom(studentIds,room);
                    map.put("status", 0);
                    map.put("msg", "提交成功");
                }else{
                    map.put("status", 1);
                    map.put("msg", "验证失败");
                }
            }else{
                map.put("status", 2);
                map.put("msg", "参数缺失");
            }
        }else{
            map.put("status", 2);
            map.put("msg", "参数缺失");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
