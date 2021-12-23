import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DB_utenti
{ 
    public ArrayList<account> carica_accounts()
    {
        // autore: Codetta
        // metodo condiviso

        ArrayList<account> elenco;
        account a;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco=new ArrayList<account>();		
        try
        {
            sc = new Scanner(new File("dati/accounts.txt"));
            while ( (sc.hasNext()) )
            {
                a = new account();
                a.matricola = sc.nextInt();
                a.password = sc.next();
                a.attivo = sc.nextBoolean();
                elenco.add(a);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file accounts");
        }

        return elenco;
    }



    public void salva_accounts(ArrayList<account> elenco)
    {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        account a;

        L=elenco.size();
        
        // SALVA TUTTO IL FILE
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/accounts.txt"), false);
            for (i=0; i<L; i++)
            {
                a=(account)elenco.get(i);
                fw.write(a.matricola + "\t" + a.password + "\t" + a.attivo + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file accounts");
        }

    }



    public void aggiungi_account(account a) 
    {
        // autore: Codetta
        // metodo condiviso
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/accounts.txt"), true);
            fw.write(a.matricola + "\t" + a.password + "\t" + a.attivo + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file accounts");
        }
    }



//=============================================================================



    public ArrayList<utente> carica_utenti()
    {
        // autore: Codetta
        // metodo condiviso

        ArrayList<utente> elenco;
        utente u;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco=new ArrayList<utente>();		
        try
        {
            sc = new Scanner(new File("dati/utenti.txt"));
            while ( (sc.hasNext()) )
            {
                u = new utente();
                u.matricola = sc.nextInt();
                u.tipo_utente = sc.next();
                u.anno = sc.nextInt();
                u.nome = sc.next();
                u.cognome  = sc.next();
                u.residenza = new indirizzo();
                u.residenza.via = sc.next();
                u.residenza.numero = sc.nextInt();
                u.residenza.localita = sc.next();
                u.residenza.CAP = sc.nextInt();
                elenco.add(u);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file utenti");
        }

        return elenco;
    }



    public void salva_utenti(ArrayList<utente> elenco)
    {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        utente u;
	
        L=elenco.size();

        // SALVA TUTTO IL FILE
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/utenti.txt"), false);
            for (i=0; i<L; i++)
            {
                u=(utente)elenco.get(i);
                fw.write(u.matricola + "\t" + u.tipo_utente + "\t" + u.anno + "\t" + u.nome + "\t" + u.cognome + "\t" + u.residenza.via + "\t" + u.residenza.numero + "\t" + u.residenza.localita + u.residenza.CAP + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file utenti");
        }
    }



    public void aggiungi_utente(utente u) 
    {
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/utenti.txt"), true);
            fw.write(u.matricola + "\t" + u.tipo_utente + "\t" + u.anno + "\t" + u.nome + "\t" + u.cognome + "\t" + u.residenza.via + "\t" + u.residenza.numero + "\t" + u.residenza.localita + "\t" + u.residenza.CAP + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file utenti");
        }
    }


//=============================================================================


   
    public account cerca_account(int matricola, String password)
    {
        //RF00: login
        //autore: Codetta

        account acc, a;
	ArrayList elenco;
	int i, L;

	a=null;
	elenco=carica_accounts();
	L=elenco.size();

	for (i=0; i<L && a==null; i++)
	{
		acc=(account)elenco.get(i);
		if (acc.matricola==matricola && acc.password.equals(password))
                	a=acc;
	}

        return a;
    }
    
    public utente cerca_utente(int matricola)
    {
        //RF00: login
        //autore: Codetta

        utente utn, u;
	ArrayList elenco;
	int i, L;

        u=null;
	elenco=carica_utenti();
	L=elenco.size();

	for (i=0; i<L && u==null; i++)
	{
		utn=(utente)elenco.get(i);
                if (utn.matricola==matricola)
                    u=utn;
        }
        
        return u;
    }
}

