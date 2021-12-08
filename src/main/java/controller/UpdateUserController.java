package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.service.UserManager;

public class UpdateUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserManager userManager = UserManager.getInstance();
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = userManager.findUser(userId);
		
		if(request.getMethod().equals("GET")) {	//회원 정보 조회 form으로 이동
			request.setAttribute("user", user);
			return "/user/system_userinfo.jsp";
		}
		
		HttpSession session = request.getSession();	
		//POST 처리 (수정)
		
		User modifyUser = new User(userId, request.getParameter("password"), request.getParameter("nickname"), request.getParameter("name"));
		int result = userManager.update(modifyUser);
		
		return "redirect:/user/adminUserList";
	}

}
