import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Davide Ceci - 20033793 - RF_14
 * @author Luca Tamone - 20034235 - RF_14
 */
public class RF14_cancella_lezione_test {
  
    gestore_lezioni g_lez;
    DB_lezioni db_lez;

    @Before
    public void setup() {
        db_lez = new DB_lezioni();
        g_lez = new gestore_lezioni(db_lez);
    }

    @Test
    public void test_cancella_lezione() {
        // aggiungo due lezioni
        lezione l1 = new lezione();
        l1.nome_corso = "Ingegneria";
        l1.cognome_docente = "Rossi";
        l1.anno = 2022;
        l1.numero_aula = 17;
        l1.posti_disponibili = 35;
        l1.giorno = LocalDate.of(2022, 5, 10);
        l1.ora_inizio = LocalTime.of(9, 00);
        l1.ora_fine = LocalTime.of(11, 00);
        lezione l2 = new lezione();
        l2.nome_corso = "Architettura";
        l2.cognome_docente = "Rossi";
        l2.anno = 2022;
        l2.numero_aula = 14;
        l2.posti_disponibili = 40;
        l2.giorno = LocalDate.of(2022, 5, 10);
        l2.ora_inizio = LocalTime.of(11, 00);
        l2.ora_fine = LocalTime.of(13, 00);

        db_lez.aggiungi_lezione(l1);
        db_lez.aggiungi_lezione(l2);

        // controllo che siano presenti
        assertNotEquals(-1, cerca_lezione(l1));
        assertNotEquals(-1, cerca_lezione(l2));
        
        // ne elimino una
        g_lez.elimina_lezione(l1);


        // controllo che ne manchi una
        assertEquals(-1, cerca_lezione(l1));
        assertNotEquals(-1, cerca_lezione(l2));

        // elimino anche l'altra
        g_lez.elimina_lezione(l2);

        // controllo che manchino tutte e due
        assertEquals(-1, cerca_lezione(l1));
        assertEquals(-1, cerca_lezione(l2));
        
    }

    
    @Test 
    public void verifica_lezione() {
        // verifico se posso eliminare una lezione
        assertFalse(g_lez.verifica_lezione(LocalDate.of(2024, 10, 24)));
        assertFalse(g_lez.verifica_lezione(LocalDate.now()));
        assertTrue(g_lez.verifica_lezione(LocalDate.of(2021, 11, 17)));

    }

    private int cerca_lezione(lezione l) {
        // restituisce l'indice della lezione se presente, -1 se non presente
        DB_lezioni db_lez = new DB_lezioni();
        ArrayList<lezione> elenco_lezioni = db_lez.carica_lezioni();
        int presente = -1;



        for(int i = 0; i < elenco_lezioni.size() && presente == -1; i++) {
            if( l.nome_corso.equals(elenco_lezioni.get(i).nome_corso) && 
                l.cognome_docente.equals(elenco_lezioni.get(i).cognome_docente) &&
                l.anno == elenco_lezioni.get(i).anno &&
                l.numero_aula == elenco_lezioni.get(i).numero_aula &&
                l.giorno.equals(elenco_lezioni.get(i).giorno) &&
                l.ora_inizio.equals(elenco_lezioni.get(i).ora_inizio) &&
                l.ora_fine.equals(elenco_lezioni.get(i).ora_fine) ) {
                presente = i;
            }
        }

        return presente;
    }

}