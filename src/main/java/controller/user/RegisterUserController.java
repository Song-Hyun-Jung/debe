package controller.user;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		HttpSession session = request.getSession();	
		
		User user = new User(
						Integer.parseInt(request.getParameter("userId")),
						request.getParameter("userPassword"),
						request.getParameter("userNickname"),
						request.getParameter("userName"),
						Integer.parseInt(request.getParameter("subjectId"))
					);
		
		try {
			UserManager userManager = UserManager.getInstance();
			userManager.create(user);
			session.setAttribute("registerFailed", false);
			return "/user/LogInUser.jsp";
		} catch (ExistingUserException e) {
			session.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "redirect:/user/join/form";
		}
		
	}
}
