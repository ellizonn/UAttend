package src;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testVisualizzaPrenotazioni {
	
	ArrayList<prenotazione> arrPren= new ArrayList<>();

	@BeforeEach
	void before() {
		prenotazione p1=new prenotazione(3675, "Basi dati", "Rossi", 3, LocalDate.of(2020, Month.JANUARY, 8), LocalTime.of(10,00,00), LocalTime.of(11,00,00), true);
		prenotazione p2=new prenotazione(4574, "Logica", "Bruni", 5, LocalDate.of(2020, Month.APRIL, 8), LocalTime.of(9,00,00), LocalTime.of(10,00,00), false);
		prenotazione p3=new prenotazione(3451, "Programmazione 1", "Nervi", 11, LocalDate.of(2020, Month.FEBRUARY, 8), LocalTime.of(10,00,00), LocalTime.of(12,00,00), true);
		prenotazione p4=new prenotazione(8362, "Analisi 1", "Bitta", 7, LocalDate.of(2020, Month.JUNE, 8), LocalTime.of(11,00,00), LocalTime.of(12,00,00), false);
		
		arrPren.add(p1);
		arrPren.add(p2);
		arrPren.add(p3);
		arrPren.add(p4);
	}
	
	@Test
	void test() throws IOException {
		
		System.out.print("//DOCENTE//\n");
		new VisualizzaPrenotazioni(VisualizzaPrenotazioni.utente.DOCENTE, arrPren);
		System.out.print("//STUDENTE//\n");
		new VisualizzaPrenotazioni(VisualizzaPrenotazioni.utente.STUDENTE, arrPren);
		System.out.print("//STAFF//\n");
		new VisualizzaPrenotazioni(VisualizzaPrenotazioni.utente.STAFF, arrPren);
		
	}
	
	

}
