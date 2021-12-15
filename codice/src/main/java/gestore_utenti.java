import java.util.ArrayList;

class gestore_utenti
{
    DB_utenti db_ut;

    public gestore_utenti(DB_utenti d1)
    {
      //autore: Codetta
      db_ut=d1;
    }

	public ArrayList<utente> fetch_lista_docenti(){
		
		ArrayList<utente> listaDocenti = new ArrayList<utente>();

		ArrayList<utente> elenco = new ArrayList<utente>();
		elenco = db_ut.carica_utenti();

		for(utente u : elenco)
		{
			if(u.tipo_utente == "docente")
			{
				listaDocenti.add(u);
			}
		}
		


		return listaDocenti;
	}


}

