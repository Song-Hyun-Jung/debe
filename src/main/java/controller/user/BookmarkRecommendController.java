package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class BookmarkRecommendController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RecommendManager manager = RecommendManager.getInstance();
		String recommendCode = request.getParameter("recommendCode");
		HttpSession session = request.getSession();	
		String userId = UserSessionUtils.getLoginUserId(session);		
		
		manager.bookmarkRecommend(recommendCode, userId);
		
		return "redirect:/user/viewRecommend";
	}

}
