package model;

/**
 *����� ������ ���� �ʿ��� ������ Ŭ����. ServiceUser ���̺�� ����
 */
public class User{
	private String userId; //����� id(�й�)
	private String userPassword; //����� pw
	private String userNickname; //����� �г���
	private String userName; //����� �̸�
	private int userLevel; //����� ����
	private int subjectId; //����� ���ɰ��� 1��
	
	private String userPasswordCheck; //����� ȸ�����Խ� ��й�ȣ ���Է�

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

	//������
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
	
	//�α��ν� ��й�ȣ �´��� Ȯ��
	public boolean isMatchPassword(String inputPassword) { //inputPassword�� �Է¹��� ��й�ȣ
		if (inputPassword == null) {
			return false;
		}
		return this.userPassword.equals(inputPassword); //user DB���� �޾ƿ� ��й�ȣ
	}
	
	//ȸ�����Խ� ��й�ȣ�� ��й�ȣ ���Է� ĭ ���� ������ Ȯ��
	public boolean getUserPasswordCheck(String userPassword, String userPasswordCheck) {
		if(userPassword == null || userPasswordCheck == null)
			return false;
		if(userPassword.equals(userPasswordCheck))
			return true;
		
		return false;
	}
	
	//������
	public int addUserLevel() {
		return userLevel + 1;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + userPassword + ", name=" + userName + ", nickname=" + userNickname + ","
				+ " userLevel=" + userLevel + "]";
	}	
	
}