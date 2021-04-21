package board_proj.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.seavice.BoardReplyService;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		BoardReplyService service = new BoardReplyService();

		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");

		BoardDto article = service.getArtcle(board_num);

		request.setAttribute("page", page);
		request.setAttribute("article", article);

		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_reply.jsp");

		return forward;
	}

}
