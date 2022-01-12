import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;



public class RF04_crea_corso_test {

	DB_utenti db_ut  = new DB_utenti();
	DB_lezioni db_lez =  new DB_lezioni();
	gestore_utenti g_utn = new gestore_utenti(db_ut);
	gestore_lezioni g_lez =  new gestore_lezioni(db_lez);



	/**
	 * Test funzione verifica_nome_corso()
	 * @author : Andrea Colaci , Gregorio Lupano
	 */

	@Test
	public void nome_corso_vuoto()
	{
		String nomeCorso = ""; 
		assertEquals("CORSO_VUOTO",g_lez.verifica_nome_corso(nomeCorso));
	}


	@Test
	public void nome_corso_corto()
	{
		String nomeCorso = "abc"; 
		assertEquals("CORSO_CORTO",g_lez.verifica_nome_corso(nomeCorso));
	}
	

	@Test
	public void annulla_creazione()
	{
		String nomeCorso = "ESC"; 
		assertEquals("ABORT",g_lez.verifica_nome_corso(nomeCorso));
	}


	@Test
	public void corso_presente()
	{
		String nomeCorso = "Programmazione_1"; 
		assertEquals("CORSO_PRESENTE",g_lez.verifica_nome_corso(nomeCorso));
	}
	

	@Test
	public void corso_ok()
	{
		String nomeCorso = "Programmazione_2"; 
		assertEquals("OK",g_lez.verifica_nome_corso(nomeCorso));
	}

	/**
	 * Test funzione verifica_selezione_docente()
	 * @author : Andrea Colaci , Gregorio Lupano
	 */
	@Test
	public void selezione_valida()
	{
		ArrayList<utente> listaDocenti =  g_utn.fetch_lista_docenti();
		Integer selezione_docente = 1; 
		
		assertEquals("OK",g_utn.verifica_selezione_docente(listaDocenti, selezione_docente));
	}
	
	
	@Test
	public void selezione_non_valida()
	{
		ArrayList<utente> listaDocenti =  g_utn.fetch_lista_docenti();
		Integer selezione_docente = -1; 

		assertEquals("SELEZIONE_NON_VALIDA",g_utn.verifica_selezione_docente(listaDocenti, selezione_docente));
	}

	/**
	 * Test funzione verifica_anno_corso()
	 * @author : Andrea Colaci , Gregorio Lupano
	 */

	@Test
	public void anno_corso_valido()
	{
		int anno = 1;
		assertEquals("OK",g_lez.verifica_anno_corso(anno));
	}
	
	
	@Test
	public void anno_corso_non_valido()
	{
		int anno = -1;
		assertEquals("ANNO_NON_VALIDO",g_lez.verifica_anno_corso(anno));
	}
	

}
