import java.util.Scanner;

public class UI_account
{   

    String current_psw;
   	String new_psw;
   	String confirm_new_psw;
   	private gestore_accessi g_acc;


    public UI_account(gestore_accessi g1)
    {	
	//autore: Codetta
        g_acc=g1;
    }
    
    public void avvio_cambio_password(int matricola) 
    {
    	//RF02
    	//autore: Miglio - Mazzarino
    	
    	boolean scelta;
    	
    	scelta = form_scelta();
    	
    	if(scelta) {
    		
    		String esito = "";
    	
    		do{
    		
    			form_cambia_password();
    			esito = g_acc.controlla_credenziali(matricola,current_psw, new_psw, confirm_new_psw);
    			mostra_esito(esito);
    			scelta = form_scelta();
    			if(scelta == false) break:
 
    		}while(esito.equals("error1") || esito.equals("error2") || esito.equals("error3") || esito.equals("error4") || esito.equals("error5"));
    	}
    }
    
    public boolean form_scelta() 
    {
    	  //RF02
    	  //autore: Miglio - Mazzarino   	
    	  Scanner sc = new Scanner(System.in);
    	  
          System.out.print("\ninserire 0 per continuare la procedura di cambio password oppure 1 per tornare al menu: ");
          int scelta = sc.nextInt();
          
          if(scelta == 0)
        	  return true;
          
          return false;
    }
    
    public void form_cambia_password() 
    {
    	//RF02
    	//autore: Miglio - Mazzarino  
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.print("\ninserire password corrente: ");
        current_psw = sc.nextLine();
        System.out.print("\ninserire la nuova password: ");
        System.out.print("\n(N.B la password deve contenere un carattere maiuscolo, un carattere minuscolo, un numero,\n un carattere speciale tra '.', ',', '#', '@', '$', '%'. La Lunghezza minima e' 8 caratteri)");
        new_psw = sc.nextLine();
        System.out.print("\nconfermare la nuova password: ");
        confirm_new_psw = sc.nextLine();
    }
 
    public void mostra_esito(String esito) 
    {
    	//RF02
    	//autore: Miglio - Mazzarino
    	
    	String conferma;
        Scanner sc = new Scanner(System.in);

        if (esito.equals("error1"))
            System.out.println("\nERRORE: una o piu' campi vuoti");
        if (esito.equals("error2"))
            System.out.println("\nERRORE: la password attuale e' sbagliata");
        if (esito.equals("error3"))
            System.out.println("\nERRORE: la nuova password deve essere diversa da quella vecchia");
        if (esito.equals("error4"))
            System.out.println("\nERRORE: la nuova password non rispetta i requisiti");	
        if (esito.equals("error5"))
            System.out.println("\nERRORE: la conferma della nuova password non coincide con quella nuova");
        if (esito.equals("ok"))
            System.out.println("\nPASSWORD CAMBIATA: la password e' stata cambiata correttamente");
			
        System.out.print("premi INVIO per confermare");
        conferma = sc.nextLine();
    }
    
}

