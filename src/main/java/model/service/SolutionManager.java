package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Solution;
import model.User;
import model.dao.SolutionDAO;
import model.dao.UserDAO;

/**
 * 질문 답변 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * AnswerDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */

public class SolutionManager {
	private static SolutionManager solutionManager = new SolutionManager();
	private UserDAO userDAO;
	private SolutionDAO solutionDAO;
	final Logger LOG = Logger.getGlobal();
	
	
	private SolutionManager() {
		try {
			userDAO = new UserDAO();
			solutionDAO = new SolutionDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static SolutionManager getInstance() {
		return solutionManager;
	}
	
	
	//솔루션 등록
	public void addSolution(Solution solution) throws SQLException {
		if (solutionDAO.addSolution(solution.getPostId(), solution) == 0) {
			System.out.println("솔루션이 등록되지 않았습니다.");
		}
		return;
	}
	
	//솔루션 삭제
	public void deleteSolution(int solutionCode) throws SQLException {
		if (solutionDAO.deleteSolution(solutionCode) == 0) {
			System.out.println("솔루션이 삭제되지 않았습니다.");
		}
		return;
		
	}
	

	//솔루션 객체들 가져오기
	public List<Solution> displayAllSolution(int viewRecommendCode) throws SQLException{
		try {
			List<Solution> solutions = new ArrayList<Solution>();
			solutions = solutionDAO.findSolutions(viewRecommendCode);
			if(solutions != null)
				return solutions;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("솔루션들을 찾지 못했습니다.");
		}
		return null;
	}
		
	//평가하기
	public void giveScore(int solutionCode, int userId, int score) throws SQLException{
			try {
				solutionDAO.giveScore(solutionCode, userId, score);
			}catch (Exception e) {
				e.printStackTrace();
			}	
		
	}
	
	//이미 평가
	public int alreadyScore(int solutionCode, int userId, int Score) throws SQLException{
		int already = -1;
		try {
			already = solutionDAO.alreadyScore(solutionCode, userId);
			if(already != 0) {
				System.out.println("재평가를 할 수 없습니다.");
				return -1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return already;
	}
}
