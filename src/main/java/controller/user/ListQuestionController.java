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
		
		request.setAttribute("questionList", questionList);
		request.setAttribute("subjectList", subjectList);
		
		return "/user/DisplayQuestion.jsp";		// DisplayQuestion.jsp�� forwarding
	}

}
