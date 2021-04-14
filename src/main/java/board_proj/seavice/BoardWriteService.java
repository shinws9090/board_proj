package board_proj.seavice;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardWriteService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardWriteService() {
		dao.setCon(con);
	}
	
	
	public boolean registerArticle(BoardDto boardDto) {
		return dao.insertArticle(boardDto) == 1? true:false;
	}
	
	
}
