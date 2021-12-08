package model.service;

import model.dao.BookmarkDAO;
import model.dao.RecommendDAO;
import model.dao.SolutionDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bookmark;
import model.Recommend;

public class RecommendManager {

	private static RecommendManager recommendMan = new RecommendManager();
	
	private RecommendDAO recommendDAO;
	private BookmarkDAO bookmarkDAO;
	private SolutionDAO solutionDAO;

	private RecommendManager() {
		try {
		recommendDAO = new RecommendDAO();
		solutionDAO = new SolutionDAO();
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

		System.out.println("addQuestion recommendCode�� : "+recommendCode);
		System.out.println("addQuestion result�� : "+result);
		
		return recommendCode;
	}
	
	public void deleteRecommend(int recommendCode) throws SQLException {
		
		List<Integer> solutionIds = new ArrayList<>();
		solutionIds = solutionDAO.findSolutionIds(recommendCode);
		
		for(Integer id : solutionIds) {
			solutionDAO.deleteSolutionCheck(id);
			solutionDAO.deleteSolution(id);
		}
		
		int resultRecommendCheck = recommendDAO.deleteRecommendCheck(recommendCode);
		int resutRecommendBookmark = recommendDAO.deleteRecommendBookmark(recommendCode);
		int resultRecommend = recommendDAO.deleteRecommend(recommendCode);
		int resultRecommendPost = recommendDAO.deleteRecommendPost(recommendCode);
		
	}
	
	
	public void bookmarkRecommend(int recommendCode, int userId) throws SQLException {	
		if (bookmarkDAO.existingBookmark(recommendCode, userId)) {
			System.out.println("�ϸ�ũ ���ִ� ���¶� �ϸ�ũ���� ����");
			bookmarkDAO.deleteBookmark(userId, recommendCode);
		}
		else {
			Bookmark bookmark = new Bookmark(recommendCode, userId);
			bookmarkDAO.createBookmark(bookmark);
			System.out.println("�ϸ�ũ�� �߰�");
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
			System.out.println("��õ�ߴ� ���¶� ��õ ���");
			recommendDAO.deleteRecommendCount(recommendCode, userId);
		}
		else {
			recommendDAO.updateRecommendCount(recommendCode, userId);
			System.out.println("��õ�ϱ�");
		}
	}
	
	
	
	public List<Recommend> display10Recommend() throws SQLException{ //���������� �ֽ� 10�� ��õ����
		return recommendDAO.display10Recommend();
	}
	
	public List<Recommend> findRecommend(String keyword) throws SQLException{ //��õ���� Ű����˻����
		return recommendDAO.findRecommend(keyword);
	}

	public boolean existingEmpathizedRecommend(int recommendCode, int userId) {
		return recommendDAO.existingEmpathizedRecommend(recommendCode, userId);
	}
}