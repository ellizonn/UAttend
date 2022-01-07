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

    public ArrayList<lezione> controllo_data(String data_inizio, String data_fine) {

        //RF12 - Ricerca lezioni per data
        //autore: Masino, Spina

        ArrayList<lezione> elenco_lezioni;

        try {
            LocalDate startDate = LocalDate.parse(data_inizio,formatter);
            LocalDate endDate = LocalDate.parse(data_fine,formatter);
            if(startDate.isBefore(LocalDate.now()) || startDate.isAfter(endDate)) {
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
}
