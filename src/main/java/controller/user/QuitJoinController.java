package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;


public class QuitJoinController implements Controller {
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			HttpSession session = request.getSession();
			session.invalidate();
			
            return "redirect:/user/login/form";
    }

}