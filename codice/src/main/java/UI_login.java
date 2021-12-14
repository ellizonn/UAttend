import java.util.Scanner;

public class UI_login
{   
    UI_avviso ui_avv;
    UI_ricerca ui_ric;
    UI_account ui_acc;
    UI_lezione ui_lez;
    UI_prenotazione ui_pren;
    UI_utente ui_ut;

    gestore_accessi g_acc;

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



    public void avvio_login()
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
                // da sostituire con la chiamata del metodo di AVVIO
                System.out.println("\nAVVIO visualizza notifiche");

                do
                {	    
                    this.mostra_menu(tipo_utente);
                    esito=g_acc.controlla_scelta(scelta, tipo_utente);
                    if (esito==false)
                        mostra_errore("error5");
                    else
                    {
                        if (scelta==1)
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO cambia password");

                        if (scelta==2)
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO ricerca avvisi per data");

                        if (scelta==3 && !tipo_utente.equals("admin"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO ricerca lezioni per data");

                        if (scelta==3 && tipo_utente.equals("admin"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO crea utente");
			    
                        if (scelta==4 && tipo_utente.equals("staff") || tipo_utente.equals("admin"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO ricerca utente"); 

                        if (scelta==4 && tipo_utente.equals("studente"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO visualizza prenotazioni");
	
                        if (scelta==5 && tipo_utente.equals("staff"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO crea corso");

                        if (scelta==6 && tipo_utente.equals("staff"))
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO aggiungi lezione");

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
            System.out.println("3. ricerca lezione");
        if (tipo_utente.equals("studente") )
            System.out.println("3. ricerca lezione\n4. visualizza prenotazioni");
        if (tipo_utente.equals("staff") )
            System.out.println("3. ricerca lezione\n4. ricerca utente\n5. crea corso\n6. aggiungi lezione\n7. scrivi avviso");
	
        System.out.print("\ninserire scelta: ");
        scelta=sc.nextInt();
   }


}

