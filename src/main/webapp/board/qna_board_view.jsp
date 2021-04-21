<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/qna_board_view.css">
</head>
<body>
	board_re_ref : ${article.board_re_ref }<br>
	board_re_lev : ${article.board_re_lev }<br>
	board_re_seq : ${article.board_re_seq }<br>
	board_pass : ${article.board_pass }
<%-- ${article }
${page } --%>
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id ="a">제목 :${article.board_subject } <br>
						첨부파일 :
						<c:if test="${article.board_file != null }">
							<a href="file_down.do?downFile=${article.board_file }">${article.board_file }</a>
						</c:if>
						</section>
		<section id ="b">${article.board_content }</section>
	</section>
	<section id="commandlist">
		<a href="boardReplyForm.do?board_num=${article.board_num }&page=${page}">[답변]</a>
		<a href="boardModifyForm.do?board_num=${article.board_num }&page=${page}">[수정]</a>
		<a href="boardDeleteForm.do?board_num=${article.board_num }&page=${page}">[삭제]</a>
		<a href="boardList.do?page=${page }">[목록 돌아가자]</a>
	</section>

</body>
</html>