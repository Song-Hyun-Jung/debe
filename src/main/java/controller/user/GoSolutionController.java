package controller.user;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Controller;


public class GoSolutionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		String recommendCode = request.getParameter("recommendCode");
		System.out.println(request.getParameter("recommendCode"));
		request.setAttribute("recommendCode", recommendCode);
		
		return "/user/AddSolution.jsp";	
	}

}

