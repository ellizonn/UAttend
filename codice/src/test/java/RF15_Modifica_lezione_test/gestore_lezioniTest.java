import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class gestore_lezioniTest {
/*
	DB_lezioni db_lezioni = new DB_lezioni();
    public gestore_lezioni gl = new gestore_lezioni(db_lezioni);
    // Solo i metodi PUBLIC vengono testati 
	@Test
	public void testControlla_lezione_modificabile() {
		LocalTime test = LocalTime.now(); 
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),test.minusHours(1),test.plusHours(2));
		lezione l2 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().plusDays(1),test.minusHours(1),test.plusHours(2));
		lezione l3 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now(),test,test.plusMinutes(60));
		assertTrue(gl.controlla_lezione_modificabile(l));
		assertFalse(gl.controlla_lezione_modificabile(l2));
		assertFalse(gl.controlla_lezione_modificabile(l3));
	}
  
	@Test
	public void testControllo_data_corretta() {
		LocalTime test = LocalTime.now();
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),test,test.plusMinutes(60));
		lezione l2 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now(),test,test.plusMinutes(60));
		lezione l3 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().plusDays(1),test, test.plusMinutes(60));
		assertTrue(gl.controllo_data_corretta(l));
		assertTrue(gl.controllo_data_corretta(l2));
		assertFalse(gl.controllo_data_corretta(l3));
	}

	@Test
	public void testControllo_ora_corretta() {
		LocalTime test = LocalTime.now();
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),test,test.plusMinutes(60));
		lezione l2 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),test.minusHours(1), test.plusMinutes(60));
		assertFalse(gl.controllo_ora_corretta(l));
		assertTrue(gl.controllo_ora_corretta(l2));
	}

	@Test
	public void testControllo_lezioni_equal() {
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(12, 0), LocalTime.of(14, 0));
		lezione l2 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(12, 0),LocalTime.of(14, 0));
		lezione l3 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(2),LocalTime.of(13, 0),LocalTime.of(15, 0));
		assertTrue(gl.controllo_lezioni_equal(l, l2));
		assertFalse(gl.controllo_lezioni_equal(l, l3));
	}

	@Test 
	public void testSalvaModifica() throws FileNotFoundException {
		//Inizializzazione lezioni 
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(12, 0), LocalTime.of(14, 0));
		lezione l2 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(2),LocalTime.of(11, 0), LocalTime.of(14, 0));
        gestore_lezioni gl1 = new gestore_lezioni(db_lezioni);
        assertFalse(gl1.salvaModifiche(l, l));
        db_lezioni.aggiungi_lezione(l);
        gestore_lezioni gl2 = new gestore_lezioni(db_lezioni);
        assertTrue(gl2.salvaModifiche(l, l2));
	}
	
	@Test
	public void testSetData() throws FileNotFoundException {
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(12, 0), LocalTime.of(14, 0));
		System.setIn(new FileInputStream("test/SetData_Test.txt"));
		gestore_lezioni gl1 = new gestore_lezioni(db_lezioni);
        lezione test = gl1.setData(l);
        assertEquals(test.giorno.toString(),"2022-09-12");
	}

	@Test
	public void testSetOraInizio() throws FileNotFoundException {
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(12, 0), LocalTime.of(14, 0));
		System.setIn(new FileInputStream("test/SetOraInizio_Test1.txt"));
		gestore_lezioni gl1 = new gestore_lezioni(db_lezioni);
        lezione test = gl1.setOraInizio(l);
        assertEquals(test.ora_inizio.toString(),"13:00");
        System.setIn(new FileInputStream("test/SetOraInizio_Test2.txt"));
		gestore_lezioni gl2 = new gestore_lezioni(db_lezioni);
        lezione test2 = gl2.setOraInizio(l);
        assertEquals(test2.ora_inizio.toString(),"15:00");
        assertEquals(test2.ora_fine.toString(),"16:00");
	}
	
	@Test
	public void testSetOraFine() throws FileNotFoundException {
		
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(12, 0), LocalTime.of(14, 0));
		System.setIn(new FileInputStream("test/SetOraFine_Test1.txt"));
		gestore_lezioni gl1 = new gestore_lezioni(db_lezioni);
        lezione test = gl1.setOraFine(l);
        assertEquals(test.ora_fine.toString(),"15:00");
        
        // Data fine = Data inizio
        lezione l2 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(22, 0), LocalTime.of(23, 0));
        System.setIn(new FileInputStream("test/SetOraFine_Test2.txt"));
		gestore_lezioni gl2 = new gestore_lezioni(db_lezioni);
        lezione test2 = gl2.setOraFine(l2);
        assertEquals(test2.ora_fine.toString(),"22:00");
        assertEquals(test2.ora_inizio.toString(),"20:00");
        
        // Data fine < Data inizio
        lezione l3 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),LocalTime.of(22, 0), LocalTime.of(23, 0));
        System.setIn(new FileInputStream("test/SetOraFine_Test3.txt"));
		gestore_lezioni gl3 = new gestore_lezioni(db_lezioni);
        lezione test3 = gl3.setOraFine(l3);
        assertEquals(test3.ora_fine.toString(),"21:00");
        assertEquals(test3.ora_inizio.toString(),"20:00");
	}
	*/
}
