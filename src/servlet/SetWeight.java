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
            String aspect1 = null;
            String aspect2 = null;
            String aspect3 = null;
            String aspect4 = null;
            boolean hasEnoughParameter = true;
            if(new Integer(interview).equals(1)){
                aspect1 = request.getParameter("aspect1");
                aspect2 = request.getParameter("aspect2");
                aspect3 = request.getParameter("aspect3");
                aspect4 = request.getParameter("aspect4");
                if(aspect1 == null ||aspect2 == null ||aspect3 == null ||aspect4 == null){
                    hasEnoughParameter = false;
                }
            }else if(new Integer(interview).equals(2)){
                aspect1 = request.getParameter("aspect1");
                aspect2 = request.getParameter("aspect2");
                if(aspect1 == null ||aspect2 == null){
                    hasEnoughParameter = false;
                }
            }
            if(hasEnoughParameter){
                admin ad = admin.cipherTextToUser(uuid);
                if(ad != null && adminDao.login(ad)){
                    int coo = new Integer(interview);
                    if(coo == 1){
                        if(new Integer(aspect1) + new Integer(aspect2) + new Integer(aspect3) + new Integer(aspect4) == 1){

                        }else{
                            map.put("status",3);
                            map.put("msg","参数不合法");
                        }
                    }else if(coo == 2){
                        if(new Integer(aspect1) + new Integer(aspect2) == 1){

                        }else{
                            map.put("status",3);
                            map.put("msg","参数不合法");
                        }
                    }
                }else{
                    map.put("status",1);
                    map.put("msg","验证失败");
                }
            }else{
                map.put("status",2);
                map.put("msg","参数不全");
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
