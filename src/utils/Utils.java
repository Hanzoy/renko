package utils;

import com.sun.javaws.IconUtil;
import dao.impl.otherDao;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Utils {

    /**
     * 规范request 与 response的默认设置
     * @param request
     * @param response
     */
    public static void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {

        }
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");

        String origin = request.getHeader("Origin");
//            // 添加白名单 要匹配到请求域名+端口 注意端口号后无斜杠
//        response.setHeader("Access-Control-Allow-Origin",origin);
            // 跨Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
//
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods","*");

        System.out.println(origin);
    }

    /**
     * 直接获取request中的实体对象
     * @param t 返回的对象
     * @param request
     * @param <T> 需要获取的对象
     * @throws IOException
     * @throws ServletException
     */
    public static <T> void getRequest(T t, HttpServletRequest request) throws IOException, ServletException {

        Map<String, String[]> requestMap = request.getParameterMap();
        try {
            BeanUtils.populate(t, requestMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int getDateByFormat(String format){
        SimpleDateFormat sdf = new SimpleDateFormat();
        if("yyyy".equals(format)||"MM".equals(format)||"dd".equals(format)){
            sdf.applyPattern(format);
            return new Integer(sdf.format(new Date()));
        }else {
            return -1;
        }
    }

    public int getDateByFormat(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat();
        if("yyyy".equals(format)||"MM".equals(format)||"dd".equals(format)){
            sdf.applyPattern(format);
            return new Integer(sdf.format(date));
        }else {
            return -1;
        }
    }

    public static boolean isFirstInterview(){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date date = new Date();
        try {
            return date.before(sdf.parse(otherDao.queryForOther("time")));
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getJsonString(HttpServletRequest request) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(
                new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        return sb.toString();
    }

    public static String getJson(HttpServletRequest request, String string){

        try {
            JSONObject jsonObject = new JSONObject(getJsonString(request));
            return jsonObject.getString(string);
        } catch (IOException e) {
            System.out.println("获取json字符串错误");
        }
        return null;
    }

    public static String jsonFindString(String json,String string){
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString(string);
    }

    public static String encryptAgain(String s){
        return s.replace('+','#');
    }
    public static String decryptAgain(String s){
        return s.replace('#','+');
    }

    public static List<Double> jsonToList(JSONArray jsonArray){
        List<Double> list = new ArrayList<>();
        for(Object o: jsonArray){
            list.add(Double.valueOf(o.toString()));
        }
        return list;
    }
}
