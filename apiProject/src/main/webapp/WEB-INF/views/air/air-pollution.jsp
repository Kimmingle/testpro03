<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시도별 대기 오염정보</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- 여기서 사용자에게 입력을 받아 컨트롤러로 요청을 보낼것  -->
	
<h1>어느지역의 공기가 궁금하세요</h1>

	시/도 : <br>
	<select id="sidoName">
		<option>서울</option>
		<option>부산</option>
		<option>대구</option>
		<option>인천</option>
		<option>광주</option>
		<option>대전</option>
		<option>울산</option>
		<option>경기</option>
		<option>강원</option>
		<option>충북</option>
		<option>전북</option>
		<option>전남</option>
		<option>경북</option>
		<option>경남</option>
		<option>제주</option>
		<option>세종</option>
	
	
	</select>
	
	<br>
	
	<button class="btn btn-info">클릭해주세요</button> | <button class="btn btn-info" id="btn-xml"></button>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>측정소명</th>
				<th>측정일시</th>
				<th>미세먼지 농도</th>
				<th>오존농도</th>
				<th>통합 대기환경 수치</th>
		</thead>
		
		<tbody>
		<tr>
			<th colspan="5">지역을 선택한 뒤 버튼을 눌러주세요</th>
		</tr>
		
		</tbody>
	</table>

	<br><br>
	
	<script>
	
	$(()=>{
		$('#btn').on('click', ()=>{
			const selectValue = $('#sidoName').val();
			
			$.ajax({
				
				url : 'pollution',
				data : {
					sidoNAme : selectValue
				},
				type :'get',
				success : result =>{
					console.log(result.response.body.items);
					
					const items =result.response.body.items;
					
					let StrEl = '';
					
					for (let i in items){
						const item = items[i];
						
						strEl += '<tr>'
								+'<td>' + item.stationName +'</td>'
								+'<td>' + item.dataTime +'</td>'
								+'<td>' + item.o3Value +'</td>'
								+'<td>' + item.pm10Value +'</td>'
								+'<td>' + item.khaiValue +'</td>'
								+ '</tr>'
					};
					
					$('tbody').html(strEl);
				}
				
			});
			
		});
		
		
		$('#btn-xml').on('click', ()=>{
			const selectValue = $('#sidoName').val();

		
			$.ajax({
				url : 'pollution/xml',
				data : {
					sidoName : selectValue
				},
				type :'get',
				success : result =>{
					console.log(result);
					console.log($(result));
					
					const items = $(result).find('item');
					
					let value = '';
					items.each((i, item)=>{
						console.log(i+'번째 요소: ');
						console.log(item);
						console.log($(item).find('stationName').text());
						
							value += '<tr>'
								+'<td>' + $(item).find('stationName').text() +'</td>'
								+'<td>' + $(item).find('dataTime').text() +'</td>'
								+'<td>' + $(item).find('o3Value').text() +'</td>'
								+'<td>' + $(item).find('pm10Value').text() +'</td>'
								+'<td>' + $(item).find('khaiValue').text() +'</td>'
								+ '</tr>'
						
					});  //반복
					
					$('tbody').html(strEl);
				}
			});
		});
		
	});
	
	</script>


	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>
</html>