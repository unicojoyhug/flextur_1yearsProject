package logic;

import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;

public interface CSVExporter {

	void generateCsvFileFlexturForBM(String filenavn, List<HistorikForBM> historikListe);

	void generateCsvFileFlexturForKunde(String filenavn, List<Flextur> historikListe);

}