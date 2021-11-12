package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;

public class RegisterUserController implements Controller {
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		User user = new User(
				request.getParameter("userId"),
				request.getParameter("userPassword"),
				request.getParameter("userNickname"),
				request.getParameter("userName"));
		
		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			
			return "redirect:/user/list";
		} catch (ExistingUserException e) {
			
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/JoinUser.jsp";
		}
		
	}
}
