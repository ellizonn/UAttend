import static org.junit.Assert.*;

import org.junit.Test;

public class RF17_Crea_utenti_test {
	//RF17: crea utente
	//autori: La Spisa & Frasson
	
	@Test
	public void controllo_generalita_e_generazione_credenziali_test() 
	{
		DB_utenti d1=new DB_utenti();
		gestore_utenti g1= new gestore_utenti(d1);
		indirizzo residenza;
		residenza=new indirizzo();
		residenza.CAP=20113;
		residenza.localita="Milano";
		residenza.numero=5;
		residenza.via="Via Roma";
		assertEquals(true, g1.controllo_generalita("Mario", "Rossi", residenza, "docente", 0));
		assertEquals(false, g1.controllo_generalita("", "Rossi", residenza, "docente", 0));
		assertEquals(false, g1.controllo_generalita("Mario", "", residenza, "docente", 0));
		assertEquals(false, g1.controllo_generalita("Mario", "Rossi", residenza, null, 0));
		gestore_accessi g2= new gestore_accessi(d1);
	    assertEquals(true, g2.generazione_credenziali());
	}
	
	
	@Test
	public void controlla_scelta_test()
	{
		DB_utenti d1=new DB_utenti();
		gestore_utenti g1= new gestore_utenti(d1);
		assertEquals(true, g1.controlla_scelta(5));
		assertEquals(false, g1.controlla_scelta(0));
	}
	
}
