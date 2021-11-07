package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Answer;
import model.Question;

public class AdoptAnswerController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Question question = request.getParameter("Question");
		String userId = question.getUserId();
		Answer answer = request.getParameter("Answer");
		List<Answer> answerList = null;
		AnswerManager answerManager = AnswerManager.getInstance();	
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser(userId, session)) { // �α����� ����ڰ� �ش� question�� �ۼ���(question��ü�� userId)�� ���		
				answerManager.AdoptAnswer(question, answer);	//�ش� question�� �ش� answer ä��
				
				return "redirect:/user/viewquestion";
			}
		
		//������ �ƴϰ� �α����� ����ڰ� �ۼ��� ������ �ƴ� ��� ���� ���� ���ϴµ� html���� ���� ��ư �� ���̰� ������ �Ÿ� ���� �� �ص� �ǰ�
				//html���� ���� �� �ϸ� ���⼭ �����ϱ�.

		return "redirect:/user/viewquestion";
	}

}
