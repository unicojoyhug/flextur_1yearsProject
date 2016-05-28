package seHistorik;

import domain.Flextur;
import domain.FlexturImpl;

public class ControllerPrototype {
	
//	Flextur flextur;
	
	public void udregnPris(Flextur flextur){
		
		PrisUdregnerProto PU = new PrisUdregnerProto(flextur);
		
		PU.start();
		
		synchronized(PU){
			
			try{
				System.out.println("waiting");

				PU.wait();
				
			}catch(InterruptedException e){
				System.out.println("ex");
			}	
			
			System.out.println("done");
		}
		
		
	}
}

//package seHistorik;
//
//import domain.Flextur;
//import domain.FlexturImpl;
//
//public class ControllerPrototype extends Thread {
//	
//	Flextur flextur;
//	
//	public void udregnPris(Flextur flextur){
//		
//		PrisUdregnerProto PU = new PrisUdregnerProto(flextur);
//		
//		PU.start();
//		
//		synchronized(PU){
//			
//			try{
//				System.out.println("waiting");
//
//				PU.wait();
//				
//			}catch(InterruptedException e){
//				System.out.println("ex");
//			}	
//			
//			System.out.println("done");
//		}
//		
//		
//	}
//}

