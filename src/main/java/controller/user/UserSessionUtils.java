package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {

	 public static final String USER_SESSION_KEY = "userId";

	    /* 현재 로그인한 사용자의 ID를 구함 */
	    public static Integer getLoginUserId(HttpSession session) {
	        int userId = (int) session.getAttribute(USER_SESSION_KEY);
	        return userId;
	    }

	    /* 로그인한 상태인지를 검사 */
	    public static boolean hasLogined(HttpSession session) {
	        if (getLoginUserId(session) != null) {
	            return true;
	        }
	        return false;
	    }

	    /* 현재 로그인한 사용자의 ID가 userId인지 검사 */
	    public static boolean isLoginUser(int userId, HttpSession session) {
	        Integer loginUser = getLoginUserId(session);
	        if (loginUser == null) {
	            return false;
	        }
	        return loginUser.equals(userId);
	    }
}
