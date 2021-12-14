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
import controller.user.AdminUserListController;
import controller.user.AdoptAnswerController;
import controller.user.BookmarkQuestionController;
import controller.user.BookmarkRecommendController;
import controller.user.CheckNicknameController;
import controller.user.DeleteAnswerController;
import controller.user.DeleteQuestionController;
import controller.user.DeleteRecommendController;
import controller.user.DeleteSolutionController;
import controller.user.FindKeywordController;
import controller.user.GoAddQuestionController;
import controller.user.GoAnswerController;
import controller.user.GoJoinController;
import controller.user.GoMainPageController;
import controller.user.GoSolutionController;
import controller.user.GoUpdateSubjectController;
import controller.user.ListQuestionController;
import controller.user.ListRecommendController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.MyViewController;
import controller.user.QuestionFilterController;
import controller.user.QuitJoinController;
import controller.user.RecommendSortController;
import controller.user.RegisterUserController;
import controller.user.ScoreController;
import controller.user.UpdateRecommendCountController;
import controller.user.UpdateSubjectController;
import controller.user.UpdateUserController;
import controller.user.ViewQuestionController;
import controller.user.ViewRecommendController;


public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
    	
        mappings.put("/", new ForwardController("index.jsp"));
      
        //�α���
        //�α��� ������ �̵�
        mappings.put("/user/login/form", new ForwardController("/user/LogInUser.jsp"));
        //�α��� Ȯ��-���ǿ� ���
        mappings.put("/user/tryLogin", new LoginController());
        //�α׾ƿ�
        mappings.put("/user/logout", new LogoutController());
        
        
        //ȸ������ ������ �̵�
        mappings.put("/user/join/form", new GoJoinController());    
        //�ߺ�üũ
        mappings.put("/user/checkNickname", new CheckNicknameController());
        //ȸ������
        mappings.put("/user/join", new RegisterUserController());
        //ȸ������ �� ���� ����
        mappings.put("/user/quitJoin", new QuitJoinController());
        
        //������
        //������-����� ����Ʈ ��ȸ
        mappings.put("/user/adminUserList", new AdminUserListController());
        //������-�Խñ� ��ȸ
        mappings.put("/user/adminPostList", new AdminPostListController());
        
        //������-����� ���� ���� �� ��û�� ���� ��û ó�� ����
        mappings.put("/user/adminUpdateUser", new UpdateUserController());
        
        
        //����������
        //������������ �̵�(�ΰ� ������ ������������ �̵�)
        mappings.put("/user/goMain", new GoMainPageController());
        //Ű���� �˻�
        mappings.put("/user/findKeyword", new FindKeywordController());
        
        
        
        //����������
        //������������ �̵�(�������������� �г���, ��������, �ϸ�ũ Ŭ���ϸ� ������������ �̵�)-������������ ����
        //���ɰ��� ��ȸ, ���� ���� ��ȸ, ���� �ϸ�ũ ��ȸ
        mappings.put("/user/myPage", new MyViewController());
        //���ɰ��� ���� ������ �̵�
        mappings.put("/user/updateSubject/form", new GoUpdateSubjectController()); 
        //���ɰ��� ����
        mappings.put("/user/updateSubject", new UpdateSubjectController());
    
        
        //�����ϱ�
        //���� ����Ʈ ��ȸ
        mappings.put("/user/questionlist", new ListQuestionController());
        //�����ϱ�� �̵�
        mappings.put("/user/addquestion/form", new GoAddQuestionController());
        //���� ���
        mappings.put("/user/addquestion", new AddQuestionController());
        //���� ����
        mappings.put("/user/deletequestion", new DeleteQuestionController());
        //���� ���͸�
        mappings.put("/user/filterquestion", new QuestionFilterController());
        
        
        //�亯 �� �̵�
        mappings.put("/user/addanswer/form", new GoAnswerController());
        //�亯 ���
        mappings.put("/user/registeranswer", new AddAnswerController());
        //�亯 ����
        mappings.put("/user/deleteanswer", new DeleteAnswerController());
        //�亯 ä��
        mappings.put("/user/adoptanswer", new AdoptAnswerController());
        
        
        //�� ���� ��ȸ
         mappings.put("/user/viewquestion", new ViewQuestionController());
         mappings.put("/WEB-INF/user/viewquestion", new ViewQuestionController());
         
        //���� �ϸ�ũ
    	mappings.put("/user/bookmarkQuestion", new BookmarkQuestionController());
        
        
        
        //��õ����
        //���� ����Ʈ ��ȸ
        mappings.put("/user/recommendlist", new ListRecommendController());
        //��õ ���� ����(�ֽż�, ��õ��)
        mappings.put("/user/sortList", new RecommendSortController());
        //���� ����ϱ�� �̵�
        mappings.put("/user/addRecommend/form", new ForwardController("/user/AddRecommend.jsp"));
        //��õ���� ���
        mappings.put("/user/addRecommend", new AddRecommendController());
        //��õ���� ����
        mappings.put("/user/deleteRecommend", new DeleteRecommendController());
        //�亯(�ַ�ǵ��) �� �̵�
        mappings.put("/user/addRecommendSolution/form", new GoSolutionController());
        
        
        //�亯(�ַ��) ���
        mappings.put("/user/addRecommendSolution", new AddSolutionController());
        //�亯 ����
        mappings.put("/user/deleteSolution", new DeleteSolutionController());
        //�亯 ���� �ű��
        mappings.put("/user/updateRecommendScore", new ScoreController());
        //���� ��õ�ϱ�
        mappings.put("/user/recommendCount", new UpdateRecommendCountController());
        //��õ���� �ϸ�ũ
        mappings.put("/user/bookmarkRecommend", new BookmarkRecommendController());
        
  
         //�� ��õ���� ��ȸ
         mappings.put("/user/viewrecommend", new ViewRecommendController());
         mappings.put("/WEB-INF/user/viewrecommend", new ViewRecommendController());
        
       
        logger.info("Initialized Request Mapping!");
        
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
