package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.QuestionManager;

public class BookmarkQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QuestionManager manager = QuestionManager.getInstance();
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		HttpSession session = request.getSession();		//UserSessionUtils.getLoginUserId()����ϱ� ���� session ����
		int userId = Integer.parseInt(UserSessionUtils.getLoginUserId(session));		//���� �α����ϰ� �ִ� userId ������
		
		manager.bookmarkQuestion(questionCode, userId);
		
		return "redirect:/user/viewquestion";	// /user/viewquestion�� redirect
	}

}
