package hello.boot;

import hello.servlet.HelloServlet;
import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.util.List;

public class MySpringApplication {
    public static void run(Class configClass, String[] args){
        System.out.println("MySpringApplication.main args="+ List.of(args));

        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        tomcat.setPort(7070);
        tomcat.setConnector(connector);

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(configClass);

        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        Context context = tomcat.addContext("", "/");

        //== 코드 추가 시작==
        File docBaseFile = new File(context.getDocBase());
        if (!docBaseFile.isAbsolute()) {
            docBaseFile = new File(((org.apache.catalina.Host) context.getParent()).getAppBaseFile(), docBaseFile.getPath());
        }
        docBaseFile.mkdirs();

        //== 코드 추가 종료==

        tomcat.addServlet("", "dispatcher", dispatcher);
        context.addServletMappingDecoded("/", "dispatcher");

        try {
            tomcat.start();
        }catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
