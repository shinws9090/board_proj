<%@ page import="board_proj.ds.JndiDS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%=JndiDS.getConnection() %>
</head>
<body>
<a href="boardWriteForm.do">게시판글쓰기</a>
</body>
</html>