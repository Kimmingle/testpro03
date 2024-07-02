<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>B강의장</title>
<style>
	.innerOuter{
	height: 500px;
	}
</style>

</head>
<body>

	<jsp:include page="common/menubar.jsp"/>
	
	<div id="content">
		<h3>게시글 조회수 TOP3</h3>
		<br>
		<a href="boardList" style="float:left; color:lightgray; font-weight:800; font-size:14px"></a>
	
		<table class="table table-hover" align="center" id="boardList">
			<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일</td>
				<td>첨부파일</td>
			</tr>
			</thead>
			
			<tbody>
			</tbody>
		</table>
		
		<table id="board-detail" class="table">
			
		
		</table>
	
	
	</div>
	
	
	
	
	
	<script>
	
	/* 
	상세보기
	-조회된 게시글 목록에서 
	게시글을 클릭하면 선택한 글번호를 가지고 
	하나의 게시글 정보를 ajax 요청을 통해 응답받아서 
	id 속성값이 board-detail인 table에 자식요소를 만들어서 출력
	*/
	
	
		$(document).on('click','#boardList > tbody> tr', e=>{
			//console.log(123)
			
			const boardNo =  $(e.currentTarget).children().eq(0).text();
			
			$.ajax({
				url:'boards' + boardNo,
				type:'get',
				success : result => {
					
					console.log(result);
					let value = '<tr><td colspan="3">게시글 상세보기</td></tr>';
					
					value += '<tr>'
						+ '<td width="600">' + result.boardTitle + '</td>'
						+ '<td width="300">' + result.boardNo + '</td>'
						+ '<td width="200">' + result.boardWriter + '</td>'
						+ '<td>';
						
					document.getElementById();
				}
			
			});
			
		});
		
		
	
	
	
	
	$(()=>{
		findTopFiveBoard();
		
		
	})
		

		function findTopFiveBoard(){
			$.ajax({
				url : 'boards',
				type : 'get',
				success : boards=>{
					
					let value = "";
					for (let i in boards){
						value += '<tr>'
							+ '<td>' + boards[i].boardNo + '</td>'
							+ '<td>' + boards[i].boardTitle + '</td>'
							+ '<td>' + boards[i].boardWriter + '</td>'
							+ '<td>' + boards[i].count + '</td>'
							+ '<td>' + boards[i].createDate + '</td>'
							+ '<td>';
							if (boards[i].originName != null){
								value += '👍';
							}
							+'</td></tr>';
					}
					
					
					
					//console.log(boards);
					$('#boardList tbody').html(value);
				}
			});  
			
		}
	
	</script>
	
	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>