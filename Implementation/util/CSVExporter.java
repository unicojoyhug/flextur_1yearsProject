package util;

import java.util.List;

import domain.HistorikForBM;

public interface CSVExporter {

	void generateCsvFileFlextur(String filenavn, List<HistorikForBM> historikListe);

}