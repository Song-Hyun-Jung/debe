package model.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recommend;

public class RecommendDAO {

	private JDBCUtil jdbcUtil = null;
	
	public RecommendDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	public List<Recommend> displayAllRecommend() throws SQLException {
		
		String sql = "SELECT r.postid, p.title, p.postdate, p.postcontent, r.difficulty, r.recommendCount, r.algorithm FROM Recommend r, POST  p WHERE r.postid = p.postid ORDER BY r.postId DESC";
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
						rs.getString("userNickname"),
						rs.getInt("userLevel")
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
		
		return postId;		//�߰��� ������ postId�� return
		
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
		
		return result;		//����� ������ 1 �ƴϸ� 0
	}
	
	public int deleteRecommendSolution(int recommendCode) throws SQLException{
		
		int resultSolution = 0;		//������ �ַ�� ����
		String deleteSolutionSql = "DELETE FROM RECOMMENDSOLUTION WHERE postId = ?";		//RecommendCode�� �ش��ϴ� �ַ�� �����
		Object[] param = new Object[] {recommendCode};
		
		try {
			jdbcUtil.setSqlAndParameters(deleteSolutionSql, param);		
			resultSolution = jdbcUtil.executeUpdate();	//������ �ش��ϴ� �亯�� ����
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultSolution;		//�ַ�� ���� �� �Ǹ� 0 ��ȯ, ���� ������ ������ �ַ�� �� ��ȯ
	}
	
	
	public int deleteRecommendPost(int recommendCode) throws SQLException {
		
		int resultRecommend = 0;		//������ �� ����		
		String deleteRecommendSql = "DELETE FROM Post WHERE postId = ?";			//��ecommendCode�� �ش��ϴ� ���� �����
	    Object[] param = new Object[] {recommendCode};
				
		try {
			jdbcUtil.setSqlAndParameters(deleteRecommendSql, param);		
			resultRecommend = jdbcUtil.executeUpdate();			//���� ����	
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultRecommend;		//��õ���� ���� �� �Ǹ� 0 ��ȯ, ���� ������ 1 ��ȯ
		
	}
	public int deleteRecommend(int recommendCode) throws SQLException {
		
		int resultRecommend = 0;		//������ �� ����		
		String deleteRecommendSql = "DELETE FROM Recommend WHERE postId = ?";			//recommendCode�� �ش��ϴ� ��õ���� �����
	    Object[] param = new Object[] {recommendCode};
				
		try {
			jdbcUtil.setSqlAndParameters(deleteRecommendSql, param);		
			resultRecommend = jdbcUtil.executeUpdate();			//��õ���� ����	
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return resultRecommend;		//��õ���� ���� �� �Ǹ� 0 ��ȯ, ���� ������ 1 ��ȯ
	}
	
	public int deleteRecommendBookmark(int recommendCode) {		//�� ���� �� �� �ϸ�ũ �Ǿ��ִ� �͵� ����
		int result = 0;
		
		String sql = "DELETE FROM Bookmark WHERE postId = ?";
		Object[] param = new Object[] {recommendCode};
		
		try {
			jdbcUtil.setSqlAndParameters(sql, param);		
			result = jdbcUtil.executeUpdate();			//��õ���� ����	
				
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

	public List<Recommend> sortRecommend(String sort) throws SQLException {
		
		String sql = "";
		List<Recommend> recommends = new ArrayList<Recommend>();
		
		if(sort.equals("recent")) {		//�ֽż� ����
			sql = "SELECT * FROM Recommend r, Post p WHERE r.postid = p.postid ORDER BY p.postdate DESC";
			jdbcUtil.setSqlAndParameters(sql, null);
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();					
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
			
			
		} else if(sort.equals("difficulty")) {	//���̵��� ����
			ResultSet rs;
			String sqlHigh = "SELECT * FROM Recommend r, Post p WHERE r.postid = p.postid and r.difficulty = 'high' ";
			jdbcUtil.setSqlAndParameters(sqlHigh, null);
			rs = jdbcUtil.executeQuery();	
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
			String sqlMid = "SELECT * FROM Recommend r, Post p WHERE r.postid = p.postid and r.difficulty = 'mid' ";
			jdbcUtil.setSqlAndParameters(sqlMid, null);
			rs = jdbcUtil.executeQuery();	
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
			String sqlLow = "SELECT * FROM Recommend r, Post p WHERE r.postid = p.postid and r.difficulty = 'low' ";
			jdbcUtil.setSqlAndParameters(sqlLow, null);
			rs = jdbcUtil.executeQuery();	
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
		} else {	//��õ�� ����
			sql = "SELECT * FROM Recommend r, Post p WHERE r.postid = p.postid ORDER BY r.recommendCount DESC";
			jdbcUtil.setSqlAndParameters(sql, null);
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();					
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
	
	public List<Recommend> findRecommend(String searchKeyword) throws SQLException { //��õ���� Ű���� �˻�
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

	public boolean existingEmpathizedRecommend(int recommendCode, int userId) {		//��õ ���� Ȯ��
		String sql = "SELECT * FROM CHECKCOUNT WHERE userid=? and postId=? ";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, recommendCode});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {	//�˻� ��� �� �ִ� == ��õ�� �� �ִ�.
				int PostId = rs.getInt("postid");
				int UserId = rs.getInt("userid");
				System.out.println("checkcount  postid��: "+ PostId + " userid��: "+UserId);
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}
	
	public int updateRecommendCount(int recommendCode, int userId) {		//��õ������ ��õ �� �����ϰ� ��õ�� user �ߺ���õ �� �ϵ��� CHECKCOUNT�� ���
		int update = 0;
		String sqlUpdateCount = "UPDATE Recommend SET recommendcount = recommendcount + 1 WHERE postid = ?";
		String sqlCountUser = "INSERT INTO CHECKCOUNT(postid, userid) VALUES(?, ?) ";
		Object[] paramUpdate = new Object[] {recommendCode};
		Object[] paramUser = new Object[] {recommendCode, userId};
		
		try {
			jdbcUtil.setSqlAndParameters(sqlUpdateCount, paramUpdate);	
			update = jdbcUtil.executeUpdate();
			jdbcUtil.setSqlAndParameters(sqlCountUser, paramUser);
			update += jdbcUtil.executeUpdate();
				
		} catch (Exception ex) { 
			jdbcUtil.rollback();
			ex.printStackTrace();
			} 
		finally { 
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
		return update;		//2�� ��� ����� ����, 0�̳� 1�̸� ���� ���� �� ��
		
	}

	public int deleteRecommendCount(int recommendCode, int userId) {
		int result = 0;
		String sqlDeleteRecommendUser = "DELETE FROM CHECKCOUNT WHERE userid=? and postId = ?";	
		String sqlDeleteRecommendCount = "UPDATE Recommend SET recommendcount = recommendcount - 1 WHERE postid = ?";
		
		
		try {			
			jdbcUtil.setSqlAndParameters(sqlDeleteRecommendUser, new Object[] {userId, recommendCode});
			result = jdbcUtil.executeUpdate();
			jdbcUtil.setSqlAndParameters(sqlDeleteRecommendCount, new Object[] {recommendCode});
			result += jdbcUtil.executeUpdate();
			
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
		
	}

	
}