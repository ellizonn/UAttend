import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class UI_ricerca
{   
    UI_account ui_acc;
    UI_lezione ui_lez;
    UI_prenotazione ui_pren;
    private String data_inizio;
    private String data_fine;
    gestore_ricerche g_ric;
    private int scelta;
    private Scanner sc;

    public UI_ricerca(UI_account ui1, UI_lezione ui2, UI_prenotazione ui3, gestore_ricerche g1)
    {	
	//autore: Codetta
	ui_acc=ui1;
	ui_lez=ui2;
	ui_pren=ui3;

	g_ric=g1;
    }

    public void avvio_ricerca_lezioni(String tipo_utente,int matricola) {
        //RF12 - Ricerca lezioni per data
        //autore: Masino, Spina

        boolean esito;
        ArrayList<lezione> elenco_lezioni;
        lezione lez;
        do {
            this.form_ricerca();
            elenco_lezioni = g_ric.controllo_data(data_inizio,data_fine);
            if(elenco_lezioni == null) {
                mostra_errore(elenco_lezioni);
            }
            else {
                lez = mostra_elenco_lezioni(elenco_lezioni);
                do {

                    if(lez != null) {
                        this.mostra_menu(tipo_utente);
                        esito = g_ric.controlla_scelta(scelta, tipo_utente);

                        if (esito==false)
                            mostra_errore(elenco_lezioni);

                        if(scelta==1 && !tipo_utente.equals("studente")) {
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO visualizza prenotazioni");
                        }
                        if(scelta == 2 && tipo_utente.equals("studente")) {
                            //ui_pren.avvio_prenota_posto(lez,matricola);
                            System.out.println("\nAVVIO prenota posto");
                        }
                        if(scelta == 3 && tipo_utente.equals("staff")) {
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO cancella lezione");
                        }
                        if(scelta == 4 && tipo_utente.equals("staff")) {
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO modifica lezione");
                        }
                    }
                }
                while(scelta != 0);
            }
        }
        while(scelta == 0 || elenco_lezioni == null);
    }

    public void form_ricerca() {

        //RF12 - Ricerca lezioni per data
        //autore: Masino, Spina

        sc = new Scanner(System.in);
        System.out.print("\nInserisci data inizio: ");
        data_inizio = sc.nextLine();
        System.out.print("Inserisci data fine: ");
        data_fine = sc.nextLine();
    }

    public void mostra_errore(ArrayList<lezione> elenco_lezioni) {

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

        if(elenco_lezioni.size() != 0) {
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
        }
        else {
            System.out.println("Nessuna lezione nel periodo selezionato.");
            System.out.println("\n1. Ricerca altre lezioni");
            System.out.println("2. Esci");
            int sel_menu;
            do {
                sc = new Scanner(System.in);
                sel_menu = sc.nextInt();
                if(sel_menu == 1) {
                    return null;
                }
                else {
                    exit(0);
                }
            }
            while(sel_menu < 1 && sel_menu > 2);
        }

        int sel_lez;
        sc = new Scanner(System.in);
        i = 1;

        do {
            System.out.println("Selezionare lezione o 0 per uscire: ");
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
                exit(0);
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
            System.out.println("0. Menù principale\n1. Visualizza prenotazioni");
        if (tipo_utente.equals("studente") )
            System.out.println("0. Menù principale\n2. Prenota posto\n");
        if (tipo_utente.equals("staff") )
            System.out.println("0. Menù principale\n1. Visualizza prenotazioni\n3. Cancella lezione\n4. Modifica lezione");

        System.out.print("\ninserire scelta: ");
        scelta = sc.nextInt();
    }
}
