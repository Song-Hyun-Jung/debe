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
		
		if(request.getMethod().equals("GET")) {	//ȸ�� ���� ��ȸ form���� �̵�
			request.setAttribute("user", user);
			return "/user/system_userinfo.jsp";
		}
		
		//POST ó�� (����, ����)
		String button = request.getParameter("button");
		if(button.equals("delete")) {
			//delete User
			System.out.println(userId+"����");
			userManager.delete(userId);
			//�� user�� �ۼ��� �۵� �� ã�Ƽ� userId�� Ż���� ���������� �ϳ� ����� ���� �ű�� ����?
			
		}
		else if(button.equals("modify")){	//update User, �г��� �ߺ�üũ�ϸ� ���⿡�� �߰�, userid�� �������� ������ �� �� �ٲٴ� �ɷ� �ϴ� �� ���ҵ�...
			User modifyUser = new User(userId, request.getParameter("password"), request.getParameter("nickname"), request.getParameter("name"));
			userManager.update(modifyUser);
		}
		
		return "redirect:/user/adminUserList";
	}

}
