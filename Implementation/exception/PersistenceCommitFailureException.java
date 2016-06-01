package exception;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class PersistenceCommitFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersistenceCommitFailureException(String message) {
		super(message);
	}

}
