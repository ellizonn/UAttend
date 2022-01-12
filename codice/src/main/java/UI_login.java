import java.io.IOException;
import java.util.Scanner;

public class UI_login
{   
    private UI_avviso ui_avv;
    private UI_ricerca ui_ric;
    private UI_account ui_acc;
    private UI_lezione ui_lez;
    private UI_prenotazione ui_pren;
    private UI_utente ui_ut;

    private gestore_accessi g_acc;

    private int scelta;
    private String tipo_utente;
    private int matricola;
    private String password;
    


    public UI_login(UI_avviso ui1, UI_ricerca ui2, UI_account ui3, UI_lezione ui4, UI_prenotazione ui5, UI_utente ui6, gestore_accessi g1)
    {	
	//autore: Codetta

        ui_avv = ui1;
        ui_ric = ui2;
        ui_acc = ui3;
	ui_lez = ui4;
	ui_pren = ui5;
	ui_ut= ui6;

        g_acc = g1;   
    }



    public void avvio_login() //throws IOException
    {
        //RF00
        //autore: Codetta
	
        boolean esito;
	
        do
        {   
            this.form_login();

            // per arrestare il prototipo
            if (matricola==0 && password.equals("0"))
                break;

            tipo_utente=g_acc.controlla_credenziali(matricola, password);
            if (tipo_utente.equals("error1") || tipo_utente.equals("error2") || tipo_utente.equals("error3") || tipo_utente.equals("error4"))
                this.mostra_errore(tipo_utente);
            else
            {
                System.out.println("\nElenco avvisi recenti:");
                ui_avv.avvio_avvisi();

                do
                {	    
                    this.mostra_menu(tipo_utente);
                    esito=g_acc.controlla_scelta(scelta, tipo_utente);
                    if (esito==false)
                        mostra_errore("error5");
                    else
                    {
                        if (scelta==1) 
                        	ui_acc.avvio_cambio_password(matricola);
                        if (scelta==2)
						{
							System.out.println("\nAVVIO ricerca avvisi per data");
							ui_ric.avvio_ricerca_avvisi_per_data();
						}
                        if (scelta==3 && !tipo_utente.equals("admin")) {
                            System.out.println("\nAVVIO ricerca lezioni per data");
                            ui_ric.avvio_ricerca_lezioni(tipo_utente,matricola);
                        }
                        if (scelta==3 && !tipo_utente.equals("admin"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            //System.out.println("\nAVVIO ricerca lezioni per data");
		            ui_ric.avvio_ricerca_lezioni(tipo_utente, matricola);

                        if (scelta==3 && tipo_utente.equals("admin"))
                        {
                            System.out.println("\nAVVIO crea utente");
                            ui_ut.form_utente();
                        }
			    
                        if (scelta==4 && (tipo_utente.equals("staff") || tipo_utente.equals("admin")) )
                        {    // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO ricerca utente"); 
                        	ui_ric.avvio_ricerca_utente(tipo_utente);
                        }
                        if (scelta==4 && tipo_utente.equals("studente"))
                            System.out.println("\nAVVIO visualizza prenotazioni");
                            VisualizzaPrenotazioni.visualizzaStudente();
	
                        if (scelta==5 && tipo_utente.equals("staff")) {
                            System.out.println("\nAVVIO crea corso");
                            ui_lez.RF04_crea_corso(); /* avvio crea corso */
                        }

                        if (scelta==6 && tipo_utente.equals("staff")) {
                            System.out.println("\nAVVIO aggiungi lezione");
                            this.ui_lez.avvio_aggiungi_lezione();
                        }

                        if (scelta==7 && tipo_utente.equals("staff"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO scrivi avviso");
                    }
                }
                while (scelta != 0);
            }	
        }
        while ( scelta==0 || tipo_utente.equals("error1") || tipo_utente.equals("error2") || tipo_utente.equals("error3") || tipo_utente.equals("error4") );
    }



    public void form_login()
    {
        //RF00
        //autore: Codetta
        
        Scanner sc = new Scanner(System.in);

        System.out.print("\ninserisci matricola: ");
        matricola = sc.nextInt();
        System.out.print("inserisci password: ");
        password = sc.nextLine();
        password = sc.nextLine();
    }	



    public void mostra_errore(String tipo_errore)
    {
        //RF00
        //autore: Codetta

        String conferma;
        Scanner sc = new Scanner(System.in);

        if (tipo_errore.equals("error1"))
            System.out.println("\nERRORE: la matricola non contiene 6 cifre");
        if (tipo_errore.equals("error2"))
            System.out.println("\nERRORE: password vuota");
        if (tipo_errore.equals("error3"))
            System.out.println("\nERRORE: credenziali errate");
        if (tipo_errore.equals("error4"))
            System.out.println("\nERRORE: account bloccato");	
        if (tipo_errore.equals("error5"))
            System.out.println("\nERRORE: scelta errata");
			
        System.out.print("premi INVIO per confermare");
        conferma = sc.nextLine();
    }



    public void mostra_menu(String tipo_utente)
    {
        //RF00
        //autore: Codetta

        Scanner sc = new Scanner(System.in);

        System.out.println("\ntipo utente: " + tipo_utente);
        System.out.println("\nMENU PRINCIPALE:\n0. logout\n1. cambia password\n2. ricerca avvisi per data");

        if (tipo_utente.equals("admin") )
            System.out.println("3. crea utente\n4. ricerca utente");
        if (tipo_utente.equals("docente"))
            System.out.println("3. ricerca lezioni per data");
        if (tipo_utente.equals("studente") )
            System.out.println("3. ricerca lezioni per data\n4. visualizza prenotazioni");
        if (tipo_utente.equals("staff") )
            System.out.println("3. ricerca lezioni per data\n4. ricerca utente\n5. crea corso\n6. aggiungi lezione\n7. scrivi avviso");
	
        System.out.print("\ninserire scelta: ");
        scelta=sc.nextInt();
   }


}

