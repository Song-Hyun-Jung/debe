package controller.recommend;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Solution;
import model.service.SolutionManager;

public class ScoreController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		try {
			int recommendCode = Integer.parseInt(request.getParameter("recommendCode"));
			int solutionCode = Integer.parseInt(request.getParameter("solutionCode"));		
			int score = Integer.parseInt(request.getParameter("score"));			
			
			SolutionManager solutionManager = SolutionManager.getInstance();	
			HttpSession session = request.getSession();	
			
			int alreadyScore = solutionManager.alreadyScore(solutionCode, (int)session.getAttribute("userId"), score);
			System.out.println("alreadyscore  " + alreadyScore);
			if(alreadyScore == 0)
				solutionManager.giveScore(solutionCode, (int)session.getAttribute("userId"), score);
			
			request.setAttribute("alreadyScore", alreadyScore);
			request.setAttribute("recommendCode", request.getParameter("recommendCode"));
			return "/user/viewrecommend";
		}
		catch(Exception e) {
			 System.out.println("failed");
			 return "/user/viewrecommend";
		}
		
	}

}

