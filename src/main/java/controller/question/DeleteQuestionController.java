package controller.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.QuestionManager;
import controller.user.UserSessionUtils;

public class DeleteQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));	//delete�� questionCode
		String postUserId = request.getParameter("userId");			//delete�� question�� �ۼ��� userId, ���� ���� Ȯ�� ����
		System.out.println("delete question postUserId��: " + postUserId + ", ��id:   "+questionCode);
		String redirect = "redirect:/user/questionlist";
		
		if(request.getParameter("admin") != null) {
			redirect = "redirect:/user/adminPostList";
		}
		
		QuestionManager manager = QuestionManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser(11111111, session)	// �α����� ����ڰ� ������(userId�� 11111111)�� ���
				   || 												// �Ǵ� 
				(!UserSessionUtils.isLoginUser(11111111, session) &&  // �α����� ����ڰ� �����ڰ� �ƴ�����
				  UserSessionUtils.isLoginUser(Integer.parseInt(postUserId), session))) { // �α����� ����ڰ� �ۼ��� ������ ���
					
				manager.deleteQuestion(questionCode);				// ���� ����
				
				return redirect;	
			}
			
		
		return redirect;
	}

}
