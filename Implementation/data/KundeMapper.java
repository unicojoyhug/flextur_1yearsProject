package data;

import domain.Kunde;
import util.DataAccess;

public interface KundeMapper extends CRUD<Kunde, String> {
	
	int createCPRogKunde(DataAccess dataAccess, Kunde kunde);

}
