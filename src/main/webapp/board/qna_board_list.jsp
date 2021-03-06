<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/qna_board_list.css">

</head>
<body>
	<section id="listForm">
		<h2>글목록</h2>
		<h5>
			<a href="boardWriteForm.do">게시판 글쓰기</a>
		</h5>
		<table>
			<tr id="tr_top">
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
			<c:forEach var="list" items="${articleList }">
				<tr>
					<td>${list.board_num }</td>
					<td id="subject">
						<c:if test="${list.board_re_seq!=0}">
							<c:forEach begin="0" end="${list.board_re_lev}">
	 							&nbsp;
	 						</c:forEach>
	 							└
	 					</c:if> 
	 				<a href="boardDetail.do?board_num=${list.board_num }&page=${pageInfo.page}">${list.board_subject}</a> 
						<c:forEach var="list2" items="${articleList }">
							<c:choose>
								<c:when test="${list2.board_re_ref  == list.board_re_ref 
	 										&& list.board_re_lev-1 == list2.board_re_lev
	 										&& list.board_re_seq-1 == list2.board_re_seq}">
									<span id="a"> ${ list2.board_subject} 의 답변 </span>
								</c:when>
								<c:when test="${list2.board_re_ref  == list.board_re_ref 
	 										&& list.board_re_lev-1 == list2.board_re_lev
	 										&& list2.board_re_seq == 0}">
									<span id="a"> ${ list2.board_subject} 의 답변 </span>
								</c:when>
							</c:choose>
						</c:forEach></td>
					<td>${list.board_name}</td>
					<td>${list.board_date }</td>
					<td>${list.board_readcount }</td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<%-- ${pageInfo} --%>

	<section id="pageList">

		<c:choose>
			<c:when test="${pageInfo.page<=1}">
				<span>[이전]</span>&nbsp;
		</c:when>
			<c:otherwise>
				<a href="boardList.do?page=${pageInfo.page-1}">[이전]</a>&nbsp;&nbsp;
		</c:otherwise>
		</c:choose>
		<%-- <c:if test="${pageInfo.page<=1}">
		<span>[이전]</span>&nbsp;
	</c:if>	
	<c:if test="${pageInfo.page > 1}">
		<a href="boardList.do?page=${pageInfo.page-1}">[이전]</a>&nbsp;&nbsp;
	</c:if> --%>


		<c:forEach var="a" begin="${pageInfo.startpage}"
			end="${pageInfo.endpage}">
			<%--  <c:if test="${a==pageInfo.page}">
	 <span>[${a}]</span>
	 </c:if>
	 <c:if test="${a!=pageInfo.page}">
	 <a href="boardList.do?page=${a}">[${a}]</a>
	 </c:if> --%>
			<c:choose>
				<c:when test="${a==pageInfo.page}">
					<span>[${a}]</span>
				</c:when>
				<c:otherwise>
					<a href="boardList.do?page=${a}">[${a}]</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageInfo.page >=pageInfo.maxpage}">
				<span>[다음]</span>&nbsp;
		</c:when>
			<c:otherwise>
		&nbsp;&nbsp;<a href="boardList.do?page=${pageInfo.page+1}">[다음]</a>
			</c:otherwise>
		</c:choose>

		<%-- <c:if test="${pageInfo.page >=pageInfo.maxpage}">
		<span>[다움]</span>&nbsp;
	</c:if>	
	<c:if test="${pageInfo.page < pageInfo.maxpage}">
		&nbsp;&nbsp;<a href="boardList.do?page=${pageInfo.page+1}">[다음]</a>
	</c:if> --%>

	</section>


</body>
</html>