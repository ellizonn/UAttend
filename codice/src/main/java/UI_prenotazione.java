import java.util.Scanner;

public class UI_prenotazione
{   
    gestore_lezioni g_lez;

    public UI_prenotazione(gestore_lezioni g1)
    {	
	//autore: Codetta
	g_lez=g1;
    }
	
	
	// =======================================================================
  
  
	public void mostra_errore(String tipo_err){
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		if(tipo_err.equals("err_data"))
			System.out.println("Errore: la data della lezione e' antecedente alla data odierna!");
		else if(tipo_err.equals("err_posti"))
			System.out.println("Errore: i posti della lezione sono esauriti!");
		System.out.println("Premi INVIO per conferma");
		Scanner sc = new Scanner(System.in);
        String enter = sc.next();
	}
	
}
