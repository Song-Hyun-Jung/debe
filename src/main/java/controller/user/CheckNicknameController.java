package controller.user;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;

public class CheckNicknameController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager userManager = UserManager.getInstance();
		HttpSession session = request.getSession();	
		
		boolean existNickname = userManager.existingNickname(request.getParameter("userNickname"));
		session.setAttribute("existNickname", existNickname);
		session.setAttribute("check", 1);
		
		session.setAttribute("userId", request.getParameter("userId"));
		session.setAttribute("userPassword", request.getParameter("userPassword"));
		session.setAttribute("userPasswordCheck", request.getParameter("userPasswordCheck"));
		session.setAttribute("userNickname", request.getParameter("userNickname"));
		session.setAttribute("userName", request.getParameter("userName"));
		session.setAttribute("subjectId", request.getParameter("subjectId"));
		
		
		return "redirect:/user/join/form";
	}
}