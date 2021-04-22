package board_proj.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.seavice.BoardReplyService;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			{
		try {
		BoardReplyService service = new BoardReplyService();

		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");

		BoardDto article;
			article = service.getArtcle(board_num);

		request.setAttribute("page", page);
		request.setAttribute("article", article);

		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_reply.jsp");

		return forward;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
