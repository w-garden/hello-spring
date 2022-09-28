package net.daum.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	@GetMapping("uploadForm") //get으로 접근하는 매핑주소처리. uploadForm URL 매핑주소 등록
	public void uploadForm() {
		//리턴타입이 없는 void형이면 URL매핑주소인 uploadForm 뷰페이지 파일명이 된다. 뷰페이지 경로는 (뷰리졸브 경로)는 /WEB-INF/views/uploadForm.jsp가 된다.
	}//uploadForm.jsp
	
	
	
	
	@PostMapping("uploadFormAction") //post방식으로 접근하는 매핑주소 처리. uploadFormAction 매핑주소 등록
	public void uploadFormAction(MultipartFile[] uploadFile) {
		/* 스프링 api의 MultipartFile 타입을 사용해서 업로드 되는 파일을 쉽게 처리한다.
		 * 다중 업로드 파일은 배열로 받는다. 
		 * <input type="file" name="uploadFile" > 네임파라미터 이름과 MultipartFile[] uploadFile의 전달인자명(매개변수명) 이름이같아야한다.
		 */
		
		String uploadFolder = "C:\\upload"; //이진파일 업로드 경로
		
		for(MultipartFile multipartFile:uploadFile) {
			System.out.println("-------------------------------->");
			System.out.println("Upload FILE Name : "+ multipartFile.getOriginalFilename()); //업로드 원본 파일명
			System.out.println("Upload File Size : " + multipartFile.getSize()); //업로드 파일 크기
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile); // C:\\upload폴더에 업로드 되는 원본 파일명으로 실제 업로드  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//for
	}//uploadFormAction()
	
	//비동기식 아작스 이진파일 업로드폼
	@GetMapping("/uploadAjaxForm")
	public ModelAndView uploadAjaxForm() {
		ModelAndView um = new ModelAndView();
		um.setViewName("uploadAjax"); //뷰리졸브 경로는 /WEB-INF/views/uploadAjax.jsp 가 된다.
		return um; 
	}//uploadAjaxForm()
	
	
	
	@PostMapping("uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		System.out.println("update ajax post........");
		
		String uploadFolder="C:\\upload";
		
		for(MultipartFile multipartFile:uploadFile) {
			System.out.println("-------------------------------->");
			System.out.println("Upload FILE Name : "+ multipartFile.getOriginalFilename()); //업로드 원본 파일명
			System.out.println("Upload File Size : " + multipartFile.getSize()); //업로드 파일 크기
			
			String uploadFileName= multipartFile.getOriginalFilename();
			
			File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile); // C:\\upload폴더에 업로드 되는 원본 파일명으로 실제 업로드  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//for
		
	}//uploadAjaxAction
}
