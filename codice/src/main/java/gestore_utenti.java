import java.util.*;

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
	
	public boolean controllo_generalita (String nome, String cognome, indirizzo residenza, String tipo_utente, int anno_immatricolazione)
	{
		//RF17: crea utente
    	//autori: La Spisa & Frasson
		int N=nome.length();
		boolean esito;
		Scanner sc= new Scanner(System.in);
		if(N==0)
		{
			System.out.println("errore inserimento nome, premere invio per continuare");
			sc.nextLine();
			esito=false;
		}
		else
		{
			int C=cognome.length();
			if(C==0)
			{
				System.out.println("errore inserimento cognome, premere invio per continuare");
				sc.nextLine();
				esito=false;
			}
			else
			{
				if(residenza==null)
				{
					System.out.println("errore inserimento residenza, premere invio per continuare");
					sc.nextLine();
					esito=false;
				}
				else
				{
					if(tipo_utente==null)
					{
						System.out.println("errore inserimento tipo_utente, premere invio per continuare");
						sc.nextLine();
						esito=false;
					}
					else
					{
						utente ut=new utente();
						ut.nome=nome;
						ut.cognome=cognome;
						ut.residenza=residenza;
						ut.tipo_utente=tipo_utente;
						if (tipo_utente=="cliente")
							ut.anno=anno_immatricolazione;
						db_ut.aggiungi_utente(ut);
						esito=true;
					}
				}
			}
		}
		return esito;
	}
	
	public boolean controlla_scelta(int scelta)
	{
		//RF17: crea utente
    	//autori: La Spisa & Frasson
		if (scelta<1 || scelta>6)
			return false;
		else
			return true;
	}

}

