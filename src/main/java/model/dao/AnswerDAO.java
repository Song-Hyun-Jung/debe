package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Answer;


/**
 * 질문별 답변 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * QuestionAnswer 테이블에 사용자 정보를 추가, 삭제 수행 
 */
public class AnswerDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public AnswerDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	 //문제별 답변 조회
	   public List<Answer> findAnswers(int questionCode) throws SQLException {
	        String sql = "SELECT a.answerContent, a.answerAdopt, a.answerDate, a.userId, a.answerId, a.postId, u.userNickname, u.userLevel "
	                + "FROM QuestionAnswer a, ServiceUser u "
	                  + "WHERE a.userId = u.userId and a.postId = ? "
	                + "ORDER BY answerAdopt DESC, answerId";
	        Object[] param = new Object[] {questionCode};   
	      jdbcUtil.setSqlAndParameters(sql, param);      
	               
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();               
	         List<Answer> answers = new ArrayList<Answer>();   
	         while (rs.next()) {
	            Answer answer = new Answer(
	                  rs.getInt("answerId"),
	                  rs.getInt("postId"),
	                  rs.getString("answerContent"),
	                  rs.getString("answerAdopt"),
	                  rs.getDate("answerDate"),
	                  rs.getInt("userId"),
	                  rs.getString("userNickname"),
	                  rs.getInt("userLevel")
	               );
	            answers.add(answer);            
	         }      
	         return answers;               
	         
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }
	
	//답변 등록
	public int addAnswer(int questionCode, Answer answer) throws SQLException {
		// answerAdopt는 기본값 'n', answerDate는 기본값으로 sysdate, answerId에는 시퀀스 값이 들어감. 
		String sql = "INSERT INTO QuestionAnswer(answerContent, postId, userId, answerId) "
				+ "VALUES (?, ?, ?, SEQUENCEANSWERID.nextval)";		
		Object[] param = new Object[] {answer.getAnswerContent(), questionCode, answer.getUserId() };				
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
	
	//답변 삭제
	public int deleteAnswer(int answerCode) throws SQLException {
		String sql = "DELETE FROM QuestionAnswer WHERE answerId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {answerCode});	// JDBCUtil에 delete문과 매개 변수 설정

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
	
	//답변 채택하기 업데이트->매니저에서 답변 채택시 UserDAO의 레벨업도 함께 호출
	/*JDBC Util ver.
	//아래 두함수를 모두 호출해야함.
		//답변글 채택 업데이트
	public int adoptAnswer(Answer answer) throws SQLException {
		String sql = "UPDATE QuestionAnswer "
					+ "SET answeradopt='y' "
					+ "WHERE answerId=?";
		Object[] param = new Object[] {answer.getAnswerId()};				
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
		//질문 본문 채택상태 업데이트
	public int updateAdopt(Question question) throws SQLException {
		String sql = "UPDATE Question "
					+ "SET questionAdopt='y' "
					+ "WHERE postId=?";
		Object[] param = new Object[] {question.getPostId()};				
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
	
	//트랜잭션
	public void adoptAnswer(int questionCode, int answerCode) {
		PreparedStatement pStmt = null;
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql1 = "UPDATE QuestionAnswer "
					+ "SET answeradopt='y' "
					+ "WHERE answerId=?";
			pStmt = conn.prepareStatement(sql1);
			pStmt.setInt(1, answerCode);
			if(pStmt.executeUpdate() != 1) { throw new Exception(); }
			pStmt.close();
			String sql2 = "UPDATE Question "
					+ "SET questionAdopt='y', solve='y' "
					+ "WHERE postId=?";
			pStmt = conn.prepareStatement(sql2);
			pStmt.setInt(1, questionCode);
			if(pStmt.executeUpdate() != 1) { throw new Exception(); }
			conn.commit();
		} 
		catch (Exception ex) {
			try {
				conn.rollback();
			}catch(SQLException e) { e.printStackTrace(); }
			ex.printStackTrace();
		}
		finally {
			try { conn.setAutoCommit(true); }
			catch(SQLException ex) {ex.printStackTrace(); }
			if(pStmt != null)
				try {pStmt.close();} catch(SQLException ex) {ex.printStackTrace();}
		}
	}
	
	//답변 채택 'y', 'n'상태 조회->체크마크 표시 위함
	public String checkAdoptState(int answerId) throws SQLException {
        String sql = "SELECT answerAdopt "
        			+ "FROM QuestionAnswer "
        			+ "WHERE answerId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {answerId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			String state;
			if (rs.next()) {						
				state = rs.getString("answerAdopt");
				return state;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}

	public Integer getAnswerUserId(int answerCode) {	//답변 채택 시 답변자 아이디 구해오기
		String sql = "SELECT userId "
    			+ "FROM QuestionAnswer "
	    			+ "WHERE answerId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {answerCode});
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {						
				int userId = rs.getInt("userId");
				return userId;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
}