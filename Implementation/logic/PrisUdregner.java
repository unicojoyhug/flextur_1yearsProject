package logic;

import sats.Sats;
import sats.UnknownKommuneException;

public class PrisUdregner {

	private double distance, takst, resultat;
	private String Origin, Destination;
	private int year, month, day;

	public double takstUdregner() {
		try {
			takst = Sats.i().getSats(Origin, Destination, year, month, day);
		} catch (UnknownKommuneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return takst;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setOrigin(String origin) {
		Origin = origin;

	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
