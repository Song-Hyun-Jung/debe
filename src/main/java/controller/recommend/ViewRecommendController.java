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
		LOG.info("viewRecommendController �ҷ�����");

		Recommend recommend = null;
		List<Solution> solutionList = null;
		boolean exist;	//�ϸ�ũ ����
		boolean empathized; //��õ ����
		HttpSession session = request.getSession();
		RecommendManager recommendManager = RecommendManager.getInstance();
		SolutionManager solutionManager = SolutionManager.getInstance();
		
		int viewRecommendCode;
		if (request.getParameter("recommendCode") != null) {	//����Ʈ���� ��õ���� ���� Ŭ���ؼ� ��õ���� ��ȸ�� ��
			viewRecommendCode = Integer.parseInt(request.getParameter("recommendCode"));
			System.out.println("�Ķ���ͷ� �ڵ�: "+ viewRecommendCode);
		} else {	//��õ���� ��� ��
			viewRecommendCode = (int) request.getAttribute("recommendCode");
			System.out.println("getattribute�� �ڵ�: "+ viewRecommendCode);
		}
		
		LOG.info(String.valueOf(viewRecommendCode));
		recommend = recommendManager.displayRecommend(viewRecommendCode);
		solutionList = solutionManager.displayAllSolution(viewRecommendCode);	//recommendCode�� �ش��ϴ� �ַ�� ��� ������
		exist = recommendManager.existingBookmarkRecommend(viewRecommendCode, UserSessionUtils.getLoginUserId(session));
		empathized = recommendManager.existingEmpathizedRecommend(viewRecommendCode, UserSessionUtils.getLoginUserId(session));
		System.out.println("controller���� RecommendCode��: "+ viewRecommendCode +"Recommend����: "+ recommend.getTitle() + " �ϸ�ũ: "+exist+"  ��õ����:" + empathized);
		request.setAttribute("exist", exist);
		request.setAttribute("Recommend", recommend);
		request.setAttribute("SolutionList", solutionList);
		request.setAttribute("empathized", empathized);
		
		return "/recommend/ViewRecommend.jsp";		//ViewRecommend.jsp�� forwarding
	}

}
