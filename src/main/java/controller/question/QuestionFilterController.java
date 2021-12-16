package controller.question;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.Subject;
import model.service.QuestionManager;

public class QuestionFilterController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		List<Question> questionList = new ArrayList<Question>();
		QuestionManager manager = QuestionManager.getInstance();
		String filter = request.getParameter("filter");		//어떤 필터링 버튼 눌렀는지
		String language = "no";
		String subjectTitle = "no";
		String solved = "no";
		int questionIndex = 0;
		
		if (request.getParameter("questionIndex") != null) {
			questionIndex = Integer.parseInt(request.getParameter("questionIndex"));
			System.out.println("quesitonIndex값이 null 아님: "+questionIndex);
		}
		
		if(filter == null) {	//필터링 버튼으로 컨트롤러 온 것 아니고 인덱스 눌렀을 때
			session.getAttribute("questionList");
		}	
		else {
			if(filter.equals("filterLS")) {
				language = request.getParameter("language");
				subjectTitle = request.getParameter("subjectTitle");
	
			} else if(filter.equals("filterSolved")) {
				solved = request.getParameter("solved");		//값은 y or n
			}
			
			questionList = manager.filterQuestion(filter, language, subjectTitle, solved);
			session.setAttribute("questionList", questionList);		//필터링 버튼 말고 인덱스 페이지 눌렀을 때도 기억할 수 있도록
		}
		
		List<Subject> subjectList = manager.getAllSubject();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("questionIndex", questionIndex);
	
		return "/question/DisplayFilterQuestion.jsp";		// DisplayFilterQuestion.jsp로 forwarding
	}

}
