<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
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

        #boardList {text-align:center;}
        #boardList>tbody>tr:hover {cursor:pointer;}

        #pagingArea {width:fit-content; margin:auto;}
        
        #searchForm {
            width:80%;
            margin:auto;
        }
        #searchForm>* {
            float:left;
            margin:5px;
        }
        .select {width:20%;}
        .text {width:53%;}
        .searchBtn {width:20%;}
    </style>
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" /> 

    <div class="content">
        <br><br>
        <div class="innerOuter" style="padding:5% 10%;">
            <h2>게시판</h2>
            <br>
            
            
            <c:if test="${ not empty sessionScope.loginUser }">
            	<!-- 로그인 후 상태일 경우만 보여지는 글쓰기 버튼 -->
            	<a class="btn btn-secondary" style="float:right;" href="noticeForm.do">글쓰기</a>
            </c:if>
            <br>
            <br>
            <table id="noticeList" class="table table-hover" align="center">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody> 
                
                <!--DB에서 가져온 내용 -->
                    <!-- 조회된 결과 있을때 -->
                	<c:choose>
                		<c:when test="${list.size() == 0 }">
                			<tr>
                				<td colspan="6">조회된 결과가 존재하지 않습니다 </td>
                			</tr>
                		</c:when>
	                	<c:otherwise>
		                    <c:forEach items="${ list }" var="notice">
		                    	<tr>
		                    		<td>${notice.noticeNo }</td>
		                    		<td>${notice.noticeTitle }</td>
		                    		<td>${notice.noticeWriter }</td>
		                    		<td>${notice.createDate }</td>
		                    		
		                    	</tr>
		                    </c:forEach>
	                    </c:otherwise>
                	</c:choose>                    
                    
                </tbody>
            </table>
            <br>

            <div id="pagingArea">
                <ul class="pagination">
                    <li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
                    
                    <c:forEach begin = "${ pageInfo.startPage }" end ="${pageInfo.endPage }" var="p">
	                    <li class="page-item">
	                    	<a class="page-link" href="boardlist?page=${p }">${p }</a>
	                    </li>
                    </c:forEach>
                    
                    <c:choose>
                    	<c:when test="${ pageInfo.currentPage ne pageInfo.maxPage }">  
                    		<li class="page-item"><a class="page-link" href="=${pageInfo.currentPage +1 }">다음</a></li>     
		                    <c:forEach begin="${ pageInfo.startPage }" end ="${pageInfo.endPage }">      
		                    	
		                	</c:forEach>
	                	</c:when>
	                	<c:otherwise>
                	   </c:otherwise>
                	</c:choose>  
                </ul>
            </div>

            <br clear="both"><br>

            <form id="searchForm" action="search.do" method="get" align="center">
                <div class="select">
                    <select class="custom-select" name="condition">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                
                
                <div class="text">
                    <input type="text" class="form-control" name="keyword" value="${ keyword}">
                </div>
                <button type="submit" class="searchBtn btn btn-secondary">검색</button>
            </form>
            <br><br>
            <script type="text/javascript">
            $(()=>{
            	$('#searchForm option[value="${condition}"]').after('selected', true);
            
            });
            </script>
            
        </div>
        <br><br>

    </div>

    <jsp:include page="../common/footer.jsp" /> 

</body>
</html>