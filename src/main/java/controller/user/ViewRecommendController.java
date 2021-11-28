package controller.user;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Controller;
import model.Recommend;
import model.Solution;
import model.service.RecommendManager;
import model.service.SolutionManager;
import model.Solution;
public class ViewRecommendController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Logger LOG = Logger.getGlobal();
		LOG.info("viewRecommendController �ҷ�����");

		int viewRecommendCode;
		if (request.getParameter("recommendCode") != null) {	//����Ʈ���� ��õ���� ���� Ŭ���ؼ� ��õ���� ��ȸ�� ��
			viewRecommendCode = Integer.parseInt(request.getParameter("recommendCode"));
			System.out.println("�Ķ���ͷ� �ڵ�: "+ viewRecommendCode);
		} else {	//��õ���� ��� ��
			viewRecommendCode = (int) request.getAttribute("recommendCode");
			System.out.println("getattribute�� �ڵ�: "+ viewRecommendCode);
		}

		RecommendManager recommendManager = RecommendManager.getInstance();
		SolutionManager solutionManager = SolutionManager.getInstance();	

		Recommend recommend = null;
		List<Solution> solutionList = null;
		boolean exist;	//�ϸ�ũ ����
		HttpSession session = request.getSession();
		
		LOG.info(String.valueOf(viewRecommendCode));
		recommend = recommendManager.displayRecommend(viewRecommendCode);
		solutionList = solutionManager.displayAllSolution(viewRecommendCode);	//recommendCode�� �ش��ϴ� �ַ�� ��� ������
		System.out.println("RecommendCode��, Recommend����: "+viewRecommendCode + recommend.getTitle());
		exist = recommendManager.existingBookmarkRecommend(viewRecommendCode, UserSessionUtils.getLoginUserId(session));
		System.out.println("RecommendCode��, Recommend����: "+viewRecommendCode + recommend.getTitle() + " �ϸ�ũ: "+exist);
		request.setAttribute("exist", exist);
		System.out.println("Controller: "+recommend.getTitle()+" " +recommend.getPostContent());
		request.setAttribute("Recommend", recommend);
		request.setAttribute("SolutionList", solutionList);
		return "/user/ViewRecommend.jsp";		//ViewRecommend.jsp�� forwarding
	}

}
