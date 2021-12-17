import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RF13_prenota_posto_test
{
    //RF13_prenota_posto
	//Autori: Rossari, Marisio
	//Classe di test per il caso d'uso "prenota_posto"

    public static DB_lezioni db_lez = new DB_lezioni();
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");

	// =======================================================================
	
    @Test
	//questo test chiama metodi che non prevedono valori 
	//di ritorno: pertanto non ci saranno assert, è utile
	//soltanto per verificare che il file delle lezioni venga 
	//modificato correttamente (i posti vengano decrementati)
    public void test01_db_modifica_lezione()
    {
    	//mi arriva una lezione esistente nel db, qui la scelgo io ma poi
		//in realtà la creerò in decrementa_prenota
		ArrayList<lezione> lezioni = db_lez.carica_lezioni(); 
		//creo un nuovo oggeto updated_lez con i posti decrementati
		lezione updated_lez = lezioni.get(3);
		updated_lez.posti_disponibili = lezioni.get(3).posti_disponibili - 1;
        db_lez.db_modifica_lezione(updated_lez);
    }
	public void test02_db_modifica_lezione()
    {
    	//mi arriva una lezione NON esistente nel db (in questo caso vuota)
		lezione lez = new lezione(); 
        db_lez.db_modifica_lezione(lez);
		//verifico che non venga modificato nulla (a parte la lezione del primo test)
    }
	public void test03_db_modifica_lezione()
    {
    	//mi arriva una lezione NON esistente nel db (parametri casuali)
		lezione lez = new lezione(); 
		//setto solo i campi che mi servono nell'if di controllo
		lez.anno = 1;
		lez.nome_corso = "Programmazione 2";
		lez.giorno = LocalDate.parse("11/11/2021", formatter);
		lez.ora_inizio = LocalTime.parse("12:00", formatter2);
        db_lez.db_modifica_lezione(lez);
		//verifico che non venga modificato nulla (a parte la lezione del primo test)
    }
	
	// =======================================================================
	
	@Test
	//questo test chiama metodi che non prevedono valori 
	//di ritorno: pertanto non ci saranno assert, è utile
	//soltanto per verificare che il file delle prenotazioni venga 
	//modificato correttamente (ovvero che la prenotazione venga aggiunta)
    public void test01_aggiungi_prenotazione()
    {
    	//mi arriva un oggetto prenotazione (con matricola già presente), 
		//qui scelgo tutto io ma poi
		//in realtà lo creerò in decrementa_prenota
		prenotazione obj_preno = new prenotazione();
		obj_preno.matricola_studente = 100006;
		obj_preno.nome_corso = "Programmazione_1";
		obj_preno.cognome_docente = "Codetta";
		obj_preno.giorno = LocalDate.parse("22/12/2021", formatter);
		obj_preno.ora_inizio = LocalTime.parse("09:15", formatter2);
		obj_preno.ora_fine = LocalTime.parse("13:15", formatter2);
		obj_preno.presente = false;
		obj_preno.aula = 4;
		db_lez.aggiungi_prenotazione(obj_preno);
    }
	public void test02_aggiungi_prenotazione()
    {
    	//faccio un'altra prova
		prenotazione obj_preno = new prenotazione();
		obj_preno.matricola_studente = 100003;
		obj_preno.nome_corso = "Sistemi_Operativi_1";
		obj_preno.cognome_docente = "Cerotti";
		obj_preno.giorno = LocalDate.parse("01/01/2022", formatter);
		obj_preno.ora_inizio = LocalTime.parse("14:00", formatter2);
		obj_preno.ora_fine = LocalTime.parse("18:00", formatter2);
		obj_preno.presente = false;
		obj_preno.aula = 7;
		db_lez.aggiungi_prenotazione(obj_preno);
    }


   
}
