<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC게시판</title>
<style type="text/css">
#passForm {
	width: 400px;
	margin: auto;
	border: 1px solid orange;
}
</style>

</head>
<body>
	${page }
	<br> ${board_num }

	<section id="passForm">
		<form action="boardDeletePro.do" method="get">
			<input type="hidden" name="page" value="${page}"> 
			<input type="hidden" name="board_num" value="${board_num}">
			<table>
				<tr>
					<td><label>글 비밀번호 : </label></td>
					<td><input name="board_pass" type="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="삭제"> &nbsp;&nbsp; 
					<input type="button" value="돌아가기" onclick="javascript:history.go(-1)">
					</td>
				</tr>
			</table>
		</form>
	</section>

</body>
</html>