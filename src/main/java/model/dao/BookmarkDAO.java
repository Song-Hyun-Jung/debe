package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import model.Bookmark;


/**
 * ����ں� �ϸ�ũ ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * Bookmark ���̺��� ����� ������ �߰�, ���� ���� 
 */
public class BookmarkDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public BookmarkDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//����� �ϸ�ũ �߰� �ϾẰ->�����
	public int createBookmark(Bookmark bookmark) throws SQLException {
		String sql = "INSERT INTO Bookmark(userid, postid) "
				+ "VALUES (?, ?)";		
		Object[] param = new Object[] {bookmark.getUserId(), bookmark.getPostId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
	
	//����� �ϸ�ũ ���� �����->�ϾẰ
	public int deleteBookmark(int userId, int postId) throws SQLException {
		String sql = "DELETE FROM Bookmark WHERE userid=? and postId = ?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, postId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
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
	
	//������ �ϸ�ũ ���� üũ ->�������� ���� �ϸ�ũ�� �� ������ ����� ǥ������.
	public boolean existingBookmark(int userId, int postId) throws SQLException {
		String sql = "SELECT count(*) FROM Bookmark WHERE userid=? and postId=? ";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, postId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}
}