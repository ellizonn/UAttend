import java.time.LocalDate;
import java.time.LocalTime;

public class prenotazione
{
    public int matricola_studente;
    public String nome_corso;
    public String cognome_docente;
    public int aula;
    public LocalDate giorno;
    public LocalTime ora_inizio;
    public LocalTime ora_fine;
    public String presente;

    public prenotazione()
    {
    }

    public prenotazione(int m, String n, String c, int a, LocalDate g, LocalTime oi, LocalTime of, String p) {
    	this.matricola_studente=m;
    	this.nome_corso=n;
    	this.cognome_docente=c;
    	this.aula=a;
    	this.giorno=g;
    	this.ora_inizio=oi;
    	this.ora_fine=of;
    	this.presente=p;
    }
    

}

