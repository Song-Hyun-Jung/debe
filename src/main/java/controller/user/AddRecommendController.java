package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Recommend;
import model.service.RecommendManager;

public class AddRecommendController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		
		Recommend recommend = new Recommend(	//postDate,postId�� DB�� insert �� sysdate,sequence�� �����ؼ� ����ڰ� �Է��ϴ� �� �ƴ�
				request.getParameter("title"),
				UserSessionUtils.getLoginUserId(session),	//userId�� session���� ����
				request.getParameter("difficulty"),
				request.getParameter("algorithm"),
				request.getParameter("recommendContent")
				);
		
		System.out.println("addRecommendController���� userid��: "+UserSessionUtils.getLoginUserId(session));
		RecommendManager manager = RecommendManager.getInstance();
		
		int recommendCode = manager.addRecommend(recommend);		//���� �߰��ϰ� �ش� ������ questionCode �� ������
		
		request.setAttribute("recommendCode", recommendCode);		//viewrecommend���� ���� �� questionCode�� �ش��ϴ� ���� ��ȸ
		System.out.println("addrecommendController���� recommendCode��: "+recommendCode);
		
		return "/user/viewrecommend";
	}

	
	

}
