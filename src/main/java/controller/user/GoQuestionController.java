package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;

public class GoQuestionController implements Controller {
	
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 
		 String contentId = request.getParameter("contentId");
		 return "/user/viewQuestion.jsp";
	 }
}