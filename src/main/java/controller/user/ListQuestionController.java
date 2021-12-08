package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.Subject;
import model.service.QuestionManager;

public class ListQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QuestionManager manager = QuestionManager.getInstance();
		List<Question> questionList = manager.displayAllQuestion();
		List<Subject> subjectList = manager.getAllSubject();
		int questionIndex = 0;
		if (request.getParameter("questionIndex") != null) {
			questionIndex = Integer.parseInt(request.getParameter("questionIndex"));
		}
		
		request.setAttribute("questionList", questionList);
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("questionIndex", questionIndex);
		
		return "/user/DisplayQuestion.jsp";		// DisplayQuestion.jsp·Î forwarding
	}

}
