import static org.junit.Assert.*;

import org.junit.Test;

public class RF07_ricerca_utente_test {
	//autori: Malavasi - Torta
	
	DB_utenti db_ut = new DB_utenti();
	gestore_ricerche g_ric = new gestore_ricerche(db_ut, null, null);
	
	@Test
	public void test_verifica_parametri_01() {
		//matricola e cognome vuoti
		assertNull("DB deve ritornare null", g_ric.verifica_parametri(0, ""));
	}
	
	@Test
	public void test_verifica_parametri_02() {
		//matricola ha meno di 6 cifre e cognome vuoto
		assertNull("DB deve ritornare null", g_ric.verifica_parametri(00, ""));
	}
	
	@Test
	public void test_verifica_parametri_03() {
		//matricola ha piu' di 6 cifre e cognome vuoto
		assertNull("DB deve ritornare null", g_ric.verifica_parametri(0000000, ""));
	}
	
	@Test
	public void test_verifica_parametri_04() {
		//utente non esistente
		assertNull("DB deve ritornare null", g_ric.verifica_parametri(0, ""));
	}
	
	@Test
	public void test_verifica_parametri_05() {
		//matricola corretta e cognome vuoto
		assertNotNull("DB deve ritornare un account", g_ric.verifica_parametri(100001, ""));
	}
	
	@Test
	public void test_verifica_parametri_06() {
		//matricola e cognome corretti
		assertNotNull("DB deve ritornare un account", g_ric.verifica_parametri(100001, "Rossi"));
	}
	
	@Test
	public void test_verifica_parametri_07() {
		//matricola errata e cognome corretto
		assertNotNull("DB deve ritornare un account", g_ric.verifica_parametri(010101, "Rossi"));
	}
	
}
