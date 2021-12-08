package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {

	 public static final String USER_SESSION_KEY = "userId";

	    /* ���� �α����� ������� ID�� ���� */
	    public static Integer getLoginUserId(HttpSession session) {
	        int userId = (int) session.getAttribute(USER_SESSION_KEY);
	        return userId;
	    }

	    /* �α����� ���������� �˻� */
	    public static boolean hasLogined(HttpSession session) {
	        if (getLoginUserId(session) != null) {
	            return true;
	        }
	        return false;
	    }

	    /* ���� �α����� ������� ID�� userId���� �˻� */
	    public static boolean isLoginUser(int userId, HttpSession session) {
	        Integer loginUser = getLoginUserId(session);
	        if (loginUser == null) {
	            return false;
	        }
	        return loginUser.equals(userId);
	    }
}
