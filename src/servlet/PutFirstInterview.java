package servlet;

import bean.admin;
import dao.impl.adminDao;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/putFirstInterview")
public class PutFirstInterview extends HttpServlet {
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
                    String studentId = jsonObject.getString("studentId");
                    String aspect1 = jsonObject.getString("aspect1");
                    String aspect2 = jsonObject.getString("aspect2");
                    String aspect3 = jsonObject.getString("aspect3");
                    String aspect4 = jsonObject.getString("aspect4");
                    List<String> tag1 = Utils.jsonToList(jsonObject.getJSONArray("tag1"));
                    List<String> tag2 = Utils.jsonToList(jsonObject.getJSONArray("tag2"));
                    List<String> tag3 = Utils.jsonToList(jsonObject.getJSONArray("tag3"));
                    List<String> tag4 = Utils.jsonToList(jsonObject.getJSONArray("tag4"));
                    String task = jsonObject.getString("task");
                    String comment = jsonObject.getString("comment");

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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
