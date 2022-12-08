<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 사진 미리보기기능 구현</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
	<h1>업로드 이미지 미리보기</h1>
	<img src="<%=request.getContextPath() %>/images/parkBogum.jpg"
	 width="200" height="200" style="border-radius:100px;"
	 onclick="fn_upfile();">
	<input type="file" name="upFile" style="display:none">
	<div id="images"></div>
	<script>
		const fn_upfile=()=>{
			$("input[name=upFile]").click();
		}
		$("input[name=upFile]").change(e=>{
			//console.dir(e.target);
			const reader=new FileReader();
			reader.onload=e=>{
				//console.log(e.target.result);	
				const img=$("<img>").attr({
					src:e.target.result,
					width:"200px",
					height:"200px"
				});
				//$("#images").append(img);
				//$("#images").html(img);
				$("img").attr("src",e.target.result);
			}
			reader.readAsDataURL(e.target.files[0]);
		});
	</script>
	
	<h2>다중파일 미리보기</h2>
	<input type="file" name="multiFile" multiple>
	<script>
		$("input[name=multiFile]").change(e=>{
			for(let i=0;i<e.target.files.length;i++){
				let reader=new FileReader();
				reader.onload=e=>{
					let img=$("<img>").attr({
						src:e.target.result,
						width:"200px",
						height:"200px"
					});
					$("#images").append(img);
				}
				reader.readAsDataURL(e.target.files[i]);
			}
		});
	</script>
	
	<h2>ajax를 이용한 파일 업로드 하기</h2>
	<p>
		js가 제공하는 Formdata객체를 이용해서 처리
		form태그와 유사하다. 서버에 보낼 데이터를 key, value형식으로 저장하고
		ajax의 data에 값으로 전달
	</p>
	<input type="file" id="upFile" multiple>
	<button id="sendFile">파일 업로드</button>
	<script>
		$("#sendFile").click(e=>{
			let form=new FormData();
			//append()메소드를 이용해서 원하는 데이터를 저장
			const files=$("#upFile")[0].files;
			/* console.log(files); */
			$.each(files,(i,v)=>{
				form.append("upfile"+i,v);
			});
			form.append("name","유병승");
			$.ajax({
				url:"<%=request.getContextPath()%>/fileUpload",
				data:form,
				type:"post",
				processData:false,
				contentType:false,
				success:e=>{
					alert("파일업로드 성공");
					$("#upFile").val("");
				},error:(r,m,e)=>{
					alert("업로드 실패 다시시도하세요!");
				}
			});
		});
	</script>
	
	
</body>
</html>