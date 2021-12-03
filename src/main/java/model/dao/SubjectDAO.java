package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Solution;
import model.Subject;

public class SubjectDAO {
	private JDBCUtil jdbcUtil = null;
	
	public SubjectDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//사용자 과목 수정 폼에서 과목 불러올때 사용
	public List<Subject> findAllSubjects() throws SQLException {
        String sql = "SELECT subjectId, subjectTitle "
                + "FROM Subject "
                + "ORDER BY subjectId";
        
      jdbcUtil.setSqlAndParameters(sql, null);      
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();               
         List<Subject> subjects = new ArrayList<Subject>();   
         while (rs.next()) {
           Subject subject = new Subject(
                  rs.getInt("subjectId"),
                  rs.getString("subjectTitle")
               );
            subjects.add(subject);            
         }      
         return subjects;               
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
   }
}
