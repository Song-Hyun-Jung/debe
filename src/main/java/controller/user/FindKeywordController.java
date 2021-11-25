package controller.user;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import model.Question;
import model.Recommend;
import controller.Controller;
import model.service.QuestionManager;
import model.service.RecommendManager;


public class FindKeywordController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		List<Question> findQuestionResult = null; //질문쪽 검색 결과
		QuestionManager questionManager = QuestionManager.getInstance();
		findQuestionResult = questionManager.findQuestion(request.getParameter("keyword"));
		
		List<Recommend> findRecommendResult = null; //추천문제쪽 검색 결과
		RecommendManager recommendManager = RecommendManager.getInstance();
		findRecommendResult = recommendManager.findRecommend(request.getParameter("keyword"));
		
		request.setAttribute("findQuestionResult", findQuestionResult);
		request.setAttribute("findRecommendResult", findRecommendResult);
		request.setAttribute("searchKeyword", request.getParameter("keyword"));
	
		return "/user/SearchResult.jsp";	
	}

}

