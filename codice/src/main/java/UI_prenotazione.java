import java.util.Scanner;

public class UI_prenotazione
{   	
	gestore_lezioni g_lez;

    public UI_prenotazione(gestore_lezioni g1)
    {	
	//autore: Codetta
	g_lez=g1;
    }
    
    public String form_prenotazione(lezione lez)
    {
    	//RF13_prenota_posto
    	//Autori: Rossari, Marisio
        
        Scanner sc = new Scanner(System.in);

        System.out.print("\nHai selezionato la lezione di "+lez.nome_corso+", prevista alle ore "+lez.ora_inizio+" del "+lez.giorno+".\nDigitare 'procedi' o 'indietro': ");
        return sc.nextLine();
    }
    
    public void mostra_messaggio(String messaggio)
    {
    	//RF13_prenota_posto
    	//Autori: Rossari, Marisio
    	
        Scanner sc = new Scanner(System.in);
        String conferma;
        
        System.out.println(messaggio);
        System.out.print("Digita 'conferma': ");
        conferma = sc.nextLine();
    }

}
