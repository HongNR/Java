package com.websocket.controller;

import java.io.IOException;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatting")
public class ChattingServer {
	
	//private List<Session> client=new ArrayList();
	
	
	@OnOpen
	public void open(Session session, EndpointConfig config) {
		System.out.println("클라이언트접속!!");
		//client.add(session);
	}
	@OnMessage
	public void messageManage(Session session,String data) {
		System.out.println("메세지 수신!");
		System.out.println(data);
		System.out.println(session.getId());
		
		//전송된 메세지를 접속한 클라이언트에게 전송해주기
		//session객체가 제공하는 getBasicRemote()메소드를 이용해서
		//전송객체를 가져와 sendText("데이터")메소드를 실행
		try {
			//접속한 session을 모두 가져오는 메소드 제공
			//getOpenSessions() -> Set<Session>
			Set<Session> clients=session.getOpenSessions();
			for(Session client:clients) {
				client.getBasicRemote().sendText(data);
			}
			//session.getBasicRemote().sendText(data);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
