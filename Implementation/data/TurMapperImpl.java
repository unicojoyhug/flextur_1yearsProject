package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


import domain.Flextur;
import domain.FlexturImpl;
import domain.HistorikForBM;
import domain.HistorikForBMImpl;
import domain.HistorikSøgning;
import exception.MissingOplysningExcpetion;
import exception.PersistenceFailureException;
import util.CloseForSQL;

/**
 * 
 * @author Juyoung Choi
 *
 */
public class TurMapperImpl implements TurMapper {
	private CloseForSQL close = new CloseForSQL();

	private final static String KUNDE_CPR = " inner join kunde on kundeid = kunde.id inner join cpr on cpr.id = kunde.loginid ";
	private final static String WHERE_DATO = " where (dato >= ? AND  dato <= ?)";
	private final static String WHERE_CPR = "cpr.cprnummer=?";
	private final static String GROUP_BY = " group by  kundeid, cpr.cprnummer, kunde.efternavn, kunde.fornavn, kommune.navn";
	private final static String KOMMUNE = " inner join kommune on kunde.kommune = kommune.id";
	private final static String MATCHEDNE_HISTORIK = " select cpr.cprnummer, kunde.efternavn, kunde.fornavn, kundeid, "
			+ " kommune.navn as kommune, sum(antalpersoner) as antalPersoner, count(id) as antalTur , sum(pris) as totalPris from flextur ";


	private final static String GET_MATCHENDE_HISTORIK_KUNDE = " select dato, fraAdress, fraPostnummer, tilAdress, tilpostnummer,  antalpersoner, pris "
			+ " from flextur"+ KUNDE_CPR + WHERE_DATO + " AND " + WHERE_CPR;


	private final static String GET_MATCHENDE_HISTORIK_BM_CPR = MATCHEDNE_HISTORIK
			+ KUNDE_CPR + KOMMUNE+ WHERE_DATO + " AND " + WHERE_CPR+ GROUP_BY;

	private final static String GET_MATCHENDE_HISTORIK_BM = MATCHEDNE_HISTORIK + KUNDE_CPR + KOMMUNE + WHERE_DATO + 
			" AND  kommune.navn = ? " + GROUP_BY;

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


	@Override
	public List<HistorikForBM> getMatchendeHistorikForBM(DataAccess dataAccess, HistorikSøgning historikSøgning) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<HistorikForBM> matchendeHistorik = new ArrayList<>();


		try {
			
			if(historikSøgning.getCprNummer()!=null){

				statement = dataAccess.getConnection().prepareStatement(GET_MATCHENDE_HISTORIK_BM_CPR);
				statement.setDate(1, Date.valueOf(historikSøgning.getFraDato()));
				statement.setDate(2, Date.valueOf(historikSøgning.getTilDato()));
				statement.setString(3, historikSøgning.getCprNummer());


			}else{
				statement = dataAccess.getConnection().prepareStatement(GET_MATCHENDE_HISTORIK_BM);
				statement.setDate(1, Date.valueOf(historikSøgning.getFraDato()));
				statement.setDate(2, Date.valueOf(historikSøgning.getTilDato()));
				statement.setString(3, historikSøgning.getKommune());
			}
			
			resultSet = statement.executeQuery();


			while (resultSet.next()){
				HistorikForBM historik = new HistorikForBMImpl();
				historik.setFraDato(historikSøgning.getFraDato());
				historik.setTilDato(historikSøgning.getTilDato());
				historik.setCprNummer(resultSet.getString("cprNummer"));
				historik.setKommune(resultSet.getString("kommune"));
				historik.setAntalPersoner(resultSet.getInt("antalPersoner"));
				historik.setTotalPris((Double)resultSet.getDouble("totalPris"));
				historik.setFornavn(resultSet.getString("fornavn"));
				historik.setEfternavn(resultSet.getString("efternavn"));
				historik.setAntalTur(resultSet.getInt("antalTur"));

				matchendeHistorik.add(historik);

			}

		} catch (SQLException exc){
			throw new PersistenceFailureException("Query has failed");		
		} catch (NullPointerException exc){
			throw new MissingOplysningExcpetion("Oplysninger mangler");
		} finally{
			close.close(resultSet, statement);		
		}

		return matchendeHistorik;
	}

}
