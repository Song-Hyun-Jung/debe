package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDAO {

	private JDBCUtil jdbcUtil = null;
	
	public QuestionDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	public List<Question> displayAllQuestion() throws SQLException {
        String sql = "SELECT postid, title, postdate, postcontent, userid, questionlanguage, solve, questionadopt, subjectid "
    				+ "FROM Question "
    				+ "ORDER BY postdate, postId";
        
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Question> questions = new ArrayList<Question>();	
			while (rs.next()) {
				Question question = new Question(
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getDate("postdate"),
						rs.getString("postcontent"),
						rs.getInt("userid"),
						rs.getString("questionlanguage"),
						rs.getString("solve"),
						rs.getString("questionadopt"),
						rs.getInt("subjectid")
					);
				questions.add(question);				
			}		
			return questions;					
			
		} catch (Exception ex) { 
			ex.printStackTrace();} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	public Question displayQuestion(int questionCode) throws SQLException {	//���� ��ȯ, �� ������ �ش��ϴ� ���� AnswerDAO���� findAnswers ȣ��
		
		String sql = "SELECT postid, title, postdate, postcontent, userid, questionlanguage, solve, questionadopt, subjectid "
				+ "FROM Question "
    		    + "WHERE postId = ?"
				+ "ORDER BY postdate, postId";
    
	    Object[] param = new Object[] {questionCode};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			
			Question question = new Question(
					rs.getInt("postId"),
					rs.getString("title"),
					rs.getDate("postdate"),
					rs.getString("postcontent"),
					rs.getInt("userid"),
					rs.getString("questionlanguage"),
					rs.getString("solve"),
					rs.getString("questionadopt"),
					rs.getInt("subjectid")
				);			
	
			return question;					
			
		} catch (Exception ex) { 
			ex.printStackTrace();} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public int deleteQuestion(int questionCode) throws SQLException {
		
		int resultAnswer = 0;		//������ �亯 ����
		int resultQuestion = 0;		//������ �� ����
		String deleteAnswerSql = "DELETE FROM Questionanswer WHERE postId = ?";
		String deleteQuestionSql = "DELETE FROM Question WHERE postId = ?";
    
	    Object[] param = new Object[] {questionCode};
		
					
		try {
			jdbcUtil.setSqlAndParameters(deleteAnswerSql, param);		
			resultAnswer = jdbcUtil.executeUpdate();	//������ �ش��ϴ� �亯�� ����
			jdbcUtil.close();	//�̰� �ϴ� ���� �� �ϴ� ���� �� ������..
			
			jdbcUtil.setSqlAndParameters(deleteQuestionSql, param);		
			resultQuestion = jdbcUtil.executeUpdate();			//���� ����	
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		
		return resultQuestion;		//���� ���� �� �Ǹ� 0 ��ȯ, ���� ������ 1 ��ȯ
	}
	
	public int addQuestion(Question question) throws SQLException {
		
		int result = 0;
		
		String sql1 = "INSERT INTO POST (postid, title, postcontent, userid) "
    		    + "VALUES (SEQUENCEPOSTID.nextval, ?, ?, ?) ";
		Object[] param = new Object[] {question.getTitle(), question.getPostContent(), question.getUserId()};
		
		try {
			jdbcUtil.setSqlAndParameters(sql1, param);	
			result = jdbcUtil.executeUpdate();
			ResultSet rs = jdbcUtil.getGeneratedKeys();		//������ pk(postid)���� ������ resultset��ü ��ȯ
			int postId = 0;
			if(rs.next()) {
				postId = rs.getInt(1);
			}
			jdbcUtil.close();		//�ؾ� �ϴ� ���� �ƴ��� �� �𸣰���
			
			String sql2 = "INSERT INTO QUESTION (questionlanguage, postId, subjectid) VALUES (?, ?, ?) ";
			//���� �߰��� �� questionLanguage �ʼ� �������� �ϱ� �� �׷��� ����....
			Object[] param2 = new Object[] {question.getQuestionLanguage(), postId, question.getSubjectId()};
			
			jdbcUtil.setSqlAndParameters(sql2, param2);		
			result += jdbcUtil.executeUpdate();	
			
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		
		return result;		//sql1, sql2 �� �� ����� ���������� 2, �ƴϸ� 1 �Ǵ� 0
	}
	
}
