package controller.user;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;

public class AddQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();	
		
		Question question = new Question(	//postDate,postId,solve,questionAdopt�� DB�� insert �� sysdate,sequence,�⺻������ ����, ����ڰ� �Է��ϴ� �� �ƴ�
				request.getParameter("title"),
				request.getParameter("questionContent"),
				Integer.parseInt(UserSessionUtils.getLoginUserId(session)),	//userId�� request parameter�� ���� �ʰ� session���� ����
				request.getParameter("questionLanguage"),
				Integer.parseInt(request.getParameter("subjectId"))
				);
		
		
		QuestionManager manager = QuestionManager.getInstance();
		
		manager.addQuestion(question);		//���� �߰�
		
		return "redirect:/user/viewquestion";	// /user/viewquestion�� redirect�ϸ� �� ���� ��ȸ ��û�� �߻��ϰ� DispatcherServlet���� ���ƿͼ� 
												// ViewQuesetionController�� ���޵Ǿ� �ش� ���� ��ȸ ȭ�� ���
	}

}

