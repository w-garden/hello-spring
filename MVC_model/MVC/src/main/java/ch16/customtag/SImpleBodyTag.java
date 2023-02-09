package ch16.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SImpleBodyTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		
		try {
			JspFragment fragment = getJspBody(); 
			//getJspBody() 메서드를 사용해서 커스텀 태그 내용을 처리하는 JspFragment 객체를 반환한다. 
			
			fragment.invoke(null); //현재 태그내용을 전달한다.
			
		
		}catch(Exception e) {e.printStackTrace();}
		
	}
	
}
