package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Answer;
import model.Solution;


/**
 * 질문별 답변 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * QuestionAnswer 테이블에 사용자 정보를 추가, 삭제 수행 
 */
public class SolutionDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public SolutionDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	 //문제별 답변 조회
	   public List<Solution> findSolutions(int recommendCode) throws SQLException {
	        String sql = "SELECT s.solutionContent, s.solutionScore, s.solutionDate, s.userId, s.solutionId, s.postId, u.userNickname "
	                + "FROM RecommendSolution s, ServiceUser u "
	                  + "WHERE s.userId = u.userId and s.postId = ? "
	                + "ORDER BY solutionId";
	        Object[] param = new Object[] {recommendCode};   
	      jdbcUtil.setSqlAndParameters(sql, param);      
	               
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();               
	         List<Solution> solutions = new ArrayList<Solution>();   
	         while (rs.next()) {
	            Solution solution = new Solution(
	                  rs.getInt("solutionId"),
	                  rs.getInt("postId"),
	                  rs.getString("solutionContent"),
	                  rs.getFloat("solutionScore"),
	                  rs.getDate("solutionDate"),
	                  rs.getInt("userId"),
	                  rs.getString("userNickname")
	               );
	            solutions.add(solution);            
	         }      
	         return solutions;               
	         
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }
	
	 
	//답변 등록
	public int addSolution(int recommendCode, Solution solution) throws SQLException {
		// solutionDate는 기본값으로 sysdate, solutionId에는 시퀀스 값이 들어감. 
		String sql = "INSERT INTO RecommendSolution(solutionContent, postId, userId, solutionId, solutionScore) "
				+ "VALUES (?, ?, ?, SEQUENCESOLUTIONID.nextval, 0.0)";		
		Object[] param = new Object[] {solution.getSolutionContent(), recommendCode, solution.getUserId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;			
	}
	
	
	//솔루션 삭제
	public int deleteSolution(int solutionCode) throws SQLException {
		String sql = "DELETE FROM RecommendSolution WHERE solutionId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {solutionCode});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	


	

	
	
}