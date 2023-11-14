package kr.co.green.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.green.board.dto.BoardDTO;
import kr.co.green.board.service.BoardServiceImpl;
import kr.co.green.common.PageInfo;
import kr.co.green.common.Pagenation;


@WebServlet("/freeList.do")
public class FreeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FreeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		BoardDTO bo = new BoardDTO();
//		
//		bo.setIdx(1);
//		bo.setTitle("제목");
//		bo.setContent("내용");
//		bo.setInDate("작성일");
//		bo.setViews(15);
//		bo.setWriter("이상인");
//		
//		List<BoardDTO> list = new ArrayList<>();
//		list.add(bo);
//		list.add(bo);
//		list.add(bo);
//		list.add(bo);
//		list.add(bo);
		
		// cpage = 현재 페이지
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		
		String searchText = request.getParameter("searchText");

		
		BoardServiceImpl boardService = new BoardServiceImpl();
		// 전체 게시글 수
		int listCount = boardService.boardListCount(searchText);
		System.out.println(listCount);
		
		// 보여질 페이지 수
		int pageLimit = 5;
		
		// 한 페이지에 들어갈 게시글 수
		int boardLimit = 5;
		
		// 나머지 페이지 처리는 common
		
		// 페이징 처리
		Pagenation page = new Pagenation();
		
		PageInfo pi = page.getPageInfo(listCount, cpage, pageLimit, boardLimit);
		
		// 목록 불러오기
		ArrayList<BoardDTO> list = boardService.boardList(pi, searchText);
		
		int row = listCount - (cpage - 1) * boardLimit;
		
		request.setAttribute("row", row);
		
		request.setAttribute("searchText", searchText);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		RequestDispatcher view = request.getRequestDispatcher("/views/board/free/freeList.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
