package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.AddAnswerController;
import controller.user.AdoptAnswerController;
import controller.user.BookmarkQuestionController;
import controller.user.DeleteAnswerController;
import controller.user.ViewQuestionController;

/*//���߿� �����ؾ���
import controller.user.*;
import controller.comm.*;
*/

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
    	
    	
        mappings.put("/", new ForwardController("index.jsp"));
        //mappings.put("/user/login/form", new ForwardController("/user/LogInUser.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/AddAnswer.jsp"));
        
        /*
        //�α���
        //�α��� ������ �̵�
        mappings.put("/user/login/form", new ForwardController("/user/LogInUser.jsp"));
        //�α��� Ȯ��-���ǿ� ���?
        mappings.put("/user/login", new LoginController());
        
        //mappings.put("/user/logout", new LogoutController());
        
        //ȸ������ ������ �̵�
        mappings.put("/user/join/form", new ForwardController("/user/JoinUser.jsp"));
        //�ߺ�üũ
        mappings.put("/user/checkNickname", new CheckNicknameController());
        //ȸ������
        mappings.put("/user/join", new JoinController());
        
        //������
        //������-����� ����Ʈ ��ȸ
        mappings.put("/user/adminList", new ListUserController());
        //������-����� ���� ���� �� ��û�� ���� ��û ó�� ����
        mappings.put("/user/adminUpdateUser", new UpdateUserController());
        //������-����� ����
        mappings.put("/user/adminDeleteUserInfo", new DeleteUserController());
        //������-�Խñ� ����
        mappings.put("/user/adminDeleteContent", new DeleteContentController());
        
        //����������
        //������������ �̵�(�ΰ� ������ ������������ �̵�)
        mappings.put("/user/goMain", new ForwardController("/user/main.jsp"));
        //Ű���� �˻�
        mappings.put("/user/findKeyword", new FindKeywordController());
        //���� �������� �̵�
        mappings.put("/user/goMyPage", new ForwardController("/user/MyPage.jsp"));
        
        
        
        
        //����������
        //������������ �̵�(�������������� �г���, ��������, �ϸ�ũ Ŭ���ϸ� ������������ �̵�)-������������ ����
        //���ɰ��� ��ȸ, ���� ���� ��ȸ, ���� �ϸ�ũ ��ȸ
        mappings.put("/user/myPage", new MyViewController());
        //���ɰ��� ���� ������ �̵�
        mappings.put("/user/updateSubjcet/form", new ForwardController("UpdateMySubjects.jsp"));
        //���ɰ��� ����
        mappings.put("/user/updateSubject", new UpdateSubjectController());
        //�ϸ�ũ, ���� �������� Q&A�׸� ����
        mappings.put("/user/goQuestion", new goQuestionController());
        //�ϸ�ũ���� ��õ���� �׸� ����
        mappings.put("/user/goRecommend", new goRecommendController());
        
        //�����ϱ�
        //���� ����Ʈ ��ȸ
        mappings.put("/user/questionlist", new ListQuestionController());
        //�����ϱ�� �̵�
        mappings.put("/user/addquestion/form", new ForwardController("/user/AddQuestion.jsp"));
        //���� ���
        mappings.put("/user/addquestion", new AddQuestionController());
        //���� ����
        mappings.put("/user/deletequestion", new DeleteQuestionController());
        */
        
        
        //�亯 �� �̵�
        mappings.put("/user/addanswer/form", new ForwardController("/user/AddAnswer.jsp"));
        //�亯 ���
        mappings.put("/user/addanswer", new AddAnswerController());
        //�亯 ����
        mappings.put("/user/deleteanswer", new DeleteAnswerController());
        //�亯 ä��
        mappings.put("/user/adoptanswer", new AdoptAnswerController());
        
        
        //�� ���� ��ȸ
         mappings.put("/user/viewquestion", new ViewQuestionController());
        //���� �ϸ�ũ
    	//mappings.put("/user/bookmarkQuestion", new BookmarkQuestionController());
        
        
        /*
        //��õ����
        //���� ����Ʈ ��ȸ
        mappings.put("/user/list", new ListRecommendController());
        //��õ ���� ����(�ֽż�, ��õ��)
        mappings.put("/user/sortList", new ListSortController());
        //���� ����ϱ�� �̵�
        mappings.put("/user/addRecommend/form", new ForwardController("/user/DisplayRecommend.jsp"));
        //��õ���� ���
        mappings.put("/user/addRecommend", new AddRecommendController());
        //��õ���� ����
        mappings.put("/user/deleteRecommend", new DeleteRecommendController());
        //�亯(�ַ�ǵ��) �� �̵�
        mappings.put("/user/addRecommendSolution/form", new ForwardController("/user/AddSolution.jsp"));
        //�亯(�ַ��) ���
        mappings.put("/user/addRecommendSolution", new AddSolutionController());
        //�亯 ����
        mappings.put("/user/deleteSolution", new DeleteSolutionController());
        //�亯 ���� �ű��
        mappings.put("/user/updateRecommendScore", new UpdateSolutionScoreController());
        //���� ��õ�ϱ�
        mappings.put("/user/recommendCount", new UpdateRecommendCountController());
        //��õ���� �ϸ�ũ
        mappings.put("/user/bookmarkRecommend", new BookmarkRecommendController());
        
        */
        
        
        logger.info("Initialized Request Mapping!");
        
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
