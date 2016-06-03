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
public class KundeMapperCRUDImpl implements KundeMapper {

	private static final String READ_KUNDEID = "Select kunde.*, cpr.cprNummer as loginid, kommune.navn from kunde"
			+ " inner join cpr on cpr.id = kunde.loginid inner join kommune on kommune.id = kommune " 
			+ " where cpr.cprNummer = ?";
	private final static String OPRET_BRUGER = "INSERT INTO kunde "
			+ "(LOGINID, KODEORD, ROLLE, EMAIL, TELEFON, FORNAVN, EFTERNAVN, ADRESS, KOMMUNE, POSTNUMMER) "
			+ "values (?,?,?,?,?,?,?,?,?,?)";
	private final static String INSERT_CPR = "INSERT INTO cpr( cprnummer ) VALUES (?);";
	private final static String CHECK_KOMMUNE = "Select id from kommune where navn = ?";
	private final static String RET_PROFIL = "UPDATE kunde SET KODEORD = ?, EMAIL = ? , "
			+ "TELEFON = ? , FORNAVN = ? , EFTERNAVN = ? , ADRESS = ? , KOMMUNE = ?, POSTNUMMER = ?  where id = ?";
	private int cprId = 0;
	private int kommuneId = 0;

	private CloseForSQL close = new CloseForSQL();

	@Override
	public void create(DataAccess dataAccess, Kunde domain) throws PersistenceFailureException {
		
		PreparedStatement statement = null;

		try {
			statement = dataAccess.getConnection().prepareStatement(OPRET_BRUGER);
			statement.setInt(1, cprId);
			statement.setString(2, domain.getKodeord());
			statement.setInt(3, 1);
			statement.setString(4, domain.getEmail());
			statement.setString(5, domain.getTelefon());
			statement.setString(6, domain.getFornavn());
			statement.setString(7, domain.getEfternavn());
			statement.setString(8, domain.getAdress());
			statement.setInt(9, kommuneId);
			statement.setInt(10, domain.getPostnummer());
			
			
			statement.execute();
		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(statement);
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
				kunde.setEfternavn(resultSet.getString("efternavn"));
				kunde.setFornavn(resultSet.getString("fornavn"));
				kunde.setAdress(resultSet.getString("adress"));
				kunde.setTelefon(resultSet.getString("telefon"));
				kunde.setEmail(resultSet.getString("email"));
				kunde.setPostnummer(resultSet.getInt("postnummer"));
				kunde.setKommune(resultSet.getString("kommune.navn"));
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
		kommuneId = checkKommuneId(dataAccess, domain.getKommune());
				
		PreparedStatement statement = null;
		try {
			statement = dataAccess.getConnection().prepareStatement(RET_PROFIL);

			statement.setString(1, domain.getKodeord());
			statement.setString(2, domain.getEmail());
			statement.setString(3, domain.getTelefon());
			statement.setString(4, domain.getFornavn());
			statement.setString(5, domain.getEfternavn());
			statement.setString(6, domain.getAdress());
			statement.setInt(7, kommuneId);
			statement.setInt(8, domain.getPostnummer());
			statement.setInt(9, domain.getKundeID());
			
			
			statement.execute();
		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(statement);
		}
	}

	@Override
	public void delete(DataAccess dataAccess, Kunde domain) throws PersistenceFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public int createCPRogKunde(DataAccess dataAccess, Kunde kunde) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		
		try {
			statement = dataAccess.getConnection().prepareStatement(INSERT_CPR, PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, kunde.getCprNummer());
			
			statement.execute();
			resultSet =  statement.getGeneratedKeys();
			if(resultSet.next()){
				cprId = resultSet.getInt(1);
				kommuneId = checkKommuneId(dataAccess, kunde.getKommune());
				
				create(dataAccess, kunde);

			}
		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(resultSet, statement);
		}

		return cprId;
	}

	private int checkKommuneId(DataAccess dataAccess, String kommune) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;	
		int kommuneId = 0;
		try {
			statement = dataAccess.getConnection().prepareStatement(CHECK_KOMMUNE);
			statement.setString(1, kommune);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				
				kommuneId = resultSet.getInt("id");
				
			}

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(resultSet, statement);
		}
		return kommuneId;
		
		
		
	}


}
