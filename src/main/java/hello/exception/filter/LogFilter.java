package hello.exception.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/*
Dispatcher Type 종류
 Request : 클라이언트 요청
 error : 오류 요청
 forward : MVC에서 배웠던 서블릿에서 다른 서블릿이나 JSP를 호출할 때
 Include : 서블릿에서 다른 서블릿이나 JSP의 결과를 포함할 때
 Async : 서블릿 비동기 호출
 */

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}][{}]", uuid, request.getDispatcherType(), requestURI);
            chain.doFilter(request, response); //필터나 서블릿으로 이동하게 함
        } catch (Exception e) {
            log.info("EXCEPTION : {}", e.getMessage());
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}][{}]", uuid, request.getDispatcherType(), requestURI);

        }
    }



    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
