package board_proj.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board_proj.dao.BoardDao;
import board_proj.dto.BoardDto;
//사랑해요
//사랑해요
//사랑해요
//사랑해요
//사랑해요
public class BoardDaoImpl implements BoardDao {
	private static final BoardDaoImpl instance = new BoardDaoImpl();

	public static BoardDaoImpl getInstance() {
		return instance;
	}

	private BoardDaoImpl() {
	}

	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int selectListCount() {
		String sql = "select count(*) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<BoardDto> selectArticleList(int page, int limit) {
		String sql = "select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, \r\n"
				+ "		BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE\r\n" + "from board \r\n"
				+ "order by BOARD_RE_REF desc, BOARD_RE_SEQ asc\r\n" + "limit ?,?;";
		int startrow = (page - 1) * limit;
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					ArrayList<BoardDto> list = new ArrayList<BoardDto>();
					do {
						list.add(getBoardDTO(rs));
					} while (rs.next());

					return list;

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private BoardDto getBoardDTO(ResultSet rs) throws SQLException {
//		"BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, \r\n"
//				+ "		BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE";
		int board_num = Integer.parseInt(rs.getString("BOARD_NUM"));
		String board_name = rs.getString("BOARD_NAME");
		String board_pass = rs.getString("BOARD_PASS");
		String board_subject = rs.getString("BOARD_SUBJECT");
		String board_content = rs.getString("BOARD_CONTENT");
		String board_file = rs.getString("BOARD_FILE");
		int board_re_ref = rs.getInt("BOARD_RE_REF");
		int board_re_lev = rs.getInt("BOARD_RE_LEV");
		int board_re_seq = rs.getInt("BOARD_RE_SEQ");
		int board_readcount = rs.getInt("BOARD_READCOUNT");
		Date board_date = rs.getDate("BOARD_DATE");

		return new BoardDto(board_num, board_name, board_pass, board_subject, board_content, board_file, board_re_ref,
				board_re_lev, board_re_seq, board_readcount, board_date);
	}

	@Override
	public BoardDto selectArticle(int board_num) {
		String sql = "select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,\r\n"
				+ "BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE\r\n"
				+ "from board where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, board_num);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					return getBoardDTO(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertArticle(BoardDto article) {
		String sql = "INSERT INTO board\r\n"
				+ "(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF) VALUES \r\n"
				+ "(?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			int nextNum = nextBoardNum();
			pstmt.setInt(1, nextNum);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());
			pstmt.setInt(7, nextNum);

//			pstmt.setInt(8, article.getBoard_re_lev());
//			pstmt.setInt(9, article.getBoard_re_seq());
//			pstmt.setInt(10, article.getBoard_readcount());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertReplayArticle(BoardDto article) {
		replayUpdate(article);
		
		String sql = "INSERT INTO web_gradle_erp.board\r\n" + 
				"(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, "
				+ "BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT)\r\n" + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			int nextNum = nextBoardNum();
			pstmt.setInt(1, nextNum);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setInt(6, article.getBoard_re_ref());
			pstmt.setInt(7, article.getBoard_re_lev()+1);
			pstmt.setInt(8, article.getBoard_re_seq()+1);
			pstmt.setInt(9, article.getBoard_readcount());
			
			int res = pstmt.executeUpdate();
			if(res==1) {
				con.commit();
				return res;
			}
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	private void replayUpdate(BoardDto article) {
		String sql = "update board \r\n" + 
				"	set BOARD_RE_SEQ = BOARD_RE_SEQ +1\r\n" + 
				"	where BOARD_RE_REF =? and BOARD_RE_SEQ >?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			pstmt.setInt(1, article.getBoard_re_ref());
			pstmt.setInt(2, article.getBoard_re_seq());
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
	}

	@Override
	public int updateArticle(BoardDto article) {
		String sql = "update board \r\n" 
				+ "	set BOARD_SUBJECT = ? ,BOARD_CONTENT = ? \r\n"
				+ "	where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, article.getBoard_subject());
			pstmt.setString(2, article.getBoard_content());
			pstmt.setInt(3, article.getBoard_num());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteArticle(int board_num) {
		String sql = "delete  from board  where BOARD_NUM = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateReadCount(int board_num) {
		String sql = "update board \r\n" + "	set BOARD_READCOUNT =BOARD_READCOUNT+1\r\n" + "	where BOARD_NUM = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean isArticleBoardWriter(int board_num, String pass) {
		String sql = "select 1 from board  where BOARD_NUM = ? and BOARD_PASS = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			pstmt.setString(2, pass);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public int nextBoardNum() {
		String sql = "select max(board_num) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1) + 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 1;
	}

}
