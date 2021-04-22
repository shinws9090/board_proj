package board_proj.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.seavice.BoardModifyService;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			{
			
		try {
		String page = request.getParameter("page");
		BoardModifyService service = new BoardModifyService();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardDto article;
			article = service.getArtcle(board_num);
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_modify.jsp");
		
		
		return forward;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
