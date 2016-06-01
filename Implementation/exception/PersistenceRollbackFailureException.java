package exception;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class PersistenceRollbackFailureException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public PersistenceRollbackFailureException(String message) {
		super(message);
	}
}
