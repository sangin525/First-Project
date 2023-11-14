package kr.co.green.board.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.green.board.dao.FreeDAO;
import kr.co.green.board.dto.BoardDTO;
import kr.co.green.common.DatabaseConnection;
import kr.co.green.common.PageInfo;

public class BoardServiceImpl implements BoardService {
	private Connection con;
	private DatabaseConnection dc;
	private FreeDAO freeDAO;
	
	public BoardServiceImpl() {
		freeDAO = new FreeDAO();
		dc = new DatabaseConnection();
		con = dc.connDB();
	}


	
	// 게시글 목록 조회
		public ArrayList<BoardDTO> boardList(PageInfo pi, String searchText) {
			
			return freeDAO.boardList(con, pi, searchText);
		}
		
	// 전체 게시글 수
	public int boardListCount(String searchText) {
		return freeDAO.boardListCount(con, searchText);
	}


	// 글 등록
	public int boardEnroll(BoardDTO board) {
		
		return freeDAO.boardEnroll(con, board);
	}
	
	// 조회수 +1
		public int boardView(int idx) {
			return freeDAO.boardView(con, idx);
		}

		// 게시글 상세보기
		@Override
		public void boardSelect(BoardDTO board) {
			freeDAO.boardSelect(con, board);
		}
		
		// 게시글 수정
		public int boardUpdate(int idx, String title, String content) {
			return freeDAO.boardUpdate(con, idx, title, content);
		}
		
		
		// 게시글 삭제
		public int boardDelete(int idx) {
			return freeDAO.boardDelete(con, idx);
		}

		
}
