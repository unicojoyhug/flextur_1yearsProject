package logic;

/**
 * 
 * @author Juyoung Choi
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;
import exception.CSVExportingFEJLException;

public class CSVExporterImpl implements CSVExporter {
	private static final String SEPERATOR = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER_TIL_KOMMUNE = "Fra Dato,Til Dato,CPR-Nummer,Fornavn,Efternavn,Kommune,Total Pris,Antal Personer,Antal Ture";
	private static final String FILE_HEADER_TIL_KUNDE = "Dato,Fra Adress,Til Adress,Pris,Antal Personer";
	private static final String NO_MATCH_FOUND = "NO MATCH FOUND";

	@Override
	public void generateCsvFileFlexturForBM(String filenavn, List<HistorikForBM> historikListe) {
		try {
			FileWriter fileWriter = new FileWriter(filenavn);

			fileWriter.append(FILE_HEADER_TIL_KOMMUNE.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);

			if (historikListe.isEmpty()) {
				fileWriter.append(NO_MATCH_FOUND);
			}

			for (HistorikForBM h : historikListe) {
				fileWriter.append(h.getFraDato().toString());
				fileWriter.append(SEPERATOR);
				fileWriter.append(h.getTilDato().toString());
				fileWriter.append(SEPERATOR);
				fileWriter.append(h.getCprNummer());
				fileWriter.append(SEPERATOR);
				fileWriter.append(h.getFornavn());
				fileWriter.append(SEPERATOR);
				fileWriter.append(h.getEfternavn());
				fileWriter.append(SEPERATOR);
				fileWriter.append(h.getKommune());
				fileWriter.append(SEPERATOR);
				fileWriter.append(String.valueOf(h.getTotalPris())); 
				fileWriter.append(SEPERATOR);
				fileWriter.append(String.valueOf(h.getAntalPersoner()));
				fileWriter.append(SEPERATOR);
				fileWriter.append(String.valueOf(h.getAntalTur()));
				fileWriter.append(NEW_LINE_SEPARATOR);

			}
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			throw new CSVExportingFEJLException("Fejl : CSV fil exporting");
		}

	}

	@Override
	public void generateCsvFileFlexturForKunde(String filenavn, List<Flextur> historikListe) {
		try {
			FileWriter fileWriter = new FileWriter(filenavn);

			fileWriter.append(FILE_HEADER_TIL_KUNDE.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);

			if (historikListe.isEmpty()) {
				fileWriter.append(NO_MATCH_FOUND);
			}

			for (Flextur h : historikListe) {
				fileWriter.append(h.getDato().toString());
				fileWriter.append(SEPERATOR);
				fileWriter.append(h.getFraAdress());
				fileWriter.append(SEPERATOR);
				fileWriter.append(h.getTilAdress());
				fileWriter.append(SEPERATOR);
				fileWriter.append(String.valueOf(h.getPris()));
				fileWriter.append(SEPERATOR);
				fileWriter.append(String.valueOf(h.getAntalPersoner()));

				fileWriter.append(NEW_LINE_SEPARATOR);

			}
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			throw new CSVExportingFEJLException("Fejl : CSV fil exporting");
		}

	}

}
