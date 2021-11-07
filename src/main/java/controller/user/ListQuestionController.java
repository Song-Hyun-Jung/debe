package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ListQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QuestionManager manager = QuestionManager.getInstance();
		List<Question> questionList = manager.displayAllQuestion();
		
		request.setAttribute("questionList", questionList);
		
		return "/user/DisplayQuestion.jsp";		// DisplayQuestion.jsp·Î forwarding
	}

}
