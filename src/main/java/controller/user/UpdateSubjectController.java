package controller.user;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class UpdateSubjectController implements Controller {
	
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 
		 Enumeration params = request.getParameterNames();
			while(params.hasMoreElements()) {
			  String name = (String) params.nextElement();
			  System.out.print(name + " : " + request.getParameter(name) + "     "); 
			}
			System.out.println();
			
		HttpSession session = request.getSession();	 
		UserManager userManager = UserManager.getInstance();
		
		int userSubject = Integer.parseInt(request.getParameter("userSubject"));
		
		userManager.updateUserSubject(UserSessionUtils.getLoginUserId(session), userSubject);
		 
		 return "redirect:/user/myPage";
	 }
}
