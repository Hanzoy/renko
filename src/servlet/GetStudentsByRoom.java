package servlet;

import bean.admin;
import dao.impl.adminDao;
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

@WebServlet("/getStudentsByRoom")
public class GetStudentsByRoom extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request,response);
        HashMap<String,Object> map = new HashMap<>();

        String uuid = request.getParameter("uuid");
        admin ad = bean.admin.cipherTextToUser(uuid);

        if(adminDao.login(ad)){
            List<Map<String,Object>> studentsMap = studentDao.getAllStudents();
        }else{
            map.put("status",1);
            map.put("msg","验证失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
