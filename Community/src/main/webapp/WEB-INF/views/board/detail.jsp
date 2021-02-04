<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<a href="/board/list?category=${requestScope.data.category}">돌아가기</a>
</div>

<div>
<c:if test="${requestScope.data.userPk == sessionScope.loginUser.userpk}">
	<button>수정</button>
	<button id="btnDel">삭제</button>
</c:if>	
</div>

<div id="data" data-pk="${requestScope.data.boardPk }" data-category="${requestScope.data.category }">
	<div>번호: ${requestScope.data.seq}</div>
	<div>조회수: ${requestScope.data.hits}</div>
	<div>제목 : <c:out value="${requestScope.data.title}"/></div>
	<div>작성자 :<c:out value="${requestScope.data.writerNm}"/></div>
	<div>등록일시 : <c:out value="${requestScope.data.regDt}"/></div>
	<label>${requestScope.data.ctnt}</label>
</div>

<div>
	<input id="updateBtn" type="submit" value="수정">
</div>
