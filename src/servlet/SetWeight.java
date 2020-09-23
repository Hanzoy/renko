package servlet;

import bean.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.impl.adminDao;
import dao.impl.tagDao;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/setWeight")
public class SetWeight extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request,response);
        HashMap<String,Object> map = new HashMap<>();

        String uuid = request.getParameter("uuid");
        String interview = request.getParameter("interview");
        if(uuid != null && interview != null){
            if(new Integer(interview).equals(1)){
                String aspect1 = request.getParameter("aspect1");
                String aspect2 = request.getParameter("aspect2");
                String aspect3 = request.getParameter("aspect3");
                String aspect4 = request.getParameter("aspect4");
            }
            admin ad = admin.cipherTextToUser(uuid);
            if(ad != null && adminDao.login(ad)){

            }else{
                map.put("status",1);
                map.put("msg","验证失败");
            }
        }else{
            map.put("status",2);
            map.put("msg","参数不全");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
