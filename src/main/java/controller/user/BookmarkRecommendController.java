package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.Question;
import model.Recommend;
import model.Solution;
import model.service.AnswerManager;
import model.service.QuestionManager;
import model.service.RecommendManager;

public class BookmarkRecommendController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RecommendManager manager = RecommendManager.getInstance();
		SolutionManager solutionManager = SolutionManager.getInstance();
		Recommend recommend = null;
		List<Solution> solutionList = null;
		int recommendCode = Integer.parseInt(request.getParameter("recommendCode"));
		HttpSession session = request.getSession();		//UserSessionUtils.getLoginUserId()����ϱ� ���� session ����
		int userId = (int)UserSessionUtils.getLoginUserId(session);		//���� �α����ϰ� �ִ� userId ������
		
		manager.bookmarkRecommend(recommendCode, userId);
		boolean exist = manager.existingBookmarkRecommend(recommendCode, userId);
		recommend = manager.displayRecommend(recommendCode);
		solutionList = solutionManager.displayAllsolution(recommendCode);	//recommendCode�� �ش��ϴ� �亯 ��� ������
		
		request.setAttribute("Recommend", recommend);
		request.setAttribute("SolutionList", solutionList);
		request.setAttribute("exist", exist);
		
		return "/user/ViewRecommend.jsp";		//ViewRecommend.jsp�� forwarding	
	}

}
