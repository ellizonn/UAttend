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

		String nomeCorso = mostra_form_nome_corso();
		if(nomeCorso == "ABORT")
			return;

		ArrayList<utente> listaDocenti =  g_utn.fetch_lista_docenti();

		System.out.println(listaDocenti);

		String docente = mostra_form_selezione_docente(listaDocenti);
		int anno = mostra_form_inserimento_anno();

	
		while(sc.nextLine() == "\n")
			{
					// Pulire la console
				System.out.print("\033[H\033[2J");  
				System.out.flush(); 
				System.out.println("--- Creazione Corso --- ");
				System.out.printf("Stai per creare il corso di %s (%d anno) tenuto da %s\nPremi Invio per confermare.\n", nomeCorso, anno, docente);
				g_lez.aggiungi_corso(nomeCorso, anno, docente);

			}
			
	}

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

	public String mostra_form_nome_corso(){
		
		String nomeCorso = null; 
		Scanner sc = new Scanner(System.in);

		
		System.out.println("--- Creazione Corso ---");
		System.out.println("Premi [Invio] per annullare");
		System.out.println("Inserisci il nome del corso: ");
		
		String esito="";  

		do{
			nomeCorso = sc.nextLine();
				esito = g_lez.verifica_nome_corso(nomeCorso);
				if(esito != "OK")
				{
					if(esito == "ABORT"){
						System.out.println("\n[ABORT] : Operazione annullata con successo.");
						return esito;
					}
					mostra_errore(esito);
				}
		}while(esito != "OK");

		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
				
		return nomeCorso;
		
	}

	public String mostra_form_selezione_docente(ArrayList<utente> listaDocenti)
	{
		utente docenteSelezionato  = null; 
		Scanner sc = new Scanner(System.in);
		int noDocente = 0;

		System.out.println("--- Creazione Corso ---");
		System.out.println("Docenti: ");
		
		for(utente u : listaDocenti)
			{
				System.out.printf("(%d) %s - %s\n", noDocente, u.cognome, u.residenza.localita);
				noDocente ++;
			}
		
		System.out.println("Inserisci il numero del docente: ");
		
		while(listaDocenti.contains(docenteSelezionato)){
			try{
				docenteSelezionato = listaDocenti.get(sc.nextInt());
			}
			catch( IndexOutOfBoundsException e){
			// Verifica selezione docente
				mostra_errore("SELEZIONE NON VALIDA");
			}
		}
		
		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		
		return docenteSelezionato.cognome;
	}

	public int mostra_form_inserimento_anno()
	{
		Scanner sc = new Scanner(System.in);
		int annoSelezionato= 0;

		System.out.println("--- Creazione Corso ---\n(1) Primo anno\n(2)Secondo anno\n(3)Terzo anno");
		System.out.println("Inserisci anno del corso: ");

		

		// verifica anno corso
		while(annoSelezionato >3 || annoSelezionato <=0)
			{
				annoSelezionato = sc.nextInt(); 
				mostra_errore("ANNO_NON_VALIDO");
			}

		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		
		return annoSelezionato;
	}
}
