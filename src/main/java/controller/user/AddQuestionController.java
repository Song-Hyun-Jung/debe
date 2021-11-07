package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Question;

public class AddQuestionController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Question question = request.getParameter("Question");
		QuestionManager manager = QuestionManager.getInstance();
		
		manager.addQuestion(question);		//���� �߰�
		
		return "redirect:/user/viewquestion";	// /user/viewquestion�� redirect�ϸ� �� ���� ��ȸ ��û�� �߻��ϰ� DispatcherServlet���� ���ƿͼ� 
												// ViewQuesetionController�� ���޵Ǿ� �ش� ���� ��ȸ ȭ�� ���
	}

}
