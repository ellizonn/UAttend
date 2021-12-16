package main.java;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DB_avvisi
{ 
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ArrayList<avviso> carica_avvisi()
    {
        // autore: Codetta
        // metodo condiviso

        ArrayList<avviso> elenco;
        avviso a;
        Scanner sc;

        // CARICA TUTTO IL FILE
        elenco=new ArrayList<avviso>();		
        try
        {
            sc = new Scanner(new File("dati/avvisi.txt"));
            while ( (sc.hasNext()) )
            {
                a = new avviso();
                a.testo = sc.next();
                a.emissione = LocalDate.parse(sc.next(), formatter);
                a.scadenza = LocalDate.parse(sc.next(), formatter);
                
                elenco.add(a);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file avvisi");
        }

        return elenco;
    }



    public void salva_avvisi(ArrayList<avviso> elenco)
    {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        avviso a;

        L=elenco.size();
        
        // SALVA TUTTO IL FILE
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/avvisi.txt"), false);
            for (i=0; i<L; i++)
            {
                a=(avviso)elenco.get(i);
                fw.write(a.testo + "\t" + a.emissione.format(formatter) + "\t" + a.scadenza.format(formatter) + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file avvisi");
        }

    }



    public void aggiungi_avviso(avviso a) 
    {
        // autore: Codetta
        // metodo condiviso
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/avvisi.txt"), true);
            fw.write(a.testo + "\t" + a.emissione.format(formatter) + "\t" + a.scadenza.format(formatter) + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file avvisi");
        }
    }
    
    public ArrayList<avviso> cerca_avvisi(){
	  	
        // autore: Beccuti/Iabichino

    	ArrayList<avviso> elenco;
    	
    	ArrayList<avviso> a = new ArrayList<avviso>();
    	
    	int i, L;
    	
    	elenco = carica_avvisi();
    	
    	L = elenco.size();
    	
    	for(i=0; i<L; i++) {
    		
    		if(elenco.get(i).scadenza.isAfter(LocalDate.now())|| elenco.get(i).scadenza.equals(LocalDate.now())) {
    			
    			a.add((avviso)elenco.get(i));
    			
    		}
    		
    	}
    	    	
    	return a;
    	
    }

}
