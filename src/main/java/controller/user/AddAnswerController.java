package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Answer;

public class AddAnswerController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String questionCode = request.getParameter("questionCode");
		Answer answer = request.getParameter("Answer");
		AnswerManager manager = AnswerManager.getInstance();
		
		manager.addAnswer(questionCode, answer);	//questionCode에 해당하는 question에 Answer객체 추가
		
		return "redirect:/user/viewquestion";	//ViewQuestion.jsp로 redirect
	}

}
