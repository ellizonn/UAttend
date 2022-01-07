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
    public boolean presente;
    
    public prenotazione(int m, String n, String c, int a, LocalDate g, LocalTime oi, LocalTime of, boolean p) {
    	this.matricola_studente=m;
    	this.nome_corso=n;
    	this.cognome_docente=c;
    	this.aula=a;
    	this.giorno=g;
    	this.ora_inizio=oi;
    	this.ora_fine=of;
    	this.presente=p;
    }
    
    // RF09 visualizza prenotazioni
	// Balossino, Battezzati

    public String toString() {
    	String s="";
    	s=s+this.matricola_studente+"\t"+this.nome_corso+"\t"+this.cognome_docente+"\t"+this.aula+"\t"+this.giorno+"\t"+this.ora_inizio+"\t"+this.ora_fine+"\t"+this.presente;
    	return s;
    }
    
}

