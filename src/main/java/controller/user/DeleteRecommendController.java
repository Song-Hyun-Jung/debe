package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.RecommendManager;

public class DeleteRecommendController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int recommendCode = Integer.parseInt(request.getParameter("recommendCode"));	//delete할 recommendCode
		String postUserId = request.getParameter("userId");			//delete할 recommend을 작성한 userId, 삭제 권한 확인 위해
		String redirect = "redirect:/user/recommendlist";
	
		if(request.getParameter("admin") != null) {
			redirect = "redirect:/user/adminPostList";
		}
		
		RecommendManager manager = RecommendManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser(11111111, session)	// 로그인한 사용자가 관리자(userId가 11111111)인 경우
				   || 												// 또는 
				(!UserSessionUtils.isLoginUser(11111111, session) &&  // 로그인한 사용자가 관리자가 아니지만
				  UserSessionUtils.isLoginUser(Integer.parseInt(postUserId), session))) { // 로그인한 사용자가 작성한 질문인 경우
					
				manager.deleteRecommend(recommendCode);				// 질문 삭제
				
				return redirect;	
			}
		
		return redirect;
	}

}
