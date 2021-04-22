package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.seavice.BoardModifyService;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			{
		try {
		BoardModifyService service = new BoardModifyService();
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_name = request.getParameter("board_name");
		String board_pass = request.getParameter("board_pass");
		String board_subject = request.getParameter("board_subject");
		String board_content = request.getParameter("board_content");
		
		boolean res;
			res = service.isArticleWriter(board_num, board_pass);
		
		if(!res) {
			getMessge(response,"수정할 권한이 없습니다.");
			return forward;
		}
		
		BoardDto boardDto = new BoardDto();
		boardDto.setBoard_num(board_num);
		boardDto.setBoard_pass(board_pass);
		boardDto.setBoard_name(board_name);
		boardDto.setBoard_subject(board_subject);
		boardDto.setBoard_content(board_content);
		
		
		boolean modifyRes = service.modifyArticle(boardDto);
		
		if(!modifyRes) {
			getMessge(response,"수정실패");
			return forward;
		}
		
		String page = request.getParameter("page");
		forward = new ActionForward();
		forward.setRediract(true);
		forward.setPath("boardDetail.do?board_num="+board_num+"&page="+page);
		
		return forward;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void getMessge(HttpServletResponse response,String str) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("<script>");
		out.write("alert('"+str+"');");
		out.write("history.back();");
		out.write("</script>");
	}
	
}
