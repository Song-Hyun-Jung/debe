package controller.user;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import model.Question;
import model.Recommend;
import controller.Controller;
import model.service.QuestionManager;
import model.service.RecommendManager;


public class FindKeywordController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		String indexButton = request.getParameter("indexButton");
		int sqIndex = 0;
		 if (request.getParameter("sqIndex") != null) { sqIndex = Integer.parseInt(request.getParameter("sqIndex")); }
		 int srIndex = 0;
		 if (request.getParameter("srIndex") != null) { srIndex = Integer.parseInt(request.getParameter("srIndex")); }
		
		if(indexButton != null) {	//index 눌러서 들어온 경우에는 session에 저장된 리스트 get
			session.getAttribute("findQuestionResult");
			session.getAttribute("findRecommendResult");
			session.getAttribute("searchKeyword");
		}
		else {
			String keyword = request.getParameter("keyword");
			Enumeration params = request.getParameterNames();
			while(params.hasMoreElements()) {
			  String name = (String) params.nextElement();
			  System.out.print(name + " : " + request.getParameter(name) + "     "); 
			}
			System.out.println();
			
			List<Question> findQuestionResult = null; //질문쪽 검색 결과
			QuestionManager questionManager = QuestionManager.getInstance();
			findQuestionResult = questionManager.findQuestion(keyword);
			
			List<Recommend> findRecommendResult = null; //추천문제쪽 검색 결과
			RecommendManager recommendManager = RecommendManager.getInstance();
			findRecommendResult = recommendManager.findRecommend(keyword);
			
			session.setAttribute("findQuestionResult", findQuestionResult);
			session.setAttribute("findRecommendResult", findRecommendResult);
			session.setAttribute("searchKeyword", keyword);
		}
		
		request.setAttribute("sqIndex", sqIndex);
		request.setAttribute("srIndex", srIndex);
	
		return "/user/SearchResult.jsp";	
	}

}

