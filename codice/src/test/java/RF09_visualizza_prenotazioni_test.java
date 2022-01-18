import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class RF09_visualizza_prenotazioni_test {

	@Test
	public void test(){
		DB_lezioni db_lez = new DB_lezioni();
		gestore_lezioni g_lez = new gestore_lezioni(db_lez);
		
		lezione lez1=new lezione();
		lez1.nome_corso=("Programmazione_1");
		lez1.cognome_docente=("Codetta");
		lez1.numero_aula=(1);
		lez1.numero_aula=(7);
		lez1.posti_disponibili=(45);
		lez1.giorno=(LocalDate.of(2021, 10, 1));
		lez1.ora_inizio=(LocalTime.of(9,0));
		lez1.ora_fine=(LocalTime.of(13,0));
		
		lezione lez2=new lezione();
		lez2.nome_corso=("Ingegneria_del_Software");
		lez2.cognome_docente=("Codetta");
		lez2.numero_aula=(3);
		lez2.numero_aula=(11);
		lez2.posti_disponibili=(24);
		lez2.giorno=(LocalDate.of(2021, 1, 17));
		lez2.ora_inizio=(LocalTime.of(9,0));
		lez2.ora_fine=(LocalTime.of(13,0));
		
		lezione lez3=new lezione();
		lez3.nome_corso=("Sistemi_Operativi_1");
		lez3.cognome_docente=("Cerotti");
		lez3.numero_aula=(2);
		lez3.numero_aula=(4);
		lez3.posti_disponibili=(30);
		lez3.giorno=(LocalDate.of(2021, 10, 2));
		lez3.ora_inizio=(LocalTime.of(11,0));
		lez3.ora_fine=(LocalTime.of(13,0));
		
		ArrayList<prenotazione> arr=g_lez.get_prenotazioni_docente(lez1);
		Assert.assertEquals(4,arr.size());
		Assert.assertEquals(100006,arr.get(0).matricola_studente);
		Assert.assertEquals(100007,arr.get(1).matricola_studente);
		Assert.assertEquals(100021,arr.get(2).matricola_studente);
		Assert.assertEquals(100011,arr.get(3).matricola_studente);
		//Assert.assertEquals(100021,arr.get(4).matricola_studente);
		
		arr=g_lez.get_prenotazioni_docente(lez2);
		Assert.assertEquals(0,arr.size());
		
		arr=g_lez.get_prenotazioni_docente(lez3);
		Assert.assertEquals(4,arr.size());
		Assert.assertEquals(100004,arr.get(0).matricola_studente);
		Assert.assertEquals(100005,arr.get(1).matricola_studente);
		Assert.assertEquals(100011,arr.get(2).matricola_studente);
		Assert.assertEquals(100021,arr.get(3).matricola_studente);
		
		arr=g_lez.get_prenotazioni_studente(100003);
		Assert.assertEquals(1,arr.size());
		arr=g_lez.get_prenotazioni_studente(100021);
		Assert.assertEquals(2,arr.size());
		Assert.assertEquals("Programmazione_1",arr.get(0).nome_corso);
		Assert.assertEquals("Sistemi_Operativi_1",arr.get(1).nome_corso);
		
	}

}
