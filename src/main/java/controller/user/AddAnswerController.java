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
		
		Answer answer = new Answer(		//answerId, answerAdopt는 db에 insert 시에 sequence, 기본값으로 지정, answerDate는 insert시 sysdate로
				//Integer.parseInt(request.getParameter("questionCode")),		//답변 어느 질문에 달았는지, 해당 질문의 postId
				14,
				request.getParameter("answerCodes"),
				//Integer.parseInt(UserSessionUtils.getLoginUserId(session))
				Integer.parseInt("20170001")
				);
		
		AnswerManager manager = AnswerManager.getInstance();
		
		manager.addAnswer(answer);	//Answer 객체 추가
	
		return "redirect:/user/viewquestion";	//ViewQuestion.jsp로 redirect
	}

}

