package util;

/**
 * Login with singleton pattern : only one user can logged in at a time. 
 * should be modified for multiuser login. 
 * 
 * @author Juyoung Choi
 *
 */
public class LoginSingleton {
	private static LoginSingleton userloggedin = new LoginSingleton();
	private String userId = null;
	
	private LoginSingleton() {
	}
	
	public static LoginSingleton instance() {
		return userloggedin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
