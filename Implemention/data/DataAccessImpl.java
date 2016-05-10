package data;

import java.sql.Connection;

import util.ConnectionHandler;

/**
 * DataAccess using Singleton pattern
 * @author Juyoung Choi
 *
 */

public class DataAccessImpl implements DataAccess {
	
	private Connection connection = null;

	public DataAccessImpl(){
		
		this.connection = ConnectionHandler.instance().getConnection();
	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public void commit()  {
		ConnectionHandler.instance().commit();

	}

	@Override
	public void close() {
		ConnectionHandler.instance().close();
	}

	@Override
	public void rollback() {
		ConnectionHandler.instance().rollback();
	}

}
