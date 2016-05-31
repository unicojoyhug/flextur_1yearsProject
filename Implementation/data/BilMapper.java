package data;

import java.util.List;

import domain.Bil;
import util.DataAccess;

public interface BilMapper extends CRUD<Bil, Integer> {

	List<Bil> hentBilListe(DataAccess dataAccess, int antalPersoner, boolean tilvalgMulighed);

	void tildelBil(DataAccess dataAccess, int bilId, long flexturId);
}
