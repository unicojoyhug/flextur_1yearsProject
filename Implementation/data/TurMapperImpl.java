package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


import domain.Flextur;
import domain.FlexturImpl;
import domain.HistorikSøgning;
import exception.PersistenceFailureException;
import util.CloseForSQL;

public class TurMapperImpl implements TurMapper {
	private CloseForSQL close = new CloseForSQL();
	
	private final static String KUNDE_CPR = " inner join kunde on kundeid = kunde.id inner join cpr on cpr.id = kunde.loginid ";
	private final static String GET_MATCHENDE_HISTORIK_KUNDE = "select dato, fraAdress, fraPostnummer, tilAdress, tilpostnummer,  antalpersoner, pris "
			+ "from flextur"+ KUNDE_CPR 
			+ "where (dato >= ? AND  dato <= ?) AND cpr.cprnummer=?";
	
	@Override
	public List<Flextur> getMatchendeHistorik(DataAccess dataAccess, HistorikSøgning historikSøgning) {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Flextur> matchendeHistorik = new ArrayList<>();
		
		try {
			statement = dataAccess.getConnection().prepareStatement(GET_MATCHENDE_HISTORIK_KUNDE);
			statement.setDate(1, Date.valueOf(historikSøgning.getFraDato()));
			statement.setDate(2, Date.valueOf(historikSøgning.getTilDato()));
			statement.setString(3, historikSøgning.getCprNummer());
			resultSet = statement.executeQuery();
			
			while (resultSet.next()){
				Flextur flextur = new FlexturImpl();
				flextur.setDato(resultSet.getDate("dato").toLocalDate());
				flextur.setFraAdress(resultSet.getString("fraAdress"));
				flextur.setFraPostnummer(resultSet.getInt("fraPostnummer"));
				flextur.setTilAdress(resultSet.getString("tilAdress"));
				flextur.setTilPostnummer(resultSet.getInt("tilPostnummer"));
				flextur.setAntalPersoner(resultSet.getInt("antalPersoner"));
				flextur.setPris((Double)resultSet.getDouble("pris"));
				
				matchendeHistorik.add(flextur);
			
			}
			
		} catch (SQLException exc){
			throw new PersistenceFailureException("Query has failed");			
		}finally{
			close.close(resultSet, statement);		
		}
		
		return matchendeHistorik;
	}

}
