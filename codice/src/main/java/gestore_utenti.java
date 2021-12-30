import java.util.ArrayList;

class gestore_utenti
{
    private DB_utenti db_ut;

    public gestore_utenti(DB_utenti d1)
    {
      //autore: Codetta
      db_ut=d1;
    }

	/**
	 *  ottiene la lista dei docenti dal DB
	 * @author Andrea Colaci, Gregorio Lupano
	 * @return la lista dei docenti nel DB
	 */
	public ArrayList<utente> fetch_lista_docenti(){
		
		ArrayList<utente> listaDocenti = new ArrayList<utente>();

		ArrayList<utente> elenco = new ArrayList<utente>();
		elenco = db_ut.carica_utenti();

		for(utente u : elenco)
		{
			if(u.tipo_utente.equals("docente"))
			{
				listaDocenti.add(u);
			}
		}
		return listaDocenti;
	}

	/**
	 *  Verifica il correttamento inserimento della selezione del docente
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @param listaDocenti - la lista dei docenti del DB 
	 * @param docenteSelezionato - l'indice del docente selezionato
	 * @return esito della verifica
	 */
	public String verifica_selezione_docente(ArrayList<utente> listaDocenti, Integer docenteSelezionato){

			String esito ="";

			try{
				utente verifica_docente = listaDocenti.get(docenteSelezionato);
				System.out.println(verifica_docente.nome);
				esito = "OK";
			}
			catch(IndexOutOfBoundsException e){
			// Verifica selezione docente
				esito = "SELEZIONE_NON_VALIDA";
			}

			return esito;
		
	}


}

