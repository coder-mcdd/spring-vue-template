package jzxy.mcdd.backend.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * HTTPUtils
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/31 14:21
 */
@Component
public class HTTPUtils {

    public void responseByPrintWriter(HttpServletResponse response,String message) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(message);
    }
}
