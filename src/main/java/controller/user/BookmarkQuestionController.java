package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class BookmarkQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QuestionManager manager = QuestionManager.getInstance();
		String questionCode = request.getParameter("questionCode");
		HttpSession session = request.getSession();		//UserSessionUtils.getLoginUserId()사용하기 위해 session 얻어옴
		String userId = UserSessionUtils.getLoginUserId(session);		//현재 로그인하고 있는 userId 가져옴
		
		manager.bookmarkQuestion(questionCode, userId);
		
		return "redirect:/user/viewquestion";	// /user/viewquestion로 redirect
	}

}
