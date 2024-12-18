import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class metodi_di_esempio
{

    public static ArrayList<account> carica_account()
    {
	ArrayList<account> elenco;
	account a;
	Scanner sc;
	int i;

	// CARICA TUTTO IL FILE
	elenco=new ArrayList<account>();		
	try
	{
	    sc = new Scanner(new File("accounts.txt"));
	    do
	    {
	        a = new account();
		a.username = sc.next();
		a.password = sc.next();
		a.attivo = sc.nextBoolean();
		a.tipo = sc.next();
		elenco.add(a);
	    }
	    while ( (sc.hasNext()) );
	    sc.close();
	}
	catch (FileNotFoundException e) 
	{
            System.out.println("ERRORE apertura file accounts");
	}

	return elenco;
    }



    public static void salva_account(ArrayList<account> elenco)
    {
	// autore: Codetta
	// metodo condiviso

	int i, l;
	account a;

	l=elenco.size();

	// SALVA TUTTO IL FILE
	try
	{
		// false per modalita' write, true per modalita' append
		FileWriter fw = new FileWriter(new File("accounts.txt"), false);
		for (i=0; i<l; i++)
		{
			a=(account)elenco.get(i);
			fw.write(a.username + "\t" + a.password + "\t" + a.attivo + "\t" + a.tipo + "\n");
		}
		fw.close();
	}
	catch (IOException e) 
	{
            System.out.println("ERRORE apertura file accounts");
	}

    }



    public static void aggiungi_account(account a) 
    {
	try
	{
		// false per modalita' write, true per modalita' append
		FileWriter fw = new FileWriter(new File("accounts.txt"), true);
		fw.write(a.username + " " + a.password + " " + a.attivo + " " +a.tipo + "\n");
		fw.close();
	}
	catch (IOException e) 
	{
            System.out.println("ERRORE apertura file accounts");
	}
    }

    
    public static account ricerca_account(String username, String password)
    {
        account a, acc;
	ArrayList elenco;
	int i, l;
	
	a=null;
	elenco=carica_account();
	l=elenco.size();

	for (i=0; i<l && a==null; i++)
	{
		acc=(account)elenco.get(i);
		if ( acc.username.equals(username) && acc.password.equals(password) )
				a=acc;		
	}

	return a;	
    }



    public static ArrayList<account> ricerca_elenco_account(String tipo)
    {
        ArrayList<account> elenco_input;
 	ArrayList<account> elenco_output = new ArrayList();
	account a;
	int i, l;

	elenco_input=carica_account();
	l=elenco_input.size();

	for (i=0; i<l; i++)
	{
		a=(account)elenco_input.get(i);
		if (a.tipo.equals(tipo))
		    elenco_output.add(a);
	}

	return elenco_output;	
    }



    public static void modifica_account(String username)
    {
        ArrayList<account> elenco;
	account a;
	int i, l;
	boolean trovato;

	// CARICA TUTTO IL FILE
	elenco=carica_account();		
	trovato=false;
	l=elenco.size();

	for (i=0; i<l && trovato==false; i++)
	{
	    a=(account)elenco.get(i);
	    if (a.username.equals(username))
	    {
		a.attivo=false;
	    	elenco.set(i, a);
		trovato=true;
	    }
	} 
	
	salva_account(elenco);
    }


    public static void rimuovi_account(String username)
    {
        ArrayList<account> elenco;
	account a;
	int i, l;
	boolean trovato;

	// CARICA TUTTO IL FILE
	elenco=carica_account();		
	trovato=false;
	l=elenco.size();

	i=0;
	while (i<l && trovato==false)
	{
	    a=(account)elenco.get(i);
	    if (a.username.equals(username))
	    {
		elenco.remove(i);
		l=elenco.size();
		trovato=true;
	    }
	    else
		i++;
	} 
	
	salva_account(elenco);
    }
    



    public static void visualizza_elenco(ArrayList<account> elenco)
    {
	int i, l;
	account a;

	l=elenco.size();

	for (i=0; i<l; i++)
	{
	    a=(account)elenco.get(i);
	    System.out.println(i + " " + a.username + " " + a.tipo + " " + a.attivo);
	} 
    }



    public static void main(String[] args) 
    {

	ArrayList<account> el;

	System.out.println();
	el=carica_account();
	visualizza_elenco(el);

	// AGGIUNTA DI UN ACCOUNT
	account a = new account();
	a.username="a";
	a.password="b";
	a.attivo=true;
	a.tipo="staff";

	System.out.println();
	aggiungi_account(a);
	el=carica_account();
	visualizza_elenco(el);
	
	// MODIFICA DI UN ACCOUNT
	System.out.println();
	modifica_account("a");
	el=carica_account();
	visualizza_elenco(el);

	// RICERCA DI UN CERTO ACCOUNT
	System.out.println();
	account b;
	b=ricerca_account("a", "b");
	if (b!=null)
	    System.out.println("TROVATO " + b.tipo + " " + b.attivo);
	else
	    System.out.println("NON TROVATO");
	
	// CANCELLAZIONE DI UN ACCOUNT
	System.out.println();
	rimuovi_account(a.username);
	el=carica_account();
	visualizza_elenco(el);

	// RICERCA DI VARI ACCOUNT
	System.out.println();
	el=ricerca_elenco_account("staff");
	if (el.size() > 0)
	{
	    System.out.println("TROVATI");
	    visualizza_elenco(el);
	}
	else
	    System.out.println("NON TROVATI");	
    }

}
