import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

public class RF15Test {

	DB_lezioni db_lezioni = new DB_lezioni();
    public gestore_lezioni gl = new gestore_lezioni(db_lezioni);
    /* Solo i metodi PUBLIC vengono testati */
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
		System.setIn(new FileInputStream("src/test/java/resources/SetData_Test.txt"));
		gestore_lezioni gl1 = new gestore_lezioni(db_lezioni);
        lezione test = gl1.setData(l);
        assertEquals(test.giorno.toString(),"2022-09-12");
	}

	@Test
	public void testSetOraInizio() throws IOException {
		//Preparazione file test 1
		LocalTime prova = LocalTime.now();
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),prova.plusHours(4), prova.plusHours(7));
		prova = LocalTime.now().plusHours(5);
		int O = prova.getHour();
		int M = prova.getMinute();
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/resources/SetOraInizio_Test1.txt",false));
		writer.write(String.valueOf(O)+"\n"+String.valueOf(M));
		writer.close();
		// Test 1
		System.setIn(new FileInputStream("src/test/java/resources/SetOraInizio_Test1.txt"));
		gestore_lezioni gl1 = new gestore_lezioni(db_lezioni);
        lezione test = gl1.setOraInizio(l);
        assertEquals(test.ora_inizio.toString(), prova.format(DateTimeFormatter.ofPattern("HH:mm")));
        
        //Preparazione file test 2
		int O2 = prova.plusHours(3).getHour();
		int M2 = prova.plusHours(3).getMinute();
		int O3 = prova.plusHours(4).getHour();
		int M3 = prova.plusHours(4).getMinute();
		writer = new BufferedWriter(new FileWriter("src/test/java/resources/SetOraInizio_Test2.txt",false));
		writer.write(String.valueOf(O2)+"\n"+String.valueOf(M2)+"\n"+String.valueOf(O3)+"\n"+String.valueOf(M3));
		writer.close();
		// Test 2
		System.setIn(new FileInputStream("src/test/java/resources/SetOraInizio_Test2.txt"));
		gestore_lezioni gl2 = new gestore_lezioni(db_lezioni);
        lezione test2 = gl2.setOraInizio(l);
        assertEquals(test2.ora_inizio.toString(),prova.plusHours(3).format(DateTimeFormatter.ofPattern("HH:mm")));
        assertEquals(test2.ora_fine.toString(),prova.plusHours(4).format(DateTimeFormatter.ofPattern("HH:mm")));
	}
	
	@Test
	public void testSetOraFine() throws IOException {
		LocalTime prova = LocalTime.now();
		lezione l = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),prova.plusHours(4), prova.plusHours(6));
		int O = prova.plusHours(7).getHour();
		int M = prova.plusHours(7).getMinute();
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/resources/SetOraFine_Test1.txt",false));
		writer.write(String.valueOf(O)+"\n"+String.valueOf(M));
		writer.close();
		
		System.setIn(new FileInputStream("src/test/java/resources/SetOraFine_Test1.txt"));
		gestore_lezioni gl1 = new gestore_lezioni(db_lezioni);
        lezione test = gl1.setOraFine(l);
        assertEquals(test.ora_fine.toString(),prova.plusHours(7).format(DateTimeFormatter.ofPattern("HH:mm")));
        
        // Data fine = Data inizio
		int O2 = prova.plusHours(4).getHour();
		int M2 = prova.plusHours(4).getMinute();
		int O3 = prova.plusHours(2).getHour();
		int M3 = prova.plusHours(2).getMinute();
		writer = new BufferedWriter(new FileWriter("src/test/java/resources/SetOraFine_Test2.txt",false));
		writer.write(String.valueOf(O2)+"\n"+String.valueOf(M2)+"\n"+String.valueOf(O3)+"\n"+String.valueOf(M3));
		writer.close();
		
        System.setIn(new FileInputStream("src/test/java/resources/SetOraFine_Test2.txt"));
		gestore_lezioni gl2 = new gestore_lezioni(db_lezioni);
        lezione test2 = gl2.setOraFine(l);
        assertEquals(test2.ora_fine.toString(),prova.plusHours(4).format(DateTimeFormatter.ofPattern("HH:mm")));
        assertEquals(test2.ora_inizio.toString(),prova.plusHours(2).format(DateTimeFormatter.ofPattern("HH:mm")));
        
        // Data fine < Data inizio
        lezione l2 = new lezione("FLT",  "Rossi", 2022, 104, 69, LocalDate.now().minusDays(1),prova.plusHours(6), prova.plusHours(8));
        int O4 = prova.plusHours(5).getHour();
		int M4 = prova.plusHours(5).getMinute();
		int O5 = prova.plusHours(3).getHour();
		int M5 = prova.plusHours(3).getMinute();
		writer = new BufferedWriter(new FileWriter("src/test/java/resources/SetOraFine_Test3.txt",false));
		writer.write(String.valueOf(O4)+"\n"+String.valueOf(M4)+"\n"+String.valueOf(O5)+"\n"+String.valueOf(M5));
		writer.close();
		
        System.setIn(new FileInputStream("src/test/java/resources/SetOraFine_Test3.txt"));
		gestore_lezioni gl3 = new gestore_lezioni(db_lezioni);
        lezione test3 = gl3.setOraFine(l2);
        assertEquals(test3.ora_fine.toString(),prova.plusHours(5).format(DateTimeFormatter.ofPattern("HH:mm")));
        assertEquals(test3.ora_inizio.toString(),prova.plusHours(3).format(DateTimeFormatter.ofPattern("HH:mm")));
	}

	@Test
	public void testComparaOre() {
		oraLocale DoTest = new oraLocale();
		assertFalse(DoTest.comparaOre(LocalTime.now().plusMinutes(30)));
		assertTrue(DoTest.comparaOre(LocalTime.now().minusMinutes(61)));
	}
	
	@Test
	public void testComparaData() {
		dataLocale DoTest = new dataLocale();
		LocalDate test = LocalDate.of(2075, 12, 24);
		assertTrue(DoTest.comparaData(test) < 1);
	}
}

