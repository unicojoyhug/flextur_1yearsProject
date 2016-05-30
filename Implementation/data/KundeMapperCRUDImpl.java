package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.Kunde;
import domain.KundeImpl;
import exception.PersistenceFailureException;
import util.CloseForSQL;

/**
 * 
 * @author Juyoung Choi & Jonas Mørch
 *
 */
public class KundeMapperCRUDImpl implements CRUD<Kunde, String> {
	private static final String READ_KUNDEID = "Select id, cpr.cprNummer as loginid from kunde"
			+ " inner join cpr on cpr.id = kunde.loginid " 
			+ " where cpr.cprNummer = ?";

	private CloseForSQL close = new CloseForSQL();

	@Override
	public void create(DataAccess dataAccess, Kunde domain) throws PersistenceFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public Kunde read(DataAccess dataAccess, String key) throws PersistenceFailureException {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Kunde kunde = new KundeImpl();

		try {
			statement = dataAccess.getConnection().prepareStatement(READ_KUNDEID);
			statement.setString(1, key);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				kunde.setCprNummer(key);
				int kundeID = resultSet.getInt("id");
				kunde.setKundeID(kundeID);

			}

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(resultSet, statement);
		}
		return kunde;
	}
	
	@Override
	public void update(DataAccess dataAccess, Kunde domain) throws PersistenceFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DataAccess dataAccess, Kunde domain) throws PersistenceFailureException {
		// TODO Auto-generated method stub

	}

}
