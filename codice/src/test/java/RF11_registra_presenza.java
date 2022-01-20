import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class RF11_registra_presenza {
    /**
     * RF11 Registra_presenza
     * @author Almasio Luca, Borova Dritan
     * La seguente classe non contiene metodi test in quanto
     * tutti i metodi del caso d'uso RF11 sono void e non ritornano parametri.
     */
    gestore_lezioni g_lez;

    @Before
    public void setG_lez(){
       g_lez = new gestore_lezioni(new DB_lezioni());
    }

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
