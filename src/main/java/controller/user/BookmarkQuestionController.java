package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Answer;
import model.Question;
import model.service.AnswerManager;
import model.service.QuestionManager;

public class BookmarkQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QuestionManager manager = QuestionManager.getInstance();
		AnswerManager answerManager = AnswerManager.getInstance();
		Question question = null;
		List<Answer> answerList = null;
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		HttpSession session = request.getSession();		//UserSessionUtils.getLoginUserId()사용하기 위해 session 얻어옴
		int userId = Integer.parseInt(UserSessionUtils.getLoginUserId(session));		//현재 로그인하고 있는 userId 가져옴
		
		manager.bookmarkQuestion(questionCode, userId);
		boolean exist = manager.existingBookmarkQuestion(questionCode, userId);
		question = manager.displayQuestion(questionCode);
		answerList = answerManager.displayAllAnswer(questionCode);	//questionCode에 해당하는 답변 모두 가져옴
		
		request.setAttribute("Question", question);
		request.setAttribute("AnswerList", answerList);
		request.setAttribute("exist", exist);
		
		return "/user/ViewQuestion.jsp";		//ViewQuestion.jsp로 forwarding	
	}

}
