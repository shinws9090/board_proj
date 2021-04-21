package board_proj.seavice;

import java.sql.Connection;
import java.sql.SQLException;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardReplyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardReplyService() {
		dao.setCon(con);
	}
	
	public BoardDto getArtcle(int board_num) throws SQLException {
		return dao.selectArticle(board_num);
	}
	
	public boolean insertReplayArticle(BoardDto boardDto) {
		return dao.insertReplayArticle(boardDto) == 1? true:false;
	}
	
	
}
