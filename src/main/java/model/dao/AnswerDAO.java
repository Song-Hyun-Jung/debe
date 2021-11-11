package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Answer;
import model.Question;


/**
 * ������ �亯 ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * QuestionAnswer ���̺� ����� ������ �߰�, ���� ���� 
 */
public class AnswerDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public AnswerDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//������ �亯 ��ȸ
	public List<Answer> findAnswers(int postId) throws SQLException {
        String sql = "SELECT answerContent, answerAdopt, answerDate, userId, answerId, postId "
    				+ "FROM QuestionAnswer "
        		    + "WHERE postId = ?"
    				+ "ORDER BY answerAdopt DESC, postId";
		jdbcUtil.setSqlAndParameters(sql, null);		
					
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
						rs.getInt("userId")
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
	
	//�亯 ���
	public int addAnswer(int questionCode, Answer answer) throws SQLException {
		// answerAdopt�� �⺻�� 'n', answerDate�� sysdate, answerId���� ������ ���� ��. 
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
	
	//�亯 ����
	public int deleteAnswer(Answer answer) throws SQLException {
		String sql = "DELETE FROM QuestionAnswer WHERE answerId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {answer.getAnswerId()});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	
	//�亯 ä���ϱ� ������Ʈ->�Ŵ������� �亯 ä�ý� UserDAO�� �������� �Բ� ȣ��
	/*JDBC Util ver.
	//�Ʒ� ���Լ��� ��� ȣ���ؾ���.
		//�亯�� ä�� ������Ʈ
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
		//���� ���� ä�û��� ������Ʈ
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
	//Ʈ�����
	public void adoptAnswer(Question question, Answer answer) {
		PreparedStatement pStmt = null;
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql1 = "UPDATE QuestionAnswer "
					+ "SET answeradopt='y' "
					+ "WHERE answerId=?";
			pStmt = conn.prepareStatement(sql1);
			pStmt.setInt(1, answer.getAnswerId());
			if(pStmt.executeUpdate() != 1) { throw new Exception(); }
			pStmt.close();
			String sql2 = "UPDATE Question "
					+ "SET questionAdopt='y' "
					+ "WHERE postId=?";
			pStmt = conn.prepareStatement(sql2);
			pStmt.setInt(1, question.getPostId());
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
	
	//�亯 ä�� 'y', 'n'���� ��ȸ->üũ��ũ ǥ�� ����
	public String checkAdoptState(Answer answer) throws SQLException {
        String sql = "SELECT answerAdopt "
        			+ "FROM QuestionAnswer "
        			+ "WHERE answerId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {answer.getAnswerId()});

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
	
	
}