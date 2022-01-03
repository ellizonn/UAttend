import java.io.FilterInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI_avviso
{   
    public gestore_avvisi g_avv;

    public UI_avviso(gestore_avvisi g1)
    {	
	//autore: Codetta
	g_avv=g1;
    }
    
    public void visualizza_errore() {

		// autore: Beccuti/Iabichino RF01
    	
    	System.out.println("Non ci sono avvisi recenti.");
    	
    	System.out.println("Premi INVIO per conferma.");
    	
    	Scanner scan = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() throws IOException {
				// do nothing here ! 
			}
		});
    	
    	scan.nextLine();
    	
    	scan.close();
    	
    }
    
    public void visualizza_elenco(ArrayList<avviso> a) {

		// autore: Beccuti/Iabichino RF01

		System.out.println("\nAvviso"+"                                  "+"|data emissione |"+"data scadenza");

		System.out.println("----------------------------------------------------------------------");
    	
    	for(avviso b:a) {
    		
    		System.out.println(b.testo+ "\t|"+b.emissione+"\t|"+ b.scadenza);
    		
    	}
    	
    }

	public void avvio_avvisi(){

		// autore: Beccuti/Iabichino RF01

		ArrayList<avviso> a = g_avv.richiesta_avvisi_recenti();

		if(a.isEmpty()) {
    		
    		visualizza_errore();
    	}

		else{

			visualizza_elenco(a);

		}

	}
    
}
