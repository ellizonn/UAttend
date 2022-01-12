import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class RF01_visualizza_avvisi_recenti_test {

	// autore: Beccuti/Iabichino RF01

	DB_avvisi db_avv = new DB_avvisi();
    
	gestore_avvisi g_avv = new gestore_avvisi(db_avv);
	
	@Test 
	public void testCerca_avvisi() {

		// autore: Beccuti/Iabichino RF01
		
		assertTrue(!db_avv.cerca_avvisi(LocalDate.now()).isEmpty());
		
		assertEquals(db_avv.cerca_avvisi(LocalDate.now()).get(0).testo, "le_lezioni_finiscono_il_15_gennaio");
		
	}

}
