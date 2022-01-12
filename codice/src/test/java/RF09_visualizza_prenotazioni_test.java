import java.io.IOException;

import org.junit.Test;

public class ok {

	@Test
	public void test() throws IOException {
		DB_lezioni db_lez = new DB_lezioni();
		gestore_lezioni g_lez = new gestore_lezioni(db_lez);
		UI_prenotazione ui_pren = new UI_prenotazione(g_lez);
		
		System.out.print("//DOCENTE//\n");
		ui_pren.visualizza_prenotazioni(UI_prenotazione.utente.DOCENTE);
		System.out.print("//STUDENTE//\n");
		ui_pren.visualizza_prenotazioni(UI_prenotazione.utente.STUDENTE);
		System.out.print("//STAFF//\n");
		ui_pren.visualizza_prenotazioni(UI_prenotazione.utente.STAFF);
	}

}
