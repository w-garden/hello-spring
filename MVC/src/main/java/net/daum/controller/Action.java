package net.daum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public abstract ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//추상메서드는 {} , 실행문장 X, 호출 X, public abstract은 생략가능
}
