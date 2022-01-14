import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_lezione {
    UI_avviso ui_avv;
    gestore_lezioni g_lez;

    String nome_corso;
    String cognome_docente;
    int anno;
    int numero_aula;
    int posti_disponibili;
    LocalDate giorno;
    LocalTime ora_inizio;
    LocalTime ora_fine;

    public UI_lezione(UI_avviso ui1, gestore_lezioni g1) {
        //autore: Codetta
        ui_avv = ui1;
        g_lez = g1;
    }

    public void visualizza_elenco_corsi(ArrayList<corso> elencoCorsi) {
        // autori: Simone Garau, Filiberto Melis
        if (elencoCorsi != null) {
            System.out.println("Elenco corsi disponibili");
            System.out.printf("N. corso\t%-30s\tAnno\tCognome docente\n", "Nome");
            for (int i = 0; i < elencoCorsi.size(); i++) {
                String nome = elencoCorsi.get(i).nome;
                String cognome_docente = elencoCorsi.get(i).cognome_docente;
                int anno = elencoCorsi.get(i).anno;
                System.out.printf("Corso %d:\t%-30s\t%-4d\t%s\n", i+1, nome, anno, cognome_docente);
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
                    System.out.println("\nATTENZIONE: data inesistente\n");
                    formato = false;
                }
            } else
                System.out.println("\nATTENZIONE: formato data errato\n");

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
                    this.ora_inizio = LocalTime.of(ora_inizio, minuto_inizio);
                    this.ora_fine = LocalTime.of(ora_fine, minuto_fine);
                } catch (DateTimeException e) {
                    System.out.println("\nATTENZIONE: orario inesistente\n");
                    formato = false;
                }
            } else
                System.out.println("\nATTENZIONE: formato ora errato\n");

            if (formato) {
                System.out.print("Confermi l'orario (s/n)? ");
                if(new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine()))
                    conferma = true;
            }
        } while (!formato || !conferma);
    }

    public void mostra_errore_data(LocalDate data) {
        // autori: Simone Garau, Filiberto Melis
        System.out.println();
        if (data == null)
            System.out.println("ERRORE: nessuna data inserita");
        else {
            if (!data.isAfter(LocalDate.now()))
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
        System.out.println();
    }

    public void mostra_errore_orario(LocalTime ora_inizio, LocalTime ora_fine) {
        // autori: Simone Garau, Filiberto Melis
        System.out.println();
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
        System.out.println();
    }

    public void mostra_errore_aula() {
        // autori: Simone Garau, Filiberto Melis
        System.out.println();
        System.out.println("ERRORE: nessuna aula disponibile");
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
        System.out.println();
    }

    public void mostra_dati_lezione_da_aggiungere() {
        // autori: Simone Garau, Filiberto Melis
        System.out.println();
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
        System.out.println();
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

                aula a = this.avvia_seleziona_aula_libera(this.giorno, this.ora_inizio, this.ora_fine);
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
				mostra_errore(esito);
				System.out.println(esito);
				if(esito == "ABORT"){
					return;
				}
			}
		}while(esito != "OK");


		SUPlistaDocenti = g_utn.fetch_lista_docenti();
		/* verifica selezione docente*/
		do{
			if(esito == "SELEZIONE_NON_VALIDA")
				mostra_errore("SELEZIONE_NON_VALIDA");

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

		if(errore == "ABORT"){
			System.out.println("[AVVISO] Operazione annullata con successo.");

			do{
				System.out.println("\nPremi invio per confermare.");
			}while(sc.nextLine() == "\n");
				
			return;

		}

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
		System.out.println("Scrivi \"ESC\" per annullare");
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
		System.out.println("  | Docenti | Sedi ");
		
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
            ui_avv.avvio_scrittura_avviso();
        } catch (Exception e) {
            System.out.println("La lezione è stata cancellata ma non è stato possibile scrivere l'avviso per via di un errore.");
            return;
        }
        
        System.out.println("La lezione è stata cancellata con successo");
    }


    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public aula avvia_seleziona_aula_libera (LocalDate date, LocalTime startHour, LocalTime endHour) {
    	ArrayList<aula> aule_libere = this.g_lez.verifica_aule_libere(date, startHour, endHour);
    	if (aule_libere.isEmpty())  {
    		this.mostra_messaggio_nessunaAulaLibera();
    		return null;
    	}	
    	else {
    		aula aula_selezionata = new aula();
    		boolean conferma = true;
    		boolean accettata = false;
    		while (!conferma || !accettata) {
    			aula_selezionata = this.mostra_elenco_aule_libere(aule_libere);
    			accettata = this.g_lez.verifica_aula_selezionata(aule_libere, aula_selezionata.numero);
    			if (accettata == true) {
    				conferma = this.mostra_messaggio_conferma();
    			}
    			else {
    				this.mostra_messaggio_aulaSelezionataNonValida();
    			}	
    		}
    		this.mostra_aula_selezionata_correttamente();
    		return aula_selezionata;
    	}
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public void mostra_messaggio_nessunaAulaLibera() {
    	System.out.println ("Nessuna aula libera presente.");
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public void mostra_messaggio_aulaSelezionataNonValida () {
    	System.out.println ("Il numero dell'aula selezionata non è presente fra l'elenco delle aule libere.");
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public aula mostra_elenco_aule_libere (ArrayList<aula> aule_libere){
    	int numero_aula = 0; 
		System.out.println ("Elenco aule libere:");
		System.out.println ("Aula Capienza");
    	for (aula a : aule_libere) {
    		System.out.println ( a.numero + "	" + a.capienza);
    	}
    	System.out.println("Per selezionare l'aula desiderata digitarne il numero.");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
    	numero_aula = Integer.decode(s);
    	
    	aula a = new aula();
    	a.numero = numero_aula;
    	for (aula aula: aule_libere) {
			if (aula.numero == numero_aula) {
				a.capienza = aula.capienza;
			}
    	}
    	return a;
    	
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public boolean mostra_messaggio_conferma () {
    	System.out.println("Confermi la scelta? y/n");
    	Scanner in = new Scanner (System.in);
    	String s = in.nextLine();
		if (s.equals("y")) return true;
		else return false;	
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public void mostra_aula_selezionata_correttamente () {
    	System.out.println("Aula selezionata correttamente.");
    }

}
