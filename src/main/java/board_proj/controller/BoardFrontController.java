package board_proj.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.BoardDeleteProAction;
import board_proj.action.BoardDetailAction;
import board_proj.action.BoardListAction;
import board_proj.action.BoardModifyFormAction;
import board_proj.action.BoardModifyProAction;
import board_proj.action.BoardReplyFormAction;
import board_proj.action.BoardReplyProAction;
import board_proj.action.BoardWriteProAction;
import board_proj.action.FileDownAction;
import board_proj.dto.ActionForward;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//    	String requestURI = request.getRequestURI();
//    	String contextPath = request.getContextPath();
//    	String command = requestURI.substring(contextPath.length());
//    	System.out.println(requestURI +">>"+ contextPath);
//    	System.out.println(">>"+command);

		String command = request.getServletPath();

		ActionForward forward = null;
		Action action = null;
//		try {
//			switch (command) {
//			case "/boardWriteForm.do":
//				forward = new ActionForward();
//				forward.setPath("/board/qna_board_write.jsp");
//			case "/boardWritePro.do":
//				action = new BoardWriteProAction();
//				forward = action.execute(request, response);
//			case "/boardList.do":
//				action = new BoardListAction();
//				forward = action.execute(request, response);
//			case "/boardDetail.do":
//				action = new BoardDetailAction();
//				forward = action.execute(request, response);
//			case "/boardReplyForm.do":
//				action = new BoardReplyFormAction();
//				forward = action.execute(request, response);
//			case "/boardReplyPro.do":
//				action = new BoardReplyProAction();
//				forward = action.execute(request, response);
//			case "/boardDeleteForm.do":
//				String nowPage = request.getParameter("page");
//				request.setAttribute("page", nowPage);
//				int board_num = Integer.parseInt(request.getParameter("board_num"));
//				request.setAttribute("board_num", board_num);
//				forward = new ActionForward();
//				forward.setPath("/board/qna_board_delete.jsp");
//			case "/boardDeletePro.do":
//				action = new BoardDeleteProAction();
//				forward = action.execute(request, response);
//			case "/file_down.do":
//				action = new FileDownAction();
//				forward = action.execute(request, response);
//			case "/boardModifyForm.do":
//				action = new BoardModifyFormAction();
//				forward = action.execute(request, response);
//			case "/boardModifyPro.do":
//				action = new BoardModifyProAction();
//				forward = action.execute(request, response);
//			}
//		} catch (IOException | SQLException e1) {
//			e1.printStackTrace();
//		}

		try {
			if (command.equals("/boardWriteForm.do")) {

				forward = new ActionForward();
				forward.setPath("/board/qna_board_write.jsp");

			} else if (command.equals("/boardWritePro.do")) {
				action = new BoardWriteProAction();
				forward = action.execute(request, response);
			} else if (command.equals("/boardList.do")) {
				action = new BoardListAction();
				forward = action.execute(request, response);
			} else if (command.equals("/boardDetail.do")) {
				action = new BoardDetailAction();
				forward = action.execute(request, response);
			} else if (command.equals("/boardReplyForm.do")) {
				action = new BoardReplyFormAction();
				forward = action.execute(request, response);
			} else if (command.equals("/boardReplyPro.do")) {
				action = new BoardReplyProAction();
				forward = action.execute(request, response);
			} else if (command.equals("/boardDeleteForm.do")) {

				String nowPage = request.getParameter("page");
				request.setAttribute("page", nowPage);
				int board_num = Integer.parseInt(request.getParameter("board_num"));
				request.setAttribute("board_num", board_num);
				forward = new ActionForward();
				forward.setPath("/board/qna_board_delete.jsp");

			} else if (command.equals("/boardDeletePro.do")) {
				action = new BoardDeleteProAction();
				forward = action.execute(request, response);
			} else if (command.equals("/file_down.do")) {
				action = new FileDownAction();
				forward = action.execute(request, response);
			} else if (command.equals("/boardModifyForm.do")) {
				action = new BoardModifyFormAction();
				forward = action.execute(request, response);
			} else if (command.equals("/boardModifyPro.do")) {
				action = new BoardModifyProAction();
				forward = action.execute(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (forward != null) {
			if (forward.isRediract()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
