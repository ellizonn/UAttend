import java.time.LocalDate;
import java.time.LocalTime;

class gestore_lezioni
{
    DB_lezioni db_lez;

    public gestore_lezioni(DB_lezioni d1)
    {
    	//autore: Codetta
    	db_lez=d1;
    }
    
    public String controllo_data_e_posti(lezione lez)
    {
    	//RF13_prenota_posto
    	//Autori: Rossari, Marisio
    	
    	LocalDate today = LocalDate.now();
    	LocalTime now = LocalTime.now();
    	
    	if((lez.giorno.isEqual(today)||lez.giorno.isAfter(today)) && lez.ora_inizio.isAfter(now) && lez.posti_disponibili>0) {
    		return "ok";
    	}
    	if(lez.giorno.isBefore(today) || (lez.ora_inizio.isBefore(now)||lez.ora_inizio.equals(now))) {
    		return "err_data";
    	}
    	//if lez.posti_disponibili == 0
    	return "err_posti";
    }
    
}
