package controller.question;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.service.AnswerManager;

public class DeleteAnswerController implements Controller{
	
	final Logger LOG = Logger.getGlobal();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("�Ѿ�Գ�?");
		LOG.info("�Ѿ� �°�:" + request.getParameter("answerCode"));
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		int answerCode = Integer.parseInt(request.getParameter("answerCode"));
		String postUserId = request.getParameter("answerUserId");		//delete�� answer�� �ۼ��� userId
		
		AnswerManager manager = AnswerManager.getInstance();		
		HttpSession session = request.getSession();	
		
		manager.deleteAnswer(answerCode);	
		//������ �ƴϰ� �α����� ����ڰ� �ۼ��� �亯�� �ƴ� ��� �亯 ���� ���ϴµ� html���� ���� ��ư �� ���̰� ������
		
		request.setAttribute("questionCode", request.getParameter("questionCode"));
		return "/user/viewquestion";
	}

}

