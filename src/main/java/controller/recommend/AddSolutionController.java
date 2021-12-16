package controller.recommend;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.DispatcherServlet;
import model.Solution;
import model.Recommend;
import model.service.AnswerManager;
import model.service.SolutionManager;

public class AddSolutionController implements Controller{

	final Logger LOG = Logger.getGlobal();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
		//LOG.info("넘어 온것:" + request.getParameter("recommendCode"));
		HttpSession session = request.getSession();	
		Solution solution = new Solution(		//answerId, answerAdopt는 db에 insert 시에 sequence, 기본값으로 지정, answerDate는 insert시 sysdate로
				Integer.parseInt(request.getParameter("recommendCode")),		//답변 어느 질문에 달았는지, 해당 질문의 postId
				request.getParameter("solutionCodes"),
				Integer.parseInt(String.valueOf(session.getAttribute("userId")))
				);
		
		SolutionManager manager = SolutionManager.getInstance();
		
		manager.addSolution(solution);	//Answer 객체 추가
	
	
		request.setAttribute("recommendCode", request.getParameter("recommendCode"));
		return "/user/viewrecommend";	
	}

}

