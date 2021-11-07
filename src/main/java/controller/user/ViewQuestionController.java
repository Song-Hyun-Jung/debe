package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ViewQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String viewQuestionCode = request.getParameter("questionCode");
		QuestionManager questionManager = QuestionManager.getInstance();
		AnswerManager answerManager = AnswerManager.getInstance();
		Question question = null;
		List<Answer> answerList = null;
		
		question = questionManager.displayQuestion(viewQuestionCode);
		answerList = answerManager.displayAllAnswer(viewQuestionCode);	//questionCode�� �ش��ϴ� �亯 ��� ������
		
		request.setAttribute("Question", question);
		request.setAttribute("AnswerList", answerList);
		
		return "/user/ViewQuestion.jsp";		//ViewQuestion.jsp�� forwarding
	}

}
