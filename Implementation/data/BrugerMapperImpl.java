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

	private static final String READ_KUNDE= "Select id, cpr.cprNummer, rolle.rolle from kunde "
			+ " inner join cpr on cpr.id = kunde.loginid " + ROLLE + " kunde.rolle "
			+ " where kunde.erAktivt = true ";
	private static final String LOGIN_ID = " where loginid = ? ";
	private static final String READ_BM = "Select id, loginid, kodeord, rolle.rolle from bestillingsmodtagelse "
			+ ROLLE + " bestillingsmodtagelse.rolle ";

	private static final String READ_BRUGER = READ_BM + " union " + READ_KUNDE + LOGIN_ID;

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
			statement = dataAccess.getConnection().prepareStatement(READ_BRUGER);
			statement.setString(1, key);
			resultSet = statement.executeQuery();

			if (resultSet.next()){

				bruger.setEncryptedKodeord(resultSet.getString("kodeord"));
				bruger.setLoginId(resultSet.getString("loginid"));
				bruger.setId(resultSet.getInt("id"));

				if(resultSet.getString("rolle.rolle").contains("kunde")){
					bruger.setErKunde(true);
					bruger.setErAktivt(resultSet.getBoolean("erAktivt"));
				
					bruger.setId(resultSet.getInt("id"));

				}else{
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
