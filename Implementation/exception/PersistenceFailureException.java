package exception;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class PersistenceFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersistenceFailureException(String message) {
		super(message);
	}

}
