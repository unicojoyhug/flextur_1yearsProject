package seHistorik;

import domain.HistorikForBM;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class HistorikForBMWrapper {
	private final StringProperty cprNummer;
	private final StringProperty kommune;
	private final StringProperty efternavn;

	private final StringProperty fornavn;

	private final DoubleProperty totalPris;
	private final IntegerProperty antalPersoner;
	private final IntegerProperty antalTur;
	
	private HistorikForBM historikForBM;
	
	public HistorikForBMWrapper (){
		this(null, null, null, null, Double.NaN, 0, 0);
	}
	
	public HistorikForBMWrapper(String cprNummer, String efternavn, String fornavn, String kommune, double totalPris, int antalPersoner, int antalTur){
		
		this.cprNummer = new SimpleStringProperty(cprNummer);
		this.efternavn = new SimpleStringProperty(efternavn);
		this.fornavn = new SimpleStringProperty(fornavn);
		this.kommune = new SimpleStringProperty(kommune);
		this.totalPris = new SimpleDoubleProperty(totalPris);
		this.antalPersoner = new SimpleIntegerProperty(antalPersoner);
		this.antalTur = new SimpleIntegerProperty(antalTur);

	}
	
	

	
	
}
