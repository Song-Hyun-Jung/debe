package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Question;
import model.service.QuestionManager;

public class AddQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		
		Question question = new Question(	//postDate,postId,solve,questionAdopt�� DB�� insert �� sysdate,sequence,�⺻������ ����, ����ڰ� �Է��ϴ� �� �ƴ�
				request.getParameter("title"),
				request.getParameter("questionContent"),
				UserSessionUtils.getLoginUserId(session),	//userId�� request parameter�� ���� �ʰ� session���� ����
				request.getParameter("questionLanguage"),
				Integer.parseInt(request.getParameter("subjectId"))
				);
		
		System.out.println("addquestionController���� userid��: "+UserSessionUtils.getLoginUserId(session));
		QuestionManager manager = QuestionManager.getInstance();
		
		int questionCode = manager.addQuestion(question);		//���� �߰��ϰ� �ش� ������ questionCode �� ������
		
		request.setAttribute("questionCode", questionCode);		//viewquestion���� ���� �� questionCode�� �ش��ϴ� ���� ��ȸ
		System.out.println("addquestionController���� questionCode��: "+questionCode);
		
		return "/user/viewquestion";
	}

}
