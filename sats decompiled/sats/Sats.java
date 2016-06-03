
package sats;
/**
 * Decomplied klasse for at forstå systemet
 * 
 */
import java.util.Calendar;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import sats.UnknownKommuneException;

public class Sats {
    private static Sats instance = null;
    private static Random rnd = new Random();
    private Map<String, Double> satser = new TreeMap<String, Double>(String.CASE_INSENSITIVE_ORDER);

    private Sats() {
        this.satser.put("Aarhus", 14.0);
        this.satser.put("Favrskov", 4.0);
        this.satser.put("Hedensted", 4.0);
        this.satser.put("Herning", 14.0);
        this.satser.put("Holstebro", 4.0);
        this.satser.put("Horsens", 4.0);
        this.satser.put("Ikast/Brande", 7.0); //TODO check with enum
        this.satser.put("Lemvig", 4.0);
        this.satser.put("Norddjurs", 4.0);
        this.satser.put("Odder", 14.0);
        this.satser.put("Randers", 7.0);
        this.satser.put("Ringkøbing/Skjern", 4.0);
        this.satser.put("Samsø", 14.0);
        this.satser.put("Silkeborg", 7.0);
        this.satser.put("Skanderborg", 4.0);
        this.satser.put("Skive", 7.0);
        this.satser.put("Struer", 7.0);
        this.satser.put("Syddjurs", 4.0);
        this.satser.put("Viborg", 7.0);
    }

    public String[] getKommuner() {
        String[] kommuner = new String[this.satser.keySet().size()];
        int i = 0;
        for (String key : this.satser.keySet()) {
            kommuner[i++] = key;
        }
        return kommuner;
    }

    public double getSats(String fraKommune, String tilKommune, int year, int month, int day) throws UnknownKommuneException {
        this.randomDelay(2.0, 5.0);
        if (!this.satser.containsKey(fraKommune)) {
            throw new UnknownKommuneException("Unknown kommune: " + fraKommune);
        }
        if (!this.satser.containsKey(tilKommune)) {
            throw new UnknownKommuneException("Unknown kommune: " + tilKommune);
        }
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        int dayOfWeek = c.get(7);
        double rate = (this.satser.get(fraKommune) + this.satser.get(tilKommune)) / 2.0;
        if (dayOfWeek == 7 || dayOfWeek == 1) {
            rate *= 1.25;
        }
        return rate;
    }

    private void randomDelay(double min, double max) {
        try {
            double delaySecs = rnd.nextDouble() * (max - min) + min;
            Thread.sleep((long)(delaySecs * 1000.0));
        }
        catch (InterruptedException delaySecs) {
            // empty catch block
        }
    }

    public static Sats i() {
        if (instance == null) {
            instance = new Sats();
        }
        return instance;
    }
}

