package model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import model.Subject;
import model.dao.SubjectDAO;


public class SubjectManager {
	private static SubjectManager subjectManager = new SubjectManager();
	
	private SubjectDAO subjectDAO;
	final Logger LOG = Logger.getGlobal();
	
	
	private SubjectManager() {
		try {
			subjectDAO = new SubjectDAO();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static SubjectManager getInstance() {
		return subjectManager;
	}
	
	public List<Subject> findAllSubjects() throws SQLException{
		return subjectDAO.findAllSubjects();
	}
}
	
	