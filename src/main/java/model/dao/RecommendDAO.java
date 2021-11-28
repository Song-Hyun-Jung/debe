package model.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recommend;

public class RecommendDAO {

	private JDBCUtil jdbcUtil = null;
	
	public RecommendDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	public List<Recommend> displayAllRecommend() throws SQLException {
		
		String sql = "SELECT r.postid, p.title, p.postdate, p.postcontent, r.difficulty, r.recommendCount, r.algorithm FROM Recommend r, POST  p WHERE r.postid = p.postid ORDER BY r.postId";
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Recommend> recommends = new ArrayList<Recommend>();	
			while (rs.next()) {
				Recommend recommend = new Recommend(
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getDate("postdate"),
						rs.getString("postcontent"),
						rs.getString("difficulty"),
						rs.getInt("recommendCount"),
						rs.getString("algorithm")
					);
				recommends.add(recommend);
				}		
				return recommends;					
		
			} catch (Exception ex) { 
				ex.printStackTrace();} 
			finally {
				jdbcUtil.close();		
			}
			return null;
	}
	
	public Recommend displayRecommend(int recommendCode) throws SQLException {
		String sql = "SELECT * FROM Recommend r, POST  p, SERVICEUSER u WHERE r.postid = p.postid and u.userid = p.userid and r.postid = ? ORDER BY r.postId";
		Object[] param = new Object[] {recommendCode};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			if (rs.next()) {
				Recommend recommend = new Recommend(
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getDate("postdate"),
						rs.getString("postcontent"),
						rs.getInt("userid"),
						rs.getString("difficulty"),
						rs.getInt("recommendCount"),
						rs.getString("algorithm"),
						rs.getString("userNickname")
					);
					System.out.println("DAO: "+recommend.getTitle());
					return recommend;
					
				}		
			} catch (Exception ex) { 
				ex.printStackTrace();} 
			finally {
				jdbcUtil.close();		
			}
			return null;
		
	}
	
	public int addRecommendPost(Recommend recommend) throws SQLException {
		int result = 0;
		int postId = 0;
		
		String sql1 = "INSERT INTO POST (postid, title, postcontent, userid) VALUES (SEQUENCEPOSTID.nextval, ?, ?, ?) ";
		Object[] param = new Object[] {recommend.getTitle(), recommend.getPostContent(), recommend.getUserId()};
		
		jdbcUtil.setSqlAndParameters(sql1, param);
		String key[] = {"postid"};
		
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
	public int addRecommendR(Recommend recommend, int postId) throws SQLException {
		int result = 0;

		String sql2 = "INSERT INTO RECOMMEND (difficulty, recommendCount, algorithm, postid) VALUES (?, 0, ?, ?) ";
		Object[] param2 = new Object[] {recommend.getDifficulty(), recommend.getAlgorithm(), postId};
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
	
	public int deleteRecommendSolution(int recommendCode) throws SQLException{
		
		int resultSolution = 0;		//삭제된 솔루션 개수
		String deleteSolutionSql = "DELETE FROM RECOMMENDSOLUTION WHERE postId = ?";		//RecommendCode에 해당하는 솔루션 지우기
		Object[] param = new Object[] {recommendCode};
		
		try {
			jdbcUtil.setSqlAndParameters(deleteSolutionSql, param);		
			resultSolution = jdbcUtil.executeUpdate();	//질문에 해당하는 답변들 삭제
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultSolution;		//솔루션 삭제 안 되면 0 반환, 삭제 됐으면 삭제된 솔루션 수 반환
	}
	
	
	public int deleteRecommendPost(int recommendCode) throws SQLException {
		
		int resultRecommend = 0;		//삭제된 행 개수		
		String deleteRecommendSql = "DELETE FROM Post WHERE postId = ?";			//ㄱecommendCode에 해당하는 질문 지우기
	    Object[] param = new Object[] {recommendCode};
				
		try {
			jdbcUtil.setSqlAndParameters(deleteRecommendSql, param);		
			resultRecommend = jdbcUtil.executeUpdate();			//질문 삭제	
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultRecommend;		//추천문제 삭제 안 되면 0 반환, 삭제 됐으면 1 반환
		
	}
	public int deleteRecommend(int recommendCode) throws SQLException {
		
		int resultRecommend = 0;		//삭제된 행 개수		
		String deleteRecommendSql = "DELETE FROM Recommend WHERE postId = ?";			//recommendCode에 해당하는 추천문제 지우기
	    Object[] param = new Object[] {recommendCode};
				
		try {
			jdbcUtil.setSqlAndParameters(deleteRecommendSql, param);		
			resultRecommend = jdbcUtil.executeUpdate();			//추천문제 삭제	
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultRecommend;		//추천문제 삭제 안 되면 0 반환, 삭제 됐으면 1 반환
	}

	public List<Recommend> sortRecommend(String sort) throws SQLException {
		
		String sql = "SELECT * FROM Recommend r, Post p WHERE r.postid = p.postid ";
		
		if(sort.equals("recent")) {		//최신순 정렬
			sql += "ORDER BY p.postdate DESC";
		} else if(sort.equals("recommend")) {	//추천순 정렬
			sql += "ORDER BY r.recommendCount DESC";
		}
		jdbcUtil.setSqlAndParameters(sql, null);
				
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Recommend> recommends = new ArrayList<Recommend>();	
			while (rs.next()) {
				Recommend recommend = new Recommend(
						rs.getInt("postId"),
						rs.getString("title"),
						rs.getDate("postdate"),
						rs.getString("postcontent"),
						rs.getString("difficulty"),
						rs.getInt("recommendCount"),
						rs.getString("algorithm")
					);
				recommends.add(recommend);
				}		
				return recommends;					
		
			} catch (Exception ex) { 
				ex.printStackTrace();} 
			finally {
				jdbcUtil.close();		
			}
			return null;
	}
	
	public List<Recommend> display10Recommend() throws SQLException {
	    String sql = "SELECT * FROM (SELECT p.postid, p.title FROM Recommend r, POST  p "
					+ "WHERE r.postid = p.postid ORDER BY postdate DESC ) WHERE ROWNUM <= 10";
	    
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Recommend> recommends = new ArrayList<Recommend>();	
			while (rs.next()) {
				Recommend recommend = new Recommend(
						rs.getInt("postId"),
						rs.getString("title")
						
					);
				recommends.add(recommend);
			}		
			return recommends;					
			
		} catch (Exception ex) { 
			ex.printStackTrace();} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public List<Recommend> findRecommend(String searchKeyword) throws SQLException { //추천문제 키워드 검색
	    String sql1 = "SELECT r.postid, p.title FROM POST p, Recommend r "
					+ "WHERE r.postid = p.postid AND p.title LIKE (?) ORDER BY postdate DESC ";
	    
	    Object[] param1 = new Object[] {"%" + searchKeyword + "%"};
		jdbcUtil.setSqlAndParameters(sql1, param1);

					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Recommend> recommends = new ArrayList<Recommend>();	
			while (rs.next()) {
				Recommend recommend = new Recommend(
						rs.getInt("postId"),
						rs.getString("title")
					);
				recommends.add(recommend);
			}		
			return recommends;					
			
		} catch (Exception ex) { 
			ex.printStackTrace();
		} 
		finally {
			jdbcUtil.close();		
		}
		return null;
	}
}