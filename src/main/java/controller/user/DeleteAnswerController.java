package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

public class DeleteAnswerController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Answer deletedAnswer = request.getParameter("Answer");	//Answer��ü �޾ƿ�
		String postUserId = deletedAnswer.getUserId();		//delete�� answer�� �ۼ��� userId
		
		AnswerManager manager = AnswerManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser("admin", session)	// �α����� ����ڰ� �������� ���
				   || 												// �Ǵ� 
				(!UserSessionUtils.isLoginUser("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴ�����
				  UserSessionUtils.isLoginUser(postUserId, session))) { // �α����� ����ڰ� �ۼ��� �亯�� ���
					
				manager.deleteAnswer(deletedAnswer);				// �亯 ����
				
				return "redirect:/user/viewquestion";
			}
			
		//������ �ƴϰ� �α����� ����ڰ� �ۼ��� �亯�� �ƴ� ��� �亯 ���� ���ϴµ� html���� ���� ��ư �� ���̰� ������ �Ÿ� ���� �� �ص� �ǰ�
		//html���� ���� �� �ϸ� ���⼭ �����ϱ�.
		
		return "redirect:/user/viewquestion";
	}

}
