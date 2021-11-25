package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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