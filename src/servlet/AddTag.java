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

@WebServlet("/addTag")
public class AddTag extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.setRequestAndResponse(request,response);
        HashMap<String,Object> map = new HashMap<>();

        String uuid = request.getParameter("uuid");
        String aspect = request.getParameter("aspect");
        String tag = request.getParameter("tag");
        if(uuid != null && aspect != null && tag != null){
            admin ad = admin.cipherTextToUser(uuid);
            if(ad != null && adminDao.login(ad)){
                int tagId = tagDao.addTag(tag, new Integer(aspect));
                map.put("status",0);
                map.put("tagId",tagId);
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
