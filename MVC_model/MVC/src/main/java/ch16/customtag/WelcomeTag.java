package ch16.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class WelcomeTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		//커스텀 태그중에서 시작태그를 만났을 때 진행
		
		try {
			pageContext.getOut().print("Welcome to My 사용자 정의 태그");
			//pageContext.getOut() 출력 스트림 객체 out 반환
		}catch(Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY; //커스텀 태그 내용이 없다.
	}
	
}
