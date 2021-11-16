package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.QuestionManager;

public class DeleteQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));	//delete�� questionCode
		String postUserId = request.getParameter("userId");			//delete�� question�� �ۼ��� userId, ���� ���� Ȯ�� ����
		
		QuestionManager manager = QuestionManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser("admin", session)	// �α����� ����ڰ� �������� ���
				   || 												// �Ǵ� 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴ�����
				  UserSessionUtils.isLoginUser(postUserId, session))) { // �α����� ����ڰ� �ۼ��� ������ ���
					
				manager.deleteQuestion(questionCode);				// ���� ����
				
				return "redirect:/user/questionlist";		// ���� ��� ȭ������ �̵� (redirect)	
			}
			
		//������ �ƴϰ� �α����� ����ڰ� �ۼ��� ������ �ƴ� ��� ���� ���� ���ϴµ� html���� ���� ��ư �� ���̰� ������ �Ÿ� ���� �� �ص� �ǰ�
		//html���� ���� �� �ϸ� ���⼭ �����ϱ�.
		
		return "redirect:/user/questionlist";
	}

}
