package logic;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import domain.Flextur;
import seHistorik.AntalPersonerException;

public class PrisUdregnerMedTråd {
	private Flextur flextur;
//	private FutureTask<Double> futureTask;
	
	public PrisUdregnerMedTråd(Flextur flextur){
		this.flextur = flextur;
	}
	
	public double udregnPris(Flextur flextur) {
		SatsFactory satsFactory = new SatsFactory();
		SatsAdapter rate = satsFactory.getSatsAdapter();
		double km = flextur.getKilometer();
		double personer = antalPersoner(flextur);
		double tilvalg = antalTilvalg(flextur);
		double sats = rate.hentSats(flextur);
		flextur.setPris((km*sats)*(personer+tilvalg));
		return(((km*sats)*(personer+tilvalg)));
	}
	
//	@Override 
//	public void setTask(Flextur flextur){
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		futureTask = new FutureTask<Double>(()-> hentPris(flextur));
//		executor.submit(futureTask);
//		executor.shutdown();
//		
////		return futureTask;
//	}
	
//	public double getPris(){
//		double pris = Double.NaN;
//		try {
//			pris = futureTask.get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return pris;
//	}
//	
//	public boolean isDone(){
////		boolean isDone = false;
//		return futureTask.isDone();
//	}
//	
//	private double hentPris(Flextur flextur){
//		SatsFactory satsFactory = new SatsFactory();
//		SatsAdapter rate = satsFactory.getSatsAdapter();
//		double km = flextur.getKilometer();
//		double personer = antalPersoner(flextur);
//		double tilvalg = antalTilvalg(flextur);
//		double sats = rate.hentSats(flextur);
//
//		flextur.setPris(((km*sats)*(personer+tilvalg)));
//		
//		return ((km*sats)*(personer+tilvalg));
//	}
//	
//	public void udregnPris(Flextur flextur) {
//		SatsFactory satsFactory = new SatsFactory();
//		SatsAdapter rate = satsFactory.getSatsAdapter();
//		double km = flextur.getKilometer();
//		double personer = antalPersoner(flextur);
//		double tilvalg = antalTilvalg(flextur);
//		double sats = rate.hentSats(flextur);
//
//		flextur.setPris(((km*sats)*(personer+tilvalg)));
//		
//		synchronized(this){
//			notify();
//			
//		}
//		
//		return ;
//	}
//	
	
	
//	private Flextur flextur;
//	private FSController fsController;
//	
//	public PrisUdregnerMedTråd (Flextur flextur){
//		this.flextur = flextur;
//	}
//
//	public Future run() {
//		SatsFactory satsFactory = new SatsFactory();
//		SatsAdapter rate = satsFactory.getSatsAdapter();
//		double km = flextur.getKilometer();
//		double personer = antalPersoner(flextur);
//		double sats = rate.hentSats(flextur);
//		double tilvalg = antalTilvalg(flextur);
//		
//		flextur.setPris(((km*sats)*(personer+tilvalg)));
//		
////		synchronized(this){
////			notify();
////			
////		}
//		
//		return ;
//	}
	
	
	
	private double antalTilvalg(Flextur flextur){
		double temp = -1;
		double tilvalg = flextur.getAutostole()+flextur.getBaggage()+flextur.getBarnevogne()+flextur.getKoerestole();
		double tilvres = 0;
		if (tilvalg > 0){
			tilvres = (temp + tilvalg)*0.5;
		}
				
		return tilvres;
		
	}
	private double antalPersoner(Flextur flextur){
		double result = 0;
		double temp = -1;
		double personer = flextur.getAntalPersoner();
			if (personer==1){
				result = 1;
			}
			else if (6< personer || personer >0) {
				result = ((personer + temp) * 0.5) + 1;
			}
			else{
				throw new AntalPersonerException("Antal Personer exception");
			}

		return result;
	}
	
}
