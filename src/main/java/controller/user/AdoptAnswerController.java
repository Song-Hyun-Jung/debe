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

		String postUserId = request.getParameter("userId");	//답변 채택할 질문 작성자 id
		String questionCode = request.getParameter("questionCode");		//채택할 답변의 질문 id
		String answerCode = request.getParameter("answerCode");			//채택할 답변 id
		
		AnswerManager answerManager = AnswerManager.getInstance();	
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser(postUserId, session)) { // 현재 로그인한 사용자가 해당 question의 작성자(question객체의 userId)일 경우		
				answerManager.AdoptAnswer(questionCode, answerCode);	//해당 question의 해당 answer 채택
				
				return "redirect:/user/viewquestion";
			}
		
		//관리자 아니고 로그인한 사용자가 작성한 질문이 아닌 경우 질문 삭제 못하는데 html에서 삭제 버튼 안 보이게 설정할 거면 따로 안 해도 되고
				//html에서 설정 못 하면 여기서 구현하기.

		return "redirect:/user/viewquestion";
	}

}

