package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;

public class CSVExporterImpl {
	private static final String SEPERATOR = ",";	
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER_TIL_KOMMUNE = "Fra Dato,Til Dato,CPR-Nummer,Fornavn,Efternavn,Kommune,Total Pris,Antal Personer,Antal Ture";

	public static void generateCsvFileFlextur(String filenavn, List<HistorikForBM> historikListe){
       	try {  
            FileWriter fileWriter = new FileWriter(filenavn);
     		
       		fileWriter.append(FILE_HEADER_TIL_KOMMUNE.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);

       		for(HistorikForBM h : historikListe){
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
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
        
    }
	
	
	
}
