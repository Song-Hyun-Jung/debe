package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.UserManager;


public class AdminUserListController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserManager userManager = UserManager.getInstance();
		List<User> userList = userManager.findUserList();

		int userIndex = 0;
		if (request.getParameter("userIndex") != null) {
			userIndex = Integer.parseInt(request.getParameter("userIndex"));
		}
		
		request.setAttribute("userList", userList);
		request.setAttribute("userIndex", userIndex);
		
		return "/user/AdminUser.jsp";
	}

}
