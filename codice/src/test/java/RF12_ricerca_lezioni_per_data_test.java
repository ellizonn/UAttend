import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class RF12_ricerca_lezioni_per_data_test {

    //RF12 - Ricerca lezioni per data
    //Autori: Masino, Spina

    DB_lezioni db_lezioni = new DB_lezioni();
    DB_utenti db_ut = new DB_utenti();
    DB_avvisi db_avv = new DB_avvisi();
    gestore_ricerche g_ric = new gestore_ricerche(db_ut,db_avv,db_lezioni);

    @Test
    public void test_cerca_lezioni() {
        LocalDate date_start = LocalDate.of(2021, 10, 01);
        LocalDate date_end = LocalDate.of(2021, 10, 03);
        assertNotNull("DB deve ritornare elenco lezioni",db_lezioni.cerca_lezioni(date_start,date_end));
    }

    @Test
    public void test_cerca_lezioni_02()
    {
        LocalDate date_start = LocalDate.of(2021, 10, 06);
        LocalDate date_end = LocalDate.of(2021, 10, 11);
        //date non presenti nel file
        assertEquals(db_lezioni.cerca_lezioni(date_start,date_end).size(), 0);
    }

    @Test
    public void test_cerca_lezioni_03()
    {
        LocalDate date_start = LocalDate.of(2022, 01, 03);
        LocalDate date_end = LocalDate.of(2022, 01, 05);
        //date non presenti nel file
        assertNotNull( "DB non deve ritornare null",db_lezioni.cerca_lezioni(date_start,date_end));
    }

    @Test
    public void test_controlla_data()
    {
        // data_inizio > data_fine
        assertNull(g_ric.controllo_data("03/10/2021", "01/10/2021"));
    }

    @Test
    public void test_controlla_data_02()
    {
        // data passata
        assertNull(g_ric.controllo_data("01/10/2021", "03/10/2021"));
    }

    @Test
    public void test_controlla_data_03()
    {
        // date corrette
        assertEquals(0, g_ric.controllo_data("05/10/2022","31/12/2022").size());
    }

    @Test
    public void test_controlla_data_04() {
        // date formato errato
        assertNull(g_ric.controllo_data("1/1/2022","15/1/2022"));
    }

    @Test
    public void controlla_scelta() {
        //utente staff
        assertTrue(g_ric.controlla_scelta(0,"staff"));
    }

    @Test
    public void controlla_scelta01() {
        //utente staff
        assertTrue(g_ric.controlla_scelta(1,"staff"));
    }

    @Test
    public void controlla_scelta02() {
        //utente staff
        assertTrue(g_ric.controlla_scelta(3,"staff"));
    }

    @Test
    public void controlla_scelta03() {
        //utente staff
        assertTrue(g_ric.controlla_scelta(4,"staff"));
    }

    @Test
    public void controlla_scelta_04()
    {
        //utente staff
        assertFalse(g_ric.controlla_scelta(-1, "staff"));
    }

    @Test
    public void controlla_scelta_05()
    {
        //utente staff
        assertFalse(g_ric.controlla_scelta(2, "staff"));
    }

    @Test
    public void controlla_scelta_06()
    {
        //utente studente
        assertTrue(g_ric.controlla_scelta(0, "studente"));
    }

    @Test
    public void controlla_scelta_07()
    {
        //utente studente
        assertTrue(g_ric.controlla_scelta(2, "studente"));
    }

    @Test
    public void controlla_scelta_08()
    {
        //utente studente
        assertFalse(g_ric.controlla_scelta(-1, "studente"));
    }

    @Test
    public void controlla_scelta_10()
    {
        //utente studente
        assertFalse(g_ric.controlla_scelta(3, "studente"));
    }

    @Test
    public void controlla_scelta_11()
    {
        //utente docente
        assertTrue(g_ric.controlla_scelta(0, "docente"));
    }

    @Test
    public void controlla_scelta_12()
    {
        //utente docente
        assertTrue(g_ric.controlla_scelta(1, "docente"));
    }

    @Test
    public void controlla_scelta_14()
    {
        //utente docente
        assertFalse(g_ric.controlla_scelta(-1, "docente"));
    }

    @Test
    public void controlla_scelta_15()
    {
        //utente docente
        assertFalse(g_ric.controlla_scelta(3, "docente"));
    }
}

