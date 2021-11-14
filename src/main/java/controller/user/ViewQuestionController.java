package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;
import model.service.AnswerManager;
import model.Answer;

public class ViewQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int viewQuestionCode = Integer.parseInt(request.getParameter("questionCode"));
		QuestionManager questionManager = QuestionManager.getInstance();
		AnswerManager answerManager = AnswerManager.getInstance();
		Question question = null;
		List<Answer> answerList = null;
		
		question = questionManager.displayQuestion(viewQuestionCode);
		answerList = answerManager.displayAllAnswer(viewQuestionCode);	//questionCode에 해당하는 답변 모두 가져옴
		
		request.setAttribute("Question", question);
		request.setAttribute("AnswerList", answerList);
		
		return "/user/ViewQuestion.jsp";		//ViewQuestion.jsp로 forwarding
	}

}
