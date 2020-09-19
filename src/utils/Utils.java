package utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Utils {
    public static void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");

        String origin = request.getHeader("Origin");
            // 添加白名单 要匹配到请求域名+端口 注意端口号后无斜杠
        response.setHeader("Access-Control-Allow-Origin", origin);
            // 跨Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

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


}
