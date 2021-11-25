package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class LoginController implements Controller {
	
		
	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	
	    	
	    	int userId = Integer.parseInt(request.getParameter("userId"));
			String password = request.getParameter("userPassword");
			
			System.out.println(userId + " " + password);
			
			try {
				UserManager manager = UserManager.getInstance();
				manager.login(userId, password);
				User user = manager.findUser(userId);
		
				HttpSession session = request.getSession();
	            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
	            session.setAttribute("userNickname", user.getUserNickname());
	            session.setAttribute("userLevel", user.getUserLevel());
	           
	            return "redirect:/user/goMain";
	            
			} catch (Exception e) {
	            request.setAttribute("loginFailed", true);
				request.setAttribute("exception", e);
	            return "/user/LogInUser.jsp";			
			}	
	    }
			
}
