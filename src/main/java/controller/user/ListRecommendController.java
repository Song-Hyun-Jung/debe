package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Recommend;
import model.service.RecommendManager;

public class ListRecommendController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RecommendManager manager = RecommendManager.getInstance();
		List<Recommend> recommendList = manager.displayAllRecommend();
		
		int recommendIndex = 0;
		if (request.getParameter("recommendIndex") != null) {
			recommendIndex = Integer.parseInt(request.getParameter("recommendIndex"));
		}
		
		request.setAttribute("recommendList", recommendList);
		request.setAttribute("recommendIndex", recommendIndex);
		
		return "/user/DisplayRecommend.jsp";		// DisplayRecommend.jsp·Î forwarding
	}

}
