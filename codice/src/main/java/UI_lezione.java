import java.util.Scanner;
import java.util.ArrayList;


public class UI_lezione
{   
    UI_avviso ui_avv;
    gestore_lezioni g_lez;
	gestore_utenti g_utn;
 
    public UI_lezione(UI_avviso ui1, gestore_lezioni g1, gestore_utenti g2)
    {	
	//autore: Codetta
	ui_avv=ui1;
	g_lez=g1;
	g_utn=g2;

    }

	public void RF04_crea_corso(){
		Scanner sc = new Scanner(System.in);
		String esito="";
		String nomeCorso = null;
		String docente = null;
		Integer SUPselezione_docente = -1; /*support per selezione_docente*/
		utente SUPdocenteSelezionato = null;  /* support per selezione docente */
		ArrayList<utente> SUPlistaDocenti; /*  support per selezione docente */
		int anno = 0;


		/* verifica nome corso*/
		do{
			nomeCorso = mostra_form_nome_corso();
			esito = g_lez.verifica_nome_corso(nomeCorso);
			if(esito != "OK")
			{
				if(esito == "ABORT"){
					System.out.println("\n[ABORT] : Operazione annullata con successo.");
					return;
				}
				mostra_errore(esito);
			}
		}while(esito != "OK");


		SUPlistaDocenti = g_utn.fetch_lista_docenti();
		/* verifica selezione docente*/
		do{
			if(esito == "SELEZIONE_NON_VALIDA")
				mostra_errore("SELEZIONE NON VALIDA");

				SUPselezione_docente = mostra_form_selezione_docente(SUPlistaDocenti);
			
			esito = g_utn.verifica_selezione_docente(SUPlistaDocenti, SUPselezione_docente);

		}while(esito.equals("SELEZIONE_NON_VALIDA"));
		SUPdocenteSelezionato = SUPlistaDocenti.get(SUPselezione_docente);
		docente = SUPdocenteSelezionato.cognome;

		/* verifica anno corso */
		do{
			if(esito == "ANNO_NON_VALIDO")
				mostra_errore("ANNO_NON_VALIDO");

			anno = mostra_form_inserimento_anno();
			
			esito = g_lez.verifica_anno_corso(anno);

		}while(esito.equals("ANNO_NON_VALIDO"));
		

		/* Salvataggio */
		do{
			mostra_conferma_creazione(nomeCorso, docente, anno);
		}while(sc.nextLine() == "\n");
		g_lez.aggiungi_corso(nomeCorso, anno, docente);

		avvio_aggiungi_lezione();
			
	}

	/**
	 *  Mostra gli errori di RF04_CREA_CORSO()
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @param errore - nome errore
	 */
	public void mostra_errore(String errore){
		Scanner sc = new Scanner(System.in);

		if(errore == "SELEZIONE_NON_VALIDA")
		{
			System.out.println("[ERRORE] Il docente selezionato non e' valido.");
			
			do{
				System.out.println("\nPremi invio per confermare.");
			}while(sc.nextLine() == "\n");
				
			return;
		}

		if(errore == "ANNO_NON_VALIDO")
		{
			System.out.println("[ERRORE] L'anno selezionato non e' valido.");

			do{
				System.out.println("\nPremi invio per confermare.");
			}while(sc.nextLine() == "\n");

			return;
		}
		
		// Errori mostra_form_nome_corso()
		if(errore == "CORSO_VUOTO"){
			System.out.println("\n[ERRORE] : Il nome del corso non puo essere vuoto.");
			do{
				System.out.println("\nPremi invio per confermare.");
			}while(sc.nextLine() == "\n");}
		
		if(errore == "CORSO_CORTO") 
			{System.out.println("\n[ERRORE] : Il nome del corso deve essere maggiore di 5 caratteri.");
			do{
				System.out.println("\nPremi invio per confermare.");
			}while(sc.nextLine() == "\n");}

		if(errore == "CORSO_PRESENTE")
			{System.out.println("\n[ERRORE] : Il corso e' gia presente nel database.");
			do{
				System.out.println("\nPremi invio per confermare.");
			}while(sc.nextLine() == "\n");}
		
	}
	
	/**
	 *  Mostra il form per l'inserimento del nome del corso 
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @return nome del corso 
	 */
	public String mostra_form_nome_corso(){

		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--- Creazione Corso ---");
		System.out.println("Premi [Invio] per annullare");
		System.out.println("Inserisci il nome del corso: ");
		
		return sc.nextLine();

	}

	/**
	 *  Mostra il form per la selezione del docente
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @param listaDocenti - lista di utenti di tipo docente
 	 * @return l'indice dell'array del docente selezionato
	 */
	public Integer mostra_form_selezione_docente(ArrayList<utente> listaDocenti)
	{
		Scanner sc = new Scanner(System.in);
		int noDocente = 0;

		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 

		System.out.println("--- Creazione Corso ---");
		System.out.println("Docenti: ");
		
		for(utente u : listaDocenti)
		{
			System.out.printf("(%d) %s - %s\n", noDocente, u.cognome, u.residenza.localita);
			noDocente ++;
		}
		
		System.out.println("Inserisci il numero del docente: ");
		
		return sc.nextInt();
		
	}

	/**
	 *  Mostra form per l'inserimento dell'anno del corso
	 * @author Andrea Colaci, Gregorio Lupano
 	 * @return anno del corso
	 */
	public int mostra_form_inserimento_anno()
	{
		Scanner sc = new Scanner(System.in);
		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 

		System.out.println("--- Creazione Corso ---\n(1) Primo anno\n(2)Secondo anno\n(3)Terzo anno");
		System.out.println("Inserisci anno del corso: ");

		return sc.nextInt();
	}

	/**
	 *  Mostra form per la conferma della creazione del corso 
	 * @author Andrea Colaci, Gregorio Lupano
	 */
	public void mostra_conferma_creazione(String nomeCorso, String docente, int anno)
	{
		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		
		System.out.println("--- Creazione Corso --- ");
		System.out.printf("Stai per creare il corso di %s (%d anno) tenuto da %s\nPremi Invio per confermare.\n", nomeCorso, anno, docente);
	}
}
