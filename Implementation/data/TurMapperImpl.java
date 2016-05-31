package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
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
import util.DataAccess;

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
			+ " from flextur" + KUNDE_CPR + WHERE_DATO + " AND " + WHERE_CPR;

	private final static String GET_MATCHENDE_HISTORIK_BM_CPR = MATCHEDNE_HISTORIK + KUNDE_CPR + KOMMUNE + WHERE_DATO
			+ " AND " + WHERE_CPR + GROUP_BY;

	private final static String GET_MATCHENDE_HISTORIK_BM = MATCHEDNE_HISTORIK + KUNDE_CPR + KOMMUNE + WHERE_DATO
			+ " AND  kommune.navn = ? " + GROUP_BY;

	private final static String BESTIL_FLEXTUR = "INSERT INTO flextur (KUNDEID, DATO, TID, FRAPOSTNUMMER, TILPOSTNUMMER, FRAADRESS, TILADRESS, ANTALPERSONER, KOMMENTAR, PRIS, BARNEVOGNE, KØRESTOLE, BAGGAGE, AUTOSTOLE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private final static String GET_BESTILTE_KØRSLER = " select cpr.cprnummer, kunde.id, kunde.loginid, kunde.fornavn,kunde.efternavn,  kunde.telefon, flextur.* from flextur " 
			+ KUNDE_CPR + WHERE_DATO + " AND flextur.erGodkendt = false";
	
	private final static String GET_ALLE_BESTILTE_KØRSLER = " select cpr.cprnummer, kunde.id, kunde.loginid, kunde.fornavn,kunde.efternavn,  kunde.telefon, flextur.* from flextur " 
			+ KUNDE_CPR + " where flextur.erGodkendt = false";
	
	private final static String GODKEND_KØRSEL = "UPDATE FLEXTUR SET ergodkendt = true, kommentar = ? where id = ?";
	
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

			while (resultSet.next()) {
				Flextur flextur = new FlexturImpl();
				flextur.setDato(resultSet.getDate("dato").toLocalDate());
				flextur.setFraAdress(resultSet.getString("fraAdress"));
				flextur.setFraPostnummer(resultSet.getInt("fraPostnummer"));
				flextur.setTilAdress(resultSet.getString("tilAdress"));
				flextur.setTilPostnummer(resultSet.getInt("tilPostnummer"));
				flextur.setAntalPersoner(resultSet.getInt("antalPersoner"));
				flextur.setPris((resultSet.getDouble("pris")));

				matchendeHistorik.add(flextur);

			}

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
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

			if (historikSøgning.getCprNummer() != null) {

				statement = dataAccess.getConnection().prepareStatement(GET_MATCHENDE_HISTORIK_BM_CPR);
				statement.setDate(1, Date.valueOf(historikSøgning.getFraDato()));
				statement.setDate(2, Date.valueOf(historikSøgning.getTilDato()));
				statement.setString(3, historikSøgning.getCprNummer());

			} else {
				statement = dataAccess.getConnection().prepareStatement(GET_MATCHENDE_HISTORIK_BM);
				statement.setDate(1, Date.valueOf(historikSøgning.getFraDato()));
				statement.setDate(2, Date.valueOf(historikSøgning.getTilDato()));
				statement.setString(3, historikSøgning.getKommune());
			}

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				HistorikForBM historik = new HistorikForBMImpl();
				historik.setFraDato(historikSøgning.getFraDato());
				historik.setTilDato(historikSøgning.getTilDato());
				historik.setCprNummer(resultSet.getString("cprNummer"));
				historik.setKommune(resultSet.getString("kommune"));
				historik.setAntalPersoner(resultSet.getInt("antalPersoner"));
				historik.setTotalPris(resultSet.getDouble("totalPris"));
				historik.setFornavn(resultSet.getString("fornavn"));
				historik.setEfternavn(resultSet.getString("efternavn"));
				historik.setAntalTur(resultSet.getInt("antalTur"));

				matchendeHistorik.add(historik);

			}

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} catch (NullPointerException exc) {
			throw new MissingOplysningExcpetion("Oplysninger mangler");
		} finally {
			close.close(resultSet, statement);
		}

		return matchendeHistorik;
	}

	@Override
	//TODO there is no execute query f.eks. statement.executeUpdate();  or execute()
	public Object gemFlextur(DataAccess dataAccess, Flextur tur) {
		PreparedStatement statement = null;
		try {
			statement = dataAccess.getConnection().prepareStatement(BESTIL_FLEXTUR);
			statement.setInt(1, tur.getKundeId());
			statement.setDate(2, Date.valueOf(tur.getDato()));
			statement.setTime(3, Time.valueOf(tur.getTid()));
			statement.setInt(4, tur.getFraPostnummer());
			statement.setInt(5, tur.getTilPostnummer());
			statement.setString(6, tur.getFraAdress());
			statement.setString(7, tur.getTilAdress());
			statement.setInt(8, tur.getAntalPersoner());
			statement.setString(9, tur.getKommentar());
			statement.setDouble(10, tur.getPris());
			statement.setInt(11, tur.getBarnevogne());
			statement.setInt(12, tur.getKoerestole());
			statement.setInt(13, tur.getBaggage());
			statement.setInt(14, tur.getAutostole());
		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} catch (NullPointerException exc) {
			throw new MissingOplysningExcpetion("Oplysninger mangler");
		} finally {
			close.close(statement);
		}
		return null;
	}


	@Override
	public List<Flextur> getBestilteKørsler(DataAccess dataAccess, LocalDate fraDato, LocalDate tilDato) {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Flextur> bestilteKørsler = new ArrayList<>();

		try {
			statement = dataAccess.getConnection().prepareStatement(GET_BESTILTE_KØRSLER);
			statement.setDate(1, Date.valueOf(fraDato));
			statement.setDate(2, Date.valueOf(tilDato));
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {


				Flextur flextur = new FlexturImpl();
				flextur.setFlexturId(resultSet.getLong("flextur.id"));
				flextur.setDato(resultSet.getDate("dato").toLocalDate());
				flextur.setTid(resultSet.getTime("tid").toLocalTime());
				flextur.setFraAdress(resultSet.getString("fraAdress"));
				flextur.setFraPostnummer(resultSet.getInt("fraPostnummer"));
				flextur.setTilAdress(resultSet.getString("tilAdress"));
				flextur.setTilPostnummer(resultSet.getInt("tilPostnummer"));
				flextur.setAntalPersoner(resultSet.getInt("antalPersoner"));
				flextur.setPris(resultSet.getDouble("pris"));
				flextur.setTelefon(resultSet.getString("telefon"));
				flextur.setEfternavn(resultSet.getString("efternavn"));
				flextur.setFornavn(resultSet.getString("fornavn"));
				flextur.setKundeId(resultSet.getInt("kunde.id"));
				flextur.setCprNummer(resultSet.getString("cprnummer"));
				flextur.setAutostole(resultSet.getInt("autostole"));
				flextur.setBaggage(resultSet.getInt("baggage"));
				flextur.setKoerestole(resultSet.getInt("kørestole"));
				flextur.setBarnevogne(resultSet.getInt("barnevogne"));
				flextur.setKommentar(resultSet.getString("kommentar"));

				bestilteKørsler.add(flextur);

			}

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(resultSet, statement);
		}

		return bestilteKørsler;
	}
	
	
	@Override
	public void godkendKørsel(DataAccess dataAccess, long flexturId, String kommentar){
		PreparedStatement statement = null;

		try {
			statement = dataAccess.getConnection().prepareStatement(GODKEND_KØRSEL);
			statement.setString(1, kommentar);
			statement.setLong(2, flexturId);	
			statement.execute();

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(statement);
			
		}

	}
	
	@Override
	public List<Flextur> getAlleBestilteKørsler(DataAccess dataAccess) {

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Flextur> bestilteKørsler = new ArrayList<>();

		try {
			statement = dataAccess.getConnection().prepareStatement(GET_ALLE_BESTILTE_KØRSLER);
						
			resultSet = statement.executeQuery();

			while (resultSet.next()) {


				Flextur flextur = new FlexturImpl();
				flextur.setFlexturId(resultSet.getLong("flextur.id"));
				flextur.setDato(resultSet.getDate("dato").toLocalDate());
				flextur.setTid(resultSet.getTime("tid").toLocalTime());
				flextur.setFraAdress(resultSet.getString("fraAdress"));
				flextur.setFraPostnummer(resultSet.getInt("fraPostnummer"));
				flextur.setTilAdress(resultSet.getString("tilAdress"));
				flextur.setTilPostnummer(resultSet.getInt("tilPostnummer"));
				flextur.setAntalPersoner(resultSet.getInt("antalPersoner"));
				flextur.setPris(resultSet.getDouble("pris"));
				flextur.setTelefon(resultSet.getString("telefon"));
				flextur.setEfternavn(resultSet.getString("efternavn"));
				flextur.setFornavn(resultSet.getString("fornavn"));
				flextur.setKundeId(resultSet.getInt("kunde.id"));
				flextur.setCprNummer(resultSet.getString("cprnummer"));
				flextur.setAutostole(resultSet.getInt("autostole"));
				flextur.setBaggage(resultSet.getInt("baggage"));
				flextur.setKoerestole(resultSet.getInt("kørestole"));
				flextur.setBarnevogne(resultSet.getInt("barnevogne"));
				flextur.setKommentar(resultSet.getString("kommentar"));

				bestilteKørsler.add(flextur);

			}

		} catch (SQLException exc) {
			throw new PersistenceFailureException("Query has failed");
		} finally {
			close.close(resultSet, statement);
		}

		return bestilteKørsler;
	}
}
