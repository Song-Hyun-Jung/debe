package controller.user;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Solution;
import model.service.SolutionManager;

public class DeleteSolutionController implements Controller{
	
	final Logger LOG = Logger.getGlobal();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("넘어왔나?");
		LOG.info("넘어 온것:" + request.getParameter("solutionCode"));
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		int solutionCode = Integer.parseInt(request.getParameter("solutionCode"));
		String postUserId = request.getParameter("solutionUserId");		//delete할 answer을 작성한 userId
		
		System.out.println(solutionCode + " " +postUserId);
		
		SolutionManager manager = SolutionManager.getInstance();		
		HttpSession session = request.getSession();	
		
		/*if (UserSessionUtils.isLoginUser("admin", session)	// 로그인한 사용자가 관리자인 경우
				   || 												// 또는 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니지만
				  UserSessionUtils.isLoginUser(postUserId, session))) { // 로그인한 사용자가 작성한 답변인 경우
					
				manager.deleteSolution(solutionCode);				// 답변 삭제
				
				return "redirect:/user/viewrecommend";
			}*/
		
		manager.deleteSolution(solutionCode);	
		
		request.setAttribute("recommendCode", request.getParameter("recommendCode"));
		return "/user/viewrecommend";
	}

}

