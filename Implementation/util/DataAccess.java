package util;

import java.sql.Connection;
/**
 * DataAccess using Singleton pattern

 * @author Juyoung Choi
 *
 */

public interface DataAccess {

	public Connection getConnection();

	public void commit();

	public void close();

	public void rollback();

}