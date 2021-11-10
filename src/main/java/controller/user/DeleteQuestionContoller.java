package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.User;
import model.service.UserManager;

public class DeleteQuestionContoller implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));	//delete할 questionCode
		String postUserId = request.getParameter("userId");			//delete할 question을 작성한 userId, 삭제 권한 확인 위해
		
		QuestionManager manager = QuestionManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser("admin", session)	// 로그인한 사용자가 관리자인 경우
				   || 												// 또는 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니지만
				  UserSessionUtils.isLoginUser(postUserId, session))) { // 로그인한 사용자가 작성한 질문인 경우
					
				manager.deleteQuestion(questionCode);				// 질문 삭제
				
				return "/user/DisplayQuestion.jsp";		// 질문 목록 화면으로 이동 (forwarding)	
			}
			
		//관리자 아니고 로그인한 사용자가 작성한 질문이 아닌 경우 질문 삭제 못하는데 html에서 삭제 버튼 안 보이게 설정할 거면 따로 안 해도 되고
		//html에서 설정 못 하면 여기서 구현하기.
		
		return "/user/DisplayQuestion.jsp";
	}

}
