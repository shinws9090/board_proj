package board_proj.seavice;

import java.sql.Connection;
import java.sql.SQLException;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardModifyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	
	public BoardModifyService() {
		dao.setCon(con);
	}


	public BoardDto getArtcle(int board_num) throws SQLException {
		return dao.selectArticle(board_num);
	}
	
	public boolean isArticleWriter(int board_num, String pass) throws SQLException {
		return dao.isArticleBoardWriter(board_num, pass);
	}
	
	public boolean modifyArticle(BoardDto article) throws SQLException {
		return dao.updateArticle(article) == 1?true:false;
	}

}
