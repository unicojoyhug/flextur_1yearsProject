package data;

import java.util.List;

/**
 * 
 * @author Juyoung Choi
 *
 * @param <D>
 * @param <K>
 */
public interface CRUD<D, K> {

	void create(DataAccess dataAccess, D domain);

	D read(DataAccess dataAccess, K key);

	void update(DataAccess dataAccess, D domain);

	void delete(DataAccess dataAccess, D domain);
	
}

