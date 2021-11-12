package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;

public class UpdateSubjectController implements Controller {
	
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 
		 String userId = request.getParameter("userId");
		 User selectSubject = new User(
				 Integer.parseInt(request.getParameter("subjectId")),
				 request.getParameter("subjectTitle"));
		 
		 UserManager manager = UserManager.getInstance();
		 manager.update(selectSubject);
		 
		 return "/user/myPage.jsp";
	 }
}
