package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Subject;
import model.User;
import model.Bookmark;
import model.Question;
import model.service.UserManager;

public class MyViewController implements Controller {
	
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 HttpSession session = request.getSession();	
		 System.out.println(UserSessionUtils.getLoginUserId(session));
		 
		 
		 UserManager userManager = UserManager.getInstance();
		 
		 Subject userSubject = userManager.findUserSubjectName(UserSessionUtils.getLoginUserId(session)); //사용자 관심 과목 조회
		 List<Question> findMyQuestions = userManager.findMyQuestions(UserSessionUtils.getLoginUserId(session)); //사용자 질문 조회
		 List<Bookmark> findMyQuestionBookmarks = userManager.findMyQuestionBookmarks(UserSessionUtils.getLoginUserId(session)); //사용자 질문 북마크 조회
		 List<Bookmark> findMyRecommendBookmarks = userManager.findMyRecommendBookmarks(UserSessionUtils.getLoginUserId(session)); //사용자 추천문제 북마크 조회
		 
		 request.setAttribute("userSubject", userSubject);
		 request.setAttribute("findMyQuestions", findMyQuestions);
		 request.setAttribute("findMyQuestionBookmarks", findMyQuestionBookmarks);
		 request.setAttribute("findMyRecommendBookmarks", findMyRecommendBookmarks);
		 
		 return "/user/MyPage.jsp";
	 }
}