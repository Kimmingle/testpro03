<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<style>
	.btn{
	width:300px;
	}
	#kh-bg{
	width:800px;
	height: 450px;
	background-image : url('https://marketplace.canva.com/EAGHa_lDxk8/2/0/1131w/canva-%EC%97%AC%EB%A6%84-%EB%B0%94%EB%8B%A4-%ED%88%AC%EB%AA%85%ED%95%9C-%ED%95%B4%EB%B3%80-%EC%BD%98%EC%84%9C%ED%8A%B8-%ED%96%89%EC%82%AC-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EC%A0%84%EB%8B%A8%EC%A7%80-T1lZuTnwybY.jpg');
	margin: auto;
	margin-top: 15px;
	background-repeat: no-repeat;
	background-size: cover;
	border-radius: 20px;
	}
	
	.items{
	width:1000px;
	height: auto;
	display: flex;
	flex-wrap: wrap;
	gep : 20px;
	}
	</style>
</head>
<body>

	<nav class="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
 		
		  <div class="container-fluid">
		    <a class="navbar-brand" href="#">
		      <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFZynojDiVFbVAYYeG_u0p6cxB2gP0iDZCng&s" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
		      부트 쇼핑몰
		    </a>
		  </div>
		  
		  <div class="container-fluid">
		    <a class="navbar-brand"></a>
		    <form class="d-flex" role="search">
		      <input id="keyword"  class="form-control me-2" type="search" placeholder="상품명을 입력해주세요" aria-label="Search">
		      <button id="search-btn"  class="btn btn-outline-success" type="button">검색</button><!-- submit은 동기식."button"해야 비동기 할 수 있음 -->
		    </form>
		  </div>
	</nav>
	
	
	<div id="kh-bg">
	
	</div>
	
	<hr/>
	<div id="total-count" style="display:none; text-align:right">
	
	</div>
	
	<button class="button btn-lg" style="background: lightgreen; color: white;" onclick="nextPage()">다음상품</button>
	
	<script>
		$('#search-btn').click(()=> {
			searchItem();
		});
		
		function nextPage(){
			searchItem();
			startNo += 9;
		}
		
		var startNo =1;
		
		function searchItem(){
			
			const $keyword = $('#keyword').val(); //입력받은 keyword
			
			$.ajax({
				url : 'product/',
				data : {
					keyword : $keyword,
					start : startNo
				},
				type : 'get',
				success : product =>{
					
					$('#total-count').fadeOut(300);
					console.log(product);
					
					$('#total-count').html('총 <b>'+product.total + '</b>건의 '
												+'<label style="color:red; font-wieght:bold;"> '+ $keyword +'</label>가 검색됨')
												.fadeIn(1000);
			
			
					const items = product.items;
					
					let item="";
												
					for (let i in items){
						
						item += '<div class="card" style="width: 18rem;">'
						  + '<img src="'+ items[i].image +'" class="card-img-top" >'
						  + '<div class="card-body">'
						  + '<h5 class="card-title">'+ items[i].brand +'</h5>'
						  + '<p class="card-text">'+ items[i].title +'</p>'
						  + '</div>'
						  + '<ul class="list-group list-group-flush">'
						  + '<li class="list-group-item">가격</li>'
						  + '<li class="list-group-item">'+ items[i].lprice +'원</li>'
						  + '<li class="list-group-item">A third item</li>'
						  + '</ul>'
						  + '<div class="card-body">'
						  + '<a href="'+ items[i].link +'" class="btn btn-primary" tatget="_blank">구매하러가기</a>'
						  + '</div>'
						  + '</div>';
					};
					$('.item').html(item);
				}
					
					
			});
			
		};
	
	</script>
	


</body>
</html>