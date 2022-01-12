import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.System.exit;

public class UI_ricerca
{   
    private UI_account ui_acc;
    private UI_lezione ui_lez;
    private UI_prenotazione ui_pren;
    private gestore_ricerche g_ric;
	private String data_i;
	private String data_f;
	private LocalDate data_inizio;
	private LocalDate data_fine;	
	private int scelta;
	private int scelta_lez;
	private Scanner sc;
	
    public UI_ricerca(UI_account ui1, UI_lezione ui2, UI_prenotazione ui3, gestore_ricerche g1)
    {	
	//autore: Codetta
        ui_acc=ui1;
        ui_lez=ui2;
        ui_pren=ui3;

        g_ric=g1;
    }
	
	public void avvio_ricerca_avvisi_per_data()
	{
		//autore: FABBRO/BRUNI RF03
		
		ArrayList<avviso> a=new ArrayList<>();
		this.form_ricerca_avvisi_per_data();
		a=g_ric.verifica_date(data_inizio,data_fine);
		if(a==null||a.size()==0)
			mostra_errore(a);
		else
		{
			visualizza_elenco_avvisi_per_data(a);
		}
		do{
			this.mostra_menu();
			if(scelta==0)
				avvio_ricerca_avvisi_per_data();
			else if(scelta==1)
				return;
		}while(scelta<0 || scelta>1);
		
	}
	
	public void mostra_menu()
    {
        
		//autore: FABBRO/BRUNI RF03
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nMENU AVVISI PER DATA:\n0. Nuova Ricerca Avvisi per data\n1. ritorna al menu principale");
        System.out.print("\ninserire scelta: ");
        scelta=sc.nextInt();
   }
	
	 public void form_ricerca_avvisi_per_data()
    {
		//autore: FABBRO/BRUNI RF03
		
        Scanner sc = new Scanner(System.in);
		String data_i = "";
		String data_f = "";
		System.out.print("\nINSERIRE LE DATE PER LA RICERCA DEGLI AVVISI\n");
        System.out.print("\ninserisci data di inizio(yyyy-mm-dd): ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		data_i = sc.nextLine();
		data_inizio = LocalDate.parse(data_i, dateFormat);
        System.out.print("inserisci data di fine(yyyy-mm-dd): ");
		data_f = sc.nextLine();
		data_fine = LocalDate.parse(data_f, dateFormat);
    }	
	
	public void visualizza_elenco_avvisi_per_data(ArrayList<avviso> a) {

		//autore: FABBRO/BRUNI RF03

		System.out.println("\ndata emissione"+"\t"+"data scadenza"+"\t"+"avviso");
    	
    	for(avviso b:a) {
    		
    		System.out.println(b.emissione+"\t"+ b.scadenza+ "\t"+b.testo);
    		
    	}
    	
    }
	 public void mostra_errore(ArrayList<avviso> a) {

		//autore: FABBRO/BRUNI RF03
		String conferma;
		Scanner sc = new Scanner(System.in);
    	if(a==null)
		{
			System.out.println("\nLe date inserite non sono corrette");
			System.out.print("premi INVIO per confermare");
			conferma = sc.nextLine();
		}
    	else
		{
			System.out.println("\nNon ci sono avvisi per le date cercate");
			System.out.print("premi INVIO per confermare");
			conferma = sc.nextLine();
		}
    	
    }

	public void avvio_ricerca_lezioni(String tipo_utente,int matricola) throws IOException{
		//RF12 - Ricerca lezioni per data
		//autore: Masino, Spina

		boolean esito;
		ArrayList<lezione> elenco_lezioni;
		lezione lez;
		int i = 0;
		int sel_menu = 0;

		do {
			if(i > 0) {
				System.out.println("MENU' RICERCA LEZIONE PER DATA:\n0. Menù principale\n1. Ricerca lezioni");
				sc = new Scanner(System.in);
				System.out.print("\nInserire scelta: ");
				sel_menu = Integer.parseInt(sc.nextLine());
				if(sel_menu == 0) {
					break;
				}
			}
			this.form_ricerca_lezioni();
			elenco_lezioni = g_ric.controllo_data(data_i,data_f);
			if(elenco_lezioni == null) {
				mostra_errore_ricerca_lezioni(elenco_lezioni);
			}
			else if(elenco_lezioni.size() == 0) {
				System.out.println("Nessuna lezione nel periodo selezionato.");
				i++;
				continue;
				/*System.out.println("\n1. Ricerca altre lezioni");
				System.out.println("2. Torna al menù principale");
				System.out.print("Inserire scelta: ");
				int sel_menu;
				do {
					sc = new Scanner(System.in);
					sel_menu = sc.nextInt();
					if(sel_menu == 1) {
						break;
					}
					else {
						exit(0);
					}
				}
				while(sel_menu < 1 && sel_menu > 2);*/
			}
			else {
				lez = mostra_elenco_lezioni(elenco_lezioni);
				do {
					if(lez != null) {
						this.mostra_menu(tipo_utente);
						esito = g_ric.controlla_scelta(scelta_lez, tipo_utente);

						if (esito==false)
							mostra_errore_ricerca_lezioni(elenco_lezioni);

						if(scelta_lez == 1 && !tipo_utente.equals("studente")) {
							System.out.println("\nAVVIO visualizza prenotazioni");
							ui_pren.visualizza_prenotaz_lez(lez.nome_corso, lez.cognome_docente, tipo_utente);
						}
						if(scelta_lez == 2 && tipo_utente.equals("studente")) {
							System.out.println("\nAVVIO prenota posto");
							ui_pren.avvio_prenota_posto(lez,matricola);
						}
						if(scelta_lez == 3 && tipo_utente.equals("staff")) {
							System.out.println("\nAVVIO cancella lezione");
							ui_lez.avvia_cancella_lezione(lez);
						}
						if(scelta_lez == 4 && tipo_utente.equals("staff")) {
							System.out.println("\nAVVIO modifica lezione");
						}
					}
				}
				while(scelta_lez != 0);
			}
			i++;
		}
		while(scelta_lez == 0 || elenco_lezioni == null || sel_menu == 0);
		return;
	}

	public void form_ricerca_lezioni() {

		//RF12 - Ricerca lezioni per data
		//autore: Masino, Spina

		sc = new Scanner(System.in);
		System.out.print("\nInserisci data inizio (dd/MM/yyyy): ");
		data_i = sc.nextLine();
		System.out.print("Inserisci data fine (dd/MM/yyyy): ");
		data_f = sc.nextLine();
	}

	public void mostra_errore_ricerca_lezioni(ArrayList<lezione> elenco_lezioni) {

		//RF12 - Ricerca lezioni per data
		//autore: Masino, Spina

		String conferma;
		sc = new Scanner(System.in);
		if(elenco_lezioni == null) {
			System.err.println("\nERRORE: data passata o formato date errato.");
		}
		else {
			System.err.println("\nERRORE: scelta errata");
		}

		System.out.print("premi INVIO per confermare");
		conferma = sc.nextLine();
	}

	public lezione mostra_elenco_lezioni(ArrayList<lezione> elenco_lezioni) {

		//RF12 - Ricerca lezioni per data
		//autore: Masino, Spina

		int i = 1;
		lezione lezione = null;

		for(lezione lez : elenco_lezioni) {
			System.out.println("\n-------------------------------------------");
			System.out.println("Lezione "+i);
			System.out.println("Giorno: "+lez.giorno);
			System.out.println("Anno: "+lez.anno);
			System.out.println("Orario: "+lez.ora_inizio+" "+lez.ora_fine);
			System.out.println("Corso: "+lez.nome_corso);
			System.out.println("Docente: "+lez.cognome_docente);
			System.out.println("Numero Aula: "+lez.numero_aula);
			System.out.println("Posti disponibili: "+lez.posti_disponibili);
			System.out.println("-------------------------------------------");
			i++;
		}

		int sel_lez;
		sc = new Scanner(System.in);
		i = 1;

		do {
			System.out.println("Selezionare lezione o 0 per tornare al menù ricerca: ");
			sel_lez = sc.nextInt();
			if(sel_lez != 0) {
				for(lezione lez : elenco_lezioni) {
					if(i==sel_lez) {
						lezione = lez;
					}
					i++;
				}
			}
			else {
				//exit(0);
				return null;
			}
		}
		while(sel_lez > elenco_lezioni.size());

		return lezione;
	}

	public void mostra_menu(String tipo_utente)
	{
		//RF12 - Ricerca lezioni per data
		//autore: Masino, Spina

		Scanner sc = new Scanner(System.in);

		System.out.println("\ntipo utente: " + tipo_utente);


		if (tipo_utente.equals("docente"))
			System.out.println("0. Menù ricerca lezione\n1. Visualizza prenotazioni");
		if (tipo_utente.equals("studente") )
			System.out.println("0. Menù ricerca lezione\n2. Prenota posto\n");
		if (tipo_utente.equals("staff") )
			System.out.println("0. Menù ricerca lezione\n1. Visualizza prenotazioni\n3. Cancella lezione\n4. Modifica lezione");

		System.out.print("\nInserire scelta: ");
		scelta_lez = sc.nextInt();
	}
	
	 public void avvio_ricerca_utente(String tipo_utente) /*throws IOException*/ {
	    	
	    	//RF07: ricerca utente
	    	//autori: Malavasi - Torta
	    	
	    	Scanner sc = new Scanner(System.in);
	    	int scelta = 1;
	    	utente ut = new utente();
	    	do {
	    		ArrayList<utente> u = form_ricerca();
	    		if(u == null)
				{
	    			visualizza_errore(1);
	    			scelta = visualizza_menu(1);
				}
	    		else if(u.size() == 0)
	    		{
	    			visualizza_errore(2);
	    			scelta = visualizza_menu(1);
	    		}
	    		else {
	    			do {
		    			visualizza_elenco(u);
		    			System.out.println("\nSelezionare l'utente sul quale operare\n");
		    			int x = sc.nextInt();
		    			for(int i=0; i<u.size(); i++)
		    			{
		    				if(i == x)
		    				{	
		    					ut = u.get(i);
			    				if(tipo_utente == "staff")
				    				avvia_visualizza_prenotazioni(ut);
				    			else if(tipo_utente == "admin")
				    				cambia_stato_account(ut);
		    				}
		    			}
		    			scelta = visualizza_menu(2);	
	    			}while(scelta==3);
	    		}
	    	}while(scelta == 1);
	    }
	    
	    private int visualizza_menu(int i) {
	    	//RF07: ricerca utente
	    	//autori: Malavasi - Torta
	    	Scanner sc = new Scanner(System.in);
	    	System.out.println("\nMENU\n1. Nuova ricerca\n2. Torna al menu principale\n");
			if(i==2)
				System.out.println("3. Seleziona nuovo utente\n");
			final int scelta = sc.nextInt();
			return scelta;	
		}

		private void avvia_visualizza_prenotazioni(utente ut) throws IOException {
			System.out.println("\nAvvia visualizza prenotazioni\n");
			ui_pren.visualizza_prenotaz_stud(ut.matricola, "Staff");
			return;
		}

		private Object cambia_stato_account(utente ut) {
			// TODO Auto-generated method stub
			return null;
		}

		private void visualizza_elenco(ArrayList<utente> u) {
			//RF07: ricerca utente
	    	//autori: Malavasi - Torta
			int s=u.size();
	    	for(int i=0;i<s;i++) {
				System.out.println(i + "):\n" + u.get(i).matricola + " " + u.get(i).cognome + " " + u.get(i).nome);
			}
		}

		private ArrayList<utente> form_ricerca() {
			//RF07: ricerca utente
	    		//autori: Malavasi - Torta
			int mat=0;
			String matricola = "";
			String cognome = "";
			Scanner sc = new Scanner(System.in);
			System.out.println("\nInserisci matricola: ");
			matricola = sc.nextLine();
			if(!matricola.equals(""))
				mat=Integer.parseInt(matricola);
			System.out.println("\nInserisci cognome: ");
			cognome = sc.nextLine();
			System.out.println("\nAvvio ricerca utente\n");
			ArrayList<utente> u = g_ric.verifica_parametri(mat, cognome);
			return u;
		}

		private void visualizza_errore(int i) /*throws IOException*/ {
			//RF07: ricerca utente
	    	//autori: Malavasi - Torta
	    	String conferma;
	    	Scanner sc = new Scanner(System.in);
			if(i==1)
				System.out.println("\nERRORE: formato parametri errato");
			if(i==2)
				System.out.println("\nERRORE: utente non trovato");
			System.out.println("premi INVIO per confermare");
			conferma = sc.nextLine();
	    }
}
