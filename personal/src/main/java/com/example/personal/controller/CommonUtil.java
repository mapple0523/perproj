package com.example.personal.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommonUtil {

    public static void Alert(String message) throws IOException {

        ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletContainer.getResponse();
        response.setCharacterEncoding("EUC-KR"); // 알럿창에서 한글 안깨지게 하려고
        response.setContentType("text/html; charset=EUC-kr");

        PrintWriter writer = response.getWriter();
        StringBuffer sb_alert = new StringBuffer();
        sb_alert.append("script type = \"text/javascript\"");
        sb_alert.append("	alert('"+message+"');");
        sb_alert.append("</script>");

        writer.print(sb_alert);
        writer.flush();

        return;
    }
}
