<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>왤컴 파일</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>


<h1>Spring에서의 비동기 통신 활용법~!</h1>
<h3>AJAX</h3>

<p>
	Asynchronous JavaScript And XML 의 약자로 <br>
	서버로부터 데이터를 응답받아 전체 페이지를 다시 만드는것이 아니라 일부만 내용들을 바꿀 수 있는 기법 <br>
	
	 참고로 우리가 그동안 a태그, form태그를 이용해서 요철한 방식은 동기식 요청
	 =>html전체를 응답 받아서 브라우저는 끝까지 화면을 랜더링해야 결과를 확인할 수 있었음
	 
	 **AJAX구현 방식
	 -JS
	 -jQuery
	 -fetchAPI
	 -axios
	 
	 동기 : 서버가 요청 처리 후 응답해야만 그 다음 작업이 가능
	 		만약 서버에서 응답페이지를 돌려주는 시간이 지연디면 무작정 기다리고 있어야함
	 		응답데이터가 돌아오면 전체 화면을 파싱
	 		
	 비동기 : 현재 페이지를 그대로 유지하는동안 중간중간에 추가요청을 보낼 수 있음 
	 		요청을 보낸다고해서 다른 페이지가 새롭게 랜더링 되는것이 아님 (현재)
	 		요청을 보내놓고 응답이 올때까지 다른작업을 할 수 있음
	 		
	 비동기식 단점 
	 - 요청 후 돌아온 응답데이터를 가지고 현재 페이지에서 새로운 요소를 동적으로 만들어줘야함
	  =>DOM요소를 새롭게 만드는 구문을 잘 익혀둬야함 
	 -페이지 내 복잡도가 기하급수적으로 증가 
	 		
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<pre>
	jQuery에서의 AJAX 통신
		$.ajax({
			속성명: 속성값,
			속성명: 속성값,
			속성명: 속성값
		});
		
		
		
		*주요 속성 : 
		  - url : 요철할 url(필수로 작성) => form태그의 action속성 (매핑값)
		  - Type : 요청 전송방식 (GET, POST, PT, DELETE => 생략시 기본값은 get) => form태그의 method 속성
		  - data : 요청시 전달할 값 ({키, 밸류/ 키, 밸류.,}) for 태그의 input요소의 value속성
		  - success :  AJAX 통신 성공시 콜백함수 정의
		  -----------
		  - error  :  통신 실패시 콜백함수를 정의
		  - comlete : AJAX통신이 성공하든 실패하든 무조건 끝나면 실행할 콜백함수
		  - async : 서버와 비동기 처리방식 설정 여부 (기본값 true)
		  - contentType : 요청 시 데이터 인코딩방식
		  - dataType :서버에서 응답시 돌아오는 데이터의 형태 설정, 작성안하면 스마트하게 판단함
</pre>

<h4>AJAX로 요청보내고 응답 받아오기</h4>

<h5>1. 서버로 요청 시 인자값을 전달하고 응답 데이터를 받아서 화면에 출력</h5>

<label>오늘 먹을거 : </label> 알밥, 돈까스, 서브웨이, 막국수, 감자탕

메뉴 : <input type="text" id="menu"/> <br/>
수량 : <input type="number" id="amount" value="0" required/> <br/><br/>

<button id="btn">서버로 전송 </button>


<div id = "resultMsg">
	아아


</div>

<!-- 버튼을 클릭 시 메뉴에 입력한 음식명과 수량에 입력한 개수를 가지고 서버에 요청해서 응답받은 응답 데이터를 div요소의 content영역에 출력 -->


<script>
	document.getElementById('btn').onclick =()=>{
		
		//전달할 변수
		const menuValue =  document.getElementById('menu').value;  
		const amountValue = document.getElementById('amount').value;

		
		$.ajax({
			
			url : 'ajax1.do', //필수 정의 속성
			type : 'get',   //기본값 get
			data : {   //요청시 전달값 (키, 밸류)
				menu :menuValue, 
				amount : amountValue
			}, 
			success : result => {
				console.log(result);
				
				const resultValue = result != 0 ? '선택하신 메뉴' + menuValue +' ' + amountValue +'개의 가격은 :' +result +'원입니다' : '입력하신 메뉴는 존재하지 않습니다';
				document.getElementById('resultMsg').innerHTML = resultValue;
			},
			error : () =>{
				console.log('아마~~');
			}
			
		});
		
		
	}
	

</script>

<!--댓글 ajax -->

	<h3>데이터베이스에서 select문을 조회했다는 가정하에 vo객체를 출력해보기</h3>

	조회할 음식 번호  <input type="number" id="menuNO" />

	<button id="select-btn">조회</button>

	<div>
	
	</div>

	<script>
	window.onload=()=>{
	
		//이벤트 타겟 						//이벤트 타입  //()는 이벤트 핸들러
		document.getElementById('select-btn').onclick=()=>{
			
			$.ajax({
				url : 'ajax2.do',
				type : 'get',
				data : { menuNumber : document.getElementById('menuNo').value
					},
					
					success: result=>{
						
						console.log(result);
						
						const obj = {
							"menuNumber ": "1",
							"menuName ": "순두부",
							"menuPrice ": "9500",
							"menuMaterial ": "순두부"
						};
						console.log(obj);
						
						
						
						
					},
					
					error : e=>{
						console.log(e);
					}
			});
		};
	}
	</script>
	
	
	
	
	
	<h3>3.조회 후 리스트를 응답받아 출력</h3>

	<button onclick="findAll()"></button>
	<br><br>
	
	<table border="1" id="find-result">
	
		<thead>
			<tr>
				<th>메뉴명</th>
				<th>가격</th>
				<th>재료</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td>김치찌개</td>
				<td>1200</td>
				<td>김치</td>
			</tr>
		</tbody>
	
	</table>

<script>

	function findAll() {
		$.ajax({
			url : 'find.do',
			type : 'get',
			

			success : result => {
				console.log(result[0].menuName);
				
				/*<tr>
				<td>김치찌개</td>
				<td>1200</td>
				<td>김치</td>
			</tr>*/
			
				const tbodyEl = document.getElementById('tbody');
			
				result.map((o, i))=>{
					console.log(o);
					console.log(i);
				});
				const trEl = document.createElement('tr');
				console.log(trEl);
				
				const tdFirst = document.createElement('td')
				console.log(tdFirst);
				
				const firstText = document.createTextNode(result[0].menuName);
				td.First.style.width = '200px';
				tdFirst.appendChild(firstText);
				console.log(tdFirst);
				
				const tdSecound = document.createElement('td');
				const secoundText = document.createTextNode(result[0].price);
				tdSecound.style.width = '200px';
				tdSecound.appendChild(secoundText);
				
				const tdThird = document.createElement('td');
				const thirdText = document.createTextNode(result[0].material);
				tdThird.style.width = '100px';
				tdThird.appendChild(thirdText);
				
				trEl.appendChild(tdFirst);
				trEl.appendChild(tdSecound);
				trEl.appendChild(tdThird);
				
				tbodyEl.appendChild(trEl);
			}//서버에서 보내준 json데이터를 화면에 출력해준거임
		})
		
	}



</script>






</body>
</html>