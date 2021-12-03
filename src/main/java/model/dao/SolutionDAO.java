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
	        String sql = "SELECT s.solutionContent, s.solutionScore, s.solutionDate, s.userId, s.solutionId, s.postId, u.userNickname, u.userLevel "
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
	                  rs.getString("userNickname"),
	                  rs.getInt("userLevel")
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
	
	
	//해당 솔루션이 checkScore에 있는 것 삭제
		public int deleteSolutionCheck(int solutionCode) throws SQLException {
			String sql = "DELETE FROM CheckScore WHERE solutionId=?";		
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
	
	
	public List<Integer> findSolutionIds(int recommendCode) throws SQLException {
		String sql = "SELECT solutionId FROM RecommendSolution "
                  + "WHERE postId = ? ";
        Object[] param = new Object[] {recommendCode};   
      jdbcUtil.setSqlAndParameters(sql, param);      
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();               
         List<Integer> solutions = new ArrayList<Integer>();   
         while (rs.next()) {
        	 int id = rs.getInt("solutionId");
            solutions.add(id);            
         }      
         return solutions;             
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
		
		
	}
	


	
	/*
	//솔루션 평가하기
	public int giveScore(int solutionId, int userId,  int giveScore) throws SQLException {
		String sql = "INSERT INTO checkScore(solutionId, userId, checked, giveScore) "
				+ "VALUES (?, ?, ?, 'y', ?)";		
		Object[] param = new Object[] {solutionId, userId, giveScore};				
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

	//솔루션 평균 점수 변경
	public int updateScore(int solutionId, int userId,  int giveScore) throws SQLException {
		String sql = "UPDATE RecommendSolution s "
					+ "SET s.solutionScore = "
					+ "(SELECT AVG(giveScore) FROM CHECKSCORE GROUP BY solutionId HAVING solutionId = ? )"
					+ "WHERE s.solutionId = ?";
		Object[] param = new Object[] {solutionId};				
		jdbcUtil.setSqlAndParameters(sql, param);	
			
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
	*/
	//트랜잭션 사용
	//평가하고 평균점수 업데이트
	public void giveScore(int solutionId, int userId,  int giveScore) {
		PreparedStatement pStmt = null;
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql1 = "INSERT INTO checkScore(solutionId, userId, checked, giveScore) "
						  + "VALUES (?, ?, 'y', ?)";		
			pStmt = conn.prepareStatement(sql1);
			pStmt.setInt(1, solutionId);
			pStmt.setInt(2, userId);
			pStmt.setInt(3, giveScore);
			if(pStmt.executeUpdate() != 1) { throw new Exception(); }
			pStmt.close();
			String sql2 = "UPDATE RecommendSolution s "
						+ "SET s.solutionScore = "
						+ "(SELECT AVG(giveScore) FROM CHECKSCORE GROUP BY solutionId HAVING solutionId = ? )"
						+ "WHERE s.solutionId = ?";
			pStmt = conn.prepareStatement(sql2);
			pStmt.setInt(1, solutionId);
			pStmt.setInt(2, solutionId);
			if(pStmt.executeUpdate() != 1) { throw new Exception(); }
			conn.commit();
		} 
		catch (Exception ex) {
			try {
				conn.rollback();
			}catch(SQLException e) { e.printStackTrace();}
			ex.printStackTrace();
		}
		finally {
			try { conn.setAutoCommit(true); }
			catch(SQLException ex) {ex.printStackTrace(); }
			if(pStmt != null)
				try {pStmt.close();} catch(SQLException ex) {ex.printStackTrace();}
		}
	}
	
	//평가 유무 확인
	 public int alreadyScore(int solutionId, int userId) throws SQLException {
	        String sql = "SELECT count(*) AS alScore From checkscore where solutionid = ? and userid in ? ";
	        Object[] param = new Object[] {solutionId, userId};   
	      jdbcUtil.setSqlAndParameters(sql, param);      
	               
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();               
	         int result = -1;
	         if (rs.next()) {
	        	 result = rs.getInt("alScore");
	        	 return result;               
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return -1;
	   }
	
	
}