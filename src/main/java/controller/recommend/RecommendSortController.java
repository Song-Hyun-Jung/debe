package controller.recommend;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Recommend;
import model.service.RecommendManager;

public class RecommendSortController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		List<Recommend> recommendList = new ArrayList<Recommend>();
		RecommendManager manager = RecommendManager.getInstance();
		String sort = request.getParameter("sortRecommend");		//� �������� ������ ����
		
		int recommendIndex = 0;
		
		if (request.getParameter("recommendIndex") != null) {
			recommendIndex = Integer.parseInt(request.getParameter("recommendIndex"));
			System.out.println("recommendIndex���� null �ƴ�: "+recommendIndex);
		}
		
		if(sort == null) {	//���͸� ��ư���� ��Ʈ�ѷ� �� �� �ƴϰ� �ε��� ������ ��
			session.getAttribute("recommendList");
		}	
		else {
			recommendList = manager.sortRecommend(sort);
			session.setAttribute("recommendList", recommendList);		//���͸� ��ư ���� �ε��� ������ ������ ���� ����� �� �ֵ���
		}
		
		request.setAttribute("recommendIndex", recommendIndex);
	
		return "/recommend/DisplaySortRecommend.jsp";
	}
	
}
