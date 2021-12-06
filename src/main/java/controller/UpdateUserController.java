package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.UserDAO;
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
		
		//POST 처리 (수정, 삭제)
		String button = request.getParameter("button");
		if(button.equals("delete")) {
			//delete User
			System.out.println(userId+"삭제");
			userManager.delete(userId);
			//이 user가 작성한 글들 다 찾아서 userId를 탈퇴한 사람들용으로 하나 사용자 만들어서 거기로 연결?
			
		}
		else if(button.equals("modify")){	//update User, 닉네임 중복체크하면 여기에도 추가, userid는 제약조건 때문에 걍 못 바꾸는 걸로 하는 게 편할듯...
			User modifyUser = new User(userId, request.getParameter("password"), request.getParameter("nickname"), request.getParameter("name"));
			userManager.update(modifyUser);
		}
		
		return "redirect:/user/adminUserList";
	}

}
