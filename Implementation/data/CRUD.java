package data;

import exception.PersistenceFailureException;
/**
 * 
 * @author Juyoung Choi
 *
 * @param <D>
 * @param <K>
 */
public interface CRUD<D, K> {

	public void create(DataAccess dataAccess, D domain) throws PersistenceFailureException;

	public D read(DataAccess dataAccess, K key) throws PersistenceFailureException;

	public void update(DataAccess dataAccess, D domain) throws PersistenceFailureException;

	public void delete(DataAccess dataAccess, D domain) throws PersistenceFailureException;

}

