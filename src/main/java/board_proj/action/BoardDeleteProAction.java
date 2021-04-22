package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.seavice.BoardDeleteService;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			{
		ActionForward forward = null;
		try {
		
		BoardDeleteService service= new BoardDeleteService();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pass = request.getParameter("board_pass");
		String page = request.getParameter("page");
		
		boolean res = service.isArticleBoardWriter(board_num, pass);
		
		if(!res) {
				getMessge(response,"삭제할 권한이 없습니다.");
			return forward;
		}
		
		boolean delRes = service.removeArtilcle(board_num);
		
		if(!delRes) {
			getMessge(response,"삭제실패");
			return forward;
		}
		
		forward = new ActionForward();
		forward.setRediract(true);
		forward.setPath("boardList.do?page = "+page);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}

	private void getMessge(HttpServletResponse response,String str) throws IOException {
		PrintWriter out = response.getWriter();
		out.write("<script>");
		out.write("alert('"+str+"');");
		out.write("history.back();");
		out.write("</script>");
	}

}
