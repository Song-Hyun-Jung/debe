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
		LOG.info("�ҷ�����");
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
		solutionList = solutionManager.displayAllSolution(viewRecommendCode);	//recommendCode�� �ش��ϴ� �ַ�� ��� ������
		//exist = questionManager.existingBookmarkQuestion(viewQuestionCode, Integer.parseInt(UserSessionUtils.getLoginUserId(session)));
		
		//System.out.println("recommendCode��, recommend����: "+viewRecommendCode + recommend.getTitle() + " �ϸ�ũ: "+exist);
		System.out.println("recommendCode��: " + viewRecommendCode);
		//request.setAttribute("exist", exist);
		//request.setAttribute("Recommend", recommend);
		request.setAttribute("SolutionList", solutionList);
		
		
		return "/user/ViewRecommend.jsp";		//ViewRecommend.jsp�� forwarding
	}

}