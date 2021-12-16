import static org.junit.Assert.*;
import org.junit.Test;

public class RF00_login_test
{
    //autore: Codetta

    DB_utenti db_ut = new DB_utenti();
    gestore_accessi g_acc = new gestore_accessi(db_ut);

    @Test
    public void test_cerca_account_01()
    {
    	//username e password corrette
        assertNotNull("DB deve ritornare un account", db_ut.cerca_account(100001, "abc"));
    }

    @Test
    public void test_cerca_account_02()
    {
        //username e/o password errata
        assertNull("DB deve ritornare null", db_ut.cerca_account(100001, "xyz"));
    }

    @Test
    public void test_cerca_account_03()
    {
        //username e/o password errata
        assertNull("DB deve ritornare null", db_ut.cerca_account(100100, "abc"));
    }
    
    @Test
    public void test_cerca_account_04()
    {
        //username e/o password errata
        assertNull("DB deve ritornare null", db_ut.cerca_account(100100, "xyz"));
    }
    
    
    @Test
    public void test_controlla_credenziali_01()
    {
        // matricola  ha meno di 6 cifre
        assertEquals("error1", g_acc.controlla_credenziali(99999, "abc"));
    }
    
    @Test
    public void test_controlla_credenziali_02()
    {
        // matricola ha piu' di 6 cifre
        assertEquals("error1", g_acc.controlla_credenziali(1000000, "abc"));
    }

    @Test
    public void test_controlla_credenziali_03()
    {
        // password vuota
        assertEquals("error2", g_acc.controlla_credenziali(100001, ""));
    }


    @Test
    public void test_controlla_credenziali_04()
    {
        // username e/o password errata
        assertEquals("error3", g_acc.controlla_credenziali(100001, "xyz"));
    }

    @Test
    public void test_controlla_credenziali_05()
    {
        //username e/o password errata
        assertEquals("error3", g_acc.controlla_credenziali(100100, "abc"));
    }

    @Test
    public void test_controlla_credenziali_06()
    {
        //username e/o password errata
        assertEquals("error3", g_acc.controlla_credenziali(100100, "xyz"));
    }

    @Test
    public void test_controlla_credenziali_07()
    {
        //account bloccato
        assertEquals("error4", g_acc.controlla_credenziali(100002, "abc"));
    }

    @Test
    public void test_controlla_credenziali_08()
    {
        //utente cliente
        assertEquals("studente", g_acc.controlla_credenziali(100003, "abc"));
    }

    @Test
    public void test_controlla_credenziali_09()
    {
        //utente staff
        assertEquals("staff", g_acc.controlla_credenziali(100008, "abc"));
    }

    @Test
    public void test_controlla_credenziali_10()
    {
        //utente docente
        assertEquals("docente", g_acc.controlla_credenziali(100011, "abc"));
    }
    
    @Test
    public void test_controlla_credenziali_11()
    {
        //utente amministratore
        assertEquals("admin", g_acc.controlla_credenziali(100000, "abc"));
    }
    
    @Test
    public void test_controlla_scelta_01()
    {
        //utente staff
        assertTrue(g_acc.controlla_scelta(0, "staff"));
    }
    
    @Test
    public void test_controlla_scelta_02()
    {
        //utente staff
        assertTrue(g_acc.controlla_scelta(7, "staff"));
    }
    
    @Test
    public void test_controlla_scelta_03()
    {
        //utente staff
        assertTrue(g_acc.controlla_scelta(4, "staff"));
    }
    
    @Test
    public void test_controlla_scelta_04()
    {
        //utente staff
        assertFalse(g_acc.controlla_scelta(-1, "staff"));
    }
    
    @Test
    public void test_controlla_scelta_05()
    {
        //utente staff
        assertFalse(g_acc.controlla_scelta(8, "staff"));
    }
    
    @Test
    public void test_controlla_scelta_06()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(0, "studente"));
    }
    
    @Test
    public void test_controlla_scelta_07()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(4, "studente"));
    }
    
    @Test
    public void test_controlla_scelta_08()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(2, "studente"));
    }
    
    @Test
    public void test_controlla_scelta_09()
    {
        //utente studente
        assertFalse(g_acc.controlla_scelta(-1, "studente"));
    }
    
    @Test
    public void test_controlla_scelta_10()
    {
        //utente studente
        assertFalse(g_acc.controlla_scelta(5, "studente"));
    }
    
    @Test
    public void test_controlla_scelta_11()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(0, "docente"));
    }
    
    @Test
    public void test_controlla_scelta_12()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(3, "docente"));
    }
    
    @Test
    public void test_controlla_scelta_13()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(1, "docente"));
    }
    
    @Test
    public void test_controlla_scelta_14()
    {
        //utente studente
        assertFalse(g_acc.controlla_scelta(-1, "docente"));
    }
    
    @Test
    public void test_controlla_scelta_15()
    {
        //utente studente
        assertFalse(g_acc.controlla_scelta(4, "docente"));
    }
    
    @Test
    public void test_controlla_scelta_16()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(0, "admin"));
    }
    
    @Test
    public void test_controlla_scelta_17()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(4, "admin"));
    }
    
    @Test
    public void test_controlla_scelta_18()
    {
        //utente studente
        assertTrue(g_acc.controlla_scelta(2, "admin"));
    }
    
    @Test
    public void test_controlla_scelta_19()
    {
        //utente studente
        assertFalse(g_acc.controlla_scelta(-1, "admin"));
    }
    
    @Test
    public void test_controlla_scelta_20()
    {
        //utente studente
        assertFalse(g_acc.controlla_scelta(5, "admin"));
    }
}
