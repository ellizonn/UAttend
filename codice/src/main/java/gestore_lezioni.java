import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

class gestore_lezioni {
    DB_lezioni db_lez;

    lezione l;

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
}
