import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RF03_test_avvisi_per_data
{
	//autore: FABBRO/BRUNI RF03

    DB_avvisi db_av;
	DB_utenti db_ut;
	DB_lezioni db_lez;
    gestore_ricerche g_ric;
	

    @Before
    public void setUp() {
		db_av = new DB_avvisi();
		db_ut = new DB_utenti();
		db_lez = new DB_lezioni();
        g_ric= new gestore_ricerche(db_ut,db_av,db_lez);
    }
	
	@Test
    public void test_cerca_avvisi_validi_01()
    {
    	//date valide e avvisi trovati
		ArrayList<avviso> a= db_av.cerca_avvisi_per_data(LocalDate.of(2021, 05 , 15),LocalDate.of(2021, 06 , 15));
		avviso aspettativa= new avviso();
		aspettativa.testo="gli_appelli_iniziano_il_15_giugno";
		aspettativa.emissione=LocalDate.of(2021, 06 , 01);
		aspettativa.scadenza=LocalDate.of(2021, 06 , 30);
        assertEquals(aspettativa.testo,a.get(0).testo);
		assertEquals(aspettativa.emissione,a.get(0).emissione);
		assertEquals(aspettativa.scadenza,a.get(0).scadenza);
    }
	@Test
    public void test_cerca_avvisi_validi_02()
    {
    	//date valide e avvisi trovati
		
		ArrayList<avviso> a= db_av.cerca_avvisi_per_data(LocalDate.of(2021, 05 , 15),LocalDate.of(2021, 07 , 15));
		avviso aspettativa0= new avviso();
		aspettativa0.testo="gli_appelli_iniziano_il_15_giugno";
		aspettativa0.emissione=LocalDate.of(2021, 06 , 01);
		aspettativa0.scadenza=LocalDate.of(2021, 06 , 30);
		
		avviso aspettativa1= new avviso();
		aspettativa1.testo="gli_appelli_finiscono_il_30_luglio";
		aspettativa1.emissione=LocalDate.of(2021, 07, 01);
		aspettativa1.scadenza=LocalDate.of(2021, 07, 31);
		
        assertEquals(aspettativa0.testo,a.get(0).testo);
		assertEquals(aspettativa0.emissione,a.get(0).emissione);
		assertEquals(aspettativa0.scadenza,a.get(0).scadenza);
		
		assertEquals(aspettativa1.testo,a.get(1).testo);
		assertEquals(aspettativa1.emissione,a.get(1).emissione);
		assertEquals(aspettativa1.scadenza,a.get(1).scadenza);
		
    }
	
	@Test
    public void test_date_non_valide_01()
    {
    	//data inizio maggiore data fine
		
		ArrayList<avviso> a= db_av.cerca_avvisi_per_data(LocalDate.of(2021, 05 , 15),LocalDate.of(2021, 02 , 15));
		assertEquals(0,a.size());
		assertEquals(null,g_ric.verifica_date(LocalDate.of(2021, 05 , 15),LocalDate.of(2021, 02 , 15)));
		
    }
	@Test
    public void test_date_non_valide_02()
    {
    	//data inizio maggiore data odierna
		
		ArrayList<avviso> a= db_av.cerca_avvisi_per_data(LocalDate.of(2022, 03 , 15),LocalDate.of(2022, 07 , 15));
		assertEquals(0,a.size());
		assertEquals(null,g_ric.verifica_date(LocalDate.of(2022, 03 , 15),LocalDate.of(2022, 07 , 15)));
		
    }
	@Test
    public void test_date_non_valide_03()
    {
    	//data inizio e fine nulle
		
		assertEquals(null,g_ric.verifica_date(null,null));
    }
	
	@Test
    public void test_avvisi_non_trovati()
    {
    	//date valide e avvisi trovati
		ArrayList<avviso> a= db_av.cerca_avvisi_per_data(LocalDate.of(2021, 01 , 15),LocalDate.of(2021, 02 , 15));
        assertEquals(0,a.size());
    }
	
	
}
