package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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