package com.filter.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListenerTest 
	implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//서버가 종료됐을 때
		System.out.println("서버가 종료됨");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//서버가 실행됐을 때
		System.out.println("서버가 실행됨");
	}
	
	
}
