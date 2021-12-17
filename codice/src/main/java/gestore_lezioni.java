class gestore_lezioni
{
    DB_lezioni db_lez;

    public gestore_lezioni(DB_lezioni d1)
    {
      //autore: Codetta
      db_lez=d1;
    }
	
	
	// =======================================================================
  
	public boolean controllo_formato_scelta(String scelta_stud){
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		boolean esito_formato_scelta;
		if( !(scelta_stud.equals("indietro")) && !(scelta_stud.equals("procedi")) || scelta_stud==null )
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
		db_lez.db_modifica_lezione(new_lez); //chiamata
		//creo oggetto prenotazione
		prenotazione obj_preno = new prenotazione();
		obj_preno.matricola_studente = matricola;
		obj_preno.nome_corso = lez.nome_corso;
		obj_preno.cognome_docente = lez.cognome_docente;
		obj_preno.giorno = lez.giorno;
		obj_preno.ora_inizio = lez.ora_inizio;
		obj_preno.ora_fine = obj_preno.ora_fine;
		obj_preno.presente = false; // di default
		obj_preno.aula = lez.numero_aula;
		db_lez.aggiungi_prenotazione(obj_preno); //chiamata
	}
	
	
}
