package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Bruger;
import domain.BrugerImpl;
import exception.PersistenceFailureException;
import util.CloseForSQL;

/**
 * 
 * @author Juyoung Choi
 *
 */
public class BrugerMapperImpl implements CRUD<Bruger, String> {
	private static final String ROLLE = "inner join rolle on rolle.id =";

	private static final String READ_KUNDE= "Select kunde.*, rolle.rolle, cpr.cprNummer from kunde "
			+  ROLLE + " kunde.rolle inner join cpr on cpr.id = kunde.loginid "
			+ "where cpr.cprnummer = ?";
	private static final String READ_BM = "Select * from bestillingsmodtagelse "
			+ ROLLE + " bestillingsmodtagelse.rolle where bestillingsmodtagelse.loginid = ?";
	
	private CloseForSQL close = new CloseForSQL();

	@Override
	public void create(DataAccess dataAccess, Bruger domain) throws PersistenceFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public Bruger read(DataAccess dataAccess, String key) throws PersistenceFailureException {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Bruger bruger = new BrugerImpl();


		try {
			if(key.contains("@flextur.dk")){
				statement = dataAccess.getConnection().prepareStatement(READ_BM);
				statement.setString(1, key);
				resultSet = statement.executeQuery();
			}else{
				statement = dataAccess.getConnection().prepareStatement(READ_KUNDE);
				statement.setString(1, key);
				resultSet = statement.executeQuery();
			}
			if (resultSet.next()){
				
				bruger.setEncryptedKodeord(resultSet.getString("kodeord"));
				
				if(resultSet.getString("rolle.rolle").contains("kunde")){
					bruger.setErKunde(true);
					bruger.setLoginId(resultSet.getString("cpr.cprNummer"));
					bruger.setErAktivt(resultSet.getBoolean("erAktivt"));
					bruger.setId(resultSet.getLong("id"));

				}else{
					bruger.setLoginId(resultSet.getString("loginid"));
					bruger.setErKunde(false);

				}
			}

		}catch (SQLException exc){
			throw new PersistenceFailureException("Query has failed");			
		}finally{
			close.close(resultSet, statement);		
		}
		return bruger;
	}

	@Override
	public void update(DataAccess dataAccess, Bruger domain) throws PersistenceFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DataAccess dataAccess, Bruger domain) throws PersistenceFailureException {
		// TODO Auto-generated method stub

	}

}
