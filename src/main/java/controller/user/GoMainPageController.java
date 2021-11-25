package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;
import model.Recommend;
import model.User;
import model.service.QuestionManager;
import model.service.RecommendManager;
import model.service.UserManager;

import java.util.logging.Logger;

public class GoMainPageController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Logger LOG = Logger.getGlobal();
		LOG.info("불러왔음");

		QuestionManager questionManager = QuestionManager.getInstance();
		UserManager userManager = UserManager.getInstance();
		RecommendManager recommendManager = RecommendManager.getInstance();
		
		List<Question> notSolveQuestion = null;
		List<User> top3User = null;
		List<Recommend> display10Recommend = null;
		
	
		notSolveQuestion = questionManager.displayNotSolveQuestion();
		top3User = userManager.findTop3User();
		display10Recommend = recommendManager.display10Recommend();
		
	
		request.setAttribute("notSolveQuestionList", notSolveQuestion);
		request.setAttribute("top3User", top3User);
		request.setAttribute("display10Recommend", display10Recommend);
		
		return "/user/main.jsp";	
	}

}