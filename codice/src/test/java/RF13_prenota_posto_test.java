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
	public static gestore_lezioni gest_lezioni = new gestore_lezioni(db_lez);
	public static UI_prenotazione ui_prenotazione = new UI_prenotazione(gest_lezioni);
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");

	// =======================================================================
	

	//questi test ["test0X_modifica_lezione"]chiamano metodi 
	//che non prevedono valori 
	//di ritorno: pertanto non ci saranno assert, è utile
	//soltanto per verificare che il file delle lezioni venga 
	//modificato correttamente (i posti vengano decrementati)
	@Test
    public void test01_modifica_lezione()
	//RF13_prenota_posto
	//Autori: Rossari, Marisio
    {
    	//mi arriva una lezione esistente nel db, qui la scelgo io ma poi
		//in realtà la creerò in decrementa_prenota
		ArrayList<lezione> lezioni = db_lez.carica_lezioni(); 
		//creo un nuovo oggeto updated_lez con i posti decrementati
		lezione updated_lez = lezioni.get(4);
		updated_lez.posti_disponibili = lezioni.get(4).posti_disponibili - 1;
        db_lez.modifica_lezione(updated_lez);
    }
    
    @Test
	public void test02_modifica_lezione()
	//RF13_prenota_posto
	//Autori: Rossari, Marisio
    {
    	//mi arriva una lezione NON esistente nel db (in questo caso vuota)
		lezione lez = new lezione(); 
        db_lez.modifica_lezione(lez);
		//verifico che non venga modificato nulla (a parte la lezione del primo test)
    }
    
    @Test
	public void test03_modifica_lezione()
	//RF13_prenota_posto
	//Autori: Rossari, Marisio
    {
    	//mi arriva una lezione NON esistente nel db (parametri casuali)
		lezione lez = new lezione(); 
		//setto solo i campi che mi servono nell'if di controllo
		lez.anno = 1;
		lez.nome_corso = "Programmazione 2";
		lez.giorno = LocalDate.parse("11/11/2021", formatter);
		lez.ora_inizio = LocalTime.parse("12:00", formatter2);
        db_lez.modifica_lezione(lez);
		//verifico che non venga modificato nulla (a parte la lezione del primo test)
    }
	
	// =======================================================================
	
	//questi test ["test0X_aggiungi_prenotazione"]chiamano metodi 
	//che non prevedono valori 
	//di ritorno: pertanto non ci saranno assert, è utile
	//soltanto per verificare che il file delle prenotazioni venga 
	//modificato correttamente (ovvero che la prenotazione venga aggiunta)
	@Test
    public void test01_aggiungi_prenotazione()
	//RF13_prenota_posto
	//Autori: Rossari, Marisio
    {
    	//mi arriva un oggetto prenotazione (con matricola già presente), 
		//qui scelgo tutto io ma poi
		//in realtà lo creerò in decrementa_prenota
		prenotazione obj_preno = new prenotazione();
		obj_preno.matricola_studente = 100011;
		obj_preno.nome_corso = "Programmazione_1";
		obj_preno.cognome_docente = "Codetta";
		obj_preno.giorno = LocalDate.parse("01/10/2021", formatter);
		obj_preno.ora_inizio = LocalTime.parse("09:00", formatter2);
		obj_preno.ora_fine = LocalTime.parse("13:00", formatter2);
		obj_preno.presente = "indefinito";
		obj_preno.aula = 7;
		db_lez.aggiungi_prenotazione(obj_preno);
    }
    
    @Test
	public void test02_aggiungi_prenotazione()
	//RF13_prenota_posto
	//Autori: Rossari, Marisio
    {
    	//faccio un'altra prova
		prenotazione obj_preno = new prenotazione();
		obj_preno.matricola_studente = 100011;
		obj_preno.nome_corso = "Sistemi_Operativi_1";
		obj_preno.cognome_docente = "Cerotti";
		obj_preno.giorno = LocalDate.parse("02/10/2021", formatter);
		obj_preno.ora_inizio = LocalTime.parse("11:00", formatter2);
		obj_preno.ora_fine = LocalTime.parse("13:00", formatter2);
		obj_preno.presente = "indefinito";
		obj_preno.aula = 4;
		db_lez.aggiungi_prenotazione(obj_preno);
    }
	
	// =======================================================================
	
	//questi test ["test0X_decrementa_prenota"]chiamano metodi
	//che non prevedono valori 
	//di ritorno: pertanto non ci saranno assert, è utile
	//soltanto per verificare che i file delle prenotazioni e
	//delle lezioni vengano modificati correttamente 
	@Test
    public void test01_decrementa_prenota()
	//RF13_prenota_posto
	//Autori: Rossari, Marisio
    {
		//matricola e lezione ci arriveranno poi dal caso d'uso precedente
		//la modifica ha successo
		lezione lez = new lezione(); 
		lez.anno = 1;
		lez.nome_corso = "Programmazione_1";
		lez.giorno = LocalDate.parse("01/10/2021", formatter);
		lez.ora_inizio = LocalTime.parse("09:00", formatter2);
		lez.ora_fine = LocalTime.parse("13:00", formatter2);
		lez.cognome_docente = "Codetta";
		lez.numero_aula = 7;
		lez.posti_disponibili = 44;
        gest_lezioni.decrementa_prenota(lez, 100021);
	}
	
	@Test
	public void test02_decrementa_prenota()
	//RF13_prenota_posto
	//Autori: Rossari, Marisio
	{
		//la lezione non esiste
		lezione lez = new lezione(); 
		lez.anno = 2;
		lez.nome_corso = "Sistemi_Operativi_1";
		lez.giorno = LocalDate.parse("02/10/2021", formatter);
		lez.ora_inizio = LocalTime.parse("11:00", formatter2);
		lez.ora_fine = LocalTime.parse("13:00", formatter2);
		lez.cognome_docente = "Cerotti";
		lez.numero_aula = 4;
		lez.posti_disponibili = 30;
        gest_lezioni.decrementa_prenota(lez, 100021);
	}

	// =======================================================================
	
	//test per verificare la correttezza di
	//scelta_stud attraverso il metodo controllo_formato_scelta
	   @Test
	   public void test01_controllo_formato_scelta()
	    //RF13_prenota_posto
		//Autori: Rossari, Marisio
	   {
		   String s = new String("indietro");
		   assertEquals(gest_lezioni.controllo_formato_scelta(s),true);
		   s = new String("procedi");
		   assertEquals(gest_lezioni.controllo_formato_scelta(s),true);
	   }
	   
	   @Test
	   public void test02_controllo_formato_scelta()
	    //RF13_prenota_posto
		//Autori: Rossari, Marisio
	   {
		   String s = new String("XndietroY");
		   assertEquals(gest_lezioni.controllo_formato_scelta(s),false);
		   s = new String(" ");
		   assertEquals(gest_lezioni.controllo_formato_scelta(s),false);
		   s = new String("");
		   assertEquals(gest_lezioni.controllo_formato_scelta(s),false);
		   s = new String();
		   assertEquals(gest_lezioni.controllo_formato_scelta(s),false);
	   }
	   
	   // =======================================================================
	   
	   @Test
	   //test per controllo_data_e_posti
	   public void test01_controllo_data_e_posti()
	    //RF13_prenota_posto
		//Autori: Rossari, Marisio
	   {
			lezione lez = new lezione(); 
			lez.anno = 2;
			lez.nome_corso = "Sistemi_Operativi_1";
			lez.giorno = LocalDate.parse("12/01/2023", formatter);
			lez.ora_inizio = LocalTime.parse("12:00", formatter2);
			lez.ora_fine = LocalTime.parse("16:00", formatter2);
			lez.cognome_docente = "Cerotti";
			lez.numero_aula = 7;
			lez.posti_disponibili = 30;
			//la chiamata deve restituire "ok"
			assertEquals("ok",gest_lezioni.controllo_data_e_posti(lez));
			//altra lezione corretta
			lez = new lezione(); 
			lez.anno = 3;
			lez.nome_corso = "Sistemi_Operativi_2";
			lez.giorno = LocalDate.parse("01/01/2023", formatter);
			lez.ora_inizio = LocalTime.parse("12:00", formatter2);
			lez.ora_fine = LocalTime.parse("16:00", formatter2);
			lez.cognome_docente = "Cerotti";
			lez.numero_aula = 7;
			lez.posti_disponibili = 1;
			//la chiamata deve restituire "ok"
			assertEquals(gest_lezioni.controllo_data_e_posti(lez),"ok");
	   }
	   
	   @Test
	   public void test02_controllo_data_e_posti()
	   //RF13_prenota_posto
		//Autori: Rossari, Marisio
	   {
			//lezione NON corretta
			lezione lez = new lezione(); 
			lez.anno = 2;
			lez.nome_corso = "Sistemi_Operativi_1";
			lez.giorno = LocalDate.parse("12/11/2021", formatter);
			lez.ora_inizio = LocalTime.parse("12:00", formatter2);
			lez.ora_fine = LocalTime.parse("16:00", formatter2);
			lez.cognome_docente = "Cerotti";
			lez.numero_aula = 7;
			lez.posti_disponibili = 30;
			//la chiamata deve restituire "err_data"
			assertEquals(gest_lezioni.controllo_data_e_posti(lez),"err_data");
			//altra lezione NON corretta
			lez = new lezione(); 
			lez.anno = 3;
			lez.nome_corso = "Sistemi_Operativi_2";
			lez.giorno = LocalDate.parse("01/01/2023", formatter);
			lez.ora_inizio = LocalTime.parse("12:00", formatter2);
			lez.ora_fine = LocalTime.parse("16:00", formatter2);
			lez.cognome_docente = "Cerotti";
			lez.numero_aula = 7;
			lez.posti_disponibili = 0;
			//la chiamata deve restituire "err_posti"
			assertEquals(gest_lezioni.controllo_data_e_posti(lez),"err_posti");
	   }
	   
		// =======================================================================
	   
	   @Test
	   public void test01_prenotazione_doppia()
	   //RF13_prenota_posto
	   //Autori: Rossari, Marisio
	   {
		    lezione lez = new lezione();
			lez.anno = 1;
			lez.nome_corso = "Programmazione_1";
			lez.cognome_docente = "Codetta";
			lez.numero_aula = 7;
			lez.giorno = LocalDate.parse("01/10/2021", formatter);
			lez.ora_inizio = LocalTime.parse("09:00", formatter2);
			lez.ora_fine = LocalTime.parse("13:00", formatter2);
			lez.posti_disponibili = 48;
		    assertTrue(gest_lezioni.controllo_prenotazione_doppia(lez,100006));
	   }

	   @Test
	   public void test02_prenotazione_doppia()
	   //RF13_prenota_posto
	   //Autori: Rossari, Marisio
	   {
		    lezione lez = new lezione();
			lez.anno = 2;
			lez.nome_corso = "Sistemi_Operativi_1";
			lez.cognome_docente = "Cerotti";
			lez.numero_aula = 4;
			lez.giorno = LocalDate.parse("02/10/2021", formatter);
			lez.ora_inizio = LocalTime.parse("11:00", formatter2);
			lez.ora_fine = LocalTime.parse("13:00", formatter2);
			lez.posti_disponibili = 38;
		    assertFalse(gest_lezioni.controllo_prenotazione_doppia(lez,100006));
	   }
}
