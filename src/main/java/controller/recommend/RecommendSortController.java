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
		String sort = request.getParameter("sortRecommend");		//어떤 기준으로 정렬할 건지
		
		int recommendIndex = 0;
		
		if (request.getParameter("recommendIndex") != null) {
			recommendIndex = Integer.parseInt(request.getParameter("recommendIndex"));
			System.out.println("recommendIndex값이 null 아님: "+recommendIndex);
		}
		
		if(sort == null) {	//필터링 버튼으로 컨트롤러 온 것 아니고 인덱스 눌렀을 때
			session.getAttribute("recommendList");
		}	
		else {
			recommendList = manager.sortRecommend(sort);
			session.setAttribute("recommendList", recommendList);		//필터링 버튼 말고 인덱스 페이지 눌렀을 때도 기억할 수 있도록
		}
		
		request.setAttribute("recommendIndex", recommendIndex);
	
		return "/recommend/DisplaySortRecommend.jsp";
	}
	
}
