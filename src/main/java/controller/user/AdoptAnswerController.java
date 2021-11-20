package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.Question;
import model.service.AnswerManager;

public class AdoptAnswerController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String postUserId = request.getParameter("userId");	//�亯 ä���� ���� �ۼ��� id
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));		//ä���� �亯�� ���� id
		int answerCode = Integer.parseInt(request.getParameter("answerCode"));			//ä���� �亯 id
		
		AnswerManager answerManager = AnswerManager.getInstance();	
		HttpSession session = request.getSession();	
		
		/*
		if (UserSessionUtils.isLoginUser(postUserId, session)) { // ���� �α����� ����ڰ� �ش� question�� �ۼ���(question��ü�� userId)�� ���		
				answerManager.AdoptAnswer(questionCode, answerCode);	//�ش� question�� �ش� answer ä��
				
				return "redirect:/user/viewquestion";
			}
		*/
		//������ �ƴϰ� �α����� ����ڰ� �ۼ��� ������ �ƴ� ��� ���� ���� ���ϴµ� html���� ���� ��ư �� ���̰� ������ �Ÿ� ���� �� �ص� �ǰ�
				//html���� ���� �� �ϸ� ���⼭ �����ϱ�.
		answerManager.AdoptAnswer(questionCode, answerCode);
		//userManager levelup �߰�
		
		request.setAttribute("questionCode", request.getParameter("questionCode"));
		return "/user/viewquestion";
	}

}

