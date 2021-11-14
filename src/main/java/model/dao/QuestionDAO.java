package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDAO {

	private JDBCUtil jdbcUtil = null;
	
	public QuestionDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
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
	
	
	public Question displayQuestion(int questionCode) throws SQLException {	//질문 반환, 이 질문에 해당하는 답은 AnswerDAO에서 findAnswers 호출
		
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
		
		int resultAnswer = 0;		//삭제된 답변 개수
		int resultQuestion = 0;		//삭제된 행 개수
		String deleteAnswerSql = "DELETE FROM Questionanswer WHERE postId = ?";
		String deleteQuestionSql = "DELETE FROM Question WHERE postId = ?";
    
	    Object[] param = new Object[] {questionCode};
		
					
		try {
			jdbcUtil.setSqlAndParameters(deleteAnswerSql, param);		
			resultAnswer = jdbcUtil.executeUpdate();	//질문에 해당하는 답변들 삭제
			jdbcUtil.close();	//이거 하는 건지 안 하는 건지 잘 몰겠음..
			
			jdbcUtil.setSqlAndParameters(deleteQuestionSql, param);		
			resultQuestion = jdbcUtil.executeUpdate();			//질문 삭제	
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		
		return resultQuestion;		//질문 삭제 안 되면 0 반환, 삭제 됐으면 1 반환
	}
	
	public int addQuestion(Question question) throws SQLException {
		
		int result = 0;
		
		String sql1 = "INSERT INTO POST (postid, title, postcontent, userid) "
    		    + "VALUES (SEQUENCEPOSTID.nextval, ?, ?, ?) ";
		Object[] param = new Object[] {question.getTitle(), question.getPostContent(), question.getUserId()};
		
		try {
			jdbcUtil.setSqlAndParameters(sql1, param);	
			result = jdbcUtil.executeUpdate();
			ResultSet rs = jdbcUtil.getGeneratedKeys();		//생성된 pk(postid)값을 포함한 resultset객체 반환
			int postId = 0;
			if(rs.next()) {
				postId = rs.getInt(1);
			}
			jdbcUtil.close();		//해야 하는 건지 아닌지 잘 모르겠음
			
			String sql2 = "INSERT INTO QUESTION (questionlanguage, postId, subjectid) VALUES (?, ?, ?) ";
			//질문 추가할 때 questionLanguage 필수 선택으로 하기 안 그러면 복잡....
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
		
		return result;		//sql1, sql2 둘 다 제대로 수행했으면 2, 아니면 1 또는 0
	}
	
}
