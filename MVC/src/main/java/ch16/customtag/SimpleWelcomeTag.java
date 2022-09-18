package ch16.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleWelcomeTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		//doTag()메서드는 커스텀 태그를 만날때마다 실행된다
		try {
			getJspContext().getOut().print("SimpleTag:환영합니다. 커스텀 태그");
			//getJspContext().getOut() 메서드를 만나면 출력스트림 객체 out 을 반환
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		super.doTag();
	}

}
