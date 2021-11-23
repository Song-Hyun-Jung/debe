package controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.Subject;
import model.service.QuestionManager;

public class QuestionFilterController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Question> questionList = new ArrayList<Question>();
		QuestionManager manager = QuestionManager.getInstance();
		String filter = request.getParameter("filter");		//어떤 필터링 버튼 눌렀는지
		String language = "no";
		String subjectTitle = "no";
		String solved = "no";
		
		if(filter.equals("filterLS")) {
			language = request.getParameter("language");
			subjectTitle = request.getParameter("subjectTitle");

		} else if(filter.equals("filterSolved")) {
			solved = request.getParameter("solved");		//값은 y or n
		}
		
		questionList = manager.filterQuestion(filter, language, subjectTitle, solved);
		List<Subject> subjectList = manager.getAllSubject();
		
		request.setAttribute("questionList", questionList);
		request.setAttribute("subjectList", subjectList);
	
		return "/user/DisplayQuestion.jsp";		// DisplayQuestion.jsp로 forwarding
	}

}
