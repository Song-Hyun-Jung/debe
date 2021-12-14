package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.Question;
import model.service.AnswerManager;
import model.service.UserManager;

public class AdoptAnswerController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String postUserId = request.getParameter("userId");	//�亯 ä���� ���� �ۼ��� id
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));		//ä���� �亯�� ���� id
		int answerCode = Integer.parseInt(request.getParameter("answerCode"));			//ä���� �亯 id
		
		AnswerManager answerManager = AnswerManager.getInstance();	
		HttpSession session = request.getSession();	
		
		answerManager.AdoptAnswer(questionCode, answerCode);
		//userManager levelup �߰�
		int answerUserId = answerManager.getAnswerUserId(answerCode);
		UserManager userManager = UserManager.getInstance();
		userManager.levelUpUser(answerUserId);
		
		request.setAttribute("questionCode", request.getParameter("questionCode"));
		return "/user/viewquestion";
	}

}

