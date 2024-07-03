<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항이당당</title>
<style>
	#content {
		width : 800px;
		height : auto;
		margin : auto;
	}

	#outerDiv{
		width : 800px;
		height : 400px;
		padding-top : 50px;
	}

	.noticeEl {
		width: 100%;
		height : 60px;
		margin: auto;
		line-height: 60px;
		text-align: center;
	}

	.noticeEl > div {
		display: inline-block;
	}

	#title{
		margin-top : 100px;
		text-align: center;
	}

	#detail{
		background-color:#23C293; 
		width:800px; 
		margin: auto;
		text-align:center;
		color : white;
		height : 150px;
		display: none;
	}
	
	#detail > div{
		height : 50px;
		line-height: 50px;
		border : 1px solid rgba(255, 255, 255, 0.656);
	}
	
	.rating__input {
	display: none; /* 라디오버튼 hide */
	}

	.rating__label .star-icon {
	width: 24px;
	height: 24px;
	display: block;
	background-image: url("../images/ico-star-empty.svg");
	background-repeat: no-repeat;
}

.rating__label--full .star-icon {
	background-position: right;
}
.rating__label--half .star-icon {
	background-position: left;
}
	

</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp" />
	
	<div id="content">
		<h1 id="title">공지사항 게시판입니다!</h1>
		<button class="btn btn-danger btn-sm" data-toggle="modal" href="#noticeModal">글작성</button>

	</div>	
	
	<div id="detail">
		<!-- 전체조회, 수정, 삭제, 상세보기 다 여기에 만듦 -->
		
		
		
		
	</div>
	
	<jsp:include page="../common/footer.jsp" />

	<div class="modal fade" id="noticeModal">
		<div class="modal-dialog">
		  <div class="modal-content">
	
			<div class="card">
			  <div class="card-header text-white" style="background-color: #ff52a0;">게시글 작성하기</div>
			  <div class="card-body">       
				
				  <div class="form-group">
					<label>작성자</label>
					<input type="text" class="form-control" id='noticeWriter' value="${sessionScope.loginUser.userId }" readonly>
				  </div>
				  
				  <div class="form-group">
					<label>제목</label>
					<input type="text" class="form-control" id='noticeTitle' value="">
				  </div>
		
				  <div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="5" id='noticeContent' style="resize: none;"></textarea>
				  </div>
				 
				  <a class="btn" data-dismiss="modal"
			  style="background-color: #ff52a0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">닫기</a>&nbsp;&nbsp;
					  <a class="btn" onclick="insert();"
				  style="background-color: red; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">작성하기</a>&nbsp;&nbsp;
				   
			  </div>
			</div>
		  </div>
		</div>
	</div>

	<div class="modal fade" id="updateModal">
		<div class="modal-dialog">
		  <div class="modal-content">
	
			<div class="card">
			  <div class="card-header text-white" style="background-color: #ff52a0;">게시글 수정하기</div>
			  <div class="card-body">       
				
					<input type="hidden" value="" id="updateNo"/>
				  <div class="form-group">
					<label>작성자</label>
					<input type="text" class="form-control" id='updateWriter' value="" readonly>
				  </div>
				  
				  <div class="form-group">
					<label>제목</label>
					<input type="text" class="form-control" id='updateTitle' value="">
				  </div>
		
				  <div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="5" id='updateContent' style="resize: none;"></textarea>
				  </div>
				 
				  <a class="btn" data-dismiss="modal"
			  style="background-color: #ff52a0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">닫기</a>&nbsp;&nbsp;
					  <a class="btn" onclick="update();"
				  style="background-color: orange; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">수정하기</a>&nbsp;&nbsp;
				   
			  </div>
			</div>
		  </div>
		</div>
	</div>
	
	
	<script>
		
		
		
		window.onload =()=>{
			findAll();
		}
		
		
		function update(){
			const updateData ={
					"noticeNo" : $('#updateNo').val(),  //key, value
					"noticeTitle" : $('#updateTitle').val(),
					"noticeWriter" : $('#updateWriter').val(),
					"noticeContent" : $('#updateContent').val()  //이걸 body영역에 포함시켜서 보내야함
			};
			
			$.ajax({
				url : "notice",
				type: "put",
				data : JSON.stringify(updateData),
				contentType : 'application/json',
				success : result => {
					console.log(result);
					
					if(result.data === 1){
						$('#outerDiv').remove();
						$('#detail').slideUp(2000);
						findAll();
						
						
					}
				}
				
			});
			
		};
		
		
		function insert(){
			 
			
			//이런식으로 객ㅊ로 담아서 보낸다
			const requestData ={
					"noticeTitle" : $('#noticeTitle').val(),  //key, value
					"noticeWriter" : $('#noticeWriter').val(),
					"noticeContent" : $('#noticeContent').val()
			};
			
			console.log(requestData);
			
			//post로 controller에 넘김
			//문제 - 컨트롤러에서 notice로 어떻게 가공할거냐
			$.ajax({
				url:'notice',
				type:'post',
				data : requestData,
				success : response => {
					
					console.log(response);
					
					if(response.message ==='서비스요청 성공'){
						$('#outerDiv').remove();
						findAll();
						$('#noticeTitle').val('');
						$('#noticeContent').val('');
					};
				}
				
			});
			
		};
		
		function deleteById(noticeNo){
			
			$.ajax({
				url: 'notice/' +noticeNo,
				type: 'delete',
				success : response =>{
					
					console.log(response);
				}
				
				
			});
		};
		
		
		$('#content').on('click','.noticeEl', e=>{
			//console.log(e);  콘솔에 찍어보면 currentTarget 있음! 
			console.log(e);
			const noticeNo = $(e.currentTarget).children().eq(0).text();  //currentTarget의 자식 요소-children() 중 첫번째 친구의-eq(0) innertext속성 값-text()
			
			$.ajax({
				
				url:'notice/'+noticeNo,
				type:'get',
				success : response => {
					console.log(response);
					
					const notice = response.data;  //데이터만 쏙 빼냄
					console.log(notice);
					
					const contentValue = '<div id="notice-detail">'
											+ '<div>' + notice.noticeTitle  +'</div>'
											+ '<div>' + notice.noticeContent  +'</div>'
											+ '<div>' +
											+ '<a class="btn btn-sm btn-warning" data=toggle="model" href="#updateModel">'
											+ '수정하기'
											+ '</a> | '
											+ '<a class="btn btn-sm btn-secondary" onclick = "delectById('+notice.noticeNo+')">삭제하기</a>'
											+ '</div>'
											+ '</div>';
							$('#updateNo').val(notice.noticeNo);
							$('#updateTitle').val(notice.noticeTitle);
							$('#updateContent').val(notice.noticeContent);
							$('#updateWriter').val(notice.noticeWriter);
							
											
							$('#detail').html(contentValue);
						
							$('#detail').slideDown(500);
				}
			
			});
		});
				
		
		const findAll =()=>{
			$.ajax({
				url : 'notice',
				type: 'get',
				success: response=>{
					//console.log(response);
					
					const noticeList = response.data;
					console.log(noticeList);
					const outerDiv = document.createElement('div');//바깥쪽을 감쌀 큰 div
					outerDiv.id = 'outerDiv';
					
					//console.log(outerDiv);
					
					//배열에 사용하는 메서드
					//조회된 notice개수만큼 반복하면서 div만들것 - 이번엔 map 사용해보자
					//map은 배열을 계속 돌면서 요소 하나하나에 접근을 하고 그걸 매개변수로 담아서 사용 가능함
					noticeList.map( o =>{
						
						
						console.log(o);
						
						//공지사항을 담을 한 행
						const noticeEl = document.createElement('div');
						noticeEl.className = 'noticeEl';
						
						/*
						noticeEl.appendChild(createDiv());
						
						
						
						//공지사항 번호 div
						const numEl = document.createElement('div');  //o를 통해서 noticenumber에 접근하면 notice번호 얻을 수 있음
						const numText = document.createTextNode(o.noticeNo);   //텍스트 노드에 쓰여지는 문자열
						numEl.style.width = '50px';
						//console.log(numEl);
						//console.log(numText);
						numEl.appendChild(numText);
						//console.log(numEl);   //이렇게하면 공지 번호만 나오게할 수 있음
						noticeEl.appendChild(numEl);
						
						const titleEl = document.createElement('div');
						const titleText = document.createElement(o.noticeTitle);
						titleEl.style.width = '400px';
						titleEl.appendChild(titleText);
						noticeEl.appendChild(titleEl);
						
						const writerEl = document.createElement('div');
						const writerText = document.createTextNode(o.noticeWriter);
						wirterEl.style.width='150px';
						wirterEl.appendChild(writerText);
						noticeEl.appendChild(writerEl);
						
						const dateEl = document.createElement('div');
						const dateText = document.createTextNode(o.createDate);
						dateEl.style.width= '200px';
						dateEl.appendChild(dateText);
						noticeEl.appendChild(dateEl)
						*/
						
						noticeEl.appendChild(createDiv(o.noticeNo, '50px'));
						noticeEl.appendChild(createDiv(o.noticeTitle, '400px'));
						noticeEl.appendChild(createDiv(o.noticeWriter, '150px'));
						noticeEl.appendChild(createDiv(o.createDate, '200px'));
						
						//console.log(noticeEl);
						outerDiv.appendChild(noticeEl);
						
					});
					document.getElementById('content').appendChild(outerDiv);
				}
			});
		}
		
		
		function createDiv(data, style){
			
			const divEl = document.createElement('div');
			const divText = document.createTextNode(data);
			divEl.style.width= style;
			divEl.appendChild(divText);
			
			return divEl;
		}
	
	
		
	
	</script>
	

</body>
</html>