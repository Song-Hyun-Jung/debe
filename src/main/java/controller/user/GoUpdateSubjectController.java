package controller.user;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Controller;
import model.Subject;
import model.service.SubjectManager;


public class GoUpdateSubjectController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		SubjectManager subjectManager = SubjectManager.getInstance();
		
		List<Subject> findAllSubjects = subjectManager.findAllSubjects();
		
		request.setAttribute("findAllSubjects", findAllSubjects);
		
		return "/user/UpdateMySubject.jsp";	
	}

}