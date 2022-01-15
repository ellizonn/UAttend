import java.util.Scanner;

public class UI_utente
{   
    private UI_account ui_acc;

    private gestore_utenti g_ut;

    public UI_utente(UI_account ui1, gestore_utenti g1)
    {	
	//autore: Codetta
	ui_acc=ui1;

        g_ut=g1;
    }

    public void form_utente()
    {
    	//RF17: crea utente
    	//autori: La Spisa & Frasson
    	boolean esito=false;
    	Scanner sc= new Scanner(System.in);
    	Scanner sc1= new Scanner(System.in);
    	Scanner sc2= new Scanner(System.in);
    	Scanner sc3= new Scanner(System.in);
    	DB_utenti db=new DB_utenti();
    	gestore_utenti ut=new gestore_utenti(db);
    	boolean esito_scelta;
    	String nome="";
    	String cognome="";
    	String tipo_utente=null;
    	indirizzo residenza=null;
    	int anno_immatricolazione=0;
    	while(esito==false)
    	{
	    	System.out.println("inserire la scelta: \n1 per inserire il nome\n2 per inserire il cognome\n3 per inserire il tipo utente\n"
	    			+ "4 per inserire la residenza\n5 per cancellare la creazione\n6 per procedere con la creazione");
	    	int scelta= sc.nextInt();
	    	esito_scelta=ut.controlla_scelta(scelta);
	    	if (esito_scelta==false)
	    	{
	    		System.out.println("error1, premere invio per continuare");
	    		sc1.nextLine();
	    	}
	    	switch(scelta)
	    	{
	    	case 1:
	    		nome=form_inserimento_nome();
	    		break;
	    	case 2:
	    		cognome=form_inserimento_cognome();
	    		break;
	    	case 3:
	    		tipo_utente=form_inserimento_tipo_utente();
	    		if (tipo_utente=="studente")
	        	{
	        		anno_immatricolazione=form_anno_immatricolazione();
	        	}
	    		break;
	    	case 4:
	    		residenza=form_inserimento_residenza();
	    		break;
	    	case 5:
	    		System.out.println("la creazione e' annullata, premere invio per continuare");
	    		sc2.nextLine();
	    		return;
	    	case 6:
	    		esito=ut.controllo_generalita(nome, cognome, residenza, tipo_utente, anno_immatricolazione);
				if(esito==false)
				{
	    		System.out.println("errore: una o più generalità non sono state inserite, premere invio per continuare");
	    		sc3.nextLine();
				}
	    		break;
	    	}
    	}
    	gestore_accessi accesso=new gestore_accessi(db);
    	esito=accesso.generazione_credenziali();
    	System.out.println("la creazione e' avvenuta con successo");
    }
    
    private String form_inserimento_nome()
    {
    	//RF17: crea utente
    	//autori: La Spisa & Frasson
    	String nome;
    	Scanner sc= new Scanner(System.in);
    	System.out.println("inserire il nome");
    	nome=sc.nextLine();
    	return nome;
    }
    
    private String form_inserimento_cognome()
    {
    	//RF17: crea utente
    	//autori: La Spisa & Frasson
    	String cognome;
    	Scanner sc= new Scanner(System.in);
    	System.out.println("inserire il cognome");
    	cognome=sc.nextLine();
    	return cognome;
    }
    
    private String form_inserimento_tipo_utente()
    {
    	//RF17: crea utente
    	//autori: La Spisa & Frasson
    	int tipo_utente;
    	String tipo=null;
    	Scanner sc= new Scanner(System.in);
    	do {
    	System.out.println("inserire il tipo utente, inserire 1 per 'studente', 2 per 'staff', 3 per 'docente' o 4 per 'admin'");
    	tipo_utente=sc.nextInt();
    	}while(tipo_utente!=1 && tipo_utente!=2 && tipo_utente!=3 && tipo_utente!=4);
    	switch(tipo_utente) {
    	case 1:
    		tipo="studente";
    		break;
    	case 2:
    		tipo="staff";
    		break;
    	case 3:
    		tipo="docente";
    		break;
    	case 4:
    		tipo="admin";
    		break;
    	}
    	return tipo;
    }
    
    private int form_anno_immatricolazione()
    {
    	//RF17: crea utente
    	//autori: La Spisa & Frasson
    	int anno;
    	Scanner sc= new Scanner(System.in);
    	System.out.println("inserire l'anno di immatricolazione");
    	anno=sc.nextInt();
    	return anno;
    }
    
    private indirizzo form_inserimento_residenza()
    {
    	//RF17: crea utente
    	//autori: La Spisa & Frasson
    	indirizzo residenza=new indirizzo();
    	Scanner sc= new Scanner(System.in);
    	System.out.println("inserire la via");
    	residenza.via=sc.next();
    	System.out.println("inserire il numero civico");
    	residenza.numero=sc.nextInt();
    	System.out.println("inserire la localita'");
    	residenza.localita=sc.next();
    	System.out.println("inserire il CAP");
    	residenza.CAP=sc.nextInt();
    	return residenza;
    }

}
