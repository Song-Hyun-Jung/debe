package controller.user;

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
		
		Question question = new Question(	//postDate,postId,solve,questionAdopt는 DB에 insert 시 sysdate,sequence,기본값으로 지정, 사용자가 입력하는 값 아님
				request.getParameter("title"),
				request.getParameter("questionContent"),
				Integer.parseInt(UserSessionUtils.getLoginUserId(session)),	//userId도 request parameter로 받지 않고 session에서 받음
				request.getParameter("questionLanguage"),
				Integer.parseInt(request.getParameter("subjectId"))
				);
		
		System.out.println("addquestionController에서 userid값: "+Integer.parseInt(UserSessionUtils.getLoginUserId(session)));
		QuestionManager manager = QuestionManager.getInstance();
		
		int questionCode = manager.addQuestion(question);		//질문 추가하고 해당 질문의 questionCode 값 가져옴
		
		request.setAttribute("questionCode", questionCode);		//viewquestion으로 가서 이 questinoCode에 해당하는 질문 조회
		System.out.println("addquestionController에서 questionCode값: "+questionCode);
		
		return "redirect:/user/questionlist";	// /user/questionlist로 redirect하면 한 질문 조회 요청이 발생하고 DispatcherServlet으로 돌아와서 
												// ListQuestionController로 전달되어 질문 목록 화면 출력
	}

}

