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
		
		Question question = new Question(	//postDate,postId,solve,questionAdopt는 DB에 insert 시 sysdate,sequence,기본값으로 지정, 사용자가 입력하는 값 아님
				request.getParameter("title"),
				request.getParameter("questionContent"),
				Integer.parseInt(UserSessionUtils.getLoginUserId(session)),	//userId도 request parameter로 받지 않고 session에서 받음
				request.getParameter("questionLanguage"),
				Integer.parseInt(request.getParameter("subjectId"))
				);
		
		
		QuestionManager manager = QuestionManager.getInstance();
		
		manager.addQuestion(question);		//질문 추가
		
		return "redirect:/user/viewquestion";	// /user/viewquestion로 redirect하면 한 질문 조회 요청이 발생하고 DispatcherServlet으로 돌아와서 
												// ViewQuesetionController로 전달되어 해당 질문 조회 화면 출력
	}

}

