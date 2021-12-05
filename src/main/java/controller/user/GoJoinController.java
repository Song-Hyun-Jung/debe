package controller.user;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Subject;
import model.service.SubjectManager;


public class GoJoinController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SubjectManager subjectManager = SubjectManager.getInstance();
		
		List<Subject> findAllSubjects = subjectManager.findAllSubjects();
		
		request.setAttribute("findAllSubjects", findAllSubjects);
		
		return "/user/JoinUser.jsp";	
	}

}