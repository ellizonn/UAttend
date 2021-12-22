class gestore_lezioni
{
    DB_lezioni db_lez;

    public gestore_lezioni(DB_lezioni d1)
    {
      //autore: Codetta
      db_lez=d1;
    }

	/**
	 *  Verifica il corretto inserimento dell'anno del corso 
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @param anno - anno selezionato 
 	 * @return esito della verifica
	 */	
	public String verifica_anno_corso(int anno)
	{
		String esito = "";
		if(anno >3 || anno <1)
			esito = "ANNO_NON_VALIDO";
		else
			esito = "OK";
		
		return esito;
	}

	/**
	 *  Verifica il corretto inserimento del nome del corso 
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @param nomeCorso - il nome del corso 
 	 * @return esito della verifica
	 */
	public String verifica_nome_corso(String nomeCorso){

		if (nomeCorso == null) return "ABORT";
		else
		if (nomeCorso == "") return "CORSO_VUOTO";
		else
		if (nomeCorso.length() < 5 ) return "CORSO_CORTO";
		else
			{
				if(db_lez.ricerca_nome_corso(nomeCorso) == 0)
					return "OK";
				else
					return "CORSO_PRESENTE";
			}
	}

	/**
	 *  Aggiunge il corso con i parametri specificati
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @param nome - il nome del corso 
	 * @param anno - l'anno del corso 
	 * @param cognomeDocente - il cognome del docente che tiene il corso
	 */
	public void aggiungi_corso(String nome, int anno, String cognomeDocente)
	{
		corso c = new corso();
		
		c.nome = nome;
		c.anno = anno;
		c.cognome_docente = cognomeDocente;

		db_lez.aggiungi_corso(c);
	}
}
