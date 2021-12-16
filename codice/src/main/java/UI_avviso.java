package main.java;

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

		// autore: Beccuti/Iabichino
    	
    	System.out.println("Non ci sono avvisi recenti.");
    	
    	System.out.println("Premi INVIO per conferma.");
    	
    	Scanner scan = new Scanner(System.in);
    	
    	scan.nextLine();
    	
    	scan.close();
    	
    }
    
    public void visualizza_elenco() {

		// autore: Beccuti/Iabichino
    	
    	ArrayList<avviso> a = g_avv.richiesta_avvisi_recenti();
    	
    	if(a.isEmpty()) {
    		
    		visualizza_errore();
    	}
    	
    	for(avviso b:a) {
    		
    		System.out.println(b.testo+ " "+b.emissione);
    		
    	}
    	
    }
    
}
