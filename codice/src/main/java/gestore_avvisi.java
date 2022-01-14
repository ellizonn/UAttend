import java.time.LocalDate;
import java.util.ArrayList;

public class gestore_avvisi
{
    public DB_avvisi db_avv;

    public gestore_avvisi(DB_avvisi d1)
    {
      //autore: Codetta
      db_avv=d1;
    }
    
    public ArrayList<avviso> richiesta_avvisi_recenti(){

      // autore: Beccuti/Iabichino RF01
		
    	return db_avv.cerca_avvisi(LocalDate.now());
    }
    
    public String controlla_avviso(avviso avviso) {
        //autore: Furnari/Gattico RF16

        String check_avviso = null;

        if (avviso.testo == null) {
            check_avviso = "testo";
        } else if (avviso.scadenza == null || avviso.scadenza.isBefore(LocalDate.now())) {
            check_avviso = "data";
        }
        return check_avviso;
    }
    
    public void salva_avviso(avviso avviso) {
        //autore: Furnari/Gattico RF16
        
        db_avv.aggiungi_avviso(avviso);
    }
}
