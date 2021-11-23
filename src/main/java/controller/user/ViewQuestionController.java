package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.service.QuestionManager;
import model.service.AnswerManager;
import model.Answer;
import java.util.logging.Logger;

public class ViewQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Logger LOG = Logger.getGlobal();
		LOG.info("�ҷ�����");

		int viewQuestionCode = Integer.parseInt(request.getParameter("questionCode"));
		QuestionManager questionManager = QuestionManager.getInstance();
		AnswerManager answerManager = AnswerManager.getInstance();
		Question question = null;
		List<Answer> answerList = null;
		boolean exist;
		HttpSession session = request.getSession();
		
		LOG.info(String.valueOf(viewQuestionCode));
		question = questionManager.displayQuestion(viewQuestionCode);
		answerList = answerManager.displayAllAnswer(viewQuestionCode);	//questionCode�� �ش��ϴ� �亯 ��� ������
		exist = questionManager.existingBookmarkQuestion(viewQuestionCode, Integer.parseInt(UserSessionUtils.getLoginUserId(session)));
		
		System.out.println("questionCode��, question����: "+viewQuestionCode + question.getTitle() + " �ϸ�ũ: "+exist);
		
		request.setAttribute("exist", exist);
		request.setAttribute("Question", question);
		request.setAttribute("AnswerList", answerList);
		
		return "/user/ViewQuestion.jsp";		//ViewQuestion.jsp�� forwarding
	}

}
