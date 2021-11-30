package model.service;

import model.dao.BookmarkDAO;
import model.dao.RecommendDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bookmark;
import model.Recommend;

public class RecommendManager {

	private static RecommendManager recommendMan = new RecommendManager();
	
	private RecommendDAO recommendDAO;
	private BookmarkDAO bookmarkDAO;

	private RecommendManager() {
		try {
		recommendDAO = new RecommendDAO();
		bookmarkDAO = new BookmarkDAO();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static RecommendManager getInstance() { return recommendMan; }
	
	public List<Recommend> displayAllRecommend() throws SQLException {
		return recommendDAO.displayAllRecommend();
	}
	
	public Recommend displayRecommend(int viewRecommendCode) throws SQLException {
		return recommendDAO.displayRecommend(viewRecommendCode);
	}
	
	public int addRecommend(Recommend recommend) throws SQLException {
		int recommendCode = recommendDAO.addRecommendPost(recommend);
		int result = recommendDAO.addRecommendR(recommend, recommendCode);

		System.out.println("addQuestion recommendCode값 : "+recommendCode);
		System.out.println("addQuestion result값 : "+result);
		
		return recommendCode;
	}
	
	public void deleteRecommend(int recommendCode) throws SQLException {
		int resultRecommendSolution = recommendDAO.deleteRecommendSolution(recommendCode);
		int resultRecommend = recommendDAO.deleteRecommend(recommendCode);
		int resultRecommendPost = recommendDAO.deleteRecommendPost(recommendCode);
		int resutRecommendBookmark = recommendDAO.deleteRecommendBookmark(recommendCode);
		System.out.println("deleteRecommendSolution에서 지운 답변 갯수 : "+ resultRecommendSolution);
		System.out.println("deleteRecommend에서 지운 질문 갯수 : "+ resultRecommend);
		System.out.println("deleteRecommendPost에서 지운 질문 갯수 : "+ resultRecommendPost);
	}
	
	
	public void bookmarkRecommend(int recommendCode, int userId) throws SQLException {	
		if (bookmarkDAO.existingBookmark(recommendCode, userId)) {
			System.out.println("북마크 돼있던 상태라서 북마크에서 지움");
			bookmarkDAO.deleteBookmark(userId, recommendCode);
		}
		else {
			Bookmark bookmark = new Bookmark(recommendCode, userId);
			bookmarkDAO.createBookmark(bookmark);
			System.out.println("북마크에 추가");
		}
	}
	
	public boolean existingBookmarkRecommend(int recommendCode, int userId) throws SQLException {
		return bookmarkDAO.existingBookmark(recommendCode, userId);
	}
	
	
	public List<Recommend> sortRecommend(String sort) throws SQLException {
		
		List<Recommend> recommendList = new ArrayList<Recommend>();
		recommendList = recommendDAO.sortRecommend(sort);
		
		return recommendList;
	}
	
	
	public void updateRecommendCount(int recommendCode, int userId) throws SQLException {
		if (recommendDAO.existingEmpathizedRecommend(recommendCode, userId)) {
			System.out.println("추천했던 상태라서 추천 취소");
			recommendDAO.deleteRecommendCount(recommendCode, userId);
		}
		else {
			recommendDAO.updateRecommendCount(recommendCode, userId);
			System.out.println("추천하기");
		}
	}
	
	
	
	public List<Recommend> display10Recommend() throws SQLException{ //메인페이지 최신 10개 추천문제
		return recommendDAO.display10Recommend();
	}
	
	public List<Recommend> findRecommend(String keyword) throws SQLException{ //추천문제 키워드검색결과
		return recommendDAO.findRecommend(keyword);
	}

	public boolean existingEmpathizedRecommend(int recommendCode, int userId) {
		return recommendDAO.existingEmpathizedRecommend(recommendCode, userId);
	}
}