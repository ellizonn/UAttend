import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class RF11_registra_presenza {
    /**
     * RF11 Registra_presenza
     * @author Almasio Luca, Borova Dritan
     * La seguente classe contiene metodi di test per il metodo avvio_registra_presenze()
     * presente in gestore_lezioni
     */
    gestore_lezioni g_lez;

    @Before
    public void setG_lez(){
       g_lez = new gestore_lezioni(new DB_lezioni());
    }

    /**
     * RF11 Registra_presenza
     * @author Almasio Luca, Borova Dritan
     * Il seguente test prova a controllare la data e l'ora della prenotazione
     * con data e ora attuali.
     */
    @Test
    public void avvio_registra_presenza_test1(){
       String expected = "err1";
       prenotazione pren = new prenotazione();

       pren.matricola_studente = 100000;
       pren.nome_corso = "Nome_corso";
       pren.cognome_docente = "Cognome_docente";
       pren.aula = 104;
       pren.giorno = LocalDate.parse("2022-04-12");
       pren.ora_inizio = LocalTime.parse("09:00");
       pren.ora_fine = LocalTime.parse("13:00");
       pren.presente = "non_registrato";

       Assert.assertEquals(expected, g_lez.avvio_registra_presenza(pren));
    }

    /**
     * RF11 Registra_presenza
     * @author Almasio Luca, Borova Dritan
     * Il seguente test prova a controllare se la prenotazione selezionata
     * ha il campo presente già impostato oppure default.
     */
    @Test
    public void avvio_registra_presenza_test2(){
        String expected = "err2";
        prenotazione pren = new prenotazione();

        pren.matricola_studente = 100000;
        pren.nome_corso = "Nome_corso";
        pren.cognome_docente = "Cognome_docente";
        pren.aula = 104;
        pren.giorno = LocalDate.parse("2021-04-12");
        pren.ora_inizio = LocalTime.parse("09:00");
        pren.ora_fine = LocalTime.parse("13:00");
        pren.presente = "Presente";

        Assert.assertEquals(expected, g_lez.avvio_registra_presenza(pren));
    }

    /**
     * RF11 Registra_presenza
     * @author Almasio Luca, Borova Dritan
     * Il seguente test verifica se il metodo funziona correttamente
     * nel caso in cui non ci siano errori nei controlli precedenti.
     */
    @Test
    public void avvio_registra_presenza_test3(){
        String expected = "noerr";
        prenotazione pren = new prenotazione();

        pren.matricola_studente = 100000;
        pren.nome_corso = "Nome_corso";
        pren.cognome_docente = "Cognome_docente";
        pren.aula = 104;
        pren.giorno = LocalDate.parse("2021-12-18");
        pren.ora_inizio = LocalTime.parse("09:00");
        pren.ora_fine = LocalTime.parse("13:00");
        pren.presente = "non_registrato";

        Assert.assertEquals(expected, g_lez.avvio_registra_presenza(pren));
    }
}
