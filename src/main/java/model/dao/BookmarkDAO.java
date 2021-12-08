package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import model.Bookmark;


/**
 * 사용자별 북마크 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Bookmark 테이블에 사용자 정보를 추가, 삭제 수행 
 */
public class BookmarkDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public BookmarkDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//사용자 북마크 추가 하얀별->노란별
	public int createBookmark(Bookmark bookmark) throws SQLException {
		String sql = "INSERT INTO Bookmark(userid, postid) "
				+ "VALUES (?, ?)";
		
		System.out.println("createBookmrak의 userid " +bookmark.getUserId() + "postId : " +bookmark.getPostId());
		
		Object[] param = new Object[] {bookmark.getUserId(), bookmark.getPostId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	//사용자 북마크 삭제 노란별->하얀별
	public int deleteBookmark(int userId, int postId) throws SQLException {
		String sql = "DELETE FROM Bookmark WHERE userid=? and postId = ?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, postId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	//질문당 북마크 상태 체크 ->이질문은 내가 북마크를 한 질문임 노란별 표시유지.
	public boolean existingBookmark(int postId, int userId) throws SQLException {
		String sql = "SELECT * FROM Bookmark WHERE userid=? and postId=? ";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, postId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {	//검색 결과 값 있다는 뜻
				int PostId = rs.getInt("postid");
				int UserId = rs.getInt("userid");
				System.out.println("existingBookmark  postid값: "+ PostId + " userid값: "+UserId);
				return true;
				//return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}
}