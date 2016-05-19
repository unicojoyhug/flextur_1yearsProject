package util;
import java.util.function.Supplier;

import data.DataAccess;
/**
 * Using supplier for lambda expression with dataAccess in facade controller
 * @author Juyoung Choi
 *
 * @param <T>
 */

public class LogicTrans<T> {
	
	private DataAccess dataAccess = null;
	
	public LogicTrans(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	public T transaction (Supplier<T> supplier) {
		try {
			T t = supplier.get();
			dataAccess.commit();
			return t;
		} catch (Exception e) {
			dataAccess.rollback();
			throw new RuntimeException("Transaction rolled back", e);
		} finally {
			if(dataAccess != null)
				dataAccess.close();			
		}
	}


	public void transaction(Runnable runnable) {
		try {
			runnable.run();
			dataAccess.commit();
		} catch (Exception e) {
			dataAccess.rollback();
			throw new RuntimeException("Transaction rolled back", e);
		} finally {
			dataAccess.close();			
		}
	}
}
