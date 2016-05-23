package util;

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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTakst() {
		return takst;
	}

	public void setTakst(double takst) {
		this.takst = takst;
	}

	public String getOrigin() {
		return Origin;
	}

	public void setOrigin(String origin) {
		Origin = origin;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
