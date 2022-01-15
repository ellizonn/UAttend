import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

class gestore_lezioni {
	private DB_lezioni db_lez;
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
	private lezione l;
	private UI_prenotazione ui_pren;

	ArrayList<lezione> lez;
	private Scanner sc;
	oraLocale ora;
	dataLocale data;

	public gestore_lezioni(DB_lezioni d1) {
		// autore: Codetta
		db_lez = d1;
		ora = new oraLocale();
		data = new dataLocale();
		sc = new Scanner(System.in);

	}

	/**
	 *
	 * @return elenco corsi
	 */
	public ArrayList<corso> richiesta_elenco_corsi() {
		// autori: Simone Garau, Filiberto Melis
		return db_lez.carica_corsi();
	}

	/**
	 *
	 * @param data data da verificare
	 * @return <em>true</em> se i campi sono corretti, <em>false</em> altrimenti
	 */
	public boolean verifica_correttezza_data(LocalDate data) {
		// autori: Simone Garau, Filiberto Melis
		return data != null && data.isAfter(LocalDate.now()) && !data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
				&& !data.getDayOfWeek().equals(DayOfWeek.SUNDAY) && !data.getMonth().equals(Month.AUGUST);
	}

	/**
	 *
	 * @param ora_inizio orario d'inizio lezione da verificare
	 * @param ora_fine   orario di fine lezione da verificare
	 * @return <em>true</em> se i campi sono corretti, <em>false</em> altrimenti
	 */
	public boolean verifica_correttezza_orario(LocalTime ora_inizio, LocalTime ora_fine) {
		// autori: Simone Garau, Filiberto Melis
		return ora_inizio != null && ora_fine != null && ora_inizio.isBefore(ora_fine)
				&& !ora_inizio.isAfter(ora_fine.minusHours(1)) && ora_inizio.getHour() >= 9
				&& ora_inizio.getHour() <= 17 && ora_fine.getHour() >= 10 && ora_fine.getHour() <= 18;
	}

	/**
	 *
	 * @param nome_corso        nome del corso
	 * @param cognome_docente   cognome del docente del corso
	 * @param anno              anno del corso
	 * @param numero_aula       numero dell'aula
	 * @param posti_disponibili numero di posti disponibili nell'aula
	 * @param giorno            data della lezione
	 * @param ora_inizio        ora d'inizio lezione
	 * @param ora_fine          ora di fine lezione
	 */
	public void richiesta_salvataggio_lezione(String nome_corso, String cognome_docente, int anno, int numero_aula,
			int posti_disponibili, LocalDate giorno, LocalTime ora_inizio, LocalTime ora_fine) {
		// autori: Simone Garau, Filiberto Melis
		this.l = new lezione();
		l.nome_corso = nome_corso;
		l.cognome_docente = cognome_docente;
		l.anno = anno;
		l.numero_aula = numero_aula;
		l.posti_disponibili = posti_disponibili;
		l.giorno = giorno;
		l.ora_inizio = ora_inizio;
		l.ora_fine = ora_fine;
		this.db_lez.aggiungi_lezione(l);
	}

	/**
	 * Verifica il corretto inserimento dell'anno del corso
	 * 
	 * @author Andrea Colaci, Gregorio Lupano
	 * @param anno - anno selezionato
	 * @return esito della verifica
	 */
	public String verifica_anno_corso(int anno) {
		String esito = "";
		if (anno > 3 || anno < 1)
			esito = "ANNO_NON_VALIDO";
		else
			esito = "OK";

		return esito;
	}

	/**
	 * Verifica il corretto inserimento del nome del corso
	 * 
	 * @author Andrea Colaci, Gregorio Lupano
	 * @param nomeCorso - il nome del corso
	 * @return esito della verifica
	 */
	public String verifica_nome_corso(String nomeCorso) {

		if (nomeCorso.equals("ESC"))
			return "ABORT";
		else if (nomeCorso.equals(""))
			return "CORSO_VUOTO";
		else if (nomeCorso.length() < 5)
			return "CORSO_CORTO";
		else {
			if (db_lez.ricerca_nome_corso(nomeCorso) == 0)
				return "OK";
			else
				return "CORSO_PRESENTE";
		}
	}

	/**
	 * Aggiunge il corso con i parametri specificati
	 * 
	 * @author Andrea Colaci, Gregorio Lupano
	 * @param nome           - il nome del corso
	 * @param anno           - l'anno del corso
	 * @param cognomeDocente - il cognome del docente che tiene il corso
	 */
	public void aggiungi_corso(String nome, int anno, String cognomeDocente) {
		corso c = new corso();

		c.nome = nome;
		c.anno = anno;
		c.cognome_docente = cognomeDocente;

		db_lez.aggiungi_corso(c);
	}

	/**
	 * Verifica se la lezione è già avvenuta
	 * 
	 * @author Davide Ceci - 20033793 - RF_14
	 * @author Luca Tamone - 20034235 - RF_14
	 * @param giorno la data della lezione
	 * @return true se la lezione è avvenuta altrimenti false
	 */
	public Boolean verifica_lezione(LocalDate giorno) {
		LocalDate data = LocalDate.now();

		return giorno.compareTo(data) < 0 ? true : false;
	}

	/**
	 * Richiama elimina_lezione dal db per cancellare la lezione
	 * 
	 * @author Davide Ceci - 20033793
	 * @author Luca Tamone - 20034235
	 * @param l la lezione da cancellare
	 */
	public void elimina_lezione(lezione l) {
		db_lez.elimina_lezione(l);
	}

	// autore: RF06 Rosilde Garavoglia, Roberto Aitchison
	public ArrayList<aula> verifica_aule_libere(LocalDate date, LocalTime startHour, LocalTime endHour) {
		ArrayList<aula> tot_aule = this.db_lez.carica_aule();
		ArrayList<aula> aule_occupate = this.db_lez.restituisci_elenco_aule_occupate(date, startHour, endHour);
		ArrayList<aula> aule_libere = this.calcola_elenco_aule_libere(tot_aule, aule_occupate);
		return aule_libere;
	}

	// autore: RF06 Rosilde Garavoglia, Roberto Aitchison
	public ArrayList<aula> calcola_elenco_aule_libere(ArrayList<aula> tot_aule, ArrayList<aula> aule_occupate) {
		if (tot_aule.isEmpty())
			return new ArrayList<aula>();
		else if (aule_occupate.isEmpty())
			return new ArrayList<aula>(tot_aule);
		else
			return differenza_insiemistica(tot_aule, aule_occupate);
	}

	// autore: RF06 Rosilde Garavoglia, Roberto Aitchison
	public ArrayList<aula> differenza_insiemistica(ArrayList<aula> tot_aule, ArrayList<aula> aule_occupate) {
		ArrayList<aula> aule_libere = new ArrayList<aula>();

		for (aula a : tot_aule) {
			if (!aule_occupate.contains(a)) {
				aule_libere.add(a);
			}
		}
		return aule_libere;
	}

	// autore: RF06 Rosilde Garavoglia, Roberto Aitchison
	public boolean verifica_aula_selezionata(ArrayList<aula> aule_libere, int numero_aula) {
		for (aula aula : aule_libere) {
			if (aula.numero == numero_aula) {
				return true;
			}
		}
		return false;
	}

	// =======================================================================

	/**
	 * Controlla che la lezione non sia passata
	 * 
	 * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
	 * @param lez oggetto lezione
	 * @return esito_data_e_posti
	 */
	public String controllo_data_e_posti(lezione lez) {

		LocalDate oggi = LocalDate.now();
		LocalTime orario = LocalTime.now();
		String strOggi = oggi.format(formatter);
		String strOrario = orario.format(formatter2);
		LocalDate today = LocalDate.parse(strOggi, formatter);
		LocalTime now = LocalTime.parse(strOrario, formatter2);

		String esito_data_e_posti;
		if ((lez.giorno.compareTo(today) > 0
				|| (lez.giorno.compareTo(today) == 0 && lez.ora_inizio.compareTo(now) >= 0))
				&& lez.posti_disponibili > 0) {
			esito_data_e_posti = new String("ok");
		} else if (lez.giorno.compareTo(today) < 0
				|| (lez.giorno.compareTo(today) == 0 && lez.ora_inizio.compareTo(now) < 0)) {
			esito_data_e_posti = new String("err_data");
		} else // if(lez.posti_disponibili == 0)
			esito_data_e_posti = new String("err_posti");
		return esito_data_e_posti;

	}

	/**
	 * Controlla che scelta_stud sia in formato compatibile
	 * 
	 * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
	 * @param scelta_stud input dell'utente nel metodo form_prenotazione delle
	 *                    classe UI_prenotazione
	 * @return true se il formato e' compatibile, altrimenti false
	 */
	public boolean controllo_formato_scelta(String scelta_stud) {

		boolean esito_formato_scelta;
		if ((!(scelta_stud.equals("indietro")) && !(scelta_stud.equals("procedi"))) || scelta_stud == null)
			esito_formato_scelta = false;
		else
			esito_formato_scelta = true;
		return esito_formato_scelta;

	}

	/**
	 * Decrementa i posti disponibili in lez Inserisce una nuova prenotazione nel DB
	 * 
	 * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
	 * @param lez       oggetto lezione
	 * @param matricola numero matricola
	 */
	public void decrementa_prenota(lezione lez, int matricola) {

		// creo nuovo oggetto lezione
		lezione new_lez = lez;
		new_lez.posti_disponibili = lez.posti_disponibili - 1;
		db_lez.modifica_lezione(new_lez); // chiamata
		// creo oggetto prenotazione (inseriamo di default presente=false)
		prenotazione obj_preno = new prenotazione(matricola, new_lez.nome_corso, new_lez.cognome_docente,
				new_lez.numero_aula, new_lez.giorno, new_lez.ora_inizio, new_lez.ora_fine, "non_registrato");
		db_lez.aggiungi_prenotazione(obj_preno); // chiamata

	}

	/**
	 * Controllo se l'utente matricola ha gia' prenotato la lezione lez
	 * 
	 * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
	 * @param lez       oggetto lezione
	 * @param matricola numero matricola
	 * @return false se la prenotazione non esiste nel db, altrimenti true (l'utente
	 *         si e' gia' prenotato)
	 */
	public boolean controllo_prenotazione_doppia(lezione lez, int matricola) {

		boolean esito_prenotazione_doppia;
		prenotazione obj_preno = new prenotazione(matricola, lez.nome_corso, lez.cognome_docente, lez.numero_aula,
				lez.giorno, lez.ora_inizio, lez.ora_fine, "non_registrato");
		prenotazione obj_cercato = db_lez.cerca_prenotazione(obj_preno);
		if (obj_cercato != null) {
			esito_prenotazione_doppia = true;
		} else { // obj_cercato == null
			esito_prenotazione_doppia = false;
		}
		return esito_prenotazione_doppia;

	}

	// RF09 @autor Balossino, Battezzati
	public ArrayList<prenotazione> get_prenotazioni_docente(String docente, String nomeCorso) {

		ArrayList<prenotazione> prenotazioni = db_lez.carica_prenotazioni();
		ArrayList<prenotazione> prenotazioni_return = new ArrayList<>();
		int l = prenotazioni.size();
		for (int i = 0; i < l; i++) {
			prenotazione prenotazione = prenotazioni.get(i);
			if (prenotazione.cognome_docente.compareTo(docente) == 0
					&& prenotazione.nome_corso.compareTo(nomeCorso) == 0)
				prenotazioni_return.add(prenotazione);
		}
		return prenotazioni_return;
	}

	// RF09 @autor Balossino, Battezzati
	public ArrayList<prenotazione> get_prenotazioni_studente(int matricola) {

		ArrayList<prenotazione> prenotazioni = db_lez.carica_prenotazioni();
		ArrayList<prenotazione> prenotazioni_return = new ArrayList<>();
		int l = prenotazioni.size();
		for (int i = 0; i < l; i++) {
			prenotazione prenotazione = prenotazioni.get(i);
			if (prenotazione.matricola_studente == matricola)
				prenotazioni_return.add(prenotazione);
		}
		return prenotazioni_return;
	}

	/**
	 * RF11 Registra_presenza Metodo d'avvio del caso d'uso. Esegue, in ordine:
	 * Confronto tra data e ora della prenotazione con data e ora attuali; Controllo
	 * del campo "presente" della prenotazione per verificare se e' gia' stata
	 * registrata la scelta. In base all'esito, mostra un messaggio di errore oppure
	 * richiama il metodo mostra_form_registra_presenze().
	 * 
	 * @author Almasio, Borova
	 * @param p : prenotazione
	 */
	public void avvio_registra_presenza(prenotazione p) {
		LocalDate giorno_att = LocalDate.now();
		LocalTime ora_att = LocalTime.now();
		String esito;
		if (giorno_att.isBefore(p.giorno) && ora_att.isBefore(p.ora_inizio)) {
			esito = "err1";
			ui_pren.mostra_errore_registrazione(esito, p);
		} else if (p.presente.equals("Presente") || p.presente.equals("Assente")) {
			esito = "err2";
			ui_pren.mostra_errore_registrazione(esito, p);
		} else {
			ui_pren.mostra_form_registra_presenze(p);
		}
	}

	/**
	 * RF11 Registra_presenza Prende la scelta passata come parametro e la modifica
	 * in una stringa piu' esplicativa da inserire nel DB.
	 * 
	 * @author Almasio, Borova
	 * @param scelta_opzione : scelta dell'utente
	 * @param p              : prenotazione
	 */
	public void gestione_scelta(String scelta_opzione, prenotazione p) {
		if (scelta_opzione.equals('p')) {
			db_lez.inserisci_scelta(p, "Presente");
		} else if (scelta_opzione.equals('a')) {
			db_lez.inserisci_scelta(p, "Assente");
		}
	}

	// RF10 -Annulla_prenotazione Autori: Orsetti,Lopez
	public void Verifica_data(prenotazione p) {
		LocalDate odierna = LocalDate.now();
		if (p.giorno.isAfter(odierna)) {
			db_lez.Cancella_prenotazione(p);
		} else {
			System.out.println("impossibile annullare prenotazione");
		}
	}

	 /**
   *  RF15 Modifica lezione 
	 * @author Manganoni Mattia Frassinelli Marco Omar
   * Metodo di controllo modificabilità della lezione generico
   * 
   * @param l  Lezione da controllare 
   * @return boolean true se è modificabile false altrimenti
   */
  
  public boolean controlla_lezione_modificabile(lezione l) { /* OK */
    if (data.comparaData(l.giorno) > 0) return true;
    
    else if (data.comparaData(l.giorno) == 0) {
      if (ora.comparaOre(l.ora_inizio)) return true;
      else return false;
    } 
    
    else return false;
  }

  
  /**
   * 
   * RF15 Modifica lezione 
	 * @author Manganoni Mattia Frassinelli Marco Omar
   * Metodo di controllo modificabilità della data della lezione 
   * 
   * @param l  Lezione da controllare 
   * @return boolean true se è modificabile false altrimenti
   */
  
  
  public boolean controllo_data_corretta(lezione l) { /* OK */
    if (data.comparaData(l.giorno) >= 0) return true;
    
    else return false;
  }

  /**
   * RF15 Modifica lezione 
	 * @author Manganoni Mattia Frassinelli Marco Omar
   * 
   * 
   * Metodo di controllo modificabilità della ora della lezione
   * 
   * @param l  Lezione da controllare 
   * @return boolean true se è modificabile false altrimenti
   */
  
  
  public boolean controllo_ora_corretta(lezione l) { /* OK */
    if (ora.comparaOre(l.ora_inizio)) return true;
    
    else return false;
  }
  
  
  /**
   * RF15 Modifica lezione 
	 * @author Manganoni Mattia Frassinelli Marco Omar
   * 
   * Metodo di controllo ugualianza dei due lezioni
   * 
   * @param old  vecchia lezione da smantellare
   * @param modi lezione da inserire al posto della lezione vecchia
   * @return boolean true se è uguale false altrimenti
   */
  
  
  public boolean controllo_lezioni_equal(lezione old, lezione nuova) { /* OK */
    return old.equals(nuova); 
  }

  
  /**
   * RF15 Modifica lezione 
	 * @author Manganoni Mattia Frassinelli Marco Omar
   * Metodo che salva la nuova lezione nel DB delle lezioni
   * 
   * @param old  vecchia lezione da smantellare
   * @param modi lezione da inserire al posto della lezione vecchia
   * @return boolean true se la lezione è stata modificata false altrimenti
   */
  
  public boolean salvaModifiche(lezione old, lezione modi) {
    // Controllo che ci siano delle mofiche 
    if (controllo_lezioni_equal(old, modi)) {
      System.out.println("Attenzione che non c'é stata alcuna modifica della lezione");
      return false;
    } 
    else {
        lez = db_lez.carica_lezioni();
    	 // Conferma le modifiche che hai fatto altrimenti annulla tutto 
        int index = ctrlLesson(lez,old);// indice della vecchia lezione da sostituire
        lez.set(index, modi);// imposta la lezione modificata nello spazio della vecchia
        db_lez.salva_lezioni(lez);// carica l'array della lezione nel database
        return true;
    }
  }  
  
  /**
   * RF15 modifica lezione
   * @author Manganoni Mattia Frassinelli Marco Omar
   * 
   * Metodo sostitutivo di indexOf in quanto qurest'ultimo da problemi
   * Creato perchè indexOf da problemi 
   * 
   * @param listaLezioni
   * @param k
   * @return
   */
  
  private int ctrlLesson(ArrayList<lezione> listaLezioni, lezione k) {
	  int i = 0;
		for(lezione a: listaLezioni) {
			if(a.equals(k)) return i;
			i++;
		}
		return -1;
  }
  
  
  

  //Metodi di modifica dei parametri delle lezioni delle lezioni 
  
  
  //Metodi di modifica della lezione

  /**
   * RF15 modifica lezione
   * @author Manganoni Mattia Frassinelli Marco Omar
   * 
   * Metodo di modifica della data; in caso di fallimento rimanda la lezione ricevuta 
   * 
   * @param lNew  lezione da modificare
   * @return lezione modificata
   */
  
  public lezione setData(lezione lNew) {
      int giorno = 0;
      int mese = 0;
      int anno = 0;
      LocalDate d;
      do {
          do {
              System.out.println("inserisci giorno");
              giorno = sc.nextInt();
          } while (giorno < 1 || giorno > 31);

          do {
              System.out.println("inserisci il mese (numerico)");
              mese = sc.nextInt();

          } while (mese < 1 || mese >= 13);

          do{
              System.out.println("inserisci l'anno");
              anno = sc.nextInt();
          }while(anno<LocalDate.now().getYear());

         

          d = LocalDate.of(anno, mese, giorno);

      } while (new dataLocale().comparaData(d) > 0);
      lNew.giorno= d;//imposta la data della lezione
      return lNew;
  }
  
  
  /**
   *  RF15 modifica lezione
   * @author Manganoni Mattia Frassinelli Marco Omar
   * Metodo di modifica della ora d'inizio della lezione; in caso di fallimento rimanda la lezione ricevuta
   * 
   * @param lNew  lezione da modificare
   * @return lezione modificata
   */

  public lezione setOraInizio(lezione lNew) {
      lNew.ora_inizio = setOrario();
      System.out.println("Ora d'inizio modificata");
      if (lNew.ora_inizio.compareTo(lNew.ora_fine) >= 0) {//Evito OraInizio>OraFine
          System.out.println("La nuova ora è uguale all'ora di fine");
          System.out.println("Setta la nuova ora di fine");
          return setOraFine(lNew);
      }
      else return lNew;

  }
 
  
  /**
   * RF15 modifica lezione
   * @author Manganoni Mattia Frassinelli Marco Omar
   * Metodo di modifica della ora fine della lezione; in caso di fallimento rimanda la lezione ricevuta
   * 
   * @param lNew  lezione da modificare
   * @return lezione modificata
   */

  public lezione setOraFine(lezione lNew) {
      lNew.ora_fine = setOrario();
      if (lNew.ora_inizio.compareTo(lNew.ora_fine) >= 0) {//Evito OraInizio>=OraFine
          System.out.println("l'ora di fine coincide o precede l'ora d'inizio");
          System.out.println("avvio modifica ora inzio");
          lNew.ora_inizio = setOrario();
          return lNew;
      }
      else return lNew;
  }
  
  
  
  /**
   * RF15 modifica lezione
   * @author Manganoni Mattia Frassinelli Marco Omar
   * Metodo comune ai metodi di modifica dell'orario 
   * @return la nuova ora
   */
  private LocalTime setOrario() {
      int ora;
      int minuto;
      LocalTime orario;
      do {
          do {
              System.out.println("inserisci l'ora");
              ora = sc.nextInt();
          } while (ora < 0 || ora > 24);

          do {
              System.out.println("inserisci il minuto");
              minuto = sc.nextInt();
          } while (minuto < 0 || minuto > 60);
          
          orario = LocalTime.of(ora, minuto);
      } while (new oraLocale().comparaOre(orario));
      return orario;
  }
  
  
  
  /**
   * RF15 modifica lezione 
   * @author Manganoni Mattia Frassinelli Marco Omar
   * 
   * Metodi di modifica dell'aula in caso di risposta affermativa
   * 
   * @param lNew lezione da modificare
   * @param au la nuova aula
   * @return la stessa lezione con la 
   */
  public lezione setAula(lezione lNew, aula au) {
    if(au != null) {
      lNew.numero_aula = au.numero;
      lNew.posti_disponibili=au.capienza;
    }
	  return lNew;
  }
}