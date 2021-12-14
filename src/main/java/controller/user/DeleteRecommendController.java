package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.RecommendManager;

public class DeleteRecommendController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		int recommendCode = Integer.parseInt(request.getParameter("recommendCode"));	//delete�� recommendCode
		String postUserId = request.getParameter("userId");			//delete�� recommend�� �ۼ��� userId, ���� ���� Ȯ�� ����
		String redirect = "redirect:/user/recommendlist";
	
		if(request.getParameter("admin") != null) {
			redirect = "redirect:/user/adminPostList";
		}
		
		RecommendManager manager = RecommendManager.getInstance();		
		HttpSession session = request.getSession();	
		
		if (UserSessionUtils.isLoginUser(11111111, session)	// �α����� ����ڰ� ������(userId�� 11111111)�� ���
				   || 												// �Ǵ� 
				(!UserSessionUtils.isLoginUser(11111111, session) &&  // �α����� ����ڰ� �����ڰ� �ƴ�����
				  UserSessionUtils.isLoginUser(Integer.parseInt(postUserId), session))) { // �α����� ����ڰ� �ۼ��� ������ ���
					
				manager.deleteRecommend(recommendCode);				// ���� ����
				
				return redirect;	
			}
		
		return redirect;
	}

}
