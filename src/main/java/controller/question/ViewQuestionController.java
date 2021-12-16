package controller.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
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

		int viewQuestionCode;
		if (request.getParameter("questionCode") != null) {	//����Ʈ���� ���� ���� Ŭ���ؼ� ���� ��ȸ�� ��
			viewQuestionCode = Integer.parseInt(request.getParameter("questionCode"));
		} else {	//���� ��� �� ����� ���� ��ȸ�� ��
			viewQuestionCode = (int) request.getAttribute("questionCode");
		}
		
		QuestionManager questionManager = QuestionManager.getInstance();
		AnswerManager answerManager = AnswerManager.getInstance();
		Question question = null;
		List<Answer> answerList = null;
		List<Question> relationQuestion = null;
		boolean exist;
		HttpSession session = request.getSession();
		
		LOG.info(String.valueOf(viewQuestionCode));
		question = questionManager.displayQuestion(viewQuestionCode);
		answerList = answerManager.displayAllAnswer(viewQuestionCode);	//questionCode�� �ش��ϴ� �亯 ��� ������
		relationQuestion = questionManager.relationQuestion(question.getSubjectId(), viewQuestionCode);
		exist = questionManager.existingBookmarkQuestion(viewQuestionCode, UserSessionUtils.getLoginUserId(session));		
		System.out.println("questionCode��, question����: "+viewQuestionCode + question.getTitle() + " �ϸ�ũ: "+exist);
		
		request.setAttribute("exist", exist);
		request.setAttribute("Question", question);
		request.setAttribute("AnswerList", answerList);
		request.setAttribute("relationQuestion", relationQuestion);
		
		return "/question/ViewQuestion.jsp";		//ViewQuestion.jsp�� forwarding
	}

}
