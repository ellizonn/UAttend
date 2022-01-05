import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class gestore_ricerche
{
    private DB_utenti db_ut;
    private DB_avvisi db_avv;
    private DB_lezioni db_lez;

    public gestore_ricerche(DB_utenti d1, DB_avvisi d2, DB_lezioni d3)
    {
      //autore: Codetta
      db_ut=d1;
      db_avv=d2;
      db_lez=d3;
    }
	
	public ArrayList<avviso> verifica_date(LocalDate data_inizio,LocalDate data_fine)
	{
		//autore: FABBRO/BRUNI RF03
		ArrayList<avviso> elenco_avvisi= new ArrayList<>();
		LocalDate odierna=LocalDate.now();;
		
		if(data_inizio==null||data_fine==null||data_inizio.isAfter(odierna)|| data_inizio.isAfter(data_fine))
		{
			return null;
		}
		else
		{
			elenco_avvisi=db_avv.cerca_avvisi_per_data(data_inizio,data_fine);
			return elenco_avvisi;
			
		}
		
	}
	
	
	
}
