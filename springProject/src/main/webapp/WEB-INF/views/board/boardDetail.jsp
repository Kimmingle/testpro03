<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style> 
        .content {
            background-color:rgb(247, 245, 245);
            width:80%;
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${board.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${board.boardWriter }</td>
                    <th>작성일</th>
                    <td>${board.createDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <c:choose>
	                    <c:when test="${empty board.originName }" >
	                    	<td colspan="3" >
	                    	글 없음
	                    	</td>
	                    </c:when>
                    
	                    <c:otherwise>
	                    <td>
	                    	<a href="${board.changeName }" download="${board.originName }">${board.originName }</a>
	                    </td>
	                    </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${board.boardContent }</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
            
            	<c:if test="${sesseionScope.loginUser.userId eq requestScope.board.boardWriter }">
                	<!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                    <!-- a태그에 herf로 적었다? ->get방식 매핑이다(즉 url를 알고있는 클라이언트가 맘대로 삭제할 수 있다.) -->
                
               		<!-- 
	                <form action= "board-update.do" method="post">
	                	<input type= "hidden" name="boardNo" value="${board.boardNo }">
	                	<a class="btn btn-primary">수정하기</a>
	                </form>
	                <form action= "board-delete.do" method="post">
	                	<input type= "hidden" name="boardNo" value="${board.boardNo }">
	                </form>
	                 -->
                	<!-- 쓰고보니 자바스크립트가 나음 -->
                	
                	
                	 </c:if>
                <a class="btn btn-primary" onclick="postSubmit(this.innerHTML)">수정하기</a>
                <a class="btn btn-danger" onclick="postSubmit(this.innerHTML)">삭제하기</a>
                
                
                
                
                
                <form method="post" action="" id="postForm">
                	<input type= "hidden" name="boardNo" value="${board.boardNo }">
                	<input type= "hidden" name="filePath" value="${board.changeName }">
                </form>
                
                
                
                <script>
            
                function postSubmit(el){
                	//console.log(el);
                	/*
                	if('수정하기'=== el){
                		$('#postForm').attr('action', 'boardUpdaateForm.do').submit();
                	}else{
                		$('#postForm').attr('action', 'boardDelete.do').submit()
                	}
                	*/
                	const attrValue = '수정하기' === el ? 'boardUpdateForm.do' : 'boardDelete.do';
                	$('#postForm').attr('action', attrValue).submit();
                }
                
                </script>
                
            </div>
            <br><br>

            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                
                
                <c:choose>
                
	                	<c:when test="${ empty sessionScope.loginUser }">
		                    <tr>
		                        <th colspan="2">
		                            <textarea class="form-control" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
		                    </tr>
	                    </c:when>
	                    
	                    <c:otherwise>
		                     <tr>
		                        <th colspan="2">
		                            <textarea class="form-control" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button onclick="saveReply();" class="btn btn-secondary">등록하기</button></th> 
		                    </tr>
	                    </c:otherwise>
                    
                    </c:choose>
                    
                    <tr>
                        <td colspan="3">댓글(<span id="rcount">0</span>)</td>
                    </tr>
                </thead>
                <tbody>
                <!--  
                    <tr>
                        <th>user02</th>
                        <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ꿀잼</td>
                        <td>2023-03-12</td>
                    </tr>
                    <tr>
                        <th>user01</th>
                        <td>재밌어요</td>
                        <td>2023-03-11</td>
                    </tr>
                    <tr>
                        <th>admin</th>
                        <td>댓글입니다!!</td>
                        <td>2023-03-10</td>
                    </tr>
                    -->
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    
    <script>
    
    
    function saveReply(){
    	
    	//빈 문자열이 아닐때만 요청 보낼거임 (빈 게시글작성 막기 위해)trim(): 앞 뒤 공백 제거
    	if($('#content').val().trim() != ''){
    		
    		$.ajax({
        		url : 'reply',
        		data : {
        			refBoardNo : ${ board.boardNo },
        			replyContent : $('#content').val(),
        			replyWriter : '${ sessionScope.loginUser.userId }'
        			
        		},
        		type : 'post',
        		
        		success : result => {
        			console.log(result);
        			
        			if(result == 'success'){
        				selectReply();
        				$('#content').val('');
        			};
        		}
        		
        	});
    		
    	}else {
    		alertify.alert('내용 쓰셈');
    	}
    	
    	
    }
    /*
    		data : {
        			refBoardNo : ${ board.boardNo },
        			replyContent : $('#content').val(),
        			replyWriter : ${ sessionScope.loginUser.userId }   
        			//이렇게하면 admin 이 바로 담겨버려서 변수로 인식함.. 'admin' 이렇게 되어야함
        			
        		},
    */
    
    
    
    
    
    	$(() =>{
    		selectReply();
    		//setInt
    	})
    
    
    	//바로 호출되게 하려고 (댓글 쓰면 업데이트도 되어야함)
    	function selectReply(){
    		
    		$.ajax({
    			url:'reply',
    			type :'get',
    			data : {
    				boardNo : ${board.boardNo}
    			},
    			
    			success : result => {
    				console.log(result);  //json타입의 배열 출력됨
    				
    				let resultStr ='';
    				
    				for(let i in result){
    					resultStr += '<tr>'
    								+'<td>'+ result[i].replyWriter +'</td>'
    								+'<td>'+result[i].replyContent +'</td>'
    								+'<td>'+ result[i].createDate +'</td>'
    								+'</tr>';
    				};
    				
    				$('#replyArea tbody').html(resultStr);
    				$('#rcount').html(result.length);
    			}
    			
    			
    			//error : e=>{
    				//console.log(e);
    			//}
    		});
    		
    		
    	}
    	
    </script>
    
    
    
    
    
     <jsp:include page="../common/footer.jsp" />
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
</body>
</html>