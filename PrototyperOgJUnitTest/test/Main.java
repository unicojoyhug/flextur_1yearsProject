package test;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import domain.Flextur;
import domain.FlexturImpl;
import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import logic.FSController;
import logic.FSControllerImpl;
import logic.PrisUdregnerMedTrådImpl;

public class Main {
	/**
	 * 
	 * prototype klasse for at løse tråd og andre problemer
	 * @author Juyoung Choi
	 *
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FSController fs = new FSControllerImpl();
		
		Flextur flextur = new FlexturImpl();
		flextur.setAntalPersoner(3);
		flextur.setFraKommune("Herning");
		flextur.setTilKommune("Aarhus");
		flextur.setDato(LocalDate.of(2016, 05, 26));
		flextur.setKilometer(200);
		

		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		Future<Double> task = executor.submit(()->fs.udregnPrisMedTråd(flextur));
		
		while(!task.isDone()){
			System.out.println("Calculating...");
			Thread.sleep(1000);
		}
		System.out.println(task.get());
		
		executor.shutdown();
	}
	
		
		
//		while(!fs.udrengPrisMedTråd(flextur).isDone()){
//			System.out.println("not done");
//			Thread.sleep(100);
//		}
//		
//		System.out.println(	fs.udrengPrisMedTråd(flextur).get());
		
//		PrisUdregnerMedTråd task = new PrisUdregnerMedTråd(flextur);
//		task.run();
//		System.out.println(flextur);

		
//		fs.søgBestilteKørsler(LocalDate.of(2016, 05, 01), LocalDate.of(2016, 05, 31));
//		
//		System.out.println(fs.getBestilteKøsler());
//		HistorikSøgning hs = new HistorikSøgningImpl();
//		hs.setCprNummer("170182-3628");
//		hs.setKommune("Herning");
//		hs.setFraDato(null);
//		hs.setTilDato(LocalDate.of(2016, 05, 31));
//		fs.søgHistorik();
//		System.out.println(fs.angivSøgningOplysninger(hs));
//		System.out.println(hs);
		
//		System.out.println(fs.angivSøgningOplysningerForBM(hs));
		
		
//		for(String s: fs.getKommuneListe()){
//			System.out.println(s);
//		}
		
//		fs.getKommuneListe();
		
//		System.out.println(fs.getKommuneListe());
		
		
	}

//}
