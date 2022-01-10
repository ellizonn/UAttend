import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

public class RF06_seleziona_aula_libera_test {

	@Test
	public void DB_test() {
		DB_lezioni db = new DB_lezioni ();
		
		ArrayList<aula> tot_aule = db.carica_aule();
		aula a0 = new aula();
		a0.numero = 4;
		a0.capienza = 40;
		aula a3 = new aula();
		a3.numero = 7;
		a3.capienza = 50;
		assertEquals (tot_aule.get(0).numero, a0.numero);
		assertEquals (tot_aule.get(0).capienza, a0.capienza);
		assertEquals (tot_aule.get(3).numero, a3.numero);
		assertEquals (tot_aule.get(3).capienza, a3.capienza);
		
		ArrayList<aula> aule_occupate = db.restituisci_elenco_aule_occupate(LocalDate.of(2021, 10 , 3), LocalTime.of(14, 0), LocalTime.of(18,0));
		assertEquals (aule_occupate.size(), 1);
		assertEquals (aule_occupate.get(0).numero, a3.numero);
		assertEquals (aule_occupate.get(0).capienza, a3.capienza);
		aule_occupate = db.restituisci_elenco_aule_occupate(LocalDate.of(2021, 10 , 3), LocalTime.of(13, 0), LocalTime.of(16,0));
		assertEquals (aule_occupate.size(), 1);
		assertEquals (aule_occupate.get(0).numero, a3.numero);
		assertEquals (aule_occupate.get(0).capienza, a3.capienza);
		aule_occupate = db.restituisci_elenco_aule_occupate(LocalDate.of(2021, 10 , 3), LocalTime.of(15, 0), LocalTime.of(16,0));
		assertEquals (aule_occupate.size(), 1);
		assertEquals (aule_occupate.get(0).numero, a3.numero);
		assertEquals (aule_occupate.get(0).capienza, a3.capienza);
		aule_occupate = db.restituisci_elenco_aule_occupate(LocalDate.of(2021, 10 , 3), LocalTime.of(15, 0), LocalTime.of(19,0));
		assertEquals (aule_occupate.size(), 1);
		assertEquals (aule_occupate.get(0).numero, a3.numero);
		assertEquals (aule_occupate.get(0).capienza, a3.capienza);
		aule_occupate = db.restituisci_elenco_aule_occupate(LocalDate.of(2021, 10 , 3), LocalTime.of(11, 0), LocalTime.of(20,0));
		assertEquals (aule_occupate.size(), 1);
		assertEquals (aule_occupate.get(0).numero, a3.numero);
		assertEquals (aule_occupate.get(0).capienza, a3.capienza);
		aule_occupate = db.restituisci_elenco_aule_occupate(LocalDate.of(2021, 10 , 3), LocalTime.of(10, 0), LocalTime.of(12,0));
		assertEquals (aule_occupate.size(), 0);
	}
	
	@Test
	public void test_differenza_insiemistica () {
		DB_lezioni db = new DB_lezioni ();
		gestore_lezioni g = new gestore_lezioni(db);
		aula a1 = new aula();
		a1.numero = 4;
		a1.capienza = 40;
		aula a2 = new aula();
		a2.numero = 5;
		a2.capienza = 45;
		aula a3 = new aula();
		a3.numero = 7;
		a3.capienza = 60;
		aula a4 = new aula();
		a4.numero = 10;
		a4.capienza = 30;
		ArrayList<aula> elenco_1 = new ArrayList<aula>();
		elenco_1.add(a1);
		elenco_1.add(a2);
		elenco_1.add(a3);
		elenco_1.add(a4);
		ArrayList<aula> elenco_2 = new ArrayList<aula>();
		elenco_2.add(a1);
		elenco_2.add(a2);
		ArrayList<aula> result = g.differenza_insiemistica(elenco_1, elenco_2);
		assertEquals (result.size(), 2);
		assertEquals (result.get(0).numero, 7);
		assertEquals (result.get(1).numero, 10);
	}
	
	@Test
	public void test_calcola_elenco_aule_libere () {
		DB_lezioni db = new DB_lezioni ();
		gestore_lezioni g = new gestore_lezioni(db);
		aula a1 = new aula();
		a1.numero = 4;
		a1.capienza = 40;
		aula a2 = new aula();
		a2.numero = 5;
		a2.capienza = 45;
		ArrayList<aula> empty = new ArrayList<aula>();
		ArrayList<aula> elenco_2 = new ArrayList<aula>();
		elenco_2.add(a1);
		elenco_2.add(a2);
		assertTrue (g.calcola_elenco_aule_libere(empty, elenco_2).isEmpty());
		assertEquals (g.calcola_elenco_aule_libere(elenco_2, empty), elenco_2);
	}
	
	@Test
	public void test_verifica_aule_libere () {
		DB_lezioni db = new DB_lezioni ();
		gestore_lezioni g = new gestore_lezioni(db);
		aula a1 = new aula();
		a1.numero = 4;
		a1.capienza = 40;
		aula a2 = new aula ();
		a2.numero = 11;
		a2.capienza = 25;
		ArrayList<aula> aule_libere = g.verifica_aule_libere(LocalDate.of(2021, 10 , 3), LocalTime.of(14, 0), LocalTime.of(18, 0));
		
		assertEquals (aule_libere.size(), 9);
		assertEquals (aule_libere.get(0).numero, a1.numero);
		assertEquals (aule_libere.get(0).capienza, a1.capienza);
		assertEquals (aule_libere.get(6).numero, a2.numero);
		assertEquals (aule_libere.get(6).capienza, a2.capienza);
	}

	@Test
	public void test_verifica_aula_selezionata () {
		DB_lezioni db = new DB_lezioni ();
		gestore_lezioni g = new gestore_lezioni(db);
		aula a1 = new aula();
		a1.numero = 4;
		a1.capienza = 40;
		aula a2 = new aula();
		a2.numero = 5;
		a2.capienza = 45;
		ArrayList<aula> elenco_2 = new ArrayList<aula>();
		elenco_2.add(a1);
		elenco_2.add(a2);
		assertTrue (g.verifica_aula_selezionata(elenco_2, 4));
		assertFalse (g.verifica_aula_selezionata(elenco_2, 9));
		
	}

}
