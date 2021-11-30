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
 * ���� �亯 ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * AnswerDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
	
	
	//�ַ�� ���
	public void addSolution(Solution solution) throws SQLException {
		if (solutionDAO.addSolution(solution.getPostId(), solution) == 0) {
			System.out.println("�ַ���� ��ϵ��� �ʾҽ��ϴ�.");
		}
		return;
	}
	
	//�ַ�� ����
	public void deleteSolution(int solutionCode) throws SQLException {
		if (solutionDAO.deleteSolution(solutionCode) == 0) {
			System.out.println("�ַ���� �������� �ʾҽ��ϴ�.");
		}
		return;
		
	}
	

	//�ַ�� ��ü�� ��������
	public List<Solution> displayAllSolution(int viewRecommendCode) throws SQLException{
		try {
			List<Solution> solutions = new ArrayList<Solution>();
			solutions = solutionDAO.findSolutions(viewRecommendCode);
			if(solutions != null)
				return solutions;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("�ַ�ǵ��� ã�� ���߽��ϴ�.");
		}
		return null;
	}
		
	//���ϱ�
	public void giveScore(int solutionCode, int userId, int score) throws SQLException{
			try {
				solutionDAO.giveScore(solutionCode, userId, score);
			}catch (Exception e) {
				e.printStackTrace();
			}	
		
	}
	
	//�̹� ��
	public int alreadyScore(int solutionCode, int userId, int Score) throws SQLException{
		int already = -1;
		try {
			already = solutionDAO.alreadyScore(solutionCode, userId);
			if(already != 0) {
				System.out.println("���򰡸� �� �� �����ϴ�.");
				return -1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return already;
	}
}
