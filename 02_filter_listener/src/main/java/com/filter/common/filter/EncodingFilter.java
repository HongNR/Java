package com.filter.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//일반클래스를 필터로 만들려면 Filter인터페이스를 구현
public class EncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("필터실행!!");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//wrapper클래스 적용하기
//		HttpServletRequestTest hrt=
//				new HttpServletRequestTest((HttpServletRequest)request);
		
		
		chain.doFilter(request, response);//다음 서블릿이나 필터가 실행
	}
	
}
