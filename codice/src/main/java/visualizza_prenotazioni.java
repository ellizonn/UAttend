package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VisualizzaPrenotazioni {
	
	
	public enum utente {STUDENTE, STAFF, DOCENTE};
	private utente tipoUtente;
	private ArrayList<prenotazione> elenco;
	
	public VisualizzaPrenotazioni(utente tipoUtente, ArrayList<prenotazione> elenco) throws IOException {
		this.tipoUtente=tipoUtente;
		this.elenco=elenco;
		if(this.tipoUtente==utente.DOCENTE) visualizzaDocente();
		else if(this.tipoUtente==utente.STUDENTE) visualizzaStudente();
		else visualizzaStaff();
	}
	
	private void visualizzaDocente() {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Inserire nome del corso\n");
			String nomeCorso = br.readLine();
			System.out.print("Inserire cognome del docente\n");
			String docente = br.readLine();
			int l = this.elenco.size();
			for(int i=0;i<l;i++) {
				prenotazione prenotazione = (prenotazione) this.elenco.get(i);
				if (prenotazione.cognome_docente.compareTo(docente)==0 && prenotazione.nome_corso.compareTo(nomeCorso)==0) System.out.print(prenotazione.toString()+"\n");
			}
			System.out.print("Fine\n");
			System.out.print("Per uscire premere un tasto\n");
			br.readLine();
			}
			catch (IOException e) {
				System.out.print("Errore I/O\n");
			}
		
	}
	
	private void visualizzaStudente() throws IOException{
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Inserire matricola\n");
			String matr = br.readLine();
			int matricola = Integer.parseInt(matr);
			int l = this.elenco.size();
			for(int i=0;i<l;i++) {
				prenotazione prenotazione = (prenotazione) this.elenco.get(i);
				if (prenotazione.matricola_studente==matricola) System.out.print(prenotazione.toString()+"\n");
			}
			System.out.print("Fine\n");
			System.out.print("Per uscire premere un tasto\n");
			br.readLine();
			}
			catch (IOException e) {
				System.out.print("Errore I/O\n");
			}
		
	}
	
	private void visualizzaStaff() throws IOException {
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Digitare s se si vuole cercare le prenotazioni di uno studente. d per cercare le prenotazioni a una lezione. Digitare un altro qualsiasi altro tasto per uscire.\n");
		String scelta = br.readLine();
		if(scelta.compareTo("s")==0) visualizzaStudente();
		else if(scelta.compareTo("d")==0) visualizzaDocente();
		else {
			System.out.print("Fine\n");
			System.out.print("Per uscire premere un tasto\n");
			br.readLine();
		}
		}
		catch (IOException e) {
			System.out.print("Errore I/O\n");
		}
	}
	
}