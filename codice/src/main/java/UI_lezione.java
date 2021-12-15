import java.util.ArrayList;
import java.util.Scanner;

public class UI_lezione
{   
    UI_avviso ui_avv;
    gestore_lezioni g_lez;

    public UI_lezione(UI_avviso ui1, gestore_lezioni g1)
    {	
	//autore: Codetta
	ui_avv=ui1;

	g_lez=g1;
    }

    /**
     * @author: Simone Garau & Filiberto Melis
     */
    public void visualizza_elenco_corsi(){
        ArrayList<corso> elencoCorsi = g_lez.richiesta_elenco_corsi();
        if(elencoCorsi != null){
            System.out.println("Selezionare una lezione");
            for(int i = 0; i < elencoCorsi.size(); i++){
                System.out.println(String.format("Lezione %d" + elencoCorsi.get(i).toString(), i+1));
            }
        }
            
    }
}
