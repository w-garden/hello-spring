package hello.upload.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }
    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws IOException, ServletException {

        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName = {}", itemName);

        Collection<Part> parts = request.getParts();
        //multipart/form-data 전송방식에서 각각 나누어진 부분을 받아서 확인할 수 있다.
        log.info("parts={}", parts);

        return "upload-form";
    }

}
