import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

class gestore_lezioni {
    DB_lezioni db_lez;
    lezione lez;

    public gestore_lezioni(DB_lezioni d1) {
        //autore: Codetta
        db_lez = d1;
    }

    /**
     * @return elenco corsi.
     */
    public ArrayList<corso> richiesta_elenco_corsi() {
        // autori: Simone Garau, Filiberto Melis
        return db_lez.carica_corsi();
    }

    public boolean verifica_correttezza_data(LocalDate data) {
        return data != null
                && data.isAfter(LocalDate.now())
                && !data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                && !data.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                && !data.getMonth().equals(Month.AUGUST);
    }

    /**
     * @return elenco corsi.
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
}
