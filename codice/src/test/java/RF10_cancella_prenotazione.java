import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;

/**
 * @author Lopez Antonio
 * @author Orsetti Lopez
 */
public class AppTest {

    gestore_lezioni g_lez;
    DB_lezioni db_lez;


    @Before
    public void setup() {
        db_lez = new DB_lezioni();
        g_lez = new gestore_lezioni(db_lez);
    }

    @Test
    public void test_cancella_prenotazione() {
        //carico lista lezioni
        ArrayList<lezione> l3 ;
        l3 = db_lez.carica_lezioni();
        int l2 = l3.get(1).posti_disponibili;


        // aggiungo una prenotazione
        prenotazione p1 = new prenotazione();
        p1.matricola_studente = 100003;
        p1.nome_corso = "Programmazione_1";
        p1.cognome_docente = "Codetta";
        p1.aula = 7;
        p1.giorno = (LocalDate.of(2021,10,3));
        p1.ora_inizio = LocalTime.of(14,00);
        p1.ora_fine = LocalTime.of(18,00);
        p1.presente = "indefinito";

        // aggiungo prenotazione in lista ArrayList<prenotazione>
        // decremento posti disponibili lezione
        g_lez.decrementa_prenota( l3.get(1), 100003);

        // cancello prenotazione effettuata
        db_lez.Cancella_prenotazione(p1);

        // controllo se è stata cancellata correttamente
        // all'interno della lista prenotazioni
        assertEquals(null,db_lez.cerca_prenotazione(p1));
    }
    @Test
    public void test_verifica_data() {
        //creo una lezione futura
        lezione l1 = new lezione();
        l1.nome_corso = "Ingegneria";
        l1.cognome_docente = "Rossi";
        l1.anno = 2022;
        l1.numero_aula = 7;
        l1.posti_disponibili = 35;
        l1.giorno = LocalDate.of(2022, 5, 10);
        l1.ora_inizio = LocalTime.of(9, 00);
        l1.ora_fine = LocalTime.of(11, 00);
        db_lez.aggiungi_lezione(l1);

        //creo prenotazione p1
        prenotazione p1 = new prenotazione();
        p1.matricola_studente = 100003;
        p1.nome_corso = "Ingegneria";
        p1.cognome_docente = "Rossi";
        p1.aula = 7;
        p1.giorno = (LocalDate.of(2022,5,10));
        p1.ora_inizio = LocalTime.of(9,00);
        p1.ora_fine = LocalTime.of(11,00);
        p1.presente = "indefinito";

        //aggiungo la prenotazione p1 all'interno della lista prenotazioni
        g_lez.decrementa_prenota( l1, 100003);
        //verifico sia una prenotazione per una lezione futura
        //altrimenti non è consentito
        g_lez.Verifica_data(p1);
        assertEquals(null,db_lez.cerca_prenotazione(p1));
        //elimino la lezione futura
        g_lez.elimina_lezione(l1);


        //creo una lezione passata
        lezione l2 = new lezione();
        l2.nome_corso = "Programmazione_1";
        l2.cognome_docente = "Codetta";
        l2.anno = 1;
        l2.numero_aula = 7;
        l2.posti_disponibili = 52;
        l2.giorno = LocalDate.of(2021, 10, 1);
        l2.ora_inizio = LocalTime.of(9, 00);
        l2.ora_fine = LocalTime.of(13, 00);

        //creo prenotazione p2 per la lezione passata
        prenotazione p2 = new prenotazione();
        p2.matricola_studente = 100003;
        p2.nome_corso = "Programmazione_1";
        p2.cognome_docente = "Codetta";
        p2.aula = 7;
        p2.giorno = (LocalDate.of(2021,10,1));
        p2.ora_inizio = LocalTime.of(9,00);
        p2.ora_fine = LocalTime.of(13,00);
        p2.presente = "indefinito";

        //effettuo la prenotazione
        g_lez.decrementa_prenota( l2, 100003);
        //controllo sia una lezione passata
        g_lez.Verifica_data(p2);
        //controllo nella lista prenotazione se la prenotazione
        //esiste ancora
        assertNotEquals(null,db_lez.cerca_prenotazione(p2));
        //cancello la prenotazione passata
        db_lez.Cancella_prenotazione(p2);

    }
    @Test
    public void test_incremento_numero_disponibili_aula(){
        ArrayList<lezione> l2 ;
        l2 = db_lez.carica_lezioni();
        int l1 = l2.get(1).posti_disponibili;


        // aggiungo una prenotazione
        prenotazione p1 = new prenotazione();
        p1.matricola_studente = 100003;
        p1.nome_corso = "Programmazione_1";
        p1.cognome_docente = "Codetta";
        p1.aula = 7;
        p1.giorno = (LocalDate.of(2021,10,3));
        p1.ora_inizio = LocalTime.of(14,00);
        p1.ora_fine = LocalTime.of(18,00);
        p1.presente = "indefinito";

        // aggiungo prenotazione
        // decremento posti disponibili lezione
        g_lez.decrementa_prenota( l2.get(1), 100003);

        // controllo se viene decrementato il numero disponibile in aula
        assertNotEquals(l2.get(1).posti_disponibili,l1);


        // cancello prenotazione effettuata
        db_lez.Cancella_prenotazione(p1);

        // aggiorno la lista lezioni
        l2 = db_lez.carica_lezioni();


        // controllo se dopo la cancellazione è aumentato
        // il numero di posti disponibili in aula
        assertEquals(l1,l2.get(1).posti_disponibili);

    }
}
