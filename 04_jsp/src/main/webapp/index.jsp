<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 첫 jsp페이지</title>
</head>
<body>
	<h1>나의 첫 jsp페이지다 호호호호!!!!</h1>
	<h2>jsp에 대해 알아보자</h2>
	<p>jsp는 html페이지에 java코드를 jsp태그를 이용해서 넣을 수 있게 만든 페이지</p>
	<p>jsp도 java파일로 변환되어 처리됨</p>
	<h2>jsp태그에 대해 알아보자</h2>
	<ul>
		<li>
			지시자 : <%-- <%@ 태그명 속성설정 %> --%><br>
			page : 페이지에 대한 설정<br>
			include : 다른 jsp페이지를 현페이지로 불러오는 기능<br>
			taglib : 외부 jsp태그 라이브러리를 불러올 때 사용(JSTL이용시 사용)<br>
		</li>
		<li>
			선언문 : <%-- <%! 내용작성(javacode) %> --%> <br>
			자바클래스내부({}안)에 들어가는 코드로 필드(멤버변수)선언,메소드 선언 가능<br>
			->조건문, 반복문은 사용할 수 없다 *잘 사용하지 않음<br>
		</li>
		<li>
			스트립트릿 : <%-- <% 내용작성(javacode) %> --%><br>
			자바의 메소드내부 들어가는 코드를 작성<br>
			지역변수, 조건문, 반복문을 사용할 수 있음 **가장 많이 사용!!
		</li>
		<li>
			표현식 : <%-- <%= 페이지에 출력할 데이터 %> --%><br>
			변수, 메소드의 반환값을 페이지에 출력할 때 사용<br>
			*표현식 내부에서 메소드 호출 시 ;을 작성하지 않는다!
		</li>
	</ul>
	
	<h2>선언문 이용하기</h2>
	<%!
		String memberVal="안녕하세요!";
		public int age=19;
		public String printTest(){
			return "이건 선언문에서 선언한 메소드";
		}
		private static int count=10;
		//선언문 내부에서 조건문, 반복문을 사용할 수 없다
		/* 
			if(age>20){} 
			for(int i=0;i<10;i++){}
		*/
	%>
	<h3>선언문에서 선언한 변수, 메소드 출력하기</h3>
	<ul>
		<li>memberVal : <%=memberVal%></li>
		<li>age : <%=age%></li>
		<li>count : <%=count %></li>
		<li>printTest() : <%=printTest() %></li>
	</ul>
	
	<h2>스크립트릿 이용하기</h2>
	<%
		//메소드 내부에 선언되는 자바구문을 작성할 때 사용
		//jsp를 작성할 때 가장 많이 사용
		//지역변수, 메소드호출, 조건문, 반복문 등 사용가능
		String msg="이제 곧 점심시간!";
		//private double weight=65.5; 불가능!
		double data=Math.random();
		int[] number=new int[10];
		for(int i=0;i<10;i++){
			number[i]=(int)(Math.random()*100);
		}
	%>
	<h3><%=msg %></h3>
	<h3>data : <%=data %></h3>
	<%for(int n:number){%>
		<p><%=n %></p>
	<%} %>
	<%
		String[] names={"유병승","홍나리","임연지","이동훈","조장환"};
	%>
	<h3>학생이름출력하기</h3>
	<ul>
		<%for(int i=0;i<names.length;i++){ %>
			<li <%=i%2==0?"style='background-color:lime;'":"" %>>
				<%=names[i] %>
			</li>
		<%} %>
	</ul>
	
	<%
		String[] hobby={"운동","독서","코딩","수면","영화"};
	%>
	<h2>취미선택</h2>
	<%for(String h:hobby){ %>
		<label><input type="checkbox" name="hobby"
		<%=h.equals("운동")?"checked":"" %>><%=h %></label>
	<%} %>
	
	<h2>jsp내장객체에 대해 알아보자</h2>
	<P>
		서블릿에서 데이터를 저장했던 객체를 의미함 <br>
		응답객체까지...-> jspservice()메소드의 지역변수, 매개변수로 선언되어 있어
						생성 없이 이용이 가능(스트립트릿 안에서) <br>
		session == HttpSession <br>
		application == ServletContext <br>
		request == HttpServletRequest <br>
		response == HttpServletResponse <br>
	</P>
	<%
		out.print(session);
		out.print(application);
		out.print(request);
		out.print(response);
	%>
	
	<h2><%=request.getContextPath() %></h2>
	
	<h3><a href="views/datatest.jsp">내장객체에 데이터 저장하기</a></h3>
	
	<h3>지시자태그 활용하기</h3>
	<h4>include태그 이용하기</h4>
	<p>
		include태그는 다른 jsp내용을 합쳐서 출력하는것<br>
		공통화면을 페이지에 넣을 때 사용(header, footer, aside)
	</p>
	<h3><a href="views/main.jsp">메인화면으로</a></h3>
	
	<h2>에러페이지 등록하기</h2>
	<h3><a href="views/errortest.jsp">에러페이지테스트</a></h3>
	
	<h2><a href="<%=request.getContextPath() %>/searchmemberall.do">전체 회원 조회하기</a></h2>
	
	<h2>조건으로 회원조회하기</h2>
	<form action="<%=request.getContextPath() %>/searchmembername.do" method="get">
		<input type="text" name="keyword" placeholder="이름으로 검색">
		<input type="submit" value="조회">
	</form>
	
	
	
	
	
	
</body>
</html>