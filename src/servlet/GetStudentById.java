package servlet;

import bean.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.adminDao;
import dao.impl.interviewDao;
import dao.impl.studentDao;
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

@WebServlet("/getStudentById")
public class GetStudentById extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request,response);
        HashMap<String,Object> map = new HashMap<>();

        String uuid = request.getParameter("uuid");
        int studentId =new Integer(request.getParameter("studentId"));
        if(uuid != null){
            admin ad = admin.cipherTextToUser(uuid);
            if(ad != null && adminDao.login(ad)){
                List<Map<String, Object>> interview = interviewDao.getInterview(studentId);
                map.put("status",0);
                map.put("data",interview);
                map.put("score1",studentDao.getFirstScores(studentId));
                map.put("score2",studentDao.getSecondScores(studentId));
            }else{
                map.put("status", 1);
                map.put("msg","验证失败");
            }
        }else{
            map.put("status", 2);
            map.put("msg","参数不全");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}