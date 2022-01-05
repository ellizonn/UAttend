import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UI_ricerca
{   
    private UI_account ui_acc;
    private UI_lezione ui_lez;
    private UI_prenotazione ui_pren;
    private gestore_ricerche g_ric;

	private LocalDate data_inizio;
	private LocalDate data_fine;	
	private int scelta;
	
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
		String data_i="";
		String data_f="";
		System.out.print("\nINSERIRE LE DATE PER LA RICERCA DEGLI AVVISI\n");
        System.out.print("\ninserisci data di inizio(yyyy-mm-dd): ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		data_i = sc.nextLine();
		data_inizio = LocalDate.parse(data_i,dateFormat);
        System.out.print("inserisci data di fine(yyyy-mm-dd): ");
		data_f = sc.nextLine();
		data_fine = LocalDate.parse(data_f,dateFormat);
    }	
	
	public void visualizza_elenco_avvisi_per_data(ArrayList<avviso> a) {

		//autore: FABBRO/BRUNI RF03

		System.out.println("\nAvviso"+"                                  "+"data emissione"+"  "+"data scadenza");
    	
    	for(avviso b:a) {
    		
    		System.out.println(b.testo+ "\t"+b.emissione+"\t"+ b.scadenza);
    		
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
	
}
