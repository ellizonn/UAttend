import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UI_prenotazione
{   
	public enum utente {STUDENTE, DOCENTE, STAFF};
    private gestore_lezioni g_lez;

    public UI_prenotazione(gestore_lezioni g1)
    {	
	//autore: Codetta
	g_lez=g1;
    }
	
	
	// =======================================================================
  
  
	//Metodo di avvio del caso d'uso RF13_prenota_posto
	public void avvio_prenota_posto(lezione lez, int matricola){
		
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		String scelta_stud = this.form_prenotazione(lez);
		boolean esito_formato_scelta = g_lez.controllo_formato_scelta(scelta_stud);
		if(esito_formato_scelta){
			if(scelta_stud.equals("procedi")){
				String esito_data_e_posti = g_lez.controllo_data_e_posti(lez);
				if(esito_data_e_posti.equals("ok")){
					g_lez.decrementa_prenota(lez, matricola);
					this.mostra_messaggio("Prenotazione avvenuta con successo!");
				}
				else
					this.mostra_errore(esito_data_e_posti);
			}
			else
				this.mostra_messaggio("Hai cliccato indietro, arrivederci.");
		}
		else
			this.mostra_messaggio("Scelta non contemplata. Processo di prenotazione annullato.");
		
	}
  
	public void mostra_errore(String tipo_err){
		
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		if(tipo_err.equals("err_data"))
			System.out.println("Errore: la data della lezione e' antecedente alla data odierna!");
		else if(tipo_err.equals("err_posti"))
			System.out.println("Errore: i posti della lezione sono esauriti!");
		System.out.println("Premi INVIO per conferma");
		Scanner sc = new Scanner(System.in);
        String enter = sc.nextLine();
		
	}
	
	public String form_prenotazione(lezione lez){
		
    	//RF13_prenota_posto
    	//Autori: Rossari, Marisio
        Scanner sc = new Scanner(System.in);
        System.out.print("\nHai selezionato la lezione di "+lez.nome_corso+", prevista alle ore "+lez.ora_inizio+" del "+lez.giorno+".\nDigitare 'procedi' o 'indietro': ");
        String x = sc.nextLine();
		return x;
		
    }
    
    public void mostra_messaggio(String messaggio){
		
    	//RF13_prenota_posto
    	//Autori: Rossari, Marisio
        Scanner sc = new Scanner(System.in);
        System.out.println(messaggio);
        System.out.println("Premi INVIO per conferma");
        String enter = sc.nextLine();
		
    }

	    //RF09 @autor Balossino, Battezzati
    public void visualizza_prenotazioni(utente tipoUtente) throws IOException {
        
		if(tipoUtente==utente.DOCENTE) visualizzaDocente();
		else if(tipoUtente==utente.STUDENTE) visualizzaStudente();
		else visualizzaStaff();
		
	}

	public void visualizza_prenotaz_lez(String nomeCorso, String docente) throws IOException {
    	
			ArrayList<prenotazione> prenotazioni = this.g_lez.get_prenotazioni_docente(docente, nomeCorso);
			for(int i=0;i<prenotazioni.size();i++) System.out.print("\n"+prenotazioni.get(i).toString()+"\n");
			System.out.print("Fine\n");
    }
    
    //RF09 @autor Balossino, Battezzati
    private void visualizzaDocente() throws IOException {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		System.out.print("Inserire nome del corso\n");
    		String nomeCorso = br.readLine();
    		System.out.print("Inserire cognome del docente\n");
    		String docente = br.readLine();
			ArrayList<prenotazione> prenotazioni = this.g_lez.get_prenotazioni_docente(docente, nomeCorso);
			for(int i=0;i<prenotazioni.size();i++) System.out.print(prenotazioni.get(i).toString()+"\n");
			System.out.print("Fine\n");
			System.out.print("Per uscire premere un tasto\n");
			br.readLine();
			}
		catch (IOException e) {
			System.out.print("Errore I/O\n");
		}
    }
    
    //RF09 @autor Balossino, Battezzati
    public void visualizzaStudente() /*throws IOException */{
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Inserire matricola\n");
			String matr = br.readLine();
			int matricola = Integer.parseInt(matr);
			ArrayList<prenotazione> prenotazioni = this.g_lez.get_prenotazioni_studente(matricola);
			for(int i=0;i<prenotazioni.size();i++) System.out.print(prenotazioni.get(i).toString()+"\n");
			System.out.print("Fine\n");
			System.out.print("Per uscire premere un tasto\n");
			br.readLine();
			}
			
			catch (IOException e) {
				System.out.print("Errore I/O\n");
			}
			
	}

	//RF09 @autor Balossino, Battezzati
    public void visualizzaStudente(int matricola){
			ArrayList<prenotazione> prenotazioni = this.g_lez.get_prenotazioni_studente(matricola);
			for(int i=0;i<prenotazioni.size();i++) System.out.print("\n"+prenotazioni.get(i).toString()+"\n");
			System.out.print("Fine\n");
			
	}
	
    
    //RF09 @autor Balossino, Battezzati
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
