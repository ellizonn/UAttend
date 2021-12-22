import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
        //Masino, Spina
        //RF12 - Ricerca lezioni per data

        boolean esito;
        ArrayList<lezione> elenco_lezioni;
        lezione lez;
        do {
            this.form_ricerca();
            if(data_inizio == null || data_fine == null) {
                break;
            }
            else {
                do {
                    this.mostra_menu(tipo_utente);
                    elenco_lezioni = g_ric.controllo_data(data_inizio,data_fine);
                    esito=g_ric.controlla_scelta(scelta, tipo_utente);
                    lez = mostra_elenco_lezioni(elenco_lezioni);
                    if (esito==false)
                        mostra_errore(elenco_lezioni);
                    else if(lez != null) {
                        if(scelta==1 && !tipo_utente.equals("studente")) {
                            // da sostituire con la chiamata del metodo di AVVIO
                            System.out.println("\nAVVIO visualizza prenotazioni");
                        }
                        if(scelta == 2 && tipo_utente.equals("studente")) {
                            // da sostituire con la chiamata del metodo di AVVIO
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

        //Spina, Masino
        //RF12 - ricerca lezioni per data

        sc = new Scanner(System.in);
        System.out.print("\nInserisci data inizio: ");
        data_inizio = sc.nextLine();
        System.out.print("Inserisci data fine: ");
        data_fine = sc.nextLine();
    }

    public void mostra_errore(ArrayList<lezione> elenco_lezioni) {

        //Masino, Spina
        //RF12 - Ricerca lezioni per data

        String conferma;
        sc = new Scanner(System.in);
        if(elenco_lezioni == null) {
            System.out.println("\nERRORE: data passata o formato date errato.");
        }
        else if(elenco_lezioni.size() == 0) {
            System.out.println("ERRORE: nessuna lezione nel periodo selezionato.");
        }

        System.out.print("premi INVIO per confermare");
        conferma = sc.nextLine();
    }

    public lezione mostra_elenco_lezioni(ArrayList<lezione> elenco_lezioni) {

        //Masino, Spina
        //RF12 - Ricerca lezioni per data

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
        System.out.println("Selezionare lezione: ");
        sel_lez = sc.nextInt();
        i = 1;
        if(sel_lez != 0) {
            for(lezione lez : elenco_lezioni) {
                if(i==sel_lez) {
                    lezione = lez;
                }
                i++;
            }
        }
        return lezione;
    }

    public void mostra_menu(String tipo_utente)
    {
        //RF00
        //autore: Codetta

        Scanner sc = new Scanner(System.in);

        System.out.println("\ntipo utente: " + tipo_utente);


        if (tipo_utente.equals("docente"))
            System.out.println("3. Visualizza prenotazioni");
        if (tipo_utente.equals("studente") )
            System.out.println("3. Prenota posto\n");
        if (tipo_utente.equals("staff") )
            System.out.println("3. Cancella lezione\n4. Modifica lezione");

        System.out.print("\ninserire scelta: ");
        scelta = sc.nextInt();
    }
}
