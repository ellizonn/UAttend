import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

class gestore_lezioni {
    private DB_lezioni db_lez;
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private lezione l;

    public gestore_lezioni(DB_lezioni d1) {
        //autore: Codetta
        db_lez = d1;
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
        return data != null
                && data.isAfter(LocalDate.now())
                && !data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                && !data.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                && !data.getMonth().equals(Month.AUGUST);
    }

    /**
     *
     * @param ora_inizio orario d'inizio lezione da verificare
     * @param ora_fine orario di fine lezione da verificare
     * @return <em>true</em> se i campi sono corretti, <em>false</em> altrimenti
     */
    public boolean verifica_correttezza_orario(LocalTime ora_inizio, LocalTime ora_fine) {
        // autori: Simone Garau, Filiberto Melis
        return ora_inizio != null
                && ora_fine != null
                && ora_inizio.isBefore(ora_fine)
                && !ora_inizio.isAfter(ora_fine.minusHours(1))
                && ora_inizio.getHour() >= 9
                && ora_inizio.getHour() <= 17
                && ora_fine.getHour() >= 10
                && ora_fine.getHour() <= 18;
    }

    /**
     *
     * @param nome_corso nome del corso
     * @param cognome_docente cognome del docente del corso
     * @param anno anno del corso
     * @param numero_aula numero dell'aula
     * @param posti_disponibili numero di posti disponibili nell'aula
     * @param giorno data della lezione
     * @param ora_inizio ora d'inizio lezione
     * @param ora_fine ora di fine lezione
     */
    public void richiesta_salvataggio_lezione(String nome_corso, String cognome_docente,
                                              int anno, int numero_aula,
                                              int posti_disponibili, LocalDate giorno,
                                              LocalTime ora_inizio,LocalTime ora_fine) {
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

		if (nomeCorso.equals("ESC")) return "ABORT";
		else
		if (nomeCorso.equals("")) return "CORSO_VUOTO";
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

    /**
     * Verifica se la lezione è già avvenuta
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
     * @author Davide Ceci - 20033793
     * @author Luca Tamone - 20034235
     * @param l la lezione da cancellare
     */
    public void elimina_lezione(lezione l) {
        db_lez.elimina_lezione(l);
    }

    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public ArrayList<aula> verifica_aule_libere (LocalDate date, LocalTime startHour, LocalTime endHour) {
    	ArrayList<aula> tot_aule = this.db_lez.carica_aule ();
    	ArrayList<aula> aule_occupate = this.db_lez.restituisci_elenco_aule_occupate(date, startHour, endHour);
    	ArrayList<aula> aule_libere = this.calcola_elenco_aule_libere(tot_aule, aule_occupate);
    	return aule_libere;
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public ArrayList<aula> calcola_elenco_aule_libere (ArrayList<aula> tot_aule, ArrayList<aula> aule_occupate) {
    	if (tot_aule.isEmpty()) return new ArrayList<aula> ();
    	else if (aule_occupate.isEmpty()) return new ArrayList<aula> (tot_aule);
    	else return differenza_insiemistica (tot_aule, aule_occupate);
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public ArrayList<aula> differenza_insiemistica (ArrayList<aula> tot_aule, ArrayList<aula> aule_occupate) {
    	ArrayList<aula> aule_libere = new ArrayList<aula>();
    	
    	for (aula a : tot_aule) {
    		if (!aule_occupate.contains(a)) {
    			aule_libere.add(a);
    		}
    	}
    	return  aule_libere;
    }
    
    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public boolean verifica_aula_selezionata (ArrayList<aula> aule_libere, int numero_aula) {
    	for (aula aula: aule_libere) {
			if (aula.numero == numero_aula) {
				return true;
			}
    	}
    	return false;
    }
	
	// =======================================================================
  
	public String controllo_data_e_posti(lezione lez){
		
    	//RF13_prenota_posto
    	//Autori: Rossari, Marisio
		
		
		LocalDate oggi = LocalDate.now();
		String str = oggi.format(formatter);
    	LocalDate today = LocalDate.parse(str, formatter);

    	String esito_data_e_posti;
    	if(lez.giorno.compareTo(today)>0 && lez.posti_disponibili>0) {
    		esito_data_e_posti = new String("ok");
    	}
    	else if(lez.giorno.compareTo(today)<=0) {
    		esito_data_e_posti = new String("err_data");
    	}
		else //if(lez.posti_disponibili == 0)
			esito_data_e_posti = new String("err_posti");
		return esito_data_e_posti;
		
    }
	
	public boolean controllo_formato_scelta(String scelta_stud){
		
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		boolean esito_formato_scelta;
		if( (!(scelta_stud.equals("indietro")) && !(scelta_stud.equals("procedi"))) || scelta_stud==null )
			esito_formato_scelta = false;
		else
			esito_formato_scelta = true;
		return esito_formato_scelta;
		
	}
  
  	public void decrementa_prenota(lezione lez, int matricola){
		
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		//creo nuovo oggetto lezione
		lezione new_lez = lez;
		new_lez.posti_disponibili = lez.posti_disponibili - 1;
		db_lez.modifica_lezione(new_lez); //chiamata
		//creo oggetto prenotazione
		prenotazione obj_preno = new prenotazione();
		obj_preno.matricola_studente = matricola;
		obj_preno.nome_corso = new_lez.nome_corso;
		obj_preno.cognome_docente = new_lez.cognome_docente;
		obj_preno.giorno = new_lez.giorno;
		obj_preno.ora_inizio = new_lez.ora_inizio;
		obj_preno.ora_fine = new_lez.ora_fine;
		obj_preno.presente = false; // di default
		obj_preno.aula = new_lez.numero_aula;
		db_lez.aggiungi_prenotazione(obj_preno); //chiamata
		
	}
	
}
