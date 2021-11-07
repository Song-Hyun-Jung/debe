package model;

/**
 *사용자 관리를 위해 필요한 도메인 클래스. ServiceUser 테이블과 대응
 */
public class User{
	private String userId; //사용자 id(학번)
	private String userPassword; //사용자 pw
	private String userNickname; //사용자 닉네임
	private String userName; //사용자 이름
	private int userLevel; //사용자 레벨
	private int subjectId; //사용자 관심과목 1개
	
	private String userPasswordCheck; //사용자 회원가입시 비밀번호 재입력

	//getter, setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getUserPasswordCheck() {
		return userPasswordCheck;
	}

	public void setUserPasswordCheck(String userPasswordCheck) {
		this.userPasswordCheck = userPasswordCheck;
	}

	//생성자
	public User(String userId, String userPassword, String userNickname, String userName, int userLevel,
			int subjectId) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userNickname = userNickname;
		this.userName = userName;
		this.userLevel = userLevel;
		this.subjectId = subjectId;
	}

	public User(String userId, String userPassword, String userNickname, String userName, int userLevel, int subjectId,
			String userPasswordCheck) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userNickname = userNickname;
		this.userName = userName;
		this.userLevel = userLevel;
		this.subjectId = subjectId;
		this.userPasswordCheck = userPasswordCheck;
	}
	
	//로그인시 비밀번호 맞는지 확인
	public boolean isMatchPassword(String inputPassword) { //inputPassword는 입력받은 비밀번호
		if (inputPassword == null) {
			return false;
		}
		return this.userPassword.equals(inputPassword); //user DB에서 받아온 비밀번호
	}
	
	//회원가입시 비밀번호와 비밀번호 재입력 칸 값이 같은지 확인
	public boolean getUserPasswordCheck(String userPassword, String userPasswordCheck) {
		if(userPassword == null || userPasswordCheck == null)
			return false;
		if(userPassword.equals(userPasswordCheck))
			return true;
		
		return false;
	}
	
	//레벨업
	public int addUserLevel() {
		return userLevel + 1;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + userPassword + ", name=" + userName + ", nickname=" + userNickname + ","
				+ " userLevel=" + userLevel + "]";
	}	
	
}