package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.QuestionManager;

public class DeleteQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));	//delete할 questionCode
		String postUserId = request.getParameter("userId");			//delete할 question을 작성한 userId, 삭제 권한 확인 위해
		
		QuestionManager manager = QuestionManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser(11111111, session)	// 로그인한 사용자가 관리자(userId가 11111111)인 경우
				   || 												// 또는 
				(!UserSessionUtils.isLoginUser(11111111, session) &&  // 로그인한 사용자가 관리자가 아니지만
				  UserSessionUtils.isLoginUser(Integer.parseInt(postUserId), session))) { // 로그인한 사용자가 작성한 질문인 경우
					
				manager.deleteQuestion(questionCode);				// 질문 삭제
				
				return "redirect:/user/questionlist";		// 질문 목록 화면으로 이동 (redirect)	
			}
			
		//관리자 아니고 로그인한 사용자가 작성한 질문이 아닌 경우 질문 삭제 못하는데 html에서 삭제 버튼 안 보이게 설정할 거면 따로 안 해도 되고
		//html에서 설정 못 하면 여기서 구현하기.
		
		return "redirect:/user/questionlist";
	}

}
