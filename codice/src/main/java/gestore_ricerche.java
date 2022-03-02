import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;

class gestore_ricerche
{

	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
		
		if(data_inizio.isAfter(odierna)|| data_inizio.isAfter(data_fine))
		{
			return null;
		}
		else
		{
			elenco_avvisi=db_avv.cerca_avvisi_per_data(data_inizio,data_fine);
			return elenco_avvisi;
			
		}
		
	}

	public ArrayList<lezione> controllo_data(String data_inizio, String data_fine) {

		//RF12 - Ricerca lezioni per data
		//autore: Masino, Spina

		ArrayList<lezione> elenco_lezioni;

		try {
			LocalDate startDate = LocalDate.parse(data_inizio,formatter);
			LocalDate endDate = LocalDate.parse(data_fine,formatter);
			if((startDate.isBefore(LocalDate.now()) && endDate.isBefore(LocalDate.now())) || startDate.isAfter(endDate)) {
				elenco_lezioni = null;
			}
			else {
				elenco_lezioni = db_lez.cerca_lezioni(startDate,endDate);
			}
		}
		catch(DateTimeException | InputMismatchException e) {
			elenco_lezioni = null;
		}

		return elenco_lezioni;
	}

	public boolean controlla_scelta(int scelta, String tipo_utente)
	{
		//RF12 - Ricerca lezioni per data
		//autore: Masino, Spina

		boolean esito;

		if (tipo_utente.equals("staff") && scelta>=0 && scelta !=2 ||
				tipo_utente.equals("studente") && scelta>=0 && scelta <=2 && scelta!=1 ||
				tipo_utente.equals("docente") && scelta>=0 && scelta<=1)
			esito=true;
		else
			esito=false;

		return esito;
	}
	
    public ArrayList<utente> verifica_parametri(int matricola, String cognome) {
    	
    	//RF07: ricerca utente
    	//autori: Malavasi-Torta
    	
    	String mat = "" + matricola;
    	int L1 = mat.length();
    	int L2 = cognome.length();
    	if(L1!=6 && L2==0)
    		return null;
    	return db_ut.ricerca_utente(matricola,cognome);
    }
	
}
