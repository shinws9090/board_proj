package board_proj.seavice;

import java.sql.Connection;
import java.sql.SQLException;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardDetailService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	
	public BoardDetailService() {
		dao.setCon(con);
	}


	public BoardDto getArtcle(int board_num) throws SQLException {
		dao.updateReadCount(board_num);
		return dao.selectArticle(board_num);
	}

}
