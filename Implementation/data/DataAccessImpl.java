package data;

import java.sql.Connection;

import util.ConnectionHandler;

/**
 * DataAccess using Singleton pattern : ConnectionHandler
 * @author Juyoung Choi
 *
 */

public class DataAccessImpl implements DataAccess {
	
	private Connection connection = null;

	public DataAccessImpl(){
		
		this.connection = ConnectionHandler.getInstance().getConnection();
	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public void commit()  {
		ConnectionHandler.getInstance().commit();

	}

	@Override
	public void close() {
		ConnectionHandler.getInstance().close();
	}

	@Override
	public void rollback() {
		ConnectionHandler.getInstance().rollback();
	}

}
