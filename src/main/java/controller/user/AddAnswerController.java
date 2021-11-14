package controller.user;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.DispatcherServlet;
import model.Answer;
import model.Question;
import model.service.AnswerManager;

public class AddAnswerController implements Controller{

	final Logger LOG = Logger.getGlobal();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//HttpSession session = request.getSession();	
		
		Answer answer = new Answer(		//answerId, answerAdopt�� db�� insert �ÿ� sequence, �⺻������ ����, answerDate�� insert�� sysdate��
				//Integer.parseInt(request.getParameter("questionCode")),		//�亯 ��� ������ �޾Ҵ���, �ش� ������ postId
				14,
				request.getParameter("answerCodes"),
				//Integer.parseInt(UserSessionUtils.getLoginUserId(session))
				Integer.parseInt("20170001")
				);
		
		AnswerManager manager = AnswerManager.getInstance();
		
		manager.addAnswer(answer);	//Answer ��ü �߰�
	
		return "redirect:/user/viewquestion";	//ViewQuestion.jsp�� redirect
	}

}

