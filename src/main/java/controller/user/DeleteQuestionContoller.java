package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Question;
import model.User;
import model.service.UserManager;

public class DeleteQuestionContoller implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Question deletedQuestion = request.getParameter("Question");	//question��ü �޾ƿ�
		String postUserId = deletedQuestion.getUserId();		//delete�� question�� �ۼ��� userId
		
		QuestionManager manager = QuestionManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser("admin", session)	// �α����� ����ڰ� �������� ���
				   || 												// �Ǵ� 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴ�����
				  UserSessionUtils.isLoginUser(postUserId, session))) { // �α����� ����ڰ� �ۼ��� ������ ���
					
				manager.deleteQuestion(deletedQuestion);				// ���� ����
				
				return "/user/DisplayQuestion.jsp";		// ���� ��� ȭ������ �̵� (forwarding)	
			}
			
		//������ �ƴϰ� �α����� ����ڰ� �ۼ��� ������ �ƴ� ��� ���� ���� ���ϴµ� html���� ���� ��ư �� ���̰� ������ �Ÿ� ���� �� �ص� �ǰ�
		//html���� ���� �� �ϸ� ���⼭ �����ϱ�.
		
		return "/user/DisplayQuestion.jsp";
	}

}
