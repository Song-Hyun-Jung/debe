package model.service;

import model.dao.BookmarkDAO;
import model.dao.RecommendDAO;

import java.sql.SQLException;
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
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static RecommendManager getInstance() { return recommendMan; }
	
	
	public List<Recommend> display10Recommend() throws SQLException{ //메인페이지 최신 10개 추천문제
		return recommendDAO.display10Recommend();
	}
	
	public List<Recommend> findRecommend(String keyword) throws SQLException{ //추천문제 키워드검색결과
		return recommendDAO.findRecommend(keyword);
	}
}