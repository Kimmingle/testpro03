<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
     <!-- 
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
    </style> -->
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" /> 

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>마이페이지</h2>
            <br>

            <form action="update.do" method="post">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" value="${sessionScope.loginUser.userId }" name="userId" readonly> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" value="${sessionScope.loginUser.userName }" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" value="${sessionScope.loginUser.email }" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" value="${sessionScope.loginUser.age }" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" value="${sessionScope.loginUser.phone }" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" value="${sessionScope.loginUser.address }" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                    
                    
                    <script>
                    	window.onload = ()=>{
                    		
                    		
                    		//속성(=type, id, name 등등) 선택자
                    		//자바스크립스 버전
                    		//document.querySelector('input[value=$(sessionScope.loginUser.gender)]').checked = true;
                    		//console.dir : 요소 출력할 수 있음
                    	}
                    		
                    	//jquery버전
                    	$(() => {
                    		//이거 엄청 유용하니 기억해둘것  !!!!!
                    		$('input[value="$(sessionScope.loginUser.gender)"]').after('checked', true);
                    		
                    	})
                    
                    </script>
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                </div>
            </form>
        </div>
        <br><br>
        
    </div>

    <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="deleteForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원탈퇴</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="delete.do" method="post">
                <input type = "hidden" value = "${sessionScope.loginUser.userId }" name="userqqId"/>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div align="center">
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴 하시겠습니까? <br>
                        </div>
                        <br>
                            <label for="userPwd" class="mr-sm-2">Password : </label>
                            <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                        <button type="submit" class="btn btn-danger" onclick= "deletePrompt()" >탈퇴하기</button>
                    </div>
                </form>
                
                <script>
                	function deletePrompt(){
                		//수행했을때 alert, confirm, prompt
                		//const value = prompt('탈퇴를 하고싶으면 "~~"를 정확히 입력해주세요');  //함수니까 반환값이 있음
                		//console.log(value);
                		
                		//if( value === '~~'){
                			//탈퇴를 위한 submit 요청을 보내고 싶은것
                			
                		//}else {
                			//submit요청을 안가게 하고싶은것
                			
                		//}
                		
                		return prompt('탈퇴를 하고싶으면 "aa"를 정확히 입력해주세요') === 'aa' ? true : false;
                		
                	}
                </script>
                
                
                
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />

</body>
</html>