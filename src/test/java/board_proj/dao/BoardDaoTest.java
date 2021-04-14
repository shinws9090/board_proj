package board_proj.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import board_proj.JdbcUtil;
import board_proj.dao.impl.BoardDaoImpl;
import board_proj.dto.BoardDto;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {
	private static Connection con = JdbcUtil.getConnection();
	private static BoardDaoImpl dao = BoardDaoImpl.getInstance();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	
	@Test
	public void test01NextBoardNum() {
		System.out.println("testNextBoardNum");
		int res = dao.nextBoardNum();
		Assert.assertNotEquals(0, res);
		System.out.println("next Number >>" +res);
	}
	
	@Test
	public void test02SelectListCount() {
		System.out.println("testSelectListCount");
		int res = dao.selectListCount();
		Assert.assertNotEquals(-1, res);
		System.out.println(res);
		
		
	}

	@Test
	public void test03SelectArticleList() {
		ArrayList<BoardDto> list = dao.selectArticleList(1, 10);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println("==============================================================================================================");
		list = dao.selectArticleList(2, 10);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void test04InsertArticle() {
		System.out.println("testInsertArticle");
		
		BoardDto article = new BoardDto("김상건", "1111", "마칠시간", "5시", "test.txt");
		int res = dao.insertArticle(article);
		Assert.assertEquals(1, res);
		System.out.println("next Number >>" +res);
	}

	@Test
	public void testInsertReplayArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateReadCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsArticleBoardWriter() {
		fail("Not yet implemented");
	}

}
