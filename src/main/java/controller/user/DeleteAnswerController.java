package controller.user;

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
		LOG.info("넘어왔나?");
		LOG.info("넘어 온것:" + request.getParameter("answerCode"));
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		int answerCode = Integer.parseInt(request.getParameter("answerCode"));
		String postUserId = request.getParameter("answerUserId");		//delete할 answer을 작성한 userId
		
		AnswerManager manager = AnswerManager.getInstance();		
		HttpSession session = request.getSession();	
		
		/*if (UserSessionUtils.isLoginUser("admin", session)	// 로그인한 사용자가 관리자인 경우
				   || 												// 또는 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니지만
				  UserSessionUtils.isLoginUser(postUserId, session))) { // 로그인한 사용자가 작성한 답변인 경우
					
				manager.deleteAnswer(answerCode);				// 답변 삭제
				
				return "redirect:/user/viewquestion";
			}*/
		
		manager.deleteAnswer(answerCode);	
		//관리자 아니고 로그인한 사용자가 작성한 답변이 아닌 경우 답변 삭제 못하는데 html에서 삭제 버튼 안 보이게 설정할 거면 따로 안 해도 되고
		//html에서 설정 못 하면 여기서 구현하기.
		
		request.setAttribute("questionCode", request.getParameter("questionCode"));
		return "/user/viewquestion";
	}

}

