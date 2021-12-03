package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.Recommend;
import model.service.QuestionManager;
import model.service.RecommendManager;

public class AdminPostListController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QuestionManager qManager = QuestionManager.getInstance();
		List<Question> questionList = qManager.displayAllQuestion();
		RecommendManager rManager = RecommendManager.getInstance();
		List<Recommend> recommendList = rManager.displayAllRecommend();

		int questionIndex = 0;
		if (request.getParameter("questionIndex") != null) {
			questionIndex = Integer.parseInt(request.getParameter("questionIndex"));
		}
		
		int recommendIndex = 0;
		if (request.getParameter("recommendIndex") != null) {
			recommendIndex = Integer.parseInt(request.getParameter("recommendIndex"));
		}
		
		request.setAttribute("questionList", questionList);
		request.setAttribute("questionIndex", questionIndex);
		request.setAttribute("recommendList", recommendList);
		request.setAttribute("recommendIndex", recommendIndex);
		
		return "/user/AdminPost.jsp";		// AdminPost.jsp·Î forwarding
	}

}
