import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class lezione
{
    public String nome_corso;
    public String cognome_docente;
    public int anno;
    public int numero_aula;
    public int posti_disponibili;
    public LocalDate giorno;
    public LocalTime ora_inizio;
    public LocalTime ora_fine;

    public lezione(String nome_corso,  String cognome_docente, int anno, int numero_aula, int posti_disponibili, LocalDate giorno, LocalTime ora_inizio, LocalTime ora_fine) {
    	this.nome_corso = nome_corso;
        this.cognome_docente = cognome_docente;
        this.anno = anno;
        this.numero_aula = numero_aula;
        this.posti_disponibili = posti_disponibili;
        this.giorno = giorno;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine; 
    }
    
    public lezione(){
    	
    }
     
    public lezione(lezione copyObj) {
    	this.nome_corso = copyObj.nome_corso;
        this.cognome_docente = copyObj.cognome_docente;
        this.anno = copyObj.anno;
        this.numero_aula = copyObj.numero_aula;
        this.posti_disponibili = copyObj.posti_disponibili;
        this.giorno = copyObj.giorno;
        this.ora_inizio = copyObj.ora_inizio;
        this.ora_fine = copyObj.ora_fine; 
    }
    
    public String toString() {
    	return ""+nome_corso+" "+cognome_docente+" "+String.valueOf(anno)+" "+String.valueOf(posti_disponibili)
    	       +" "+giorno.format((DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.ITALY)))
    	       +" "+ora_inizio.format((DateTimeFormatter.ofPattern("HH:mm", Locale.ITALY)))
    	       +" "+ora_fine.format((DateTimeFormatter.ofPattern("HH:mm", Locale.ITALY)));
    }
    
    public boolean equals(lezione old) {
		return this.toString().equals(old.toString());
    }
}
