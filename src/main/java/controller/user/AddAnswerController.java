package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.Question;

public class AddAnswerController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		
		Answer answer = new Answer(		//answerId, answerAdopt�� db�� insert �ÿ� sequence, �⺻������ ����, answerDate�� insert�� sysdate��
				Integer.parseInt(request.getParameter("questionCode")),		//�亯 ��� ������ �޾Ҵ���, �ش� ������ postId
				request.getParameter("answerContent"),
				Integer.parseInt(UserSessionUtils.getLoginUserId(session))
				);
		
		AnswerManager manager = AnswerManager.getInstance();
		
		manager.addAnswer(answer);	//Answer ��ü �߰�
		
		return "redirect:/user/viewquestion";	//ViewQuestion.jsp�� redirect
	}

}

