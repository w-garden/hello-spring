package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/response-header
 */
@WebServlet(name="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //[status-line]
        resp.setStatus(HttpServletResponse.SC_OK); //상수로 지정된 값 불러옴 (200)

        //[response-headers]
        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello"); //임의의 헤더 만들기



        //[Header 편의 메서드]
        content(resp);
        cookie(resp);
        redirect(resp);

        //[message body]
        PrintWriter writer = resp.getWriter();
        writer.println("ok");
    }


    private void content(HttpServletResponse response) {
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        //위 코드를 아래 코드로 풀어서 작성 가능

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }
    private void cookie(HttpServletResponse response) {
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        //위 코드를 아래 코드로 풀어서 작성가능
        Cookie cookie =new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }
    private void redirect(HttpServletResponse response) throws IOException {

        /*
        response.setStatus(HttpServletResponse.SC_OK); //302
        response.setHeader("Location", "/basic/hello-form.html");
         */

        //위 코드를 아래 코드로 풀어서 작성 가능
        response.sendRedirect("/basic/hello-form.html");

    }
}
