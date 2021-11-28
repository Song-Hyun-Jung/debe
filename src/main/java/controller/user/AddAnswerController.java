package controller.user;

import java.util.Enumeration;
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
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		LOG.info("�Ѿ� �°�:" + request.getParameter("questionCode"));
		HttpSession session = request.getSession();	
		Answer answer = new Answer(		//answerId, answerAdopt�� db�� insert �ÿ� sequence, �⺻������ ����, answerDate�� insert�� sysdate��
				Integer.parseInt(request.getParameter("questionCode")),		//�亯 ��� ������ �޾Ҵ���, �ش� ������ postId
				request.getParameter("answerCodes"),
				(int)session.getAttribute("userId")
				);
		
		AnswerManager manager = AnswerManager.getInstance();
		
		manager.addAnswer(answer);	//Answer ��ü �߰�
	
	
		request.setAttribute("questionCode", request.getParameter("questionCode"));
		return "/user/viewquestion";	
	}

}

