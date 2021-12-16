package controller.question;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.Subject;
import model.service.QuestionManager;

public class QuestionFilterController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		List<Question> questionList = new ArrayList<Question>();
		QuestionManager manager = QuestionManager.getInstance();
		String filter = request.getParameter("filter");		//� ���͸� ��ư ��������
		String language = "no";
		String subjectTitle = "no";
		String solved = "no";
		int questionIndex = 0;
		
		if (request.getParameter("questionIndex") != null) {
			questionIndex = Integer.parseInt(request.getParameter("questionIndex"));
			System.out.println("quesitonIndex���� null �ƴ�: "+questionIndex);
		}
		
		if(filter == null) {	//���͸� ��ư���� ��Ʈ�ѷ� �� �� �ƴϰ� �ε��� ������ ��
			session.getAttribute("questionList");
		}	
		else {
			if(filter.equals("filterLS")) {
				language = request.getParameter("language");
				subjectTitle = request.getParameter("subjectTitle");
	
			} else if(filter.equals("filterSolved")) {
				solved = request.getParameter("solved");		//���� y or n
			}
			
			questionList = manager.filterQuestion(filter, language, subjectTitle, solved);
			session.setAttribute("questionList", questionList);		//���͸� ��ư ���� �ε��� ������ ������ ���� ����� �� �ֵ���
		}
		
		List<Subject> subjectList = manager.getAllSubject();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("questionIndex", questionIndex);
	
		return "/question/DisplayFilterQuestion.jsp";		// DisplayFilterQuestion.jsp�� forwarding
	}

}
