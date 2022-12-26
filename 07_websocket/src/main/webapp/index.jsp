<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본채팅구현하기</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div id="container">
		보낸사람<input type="text" size="4" id="sender">
		받는사람<input type="text" size="4" id="receiver"><br>
		<input type="text" id="sendMsg" placeholder="전송할 메세지 작성!!">
		<button id="btnSend">전송</button>
	</div>
	<div id="msgcontainer"></div>
	<script>
		//웹소켓 서버에 연결하기
		//주소
		// ws : http프로토콜 이용
		// wss : https프로토콜 이용
		// ws: 서버주소(//localhost:9090)/프로젝트주소/mapping주소)
		const socket=new WebSocket("ws://localhost:9090/<%=request.getContextPath()%>/chatting");
		//서버에 접속이 완료됐을 때 실행되는 함수 등록하기
		socket.onopen=e=>{
			//console.log(e);
			alert("채팅서버 접속!");
		}
		
		//서버에서 보낸 데이터를 처리하는 핸들러를 등록
		socket.onmessage=e=>{
			console.log(e);
			//const data=e.data.split(",");
			//json방식의 문자열을 다시 객체로 만들어주는 함수
			//JSON.parse()
			const data=JSON.parse(e.data);
			console.log(data)
			const div=$("<div>").css("display","flex");
			const msg=$("<h3>").text(data["msg"]);
			const sender=$("<sup>").text(data["sender"]);
			if($("#sender").val()==data[0])
				div.append(sender).append(msg).css("justify-content","right");
			else
				div.append(sender).append(msg).css("justify-content","left");
			$("#msgcontainer").append(div);
			
		}
		$("#btnSend").click(e=>{
			//작성한 메세지를 웹소켓서버에 전송하는 함수
			//1. 작성한 데이터를 가져오기
			//2. send(전송할 데이터)함수를 이용해서 데이터를 서버에 전송
			//3. 보내는 데이터 패턴만들기
			//보낸사람, 메세지 작성!
			
// 			const data=$("#sender").val()+","+$("#receiver").val()+","
// 			+$("#sendMsg").val();
			const data=new Message($("#sender").val(),
					$("#receiver").val()
					,$("#sendMsg").val());
			console.log(data);
			//JSON.stringify객체를 javascript패턴문자열로 변환해주는 함수
			socket.send(JSON.stringify(data));
		});
		class Message{
			constructor(sender, receiver,msg){
				this.sender=sender;
				this.receiver=receiver;
				this.msg=msg;
			}
		}
	</script>

</body>
</html>
