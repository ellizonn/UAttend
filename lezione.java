import java.time.LocalDate;
import java.time.LocalTime;

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

     /**
     * RF15 Modifica lezione 
     * @author Manganoni Mattia Frassinelli Marco Omar
     * 
     * costruttore usato dalla modifica lezione
     * @param nome_corso
     * @param cognome_docente
     * @param anno
     * @param numero_aula
     * @param posti_disponibili
     * @param giorno
     * @param ora_inizio
     * @param ora_fine
     */
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
    
      /**
     * RF15 Modifica lezione 
     * @author Manganoni Mattia Frassinelli Marco Omar
     * 
     * 
     * funzione che trasforma l'oggetto in stringa
     */
    public String toString() {
    	return ""+nome_corso+" "+cognome_docente+" "+String.valueOf(anno)+" "+String.valueOf(posti_disponibili)
    	       +" "+giorno.format((DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.ITALY)))
    	       +" "+ora_inizio.format((DateTimeFormatter.ofPattern("HH:mm", Locale.ITALY)))
    	       +" "+ora_fine.format((DateTimeFormatter.ofPattern("HH:mm", Locale.ITALY)));
    }
    
    /**
     * RF15 Modifica lezione 
     * @author Manganoni Mattia Frassinelli Marco Omar
     * 
     * funzione che controlla se 2 lezioni sono uguali mediante stringhe
     * @param old
     * @return
     */
    public boolean equals(lezione old) {
		return this.toString().equals(old.toString());
    }
}
