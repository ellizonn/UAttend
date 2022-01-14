import java.io.IOException;

import org.junit.Test;

public class RF09_visualizza_prenotazioni_test {

	@Test
	public void test() throws IOException {
		DB_lezioni db_lez = new DB_lezioni();
		gestore_lezioni g_lez = new gestore_lezioni(db_lez);
		UI_prenotazione ui_pren = new UI_prenotazione(g_lez);
		
		System.out.print("\n//STAFF LEZIONI//\n");
		ui_pren.visualizza_prenotaz_lez("Programmazione_1", "Codetta", "Staff");
		System.out.print("\n//STAFF LEZIONI//\n");
		ui_pren.visualizza_prenotaz_lez("Sistemi_Operativi_1", "Cerotti", "Staff");
		System.out.print("\n//STAFF LEZIONI//\n");
		ui_pren.visualizza_prenotaz_lez("Programmazione_1", "Cerotti", "Staff");
		
		System.out.print("\n//DOCENTE LEZIONI//\n");
		ui_pren.visualizza_prenotaz_lez("Programmazione_1", "Codetta", "Docente");
		System.out.print("\n//DOCENTE LEZIONI//\n");
		ui_pren.visualizza_prenotaz_lez("Sistemi_Operativi_1", "Cerotti", "Docente");
		System.out.print("\n//DOCENTE LEZIONI//\n");
		ui_pren.visualizza_prenotaz_lez("Programmazione_1", "Cerotti", "Docente");
		
		System.out.print("\n//STUDENTE LEZIONI//\n");
		ui_pren.visualizza_prenotaz_lez("Programmazione_1", "Codetta", "Studente");
		
		System.out.print("\n//STUDENTE//\n");
		ui_pren.visualizza_prenotaz_stud(100003, "Studente");
		System.out.print("\n//STUDENTE//\n");
		ui_pren.visualizza_prenotaz_stud(100003, "Docente");
		
	}

}
