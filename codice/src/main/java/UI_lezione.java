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
				// Pulire la console
				System.out.print("\033[H\033[2J");  
				System.out.flush(); 

		String nomeCorso = mostra_form_nome_corso();
		if(nomeCorso == "ABORT")
			return;

		String docente = mostra_form_selezione_docente(g_utn.fetch_lista_docenti());
		int anno = mostra_form_inserimento_anno();

	
		do{
					// Pulire la console
				System.out.print("\033[H\033[2J");  
				System.out.flush(); 
				System.out.println("--- Creazione Corso --- ");
				System.out.printf("Stai per creare il corso di %s (%d anno) tenuto da %s\nPremi Invio per confermare.\n", nomeCorso, anno, docente);
				g_lez.aggiungi_corso(nomeCorso, anno, docente);

			}while(sc.nextLine() == "\n");
			
			
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
		
		Integer selezione_docente = -1;
		String esito = "";

		do{
			if(esito == "SELEZIONE_NON_VALIDA")
				mostra_errore("SELEZIONE NON VALIDA");

			selezione_docente = sc.nextInt();
			
			esito = g_utn.verifica_selezione_docente(listaDocenti, selezione_docente);

		}while(esito.equals("SELEZIONE_NON_VALIDA"));
	
		 // Veri<fica ha avuto successo 
		docenteSelezionato = listaDocenti.get(selezione_docente);
		
		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		
		return docenteSelezionato.cognome;
	}

	public int mostra_form_inserimento_anno()
	{
		Scanner sc = new Scanner(System.in);
		int annoSelezionato= 0;
		String esito = "";

		System.out.println("--- Creazione Corso ---\n(1) Primo anno\n(2)Secondo anno\n(3)Terzo anno");
		System.out.println("Inserisci anno del corso: ");

		

		// verifica anno corso

		do{
			if(esito == "ANNO_NON_VALIDO")
				mostra_errore("ANNO_NON_VALIDO");

			annoSelezionato = sc.nextInt(); 
			
			esito = g_lez.verifica_anno_corso(annoSelezionato);

		}while(esito.equals("ANNO_NON_VALIDO"));


		// Pulire la console
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		
		return annoSelezionato;
	}
}
