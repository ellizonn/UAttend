import static org.junit.Assert.*;
import org.junit.Test;

public class RF02_cambia_password_test
{

	//RF02
    //autore: Miglio - Mazzarino

    DB_utenti db_ut = new DB_utenti();
    gestore_accessi g_acc = new gestore_accessi(db_ut);

    @Test
    public void test_controlla_credenziali_01()
    {
    	//tre campi password vuoti
    	 assertEquals("error1", g_acc.controlla_credenziali(100001, "", "",""));
    }
    
    @Test
    public void test_controlla_credenziali_02()
    {
    	//due campi password vuoti
    	 assertEquals("error1", g_acc.controlla_credenziali(100001, "", "abc",""));
    }
    
    @Test
    public void test_controlla_credenziali_03()
    {
    	//un campo password vuoti
    	 assertEquals("error1", g_acc.controlla_credenziali(100001, "", "abc","abc"));
    }
    
    @Test
    public void test_controlla_credenziali_04()
    {
    	//password attuale sbagliata
    	 assertEquals("error2", g_acc.controlla_credenziali(100001, "aaa", "Asdfgh3#","Asdfgh3#"));
    }
    
    @Test
    public void test_controlla_credenziali_05()
    {
    	//nuova password coincide con quella attuale
    	 assertEquals("error3", g_acc.controlla_credenziali(100001, "abc", "abc","abc"));
    }
    
    @Test
    public void test_controlla_credenziali_06()
    {
    	//nuova password non rispetta requisiti - lunghezza
    	 assertEquals("error4", g_acc.controlla_credenziali(100001, "abc", "Asdfg3#","Asdfg3#"));
    }
    
    @Test
    public void test_controlla_credenziali_07()
    {
    	//nuova password non rispetta requisiti - presenza numero
    	 assertEquals("error4", g_acc.controlla_credenziali(100001, "abc", "Asdfghh#","Asdfghh#"));
    }
    
    @Test
    public void test_controlla_credenziali_08()
    {
    	//nuova password non rispetta requisiti - presenza lettera maiuscola
    	 assertEquals("error4", g_acc.controlla_credenziali(100001, "abc", "asdfgh3#","asdfgh3#"));
    }
    
    @Test
    public void test_controlla_credenziali_09()
    {
    	//nuova password non rispetta requisiti - presenza lettera minuscola
    	 assertEquals("error4", g_acc.controlla_credenziali(100001, "abc", "ASDFGH3#","ASDFGH3#"));
    }
    
    @Test
    public void test_controlla_credenziali_10()
    {
    	//nuova password non rispetta requisiti - presenza carattere speciale
    	 assertEquals("error4", g_acc.controlla_credenziali(100001, "abc", "Asdfgh33","Asdfgh33"));
    }
    
    @Test
    public void test_controlla_credenziali_11()
    {
    	//nuova password non rispetta requisiti - presenza carattere speciale e numero
    	 assertEquals("error4", g_acc.controlla_credenziali(100001, "abc", "Asdfghjk","Asdfghjk"));
    }
    
    @Test
    public void test_controlla_credenziali_12()
    {
    	//nuova password non rispetta requisiti - presenza carattere speciale, numero e lettera maiscola
    	 assertEquals("error4", g_acc.controlla_credenziali(100001, "abc", "aaaaaaaa","aaaaaaaa"));
    }
    
    @Test
    public void test_controlla_credenziali_13()
    {
    	//conferma nuova password diversa dalla nuova password
    	 assertEquals("error5", g_acc.controlla_credenziali(100001, "abc", "Asdfgh3#","Asdfgh33"));
    }
    
}