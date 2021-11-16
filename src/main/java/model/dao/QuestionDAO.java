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
        String sql = "SELECT * FROM Question q, POST  p, SUBJECT s "
    				+ "WHERE q.postid = p.postid and q.subjectId = s.subjectId ORDER BY postdate, q.postId";
        
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
						rs.getInt("subjectid"),
						rs.getString("subjectTitle")
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
		
		String sql = "SELECT q.postid, p.title, p.postdate, p.postcontent, p.userid, q.questionlanguage, q.solve, q.questionadopt, s.subjectid, s.subjectTitle "
				+ "FROM Question q, Post p, Subject s "
    		    + "WHERE q.postId = p.postId and q.subjectId = s.subjectId and q.postId = ?"
				+ "ORDER BY p.postdate, q.postId";
    
	    Object[] param = new Object[] {questionCode};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			if(rs.next()) {
				Question question = new Question(
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getDate("postdate"),
						rs.getString("postcontent"),
						rs.getInt("userid"),
						rs.getString("questionlanguage"),
						rs.getString("solve"),
						rs.getString("questionadopt"),
						rs.getInt("subjectid"),
						rs.getString("subjectTitle")
					);			
		
				return question;
			}
							
		} catch (Exception ex) { 
			ex.printStackTrace();} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
public int deleteQuestionAnswer(int questionCode) throws SQLException{
		
		int resultAnswer = 0;		//������ �亯 ����
		String deleteAnswerSql = "DELETE FROM Questionanswer WHERE postId = ?";		//questionCode�� �ش��ϴ� �亯 �����
		Object[] param = new Object[] {questionCode};
		
		try {
			jdbcUtil.setSqlAndParameters(deleteAnswerSql, param);		
			resultAnswer = jdbcUtil.executeUpdate();	//������ �ش��ϴ� �亯�� ����
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultAnswer;		//�亯 ���� �� �Ǹ� 0 ��ȯ, ���� ������ ������ �亯 �� ��ȯ
	}
	
	public int deleteQuestion(int questionCode) throws SQLException {

		int resultQuestion = 0;		//������ �� ����		
		String deleteQuestionSql = "DELETE FROM Question WHERE postId = ?";			//questionCode�� �ش��ϴ� ���� �����
	    Object[] param = new Object[] {questionCode};
				
		try {
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
	
	public int deleteQuestionPost(int questionCode) throws SQLException {

		int resultQuestion = 0;		//������ �� ����		
		String deleteQuestionSql = "DELETE FROM Post WHERE postId = ?";			//questionCode�� �ش��ϴ� ���� �����
	    Object[] param = new Object[] {questionCode};
				
		try {
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

	
	public int addQuestionPost(Question question) throws SQLException {		//1.post table�� �ֱ�
		
		int result = 0;
		int postId = 0;
		
		String sql1 = "INSERT INTO POST (postid, title, postcontent, userid) VALUES (SEQUENCEPOSTID.nextval, ?, ?, ?) ";
		Object[] param = new Object[] {question.getTitle(), question.getPostContent(), question.getUserId()};
		System.out.println("����, ����, userid : "+question.getTitle()+question.getPostContent()+question.getUserId());
		jdbcUtil.setSqlAndParameters(sql1, param);	
		String key[] = {"postId"};
		
		try {
			result = jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) { postId = rs.getInt(1); }
			System.out.println("���� postId ��: "+postId);
		
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		
		return postId;		//�߰��� ������ postId�� return
	}
	
	
public int addQuestionQ(Question question, int postId) throws SQLException {		//2. question table�� �ֱ�
		
		int result = 0;

		String sql2 = "INSERT INTO QUESTION (questionlanguage, postId, subjectid) VALUES (?, ?, ?) ";
		Object[] param2 = new Object[] {question.getQuestionLanguage(), postId, question.getSubjectId()};
		jdbcUtil.setSqlAndParameters(sql2, param2);
		
		try {
			System.out.println("���, postid, subjectid�� : "+question.getQuestionLanguage() + postId + question.getSubjectId());
			result = jdbcUtil.executeUpdate();	
			
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		
		return result;		//����� ������ 1 �ƴϸ� 0
	}
}
