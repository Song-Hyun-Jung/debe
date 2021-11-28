package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Recommend;
import model.service.RecommendManager;
import model.service.SolutionManager;
import model.Solution;
import java.util.logging.Logger;

public class ViewRecommendController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Logger LOG = Logger.getGlobal();
		LOG.info("불러왔음");
		int viewRecommendCode = Integer.parseInt("35");
		//int viewRecommendCode = Integer.parseInt(request.getParameter("recommendCode"));
		//RecommnedManager recommendManager = RecommendManager.getInstance();
		
		SolutionManager solutionManager = SolutionManager.getInstance();
		Recommend recommend = null;
		List<Solution> solutionList = null;
		boolean exist;
		HttpSession session = request.getSession();
		
		//LOG.info(String.valueOf(viewRecommendCode));
		//recommend = recommendManager.displayQuestion(viewRecommendCode);
		solutionList = solutionManager.displayAllSolution(viewRecommendCode);	//recommendCode에 해당하는 솔루션 모두 가져옴
		//exist = questionManager.existingBookmarkQuestion(viewQuestionCode, Integer.parseInt(UserSessionUtils.getLoginUserId(session)));
		
		//System.out.println("recommendCode값, recommend제목: "+viewRecommendCode + recommend.getTitle() + " 북마크: "+exist);
		System.out.println("recommendCode값: " + viewRecommendCode);
		//request.setAttribute("exist", exist);
		//request.setAttribute("Recommend", recommend);
		request.setAttribute("SolutionList", solutionList);
		
		
		return "/user/ViewRecommend.jsp";		//ViewRecommend.jsp로 forwarding
	}

}