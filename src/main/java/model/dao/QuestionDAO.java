package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Subject;

public class QuestionDAO {

	private JDBCUtil jdbcUtil = null;
	
	public QuestionDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	public List<Question> displayAllQuestion() throws SQLException {
        String sql = "SELECT * FROM Question q, POST  p, SUBJECT s "
    				+ "WHERE q.postid = p.postid and q.subjectId = s.subjectId ORDER BY q.postId";
        
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
	
	
	public Question displayQuestion(int questionCode) throws SQLException {	//질문 반환, 이 질문에 해당하는 답은 AnswerDAO에서 findAnswers 호출
		
		String sql = "SELECT q.postid, p.title, p.postdate, p.postcontent, p.userid, q.questionlanguage, q.solve, q.questionadopt, s.subjectid, s.subjectTitle, u.userNickname "
				+ "FROM Question q, Post p, Subject s, Serviceuser u "
    		    + "WHERE q.postId = p.postId and q.subjectId = s.subjectId and u.userId = p.userId and q.postId = ? "
				+ "ORDER BY q.postId";
    
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
						rs.getString("subjectTitle"),
						rs.getString("userNickname")
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
		
		int resultAnswer = 0;		//삭제된 답변 개수
		String deleteAnswerSql = "DELETE FROM Questionanswer WHERE postId = ?";		//questionCode에 해당하는 답변 지우기
		Object[] param = new Object[] {questionCode};
		
		try {
			jdbcUtil.setSqlAndParameters(deleteAnswerSql, param);		
			resultAnswer = jdbcUtil.executeUpdate();	//질문에 해당하는 답변들 삭제
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultAnswer;		//답변 삭제 안 되면 0 반환, 삭제 됐으면 삭제된 답변 수 반환
	}
	
	public int deleteQuestion(int questionCode) throws SQLException {

		int resultQuestion = 0;		//삭제된 행 개수		
		String deleteQuestionSql = "DELETE FROM Question WHERE postId = ?";			//questionCode에 해당하는 질문 지우기
	    Object[] param = new Object[] {questionCode};
				
		try {
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
	
	public int deleteQuestionPost(int questionCode) throws SQLException {

		int resultQuestion = 0;		//삭제된 행 개수		
		String deleteQuestionSql = "DELETE FROM Post WHERE postId = ?";			//questionCode에 해당하는 질문 지우기
	    Object[] param = new Object[] {questionCode};
				
		try {
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

	
	public int addQuestionPost(Question question) throws SQLException {		//1.post table에 넣기
		
		int result = 0;
		int postId = 0;
		
		String sql1 = "INSERT INTO POST (postid, title, postcontent, userid) VALUES (SEQUENCEPOSTID.nextval, ?, ?, ?) ";
		Object[] param = new Object[] {question.getTitle(), question.getPostContent(), question.getUserId()};
		System.out.println("addQuestionPost: "+question.getTitle()+" "+question.getPostContent()+" "+question.getUserId());
		jdbcUtil.setSqlAndParameters(sql1, param);	
		String key[] = {"postId"};
		
		try {
			result = jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) { postId = rs.getInt(1); }
		
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		
		return postId;		//추가한 질문의 postId값 return
	}
	
	
	public int addQuestionQ(Question question, int postId) throws SQLException {		//2. question table에 넣기
		
		int result = 0;

		String sql2 = "INSERT INTO QUESTION (questionlanguage, postId, subjectid) VALUES (?, ?, ?) ";
		Object[] param2 = new Object[] {question.getQuestionLanguage(), postId, question.getSubjectId()};
		jdbcUtil.setSqlAndParameters(sql2, param2);
		
		try {
			result = jdbcUtil.executeUpdate();	
			
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		
		return result;		//제대로 들어갔으면 1 아니면 0
	}
	
	public List<Question> filterLS(String language, String subjectId) throws SQLException{
	
		String sql = "SELECT * FROM Question q, POST  p, SUBJECT s WHERE q.postid = p.postid and q.subjectId = s.subjectId ";
		
		if (language.equals("no") && subjectId.equals("no")) { //둘 다 선택 안 함 고르고 필터링 버튼 눌렀을 경우
			sql += "ORDER BY postdate, q.postId";
			jdbcUtil.setSqlAndParameters(sql, null);
			
		} else if (language.equals("no")) {		//language 선택 안 함 고른 경우
			sql += "and s.subjectId = ? ORDER BY postdate, q.postId";
			Object[] param = new Object[] {subjectId};
			jdbcUtil.setSqlAndParameters(sql, param);	
			
		} else if(subjectId.equals("no")) {	//subjectName 선택 선택 안 함 고른 경우
			sql += "and q.questionlanguage = ? ORDER BY postdate, q.postId";
			Object[] param = new Object[] {language};
			jdbcUtil.setSqlAndParameters(sql, param);	
			
		} else {	//language와 subjectName 둘 다 선택했을 경우
			sql += "and q.questionlanguage = ? and s.subjectId = ? ORDER BY postdate, q.postId";
			 Object[] param = new Object[] {language, subjectId};
			 jdbcUtil.setSqlAndParameters(sql, param);	
		}
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Question> filterList = new ArrayList<Question>();	
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
				filterList.add(question);
			}		
			return filterList;					
			
		} catch (Exception ex) { 
			ex.printStackTrace();} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	public List<Question> filterSolved(String solved) throws SQLException{
		
		 String sql = "SELECT * FROM Question q, POST  p, SUBJECT s "
				+ "WHERE q.postid = p.postid and q.subjectId = s.subjectId and q.solve = ? ORDER BY q.postId";
		Object[] param = new Object[] {solved};
    
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Question> filterList = new ArrayList<Question>();	
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
				filterList.add(question);
			}		
			return filterList;					
			
		} catch (Exception ex) { 
			ex.printStackTrace();} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	public List<Subject> getAllSubject() {
		
		 String sql = "SELECT subjectId, subjectTitle FROM SUBJECT s ORDER BY subjectId";
	    
		jdbcUtil.setSqlAndParameters(sql, null);		
						
			try {
				ResultSet rs = jdbcUtil.executeQuery();					
				List<Subject> subjectList = new ArrayList<Subject>();	
				while (rs.next()) {
					Subject subject = new Subject(
							rs.getInt("subjectId"),
							rs.getString("subjectTitle")
						);
					subjectList.add(subject);
				}		
				return subjectList;					
				
			} catch (Exception ex) { 
				ex.printStackTrace();} 
			finally {
				jdbcUtil.close();		
			}
			return null;
	}

	public List<Question> displayNotSolveQuestion() throws SQLException {
	    String sql = "SELECT * FROM (SELECT p.postid, p.title FROM Question q, POST  p "
					+ "WHERE q.postid = p.postid AND q.solve='n' ORDER BY postdate DESC ) WHERE ROWNUM <= 10";
	    
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Question> questions = new ArrayList<Question>();	
			while (rs.next()) {
				Question question = new Question(
						rs.getInt("postId"),
						rs.getString("title")
						
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
	

	public List<Question> findQuestion(String searchKeyword) throws SQLException {
	    String sql1 = "SELECT q.postid, p.title FROM POST p, Question q "
					+ "WHERE q.postid = p.postid AND p.title LIKE (?) ORDER BY postdate DESC ";
	    
	    Object[] param1 = new Object[] {"%" + searchKeyword + "%"};
		jdbcUtil.setSqlAndParameters(sql1, param1);

					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Question> questions = new ArrayList<Question>();	
			while (rs.next()) {
				Question question = new Question(
						rs.getInt("postId"),
						rs.getString("title")
						
					);
				questions.add(question);
			}		
			return questions;					
			
		} catch (Exception ex) { 
			ex.printStackTrace();
		} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}

	public int deleteQuestionBookmark(int questionCode) {		//글 삭제 할 때 북마크 되어있는 것도 삭제
		int result = 0;
		
		String sql = "DELETE FROM Bookmark WHERE postId = ?";
		Object[] param = new Object[] {questionCode};
		
		try {
			jdbcUtil.setSqlAndParameters(sql, param);		
			result = jdbcUtil.executeUpdate();			//추천문제 삭제	
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return result;
	}
}
