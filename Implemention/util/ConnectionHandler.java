package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.PersistenceCommitFailureException;
import exception.PersistenceConnectionFailureException;
import exception.PersistenceRollbackFailureException;

/**
 * ConnectionHandler with Singleton : so it can use only one connection
 * @author Juyoung Choi
 *
 */

public class ConnectionHandler {

	private static ConnectionHandler handler = new ConnectionHandler();
	private Connection connection;
	private int level = 0;

	private ConnectionHandler() {

	}

	public static ConnectionHandler getInstance() {
		return handler;
	}

	public Connection getConnection() throws PersistenceConnectionFailureException {
		if (level == 0) {
			try {
				this.connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydatabase", "SA", "");
				this.connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new PersistenceConnectionFailureException("Failed to connect to database");
			}
		}
		level++;
		return this.connection;
	}

	public void commit() throws PersistenceCommitFailureException {
		if (level == 1) {
			try {
				this.connection.commit();
			} catch (SQLException e) {
				throw new PersistenceCommitFailureException("Failed to commit transaction");
			}
		}
	}

	public void rollback() {
		if (level == 1) {
			try {
				this.connection.rollback();
			} catch (SQLException e) {
				throw new PersistenceRollbackFailureException("Failed to rolleback and Exception caught");
			}
		}
	}

	public void close() {
		if (level == 1) {
			try {
				this.connection.close();
				this.connection = null;
			} catch (SQLException e) {
				throw new RuntimeException("Exception caught", e); //what is the difference when those above are also extends RuntimeException?
			}
		}
		level--;
	}

}
