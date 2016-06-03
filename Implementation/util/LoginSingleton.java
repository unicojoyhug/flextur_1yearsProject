package util;

/**
 * Login with singleton pattern : only one user can logged in at a time. 
 * should be modified for multiuser login. 
 * 
 * Vi fravalgte den mønster på grund at multisuser platform i fremtiden
 * 
 * @author Juyoung Choi
 *
 */
public class LoginSingleton {
	private static LoginSingleton userloggedin = new LoginSingleton();
	private String userId = null;
	
	private LoginSingleton() {
	}
	
	public static LoginSingleton getInstance() {
		return userloggedin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
