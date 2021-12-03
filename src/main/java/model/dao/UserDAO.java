package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bookmark;
import model.Question;
import model.User;
import model.Subject;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * ServiceUser ���̺� ����� ������ �߰�, ����, ����, ��ȸ ���� 
 */
public class UserDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//����� ���� �߰�
	public int createUser(User user) throws SQLException {
		String sql = "INSERT INTO SERVICEUSER(userid, userpassword, username, usernickname, subjectid) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(), user.getUserPassword(), 
						user.getUserName(), user.getUserNickname(), user.getSubjectId() };				
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
	
	
	//����� ���� ����
	public int updateUser(User user) throws SQLException {
		String sql = "UPDATE SERVICEUSER "
					+ "SET userid=?, userpassword=?, usernickname=?, username=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {user.getUserId(), user.getUserPassword(), 
					user.getUserNickname(), user.getUserName(), 
					user.getUserId()};				
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
	
	//����� ���� ���� -->deletemanager���� deleteUser, findUserList������ �����ͼ� List return
	public int deleteUser(int userId) throws SQLException {
		String sql = "DELETE FROM SERVICEUSER WHERE userid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

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
	
	
	//�� ����� ���� ��ȸ
	public User findUser(int userId) throws SQLException {
        String sql = "SELECT userId, userPassword, userName, userNickname, userLevel, u.subjectId, s.subjectTitle "
        			+ "FROM ServiceUser u JOIN Subject s ON u.subjectId = s.subjectId "
        			+ "WHERE userid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			System.out.println(userId);
			if (rs.next()) {						
				User user = new User(		
					rs.getInt("userId"),
					rs.getString("userPassword"),
					rs.getString("userNickname"),
					rs.getString("userName"),
					rs.getInt("userLevel"),
					rs.getString("subjectTitle"),					
					rs.getInt("subjectId"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//��� ����� ��������
	public List<User> findUserList() throws SQLException {
        String sql = "SELECT userId, userPassword, userName, userNickname, userLevel, subjectId "
    				+ "FROM ServiceUser "
        		    + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<User> userList = new ArrayList<User>();	
			while (rs.next()) {
				User user = new User(			
						rs.getInt("userId"),
						rs.getString("userPassword"),
						rs.getString("userNickname"),
						rs.getString("userName"),
						rs.getInt("userLevel"),					
						rs.getInt("subjectId")
					);
				userList.add(user);				
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//���� ���� 3���� ��ȸ->�̸��� ����Ʈ�� 1.������ -> 2.���й��� / (�г���, ����)��ȯ
	public List<User> findTop3User() throws SQLException {
        String sql = "SELECT userNickname, userLevel "
    				+ "FROM (SELECT userId, userNickname, userLevel FROM serviceUser "
        		    + "ORDER BY userLevel DESC, userId ASC) "
    				+ "WHERE ROWNUM <= 3";
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<User> userList = new ArrayList<User>();	
			while (rs.next()) {
				User user = new User(		
						rs.getString("userNickname"),
						rs.getInt("userLevel")
					);
				userList.add(user);			
			}		
			
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	//���������� ����
	//����ں� ���� ���� ��ȸ->���� �ڵ� ��ȯ. ���ð��� ���� ��õ�� ���
	public int findUserSubject(int userId) throws SQLException {
		String sql = "SELECT subjectid FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			int result;
			if(rs.next()) {
				result = rs.getInt("SUBJECTID");
				return result;
			}
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
	
	//����ں� ���� ���� ��ȸ->���������� ���. ����� ��ȯ
		public Subject findUserSubjectName(int userId) throws SQLException {
			String sql = "SELECT subjectTitle, s.subjectId AS subjectId "
					+ "FROM ServiceUser u JOIN Subject s ON u.subjectId = s.subjectId "
					+ "WHERE userId = ?";
			Object[] param = new Object[] {userId};
						
			jdbcUtil.setSqlAndParameters(sql, param);	
				
			try {				
				ResultSet rs = jdbcUtil.executeQuery();	
				Subject userSubject = null;
				if(rs.next()) {
					userSubject = new Subject(
							rs.getInt("subjectId"),
							rs.getString("subjectTitle")
							);
					return userSubject;
				}
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			}
			finally {
				jdbcUtil.commit();
				jdbcUtil.close();	
			}		
			return null;
		}
		
	//����ں� ���� ���� ����
	public int updateUserSubject(int userId, int subjectId) throws SQLException { //subject�� �Ķ���ͷ� �Ѿ���� �����ڵ�
		String sql = "UPDATE SERVICEUSER "
					+ "SET subjectid=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {subjectId, 
					userId};				
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
	
	//���� ��ȸ->���� ���� ��ȯ. ����� 0�̸� �߸��Ȱ�
	public int getLevel(int userId) throws SQLException {
		String sql = "SELECT userlevel FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			int result;
			if(rs.next()) {
				result = rs.getInt("USERLEVEL");
				return result;
			}
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
	
	//������->������ �� ��� ��ȯ.(����� ����)
	public int levelUp(int userId) throws SQLException {
		int level = getLevel(userId); //���� ���� ��������
		String sql = "UPDATE SERVICEUSER "
					+ "SET userlevel=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {level + 1, 
					userId};				
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

	
	//�α���, ȸ������ ����
	//����� �г��� ��������->���ο��� ǥ��
	public String getNickname(int userId) throws SQLException {
		String sql = "SELECT userNickname FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			String result;
			if(rs.next()) {
				result = rs.getString("usernickname");
				return result;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return null;
	}
	
	//����� ��й�ȣ ��������->manager���� �Է°��� ������ üũ
	public int getPassword(int userId) throws SQLException {
		String sql = "SELECT userPassword FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			int result;
			if(rs.next()) {
				result = rs.getInt("userpassword");
				return result;
			}
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
	
	//����� �г��� �ߺ�üũ(�̹� �����ϴ� �г������� üũ->false�̸� �г��� ��� �Ұ�) unique�� �ʿ�����Ͱ�����...
	public boolean existingNickname(String userNickname) throws SQLException {
		String sql = "SELECT count(*) FROM SERVICEUSER WHERE userNickname=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNickname});	

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
	
	//id ���� üũ(�̹� ����ڰ� �����ϴ��� üũ->false�̸� ����� �߰� �Ұ�) unique�� �ʿ�����Ͱ�����...
	public boolean existingUser(int userId) throws SQLException {
		String sql = "SELECT count(*) FROM SERVICEUSER WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

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
	
	//���������� ����
	//���� ���� ��ȸ
	public List<Question> findMyQuestions(int userId) throws SQLException {
        String sql = "SELECT p.postId, p.title, p.userId, p.postdate "
        			+ "FROM Post p, Question q WHERE p.postId = q.postId and p.userId = ? "
        			+ "ORDER BY p.postdate DESC";
        		
        Object[] param = new Object[] {userId};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Question> myQuestions = new ArrayList<Question>();	
			while (rs.next()) {
				Question question = new Question(			
						rs.getInt("postId"),
						rs.getString("title")
					);
				myQuestions.add(question);				
			}		
			return myQuestions;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//+���� �ϸ�ũ ��ȸ-Q&A
	public List<Bookmark> findMyQuestionBookmarks(int userId) throws SQLException {
        String sql = "SELECT b.postId, title " 
    				+ "FROM Bookmark b, "
    				+ "(SELECT p.postId, p.title, p.postdate FROM Post p, Question q WHERE p.postId = q.postId order by p.postdate DESC) t "
        		    + "WHERE t.postId = b.postId and b.userId = ? ";
        Object[] param = new Object[] {userId};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<Bookmark> myQuestionBookmarks = new ArrayList<Bookmark>();	
			while (rs.next()) {
				Bookmark bookmark = new Bookmark(			
						rs.getInt("postId"),
						rs.getString("title")
					);
				myQuestionBookmarks.add(bookmark);				
			}		
			return myQuestionBookmarks;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//+���� �ϸ�ũ ��ȸ-��õ����
	public List<Bookmark> findMyRecommendBookmarks(int userId) throws SQLException {
        String sql = "SELECT b.postId, title "
    				+ "FROM Bookmark b, "
    				+ "(SELECT p.postId, p.title, p.postdate FROM Post p, Recommend r WHERE p.postId = r.postId order by p.postdate DESC) t "
        		    + "WHERE t.postId = b.postId and b.userId = ? ";
        
        Object[] param = new Object[] {userId};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<Bookmark> myRecommendBookmarks = new ArrayList<Bookmark>();	
			while (rs.next()) {
				Bookmark bookmark = new Bookmark(			
						rs.getInt("postId"),
						rs.getString("title")
					);
				myRecommendBookmarks.add(bookmark);				
			}		
			return myRecommendBookmarks;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
}