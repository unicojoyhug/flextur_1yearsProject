package seHistorik;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println(new Main().seHistorik());
		
	}

	private List<Tur> seHistorik() {
		Tur tur1 = new Tur();
		Tur tur2 = new Tur();
		
		tur1.setAfstand(100);
		tur1.setDato(LocalDate.of(2016, 5, 3));
		tur1.setAntalPersoner(3);
		tur1.setFraKommune(Kommune.Herning);
		tur1.setTilKommune(Kommune.Aarhus);		
		tur1.setTotalPris(tur1);
	
		
		tur2.setAfstand(10);
		tur2.setDato(LocalDate.of(2016, 5, 3));
		tur2.setAntalPersoner(1);
		tur2.setFraKommune(Kommune.Herning);
		tur2.setTilKommune(Kommune.Herning);
		tur2.setTotalPris(tur2);

			
		List<Tur> turHistorik = new ArrayList<>();
		turHistorik.add(tur1);
		turHistorik.add(tur2);
		
		return turHistorik;

	}
	
	
}
