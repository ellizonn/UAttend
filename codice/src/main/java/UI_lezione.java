import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_lezione {
    private UI_avviso ui_avv;
    private gestore_lezioni g_lez;

    private String nome_corso;
    private String cognome_docente;
    private int anno;
    private int numero_aula;
    private int posti_disponibili;
    private LocalDate giorno;
    private LocalTime ora_inizio;
    private LocalTime ora_fine;

    public UI_lezione(UI_avviso ui1, gestore_lezioni g1) {
        //autore: Codetta
        ui_avv = ui1;
        g_lez = g1;
    }

    public void visualizza_elenco_corsi(ArrayList<corso> elencoCorsi) {
        // autori: Simone Garau, Filiberto Melis
        if (elencoCorsi != null) {
            System.out.println("Elenco corsi disponibili");
            for (int i = 0; i < elencoCorsi.size(); i++) {
                String nome = elencoCorsi.get(i).nome;
                String cognome_docente = elencoCorsi.get(i).cognome_docente;
                int anno = elencoCorsi.get(i).anno;
                System.out.println("Corso " + (i+1) + ": " + nome + " " + anno + " " + cognome_docente);
            }
        }
    }

    public void mostra_form_data() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        boolean conferma = false;
        boolean formato;

        do {
            System.out.print("Inserire data (gg/MM/aaaa): ");
            String data = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
            Matcher m = p.matcher(data);

            formato = m.matches();
            if (formato) {
                int giorno = Integer.parseInt(m.group(1));
                int mese = Integer.parseInt(m.group(2));
                int anno = Integer.parseInt(m.group(3));
                try {
                    this.giorno = LocalDate.of(anno, mese, giorno);
                } catch (DateTimeException e) {
                    System.out.println("ATTENZIONE: data inesistente");
                    formato = false;
                }
            } else
                System.out.println("ATTENZIONE: formato data errato");

            if (formato) {
                System.out.print("Confermi la data (s/n)? ");
                if(new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine()))
                    conferma = true;
            }
        } while (!formato || !conferma);
    }

    public void mostra_form_orario() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        boolean conferma = false;
        boolean formato;

        do {
            System.out.print("Inserire orario di inizio e di fine lezione (h24) (HH:mm-HH:mm): ");
            String orario = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2}):(\\d{2})-(\\d{2}):(\\d{2})");
            Matcher m = p.matcher(orario);

            formato = m.matches();
            if (formato) {
                int ora_inizio = Integer.parseInt(m.group(1));
                int minuto_inizio = Integer.parseInt(m.group(2));
                int ora_fine = Integer.parseInt(m.group(3));
                int minuto_fine = Integer.parseInt(m.group(4));
                try {
                    this.ora_fine = LocalTime.of(ora_fine, minuto_fine);
                    this.ora_inizio = LocalTime.of(ora_inizio, minuto_inizio);
                } catch (DateTimeException e) {
                    System.out.println("ATTENZIONE: orario inesistente");
                    formato = false;
                }
            } else
                System.out.println("ATTENZIONE: formato ora errato");

            if (formato) {
                System.out.print("Confermi l'orario (s/n)? ");
                if(new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine()))
                    conferma = true;
            }
        } while (!formato || !conferma);
    }

    public void mostra_errore_data(LocalDate data) {
        // autori: Simone Garau, Filiberto Melis
        if (data == null)
            System.out.println("ERRORE: nessuna data inserita");
        else {
            if (data.isBefore(LocalDate.now()))
                System.out.println("ERRORE: la data deve essere successiva alla data odierna");
            else {
                if (data.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                    System.out.println("ERRORE: non ci possono essere lezioni il sabato");
                if (data.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                    System.out.println("ERRORE: non ci possono essere lezioni la domenica");
                if (data.getMonth().equals(Month.AUGUST))
                    System.out.println("ERRORE: non ci possono essere lezioni ad agosto");
            }
        }
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    public void mostra_errore_orario(LocalTime ora_inizio, LocalTime ora_fine) {
        // autori: Simone Garau, Filiberto Melis
        if (ora_inizio == null || ora_fine == null) {
            if (ora_inizio == null)
                System.out.println("ERRORE: nessun orario di inizio inserito");
            else
                System.out.println("ERRORE: nessun orario di fine inserito");
        } else {
            if (ora_inizio.isAfter(ora_fine) || ora_inizio.isAfter(ora_fine.minusHours(1))) {
                if (ora_inizio.isAfter(ora_fine))
                    System.out.println("ERRORE: l'inizio della lezione deve precedere la fine");
                else
                    System.out.println("ERRORE: la lezione deve durare almeno un'ora");
            }
            if (ora_inizio.getHour() < 9)
                System.out.println("ERRORE: la lezione non deve iniziare prima delle 9");
            if (ora_inizio.getHour() > 17)
                System.out.println("ERRORE: la lezione non deve iniziare dopo le 17");
            if (ora_fine.getHour() < 10)
                System.out.println("ERRORE: la lezione non deve finire prima delle 10");
            if (ora_fine.getHour() > 18)
                System.out.println("ERRORE: la lezione non deve finire dopo le 18");
        }
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    public void mostra_errore_aula() {
        // autori: Simone Garau, Filiberto Melis
        System.out.println("ERRORE: nessuna aula disponibile");
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    public void mostra_dati_lezione_da_aggiungere() {
        // autori: Simone Garau, Filiberto Melis
        System.out.println("Dati lezione");
        System.out.println("Corso: " + this.nome_corso);
        System.out.println("Docente: " + this.cognome_docente);
        System.out.println("Anno: " + this.anno);
        System.out.println("Aula: " + this.numero_aula);
        System.out.println("Capienza: " + this.posti_disponibili);
        System.out.println("Data: " + this.giorno);
        System.out.println("Ora inizio: " + this.ora_inizio);
        System.out.println("Ora fine: " + this.ora_fine);
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    public void avvio_aggiungi_lezione() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        boolean risposta = false;

        do {
            boolean selezione = false;
            do {
                ArrayList<corso> elencoCorsi = this.g_lez.richiesta_elenco_corsi();
                this.visualizza_elenco_corsi(elencoCorsi);
                System.out.print("Selezionare un corso indicandone il numero: ");
                try {
                    int numero_corso = Integer.parseInt(scanner.nextLine());
                    corso c = elencoCorsi.get(numero_corso-1);
                    this.nome_corso = c.nome;
                    this.anno = c.anno;
                    this.cognome_docente = c.cognome_docente;
                    selezione = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("ATTENZIONE: numero di corso non presente");
                } catch (NumberFormatException e) {
                    System.out.println("ATTENZIONE: digitare un numero");
                }
            } while (!selezione);

            boolean errore_aula;
            do {
                boolean errore_data;
                do {
                    this.mostra_form_data();
                    errore_data = !this.g_lez.verifica_correttezza_data(this.giorno);
                    if (errore_data)
                        this.mostra_errore_data(this.giorno);
                } while (errore_data);

                boolean errore_orario;
                do {
                    this.mostra_form_orario();
                    errore_orario = !this.g_lez.verifica_correttezza_orario(this.ora_inizio, this.ora_fine);
                    if (errore_orario)
                        this.mostra_errore_orario(this.ora_inizio, this.ora_fine);
                } while (errore_orario);

                // aula a = richiamo a RF06 per selezione aula libera;
                aula a = new aula(); a.capienza = 50; a.numero = 7; // aula di prova (da rimuovere con l'arrivo di RF06)
                errore_aula = (a == null);
                if (errore_aula) {
                    this.mostra_errore_aula();
                } else {
                    this.numero_aula = a.numero;
                    this.posti_disponibili = a.capienza;
                }
            } while (errore_aula);

            this.mostra_dati_lezione_da_aggiungere();
            System.out.print("Sei sicuro di voler salvare questa lezione (s/n)? ");
            if(new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine()))
                risposta = true;

        } while (!risposta);

        this.g_lez.richiesta_salvataggio_lezione(this.nome_corso, this.cognome_docente,
                this.anno, this.numero_aula, this.posti_disponibili, this.giorno,
                this.ora_inizio, this.ora_fine);
    }

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

    /**
     * Avvia cancella lezione
     * @author Davide Ceci - 20033793 - RF_14
     * @author Luca Tamone - 20034235 - RF_14
     * @param lez la lezione da cancellare
     */
    public void avvia_cancella_lezione(lezione lez) {
        Scanner scanner = new Scanner(System.in);

        // Se la lezione è già avvenuta non posso cancellarla
        if(g_lez.verifica_lezione(lez.giorno) == true) {
            System.out.println("Non puoi cancellare una lezione che è già avvenuta.");
            return;
        }

        // Conferma eliminazione
        System.out.println("Vuoi veramente cancellare questa lezione? [s/N]");
        if(!scanner.nextLine().toLowerCase().equals("s")) {
            System.out.println("La lezione non è stata cancellata");
            return;
        }

        // Elimina lezione
        try {
            g_lez.elimina_lezione(lez);
        } catch (Exception e) {
            System.out.println("Si è verificato un errore durante l'eliminazione della lezione.");
            return;
        }

        // Scrivi avviso
        try {
            // Richiama scrivi avviso
        } catch (Exception e) {
            System.out.println("La lezione è stata cancellata ma non è stato possibile scrivere l'avviso per via di un errore.");
            return;
        }
        
        System.out.println("La lezione è stata cancellata con successo");
    }


}
