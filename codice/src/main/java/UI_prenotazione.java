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
  
  
	/**
     * Metodo di avvio del caso d'uso RF13_prenota_posto
     * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
	 * @param lez oggetto lezione
	 * @param matricola numero matricola
     */
	public void avvio_prenota_posto(lezione lez, int matricola){
		
		String scelta_stud = this.form_prenotazione(lez);
		boolean esito_formato_scelta = g_lez.controllo_formato_scelta(scelta_stud);
		if(esito_formato_scelta){
			if(scelta_stud.equals("procedi")){
				boolean esito_prenotazione_doppia = g_lez.controllo_prenotazione_doppia(lez, matricola);
				if(!esito_prenotazione_doppia){
					String esito_data_e_posti = g_lez.controllo_data_e_posti(lez);
					if(esito_data_e_posti.equals("ok")){
						g_lez.decrementa_prenota(lez, matricola);
						this.mostra_messaggio("Prenotazione avvenuta con successo!");
					}
					else
						this.mostra_errore(esito_data_e_posti);
				}
				else
					this.mostra_messaggio("La lezione e' gia' stata prenotata!");
			}
			else
				this.mostra_messaggio("Hai cliccato indietro, arrivederci.");
		}
		else
			this.mostra_messaggio("Scelta non contemplata. Processo di prenotazione annullato.");
		
	}
  
	/**
	 * Stampa errore in base a tipo_err
	 * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
	 * @param tipo_err stringa che indica il tipo di errore (err_data o err_posti)
	 */
	public void mostra_errore(String tipo_err){
		
		if(tipo_err.equals("err_data"))
			System.out.println("Errore: impossibile prenotare una lezione passata!");
		else if(tipo_err.equals("err_posti"))
			System.out.println("Errore: i posti della lezione sono esauriti!");
		System.out.println("Premi INVIO per conferma");
		Scanner sc = new Scanner(System.in);
        String enter = sc.nextLine();
		
	}
	
	/**
	 * Stampa il form e chiedo in input scelta_stud
	 * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
	 * @param lez oggetto lezione
	 * @return scelta_stud stringa nel formato "indietro" o "procedi"
	 */
	public String form_prenotazione(lezione lez){
		
        Scanner sc = new Scanner(System.in);
        System.out.print("\nHai selezionato la lezione di "+lez.nome_corso+", prevista alle ore "+lez.ora_inizio+" del "+lez.giorno+".\nDigitare 'procedi' o 'indietro': ");
        String scelta_stud = sc.nextLine();
		return scelta_stud;
		
    }
    
    /**
     * Stampa stringa messaggio
     * @author RF13 prenota_posto
	 * @author Paolo Rossari 20034882
	 * @author Elia Marisio 20036782
     * @param messaggio stringa
     */
	public void mostra_messaggio(String messaggio){
		
        Scanner sc = new Scanner(System.in);
        System.out.println(messaggio);
        System.out.println("Premi INVIO per conferma");
        String enter = sc.nextLine();
		
    }

	   //RF09 @autor Balossino, Battezzati
    public void visualizza_prenotaz_lez(lezione lez, String tipo_utente) /*throws IOException */{
    	
    	try {
			ArrayList<prenotazione> prenotazioni = this.g_lez.get_prenotazioni_docente(lez);
			if(prenotazioni.size()==0) System.out.print("\nNESSUNA PRENOTAZIONE\n");
			else {
			for(int i=0;i<prenotazioni.size();i++) System.out.print("\n"+(i+1)+"\nMATRICOLA STUDENTE:\t"+prenotazioni.get(i).matricola_studente+"\nNOME CORSO:\t"+prenotazioni.get(i).nome_corso+"\nCOGNOME DOCENTE:\t"+prenotazioni.get(i).cognome_docente+"\nAULA:\t"+prenotazioni.get(i).aula+"\nGIORNO:\t"+prenotazioni.get(i).giorno+"\nORA INIZIO:\t"+prenotazioni.get(i).ora_inizio+"\nORA FINE:\t"+prenotazioni.get(i).ora_fine+"\nPRESENTE:\t"+prenotazioni.get(i).presente+"\n");
			System.out.print("\n");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			if(tipo_utente=="Staff") {
				System.out.print("Digitare 0 per uscire, oppure il numero della prenotazione per annullarla o confermarne la presenza");
				String scelta = br.readLine();
				if(scelta.compareTo("0")==0) {
					System.out.print("\nFine\n");
					return;
				}
				else {
					prenotazione p=prenotazioni.get(Integer.valueOf(scelta)-1);
					System.out.print("Digitare 1 per annullare la prenotazione, oppure 2 per confermarne la presenza");
					scelta = br.readLine();
					//if(scelta.compareTo("1")==0) g_lez.Verifica_data(p); // annulla prenotazione
					//if(scelta.compareTo("2")==0) g_lez.avvio_registra_presenza(p);

					System.out.print("\nFine\n");
					return;
				}

			}
			else {
				System.out.print("\nFine\n");
				return;
			}
			}
    	}
    	catch(IOException e) {
    		System.out.print("\ninput non valido\n");
    	}
    }
    
  //RF09 @autor Balossino, Battezzati
    public void visualizza_prenotaz_stud(int matricola, String tipo_utente) /*throws IOException */ {
    	try {
			ArrayList<prenotazione> prenotazioni = this.g_lez.get_prenotazioni_studente(matricola);
			if(prenotazioni.size()==0) System.out.print("\nNESSUNA PRENOTAZIONE\n");
			else {
			for(int i=0;i<prenotazioni.size();i++) System.out.print("\n"+(i+1)+"\nMATRICOLA STUDENTE:\t"+prenotazioni.get(i).matricola_studente+"\nNOME CORSO:\t"+prenotazioni.get(i).nome_corso+"\nCOGNOME DOCENTE:\t"+prenotazioni.get(i).cognome_docente+"\nAULA:\t"+prenotazioni.get(i).aula+"\nGIORNO:\t"+prenotazioni.get(i).giorno+"\nORA INIZIO:\t"+prenotazioni.get(i).ora_inizio+"\nORA FINE:\t"+prenotazioni.get(i).ora_fine+"\nPRESENTE:\t"+prenotazioni.get(i).presente+"\n");
			System.out.print("\n");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			if(tipo_utente=="Staff") {
				System.out.print("Digitare 0 per uscire, oppure il numero della prenotazione per annullarla o confermarne la presenza: ");
				String scelta = br.readLine();
				if(scelta.compareTo("0")==0) {
					System.out.print("\nFine\n");
					return;
				}
				else {
					prenotazione p=prenotazioni.get(Integer.valueOf(scelta)-1);
					System.out.print("Digitare 1 per annullare la prenotazione, oppure 2 per confermarne la presenza: ");
					scelta = br.readLine();
					//if(scelta.compareTo("1")==0) g_lez.Verifica_data(p); // annulla prenotazione
					if(scelta.compareTo("2")==0) mostra_form_registra_presenze(p);
					System.out.print("\nFine\n");
					return;
				}
			}
			else {
				System.out.print("Digitare 0 per uscire, oppure il numero della prenotazione per annullarla: ");
				String scelta = br.readLine();
				if(scelta.compareTo("0")==0) {
					System.out.print("\nFine\n");
					return;
				}
				else {
					prenotazione p=prenotazioni.get(Integer.valueOf(scelta)-1);
					//if(scelta.compareTo("1")==0) g_lez.Verifica_data(p); // annulla prenotazione
					System.out.print("\nFine\n");
					return;
				}
			}
			}
    	}
    	catch(IOException e) {
    		System.out.print("\ninput non valido\n");
    	}	
	}
	/**
	 * RF11 Registra_presenza
	 * Richiede all'utente di inserire la presenza, l'assenza o di annullare l'operazione.
	 * In base alla scelta, richiama il metodo gestione_scelta(), annulla l'operazione o mostra un messaggio di errore.
	 * @author Almasio, Borova
	 * @param p : prenotazione
	 */
	public void mostra_form_registra_presenze(prenotazione p){
		Scanner sc = new Scanner(System.in);
		String scelta = null;
		try{
		String str = g_lez.avvio_registra_presenza(p);
		if(str.equals("err1")) {
			mostra_errore_registrazione("err1",p);
		} else if (str.equals("err2")){
			mostra_errore_registrazione("err2", p);
		} else if (str.equals("noerr")){
			System.out.println("Inserire: 'p'-PRESENTE ---- 'a'-ASSENTE ---- 'annulla' per annullare: ");
			scelta = sc.next();
			if (scelta.equals("p")) {
				g_lez.gestione_scelta(scelta, p);
			} else if (scelta.equals("a")) {
				g_lez.gestione_scelta(scelta, p);
			} else if (scelta.equals("annulla")) {
				return;
			} else {
				mostra_errore_registrazione("err3", p);
			}
		}
		} catch (NullPointerException e){
			System.out.println("Input non valido. (UI_prenotazione.java)");
		}
		mostra_messaggio_conferma(scelta);
	}

	/**
	 * RF11 Registra_presenza
	 * In base all'input, stampa un differente messaggio di errore; l'utente dovra' confermarne la lettura.
	 * @author Almasio, Borova
	 * @param esito : esito dei controlli effettuati
	 */
	public void mostra_errore_registrazione(String esito, prenotazione p){
		String enter;
		switch (esito){
			case "err1":
				System.out.println("ERRORE REGISTRAZIONE!\nLa lezione in data: " + p.giorno + " delle ore: " + p.ora_inizio + " non e' ancora avvenuta.\n");
				System.out.println("Premere ENTER per confermare lettura.");
				Scanner s1 = new Scanner(System.in);
				enter = s1.nextLine();
				break;
			case "err2":
				System.out.println("Registrazione gia' effettuata!");
				System.out.println("Premere ENTER per confermare lettura.");
				Scanner s2 = new Scanner(System.in);
				enter = s2.nextLine();
				break;
			case "err3":
				System.out.println("Scelta non valida!");
				System.out.println("Premere ENTER per confermare lettura.");
				Scanner s3 = new Scanner(System.in);
				enter = s3.nextLine();
				break;
		}
		return;
	}

	/**
	 * RF11 Registra_presenza
	 * In base all'input, stampa un differente messaggio; l'utente dovra' confermarne la lettura.
	 * @author Almasio, Borova
	 * @param scelta_opzione
	 */
	public void mostra_messaggio_conferma(String scelta_opzione){
		String enter;
		if(scelta_opzione.equals("p")){
			System.out.println("Presenza confermata con successo!");
			System.out.println("Premere ENTER per confermare lettura.");
			Scanner s1 = new Scanner(System.in);
			enter = s1.nextLine();
		} else if(scelta_opzione.equals("a")){
			System.out.println("Assenza confermata con successo!");
			System.out.println("Premere ENTER per confermare lettura.");
			Scanner s2 = new Scanner(System.in);
			enter = s2.nextLine();
		} else {
			System.out.println("Errore nell'inserimento della scelta.");
			System.out.println("Premere ENTER per confermare lettura.");
			Scanner s3 = new Scanner(System.in);
			enter = s3.nextLine();
		}
	}
}
    
    
