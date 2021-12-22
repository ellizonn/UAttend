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
}
