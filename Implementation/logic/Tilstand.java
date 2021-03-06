package logic;
/**
 * Tilstand (forskellige scenarier på grund af use cases
 * bruges for notifyObservers
 * @author Juyoung Choi
 *
 */
public enum Tilstand {
	SØG_HISTORIK_KUNDE, SØG_HISTORIK_BM, LOGIN_KUNDE, LOGIN_BM, LOGIN_FEJL, BESTIL_KØRSEL, REGISTER_KØRSEL, SØG_IKKE_GODKENDT,
	SØG_BESTILTE_KØRSLER, HENT_BIL_LISTE, TILDEL_BIL, GODKEND_KØRSEL, PRIS_UDREGNET, KUNDE_OPRETTET, KUNDE_RETTET, SØG_ALLE_BESTILTE_KØRSLER;
}
