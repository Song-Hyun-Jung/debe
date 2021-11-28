package controller.user;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Solution;
import model.service.SolutionManager;

public class DeleteSolutionController implements Controller{
	
	final Logger LOG = Logger.getGlobal();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("�Ѿ�Գ�?");
		LOG.info("�Ѿ� �°�:" + request.getParameter("solutionCode"));
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		int solutionCode = Integer.parseInt(request.getParameter("solutionCode"));
		String postUserId = request.getParameter("solutionUserId");		//delete�� answer�� �ۼ��� userId
		
		System.out.println(solutionCode + " " +postUserId);
		
		SolutionManager manager = SolutionManager.getInstance();		
		HttpSession session = request.getSession();	
		
		/*if (UserSessionUtils.isLoginUser("admin", session)	// �α����� ����ڰ� �������� ���
				   || 												// �Ǵ� 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴ�����
				  UserSessionUtils.isLoginUser(postUserId, session))) { // �α����� ����ڰ� �ۼ��� �亯�� ���
					
				manager.deleteSolution(solutionCode);				// �亯 ����
				
				return "redirect:/user/viewrecommend";
			}*/
		
		manager.deleteSolution(solutionCode);	
		
		request.setAttribute("recommendCode", request.getParameter("recommendCode"));
		return "/user/viewrecommend";
	}

}

