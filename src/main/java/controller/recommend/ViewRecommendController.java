package controller.recommend;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Recommend;
import model.Solution;
import model.service.RecommendManager;
import model.service.SolutionManager;
import model.Solution;
public class ViewRecommendController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Logger LOG = Logger.getGlobal();
		LOG.info("viewRecommendController 불러왔음");

		Recommend recommend = null;
		List<Solution> solutionList = null;
		boolean exist;	//북마크 여부
		boolean empathized; //추천 여부
		HttpSession session = request.getSession();
		RecommendManager recommendManager = RecommendManager.getInstance();
		SolutionManager solutionManager = SolutionManager.getInstance();
		
		int viewRecommendCode;
		if (request.getParameter("recommendCode") != null) {	//리스트에서 추천문제 제목 클릭해서 추천문제 조회할 때
			viewRecommendCode = Integer.parseInt(request.getParameter("recommendCode"));
			System.out.println("파라미터로 코드: "+ viewRecommendCode);
		} else {	//추천문제 등록 후
			viewRecommendCode = (int) request.getAttribute("recommendCode");
			System.out.println("getattribute로 코드: "+ viewRecommendCode);
		}
		
		LOG.info(String.valueOf(viewRecommendCode));
		recommend = recommendManager.displayRecommend(viewRecommendCode);
		solutionList = solutionManager.displayAllSolution(viewRecommendCode);	//recommendCode에 해당하는 솔루션 모두 가져옴
		exist = recommendManager.existingBookmarkRecommend(viewRecommendCode, UserSessionUtils.getLoginUserId(session));
		empathized = recommendManager.existingEmpathizedRecommend(viewRecommendCode, UserSessionUtils.getLoginUserId(session));
		System.out.println("controller에서 RecommendCode값: "+ viewRecommendCode +"Recommend제목: "+ recommend.getTitle() + " 북마크: "+exist+"  추천여부:" + empathized);
		request.setAttribute("exist", exist);
		request.setAttribute("Recommend", recommend);
		request.setAttribute("SolutionList", solutionList);
		request.setAttribute("empathized", empathized);
		
		return "/recommend/ViewRecommend.jsp";		//ViewRecommend.jsp로 forwarding
	}

}
