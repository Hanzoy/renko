package servlet;

import bean.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.adminDao;
import dao.impl.interviewDao;
import dao.impl.studentDao;
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
import java.util.List;

@WebServlet("/putFirstInterview")
public class PutFirstInterview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request, response);
        HashMap<String, Object> map = new HashMap<>();

        String jsonString = Utils.getJsonString(request);
        JSONObject jsonObject = new JSONObject(jsonString);
        String uuid = jsonObject.getString("uuid");
        if (uuid != null) {
            admin ad = admin.cipherTextToUser(uuid);
            if (ad != null) {
                if (adminDao.login(ad)) {
                    int studentId = jsonObject.getInt("studentId");
                    double aspect1 = jsonObject.getDouble("aspect1");
                    double aspect2 = jsonObject.getDouble("aspect2");
                    double aspect3 = jsonObject.getDouble("aspect3");
                    double aspect4 = jsonObject.getDouble("aspect4");
//                    List<Double> tag1 = Utils.jsonToList(jsonObject.getJSONArray("tag1"));
//                    List<Double> tag2 = Utils.jsonToList(jsonObject.getJSONArray("tag2"));
//                    List<Double> tag3 = Utils.jsonToList(jsonObject.getJSONArray("tag3"));
//                    List<Double> tag4 = Utils.jsonToList(jsonObject.getJSONArray("tag4"));
                    String tag1 = "";
                    String tag2 = "";
                    String tag3 = "";
                    String tag4 = "";
                    String task = "";
                    String comment = "";
                    try {
                        tag1 = jsonObject.getString("tag1");
                    }catch (JSONException ignored){
                    }
                    try {
                        tag2 = jsonObject.getString("tag2");
                    }catch (JSONException ignored){
                    }
                    try {
                        tag3 = jsonObject.getString("tag3");
                    }catch (JSONException ignored){
                    }
                    try {
                        tag4 = jsonObject.getString("tag4");
                    }catch (JSONException ignored){
                    }
                    try {
                        task = jsonObject.getString("task");
                    }catch (JSONException ignored){
                    }
                    try {
                        comment = jsonObject.getString("comment");
                    }catch (JSONException ignored){
                    }
                    interviewDao.addInterviewOne(ad.getName(), studentId, aspect1, aspect2, aspect3, aspect4, tag1, tag2, tag3, tag4, task, comment);
                    studentDao.updateScore(studentId);
                    map.put("status", 0);
                    map.put("msg", "提交成功");
                } else {
                    map.put("status", 1);
                    map.put("msg", "验证失败");
                }
            } else {
                map.put("status", 2);
                map.put("msg", "参数缺失");
            }
        } else {

            map.put("status", 2);
            map.put("msg", "参数缺失");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
