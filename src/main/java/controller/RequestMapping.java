package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.AddAnswerController;
import controller.user.AddQuestionController;
import controller.user.AddRecommendController;
import controller.user.AddSolutionController;
import controller.user.AdminPostListController;
import controller.user.AdoptAnswerController;
import controller.user.BookmarkQuestionController;
import controller.user.BookmarkRecommendController;
import controller.user.DeleteAnswerController;
import controller.user.DeleteQuestionController;
import controller.user.DeleteRecommendController;
import controller.user.DeleteSolutionController;
import controller.user.FindKeywordController;
import controller.user.GoAnswerController;
import controller.user.GoMainPageController;
import controller.user.GoSolutionController;
import controller.user.GoUpdateSubjectController;
import controller.user.ListQuestionController;
import controller.user.ListRecommendController;
import controller.user.LoginController;
import controller.user.MyViewController;
import controller.user.QuestionFilterController;
import controller.user.RecommendSortController;
import controller.user.ScoreController;
import controller.user.UpdateSubjectController;
import controller.user.ViewQuestionController;
import controller.user.ViewRecommendController;

/*//나중에 수정해야함
import controller.user.*;
import controller.comm.*;
*/

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	
    	
        mappings.put("/", new ForwardController("index.jsp"));
        //mappings.put("/user/login/form", new ForwardController("/user/AddSolution.jsp")); //솔루션 등록 테스트
       // mappings.put("/user/login/form", new ViewRecommendController());
        //mappings.put("/user/login/form", new GoMainPageController());
        
        //로그인
        //로그인 폼으로 이동
        mappings.put("/user/login/form", new ForwardController("/user/LogInUser.jsp"));
        //로그인 확인-세션에 등록
        mappings.put("/user/tryLogin", new LoginController());
        
        //mappings.put("/user/logout", new LogoutController());
        
        
        //회원가입 폼으로 이동
        mappings.put("/user/join/form", new ForwardController("/user/JoinUser.jsp"));
        
        /*
        //중복체크
        mappings.put("/user/checkNickname", new CheckNicknameController());
        //회원가입
        mappings.put("/user/join", new JoinController());
        */
        //관리자
        //관리자-사용자 리스트 조회
        mappings.put("/user/adminUserList", new ForwardController("/user/AdminUser.jsp"));
        //관리자-게시글 조회
        mappings.put("/user/adminPostList", new AdminPostListController());
        /*
        //관리자-사용자 정보 수정 폼 요청과 수정 요청 처리 병합
        mappings.put("/user/adminUpdateUser", new UpdateUserController());
        //관리자-사용자 삭제
        mappings.put("/user/adminDeleteUserInfo", new DeleteUserController());
        //관리자-게시글 삭제
        mappings.put("/user/adminDeleteContent", new DeleteContentController());
        */
        
        //메인페이지
        //메인페이지로 이동(로고 누르면 메인페이지로 이동)
        mappings.put("/user/goMain", new GoMainPageController());
        //키워드 검색
        mappings.put("/user/findKeyword", new FindKeywordController());
        
        
        
        //마이페이지
        //마이페이지로 이동(메인페이지에서 닉네임, 나의질문, 북마크 클릭하면 마이페이지로 이동)-메인페이지에 정의
        //관심과목 조회, 나의 질문 조회, 나의 북마크 조회
        mappings.put("/user/myPage", new MyViewController());
        //관심과목 수정 폼으로 이동
        mappings.put("/user/updateSubject/form", new GoUpdateSubjectController()); 
        //관심과목 수정
        mappings.put("/user/updateSubject", new UpdateSubjectController());
    
        
        //질문하기
        //질문 리스트 조회
        mappings.put("/user/questionlist", new ListQuestionController());
        //질문하기로 이동
        mappings.put("/user/addquestion/form", new ForwardController("/user/AddQuestion.jsp"));
        //질문 등록
        mappings.put("/user/addquestion", new AddQuestionController());
        //질문 삭제
        mappings.put("/user/deletequestion", new DeleteQuestionController());
        //질문 필터링
        mappings.put("/user/filterquestion", new QuestionFilterController());
        
        
        //답변 폼 이동
        mappings.put("/user/addanswer/form", new GoAnswerController());
        //답변 등록
        mappings.put("/user/registeranswer", new AddAnswerController());
        //답변 삭제
        mappings.put("/user/deleteanswer", new DeleteAnswerController());
        //답변 채택
        mappings.put("/user/adoptanswer", new AdoptAnswerController());
        
        
        //한 질문 조회
         mappings.put("/user/viewquestion", new ViewQuestionController());
         mappings.put("/WEB-INF/user/viewquestion", new ViewQuestionController());		//답변 등록 후 viewQuestion으로 갈 때 WEB-INF 붙어서 uri전달됨 왜인지 몰겠음
         
        //질문 북마크
    	mappings.put("/user/bookmarkQuestion", new BookmarkQuestionController());
        
        
        
        //추천문제
        //문제 리스트 조회
        mappings.put("/user/recommendlist", new ListRecommendController());
        //추천 문제 정렬(최신순, 추천순)
        mappings.put("/user/sortList", new RecommendSortController());
        //문제 등록하기로 이동
        mappings.put("/user/addRecommend/form", new ForwardController("/user/AddRecommend.jsp"));
        //추천문제 등록
        mappings.put("/user/addRecommend", new AddRecommendController());
        //추천문제 삭제
        mappings.put("/user/deleteRecommend", new DeleteRecommendController());
        //답변(솔루션등록) 폼 이동
        mappings.put("/user/addRecommendSolution/form", new GoSolutionController());
        
        
        //답변(솔루션) 등록
        mappings.put("/user/addRecommendSolution", new AddSolutionController());
        //답변 삭제
        mappings.put("/user/deleteSolution", new DeleteSolutionController());
        //답변 별점 매기기
        mappings.put("/user/updateRecommendScore", new ScoreController());
        //문제 추천하기
        //mappings.put("/user/recommendCount", new UpdateRecommendCountController());
        //추천문제 북마크
        mappings.put("/user/bookmarkRecommend", new BookmarkRecommendController());
        
  
         //한 추천문제 조회
         mappings.put("/user/viewrecommend", new ViewRecommendController());
         mappings.put("/WEB-INF/user/viewrecommend", new ViewRecommendController());		//답변 등록 후 viewRecommend으로 갈 때 WEB-INF 붙어서 uri전달됨 왜인지 몰겠음
         
        
        
        logger.info("Initialized Request Mapping!");
        
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
