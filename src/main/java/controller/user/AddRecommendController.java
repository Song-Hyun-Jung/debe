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
		
		Recommend recommend = new Recommend(	//postDate,postId는 DB에 insert 시 sysdate,sequence로 지정해서 사용자가 입력하는 값 아님
				request.getParameter("title"),
				UserSessionUtils.getLoginUserId(session),	//userId는 session에서 받음
				request.getParameter("difficulty"),
				request.getParameter("algorithm"),
				request.getParameter("recommendContent")
				);
		
		System.out.println("addRecommendController에서 userid값: "+UserSessionUtils.getLoginUserId(session));
		RecommendManager manager = RecommendManager.getInstance();
		
		int recommendCode = manager.addRecommend(recommend);		//질문 추가하고 해당 질문의 questionCode 값 가져옴
		
		request.setAttribute("recommendCode", recommendCode);		//viewrecommend으로 가서 이 questionCode에 해당하는 질문 조회
		System.out.println("addrecommendController에서 recommendCode값: "+recommendCode);
		
		return "/user/viewrecommend";
	}

	
	

}
