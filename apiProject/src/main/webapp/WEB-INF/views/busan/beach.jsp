<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<h1>부산어쩌고 대장균이 살까</h1>

<table class="table table-success" >
	<thead>
		<tr>
			<td>측정지점</td>
			<td>대장균 측정값</td>
			<td>장구균 측정값</td>
			<td>측정일시</td>
		</tr>
	</thead>
	
	<tbody>
	
	</tbody>
	
	
	
	</table>
	
	
	<button class="btn btn-info" id="page">다음</button>
	
	<script>
	var pageNo=1;
	
		$(()=> {
			
			getBeachInfo();
		});
		
		function getBeachInfo(){
			$.ajax({
				url : 'beach',
				type : 'get',
				data : {
					pageNo : pageNo
				},
				success : info => {
					
					console.log(info.getBeachInfo.body.items.item);
					
					const items =info.getBeachInfo.body.items.item;
					
					pageNo+=1;
					
					let strEl ='';
					for (let i in items){
						const item =items[i];
						
						strEl += '<tr>'
							+'<td>' + item.inspecArea +'</td>'
							+'<td>' + item.water01 +'</td>'
							+'<td>' + item.water02 +'</td>'
							+'<td>' + item.inspecYm +'</td>'
							+ '</tr>'
					};
				
					$('tbody').html(strEl);
					
				}
			
			});
		}
	
	</script>


</body>
</html>