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
		
		manager.addQuestion(question);		//질문 추가
		
		return "redirect:/user/viewquestion";	// /user/viewquestion로 redirect하면 한 질문 조회 요청이 발생하고 DispatcherServlet으로 돌아와서 
												// ViewQuesetionController로 전달되어 해당 질문 조회 화면 출력
	}

}
