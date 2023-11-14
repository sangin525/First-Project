package kr.co.green.common;

public class Pagenation {
	
	public static PageInfo getPageInfo(int listCount,
									   int currentPage,
									   int pageLimit,
									   int boardLimit) {
		// 전체 페이지 구하기
		// listCount : 101, boardLimit : 5
		// 나눴을 때 정확히 나눠지지 않음 (int 기준)
		// double로 형변환해서 소수점까지 가지고 올 수 있도록 형변환
		// math.ceil : 소수점 가지고 왔을 때 소수점 올림 처리
		int maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		// 시작 페이지 구하기 
		// 상황 ) 페이지 10개씩 보여준다, 사용자가 15페이지를 보고 있다
		// currentPage = 16, pageLimit = 10
		// int타입은 소수점 버림 따라서 계산하면 11
		int startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		
		// 끝 페이지 구하기
		// startPage : 11 , pageLimit : 10
		int endPage = startPage + pageLimit - 1;
		
		// 끝 페이지가 전체 페이지보다 클 때
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 한 페이지에 보여줄 게시글 수 : 10
		// 현재 페이지 1
		// 1~10까지의 게시글을 가져와야 함
		
		// 현재 페이지 2
		// 11~20까지의 게시글을 가져와야 함
		int offset = (currentPage - 1) * boardLimit;
		
//		if(currentPage == 1) {
//			boardLimit = offset+5;
//		} else {
//			boardLimit = offset+4;
//		}
		PageInfo pi = new PageInfo(listCount, 
								   currentPage,
								   pageLimit,
								   boardLimit,
								   maxPage,
								   startPage,
								   endPage,
								   offset);
		
		return pi;
	}

}
