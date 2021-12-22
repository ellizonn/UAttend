//package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

//import main.java.DB_avvisi;
//import main.java.UI_avviso; debug UI
//import main.java.gestore_avvisi;

public class RF01_visualizza_avvisi_recenti_test {

	// autore: Beccuti/Iabichino

	DB_avvisi db_avv = new DB_avvisi();
    
	gestore_avvisi g_avv = new gestore_avvisi(db_avv);
	
//	UI_avviso ui_avv = new UI_avviso(g_avv); debug UI
	
	@Test 
	public void testCerca_avvisi() {

		// autore: Beccuti/Iabichino
		
		assertTrue(!db_avv.cerca_avvisi().isEmpty());
		
		assertEquals(db_avv.cerca_avvisi().get(0).testo, "le_lezioni_finiscono_il_15_gennaio");

		//assertEquals(db_avv.cerca_avvisi().get(1).testo, "le_lezioni_finiscono_il_17_gennaio"); //testato anche in data odierna
		
	}

	/*Solo per debug 
	@Test
	public void testVisualizza_elenco() {

		// autore: Beccuti/Iabichino
		
		ui_avv.visualizza_elenco();
		
	}
	*/
}
