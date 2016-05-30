package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Bil;
import domain.BilImpl;
import exception.PersistenceFailureException;
import util.CloseForSQL;

public class BilMapperCRUDImpl implements BilMapper {
	private CloseForSQL close = new CloseForSQL();
 
	private final static String BIL_LISTE = " SELECT bil.id, bil.navn, biltype.maxantalpersoner, biltype.tilvalgmulighed FROM BIL inner join biltype "
			+ "on biltype.id = bil.type where erReserveret = false AND maxantalpersoner >=? "
			+ "AND (tilvalgmulighed = true OR tilvalgmulighed = ?)";
	
	private final static String GEM_FLEXTUR_MED_BIL = "INSERT INTO flexturmedbil ( BIL, FLEXTUR ) VALUES ( ?,? )";
	
	private final static String SET_ER_RESERVERET = "UPDATE Bil SET erReserveret = true where id = ?";
	
	
	
	@Override
	public void create(DataAccess dataAccess, Bil domain) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bil read(DataAccess dataAccess, Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DataAccess dataAccess, Bil domain) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DataAccess dataAccess, Bil domain) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bil> hentBilListe(DataAccess dataAccess, int antalPersoner, boolean tilvalgMulighed) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Bil> bilListe = new ArrayList<>();

		try {
			statement = dataAccess.getConnection().prepareStatement(BIL_LISTE);
			statement.setInt(1, antalPersoner);
			statement.setBoolean(2, tilvalgMulighed);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Bil bil = new BilImpl();
				bil.setId(resultSet.getInt("bil.id"));
				bil.setNavn(resultSet.getString("bil.navn"));
				bil.setMaxAntalPersoner(resultSet.getInt("biltype.maxantalpersoner"));
				bil.setTilvalgsMulighed(resultSet.getBoolean("biltype.tilvalgmulighed"));
				bilListe.add(bil);

			}

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(resultSet, statement);
		}

		return bilListe;
	}

	
	@Override
	public void tildelBil(DataAccess dataAccess, int bilId, long flexturId){
		PreparedStatement statement = null;

		try {
			statement = dataAccess.getConnection().prepareStatement(GEM_FLEXTUR_MED_BIL);
			statement.setInt(1, bilId);
			statement.setLong(2, flexturId);	
			statement.execute();

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(statement);
			
			setErReserveret (dataAccess, bilId);
		}

	}

	private void setErReserveret(DataAccess dataAccess, int bilId) {
		PreparedStatement statement = null;

		try {
			statement = dataAccess.getConnection().prepareStatement(SET_ER_RESERVERET);
			statement.setInt(1, bilId);
			statement.execute();

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(statement);
		}		
	}
	

	
	
}
