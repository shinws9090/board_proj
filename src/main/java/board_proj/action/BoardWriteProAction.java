package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.seavice.BoardWriteService;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			BoardWriteService service = new BoardWriteService();
			String realFolder = request.getServletContext().getRealPath("/boardUpload");
			;
//		System.out.println(realFolder);
			int fileSize = 5 * 1024 * 1024;

			// 생성 시키면서 파일은 업로드 폴더엔 업로드된다
			MultipartRequest multi;
			multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_name(multi.getParameter("board_name"));
			boardDto.setBoard_pass(multi.getParameter("board_pass"));
			boardDto.setBoard_subject(multi.getParameter("board_subject"));
			boardDto.setBoard_content(multi.getParameter("board_content"));
			boardDto.setBoard_file(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));

			boolean isWrite = service.registerArticle(boardDto);

			ActionForward forward = null;
			if (isWrite) {
				forward = new ActionForward();
				forward.setRediract(true);
				forward.setPath("boardList.do");
			} else {
				PrintWriter out = response.getWriter();
				out.write("<script>");
				out.write("alert('등록실패');");
				out.write("history.back();");
				out.write("</script>");
			}

			return forward;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
