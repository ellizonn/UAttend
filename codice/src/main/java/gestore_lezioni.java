import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class gestore_lezioni
{
    DB_lezioni db_lez;

    public gestore_lezioni(DB_lezioni d1)
    {
      //autore: Codetta
      db_lez=d1;
    }
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	// =======================================================================
  
	public String controllo_data_e_posti(lezione lez){
		
    	//RF13_prenota_posto
    	//Autori: Rossari, Marisio
		
		
		LocalDate oggi = LocalDate.now();
		String str = oggi.format(formatter);
    	LocalDate today = LocalDate.parse(str, formatter);

    	String esito_data_e_posti;
    	if(lez.giorno.compareTo(today)>0 && lez.posti_disponibili>0) {
    		esito_data_e_posti = new String("ok");
    	}
    	else if(lez.giorno.compareTo(today)<=0) {
    		esito_data_e_posti = new String("err_data");
    	}
		else //if(lez.posti_disponibili == 0)
			esito_data_e_posti = new String("err_posti");
		return esito_data_e_posti;
		
    }
	
	public boolean controllo_formato_scelta(String scelta_stud){
		
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		boolean esito_formato_scelta;
		if( (!(scelta_stud.equals("indietro")) && !(scelta_stud.equals("procedi"))) || scelta_stud==null )
			esito_formato_scelta = false;
		else
			esito_formato_scelta = true;
		return esito_formato_scelta;
		
	}
  
  	public void decrementa_prenota(lezione lez, int matricola){
		
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		//creo nuovo oggetto lezione
		lezione new_lez = lez;
		new_lez.posti_disponibili = lez.posti_disponibili - 1;
		db_lez.modifica_lezione(new_lez); //chiamata
		//creo oggetto prenotazione
		prenotazione obj_preno = new prenotazione();
		obj_preno.matricola_studente = matricola;
		obj_preno.nome_corso = new_lez.nome_corso;
		obj_preno.cognome_docente = new_lez.cognome_docente;
		obj_preno.giorno = new_lez.giorno;
		obj_preno.ora_inizio = new_lez.ora_inizio;
		obj_preno.ora_fine = new_lez.ora_fine;
		obj_preno.presente = false; // di default
		obj_preno.aula = new_lez.numero_aula;
		db_lez.aggiungi_prenotazione(obj_preno); //chiamata
		
	}
	
	
}
