package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.Kunde;
import domain.KundeImpl;
import exception.PersistenceFailureException;
import util.CloseForSQL;
import util.DataAccess;

/**
 * 
 * @author Juyoung Choi & Jonas Mørch
 *
 */
public class KundeMapperCRUDImpl implements CRUD<Kunde, String> {
	private static final String READ_KUNDEID = "Select id, cpr.cprNummer as loginid from kunde"
			+ " inner join cpr on cpr.id = kunde.loginid " + " where cpr.cprNummer = ?";
	private final static String OPRET_BRUGER = "INSERT INTO kunde (LOGINID, KODEORD, ROLLE, EMAIL, TELEFON, FORNAVN, EFTERNAVN, KOMMUNE, POSTNUMMMER, ERAKTIVT) values (?,?,?,?,?,?,?,?,?,?)";

	private CloseForSQL close = new CloseForSQL();

	@Override
	public void create(DataAccess dataAccess, Kunde domain) throws PersistenceFailureException {

		PreparedStatement statement = null;
		
		try {
			statement = dataAccess.getConnection().prepareStatement(OPRET_BRUGER);
			statement.setString(1, domain.getCprNummer());
			statement.setString(2, domain.getKodeord());
			statement.setInt(3, 1);
			statement.setString(4, domain.getEmail());
			statement.setString(5, domain.getTelefon());
			statement.setString(6, domain.getFornavn());
			statement.setString(7, domain.getEfternavn());
			statement.setString(8, domain.getKommune());
			statement.setInt(9, domain.getPostnummer());
			statement.setBoolean(10, domain.erAktivt());

			statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
