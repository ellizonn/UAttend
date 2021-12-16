import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DB_lezioni
{ 
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");

    public ArrayList<lezione> carica_lezioni()
    {
        // autore: Codetta
        // metodo condiviso

        ArrayList<lezione> elenco;
        lezione l;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco=new ArrayList<lezione>();		
        try
        {
            sc = new Scanner(new File("dati/lezioni.txt"));
            while ( (sc.hasNext()) )
            {
                l = new lezione();
                l.nome_corso = sc.next();
                l.cognome_docente = sc.next();
                l.anno = sc.nextInt();
                l.numero_aula = sc.nextInt();
                l.posti_disponibili = sc.nextInt();
                l.giorno = LocalDate.parse(sc.next(), formatter);
                l.ora_inizio = LocalTime.parse(sc.next(), formatter2);
                l.ora_fine = LocalTime.parse(sc.next(), formatter2);
                
                elenco.add(l);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file lezioni.txt");
        }

        return elenco;
    }



    public void salva_lezioni(ArrayList<lezione> elenco)
    {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        lezione l;

        L=elenco.size();
        
        // SALVA TUTTO IL FILE
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/lezioni.txt"), false);
            for (i=0; i<L; i++)
            {
                l=(lezione)elenco.get(i);
                fw.write(l.nome_corso + "\t" + l.cognome_docente + "\t" + l.anno + "\t" + 
                l.numero_aula + "\t" + l.posti_disponibili + "\t" +               
                l.giorno.format(formatter) + "\t" + l.ora_inizio.format(formatter2) 
                + "\t" + l.ora_fine.format(formatter2) + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file lezioni.txt");
        }

    }



    public void aggiungi_lezione(lezione l) 
    {
        // autore: Codetta
        // metodo condiviso
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/lezioni.txt"), true);
            fw.write(l.nome_corso + "\t" + l.cognome_docente + "\t" + l.anno + "\t" + 
            l.numero_aula + "\t" + l.posti_disponibili + "\t" +               
            l.giorno.format(formatter) + "\t" + l.ora_inizio.format(formatter2) + "\t" +
            l.ora_fine.format(formatter2) + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file lezioni.txt");
        }
    }

// =======================================================================

    public ArrayList<aula> carica_aule()
    {
        // autore: Codetta
        // metodo condiviso

        ArrayList<aula> elenco;
        aula a;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco=new ArrayList<aula>();		
        try
        {
            sc = new Scanner(new File("dati/aule.txt"));
            while ( (sc.hasNext()) )
            {
                a = new aula();
                a.numero = sc.nextInt();
                a.capienza = sc.nextInt();
                
                elenco.add(a);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file aule.txt");
        }

        return elenco;
    }



    public void salva_aule(ArrayList<aula> elenco)
    {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        aula a;

        L=elenco.size();
        
        // SALVA TUTTO IL FILE
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/aule.txt"), false);
            for (i=0; i<L; i++)
            {
                a=(aula)elenco.get(i);
                fw.write(a.numero + "\t" + a.capienza + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file aule.txt");
        }

    }



    public void aggiungi_aula(aula a) 
    {
        // autore: Codetta
        // metodo condiviso
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/aule.txt"), true);
            fw.write(a.numero + "\t" + a.capienza + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file aule.txt");
        }
    }

// =======================================================================

    public ArrayList<corso> carica_corsi()
    {
        // autore: Codetta
        // metodo condiviso

        ArrayList<corso> elenco;
        corso c;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco=new ArrayList<corso>();		
        try
        {
            sc = new Scanner(new File("dati/corsi.txt"));
            while ( (sc.hasNext()) )
            {
                c = new corso();
                c.nome = sc.next();
                c.anno = sc.nextInt();
                c.cognome_docente = sc.next();
                
                elenco.add(c);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file corsi.txt");
        }

        return elenco;
    }



    public void salva_corsi(ArrayList<corso> elenco)
    {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        corso c;

        L=elenco.size();
        
        // SALVA TUTTO IL FILE
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/corsi.txt"), false);
            for (i=0; i<L; i++)
            {
                c=(corso)elenco.get(i);
                fw.write(c.nome + "\t" + c.anno + "\t" + c.cognome_docente + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file lezioni");
        }

    }



    public void aggiungi_corso(corso c) 
    {
        // autore: Codetta
        // metodo condiviso
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/aule.txt"), true);
            fw.write(c.nome + "\t" + c.anno + "\t" + c.cognome_docente + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file aule.txt");
        }
    }
  
// =======================================================================

    public ArrayList<prenotazione> carica_prenotazioni()
    {
        // autore: Codetta
        // metodo condiviso

        ArrayList<prenotazione> elenco;
        prenotazione p;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco=new ArrayList<prenotazione>();		
        try
        {
            sc = new Scanner(new File("dati/prenotazioni.txt"));
            while ( (sc.hasNext()) )
            {
                p = new prenotazione();
                p.matricola_studente = sc.nextInt();
                p.nome_corso = sc.next();
                p.cognome_docente = sc.next();
				p.aula = sc.nextInt();
                p.giorno = LocalDate.parse(sc.next(), formatter);
                p.ora_inizio = LocalTime.parse(sc.next(), formatter2);
				p.ora_fine = LocalTime.parse(sc.next(), formatter2);
                p.presente = sc.nextBoolean();
                
                elenco.add(p);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file corsi.txt");
        }

        return elenco;
    }



    public void salva_prenotazioni(ArrayList<prenotazione> elenco)
    {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        prenotazione p;

        L=elenco.size();
        
        // SALVA TUTTO IL FILE
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/corsi.txt"), false);
            for (i=0; i<L; i++)
            {
                p=(prenotazione)elenco.get(i);
                fw.write(p.matricola_studente + "\t" + p.nome_corso + "\t" + p.cognome_docente + "\t" + p.aula + "\t" + p.giorno.format(formatter) + "\t" + p.ora_inizio.format(formatter2) + "\t" + p.ora_fine.format(formatter2) + "\t" + p.presente + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file lezioni");
        }

    }



    public void aggiungi_prenotazione(prenotazione p) 
    {
        // autore: Codetta
        // metodo condiviso
        try
        {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/aule.txt"), true);
            fw.write(p.matricola_studente + "\t" + p.nome_corso + "\t" + p.cognome_docente + "\t" + p.aula + "\t" + p.giorno.format(formatter) + "\t" + p.ora_inizio.format(formatter2) + "\t" + p.ora_fine.format(formatter2) + "\t" + p.presente + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file aule.txt");
        }
    }
  
  
  // =======================================================================
  
  
  	public void db_modifica_lezione(lezione updated_lez){
		//RF13_prenota_posto
		//Autori: Rossari, Marisio
		ArrayList<lezione> elenco = carica_lezioni();
		int L = elenco.size();
		int i = 0;
		while(i<L){
			if(	
				elenco.get(i).nome_corso.equals(updated_lez.nome_corso) &&
				elenco.get(i).anno == updated_lez.anno &&
				elenco.get(i).giorno.compareTo(updated_lez.giorno)==0 &&
				elenco.get(i).ora_inizio.compareTo(updated_lez.ora_inizio)==0 
			){
				elenco.get(i).posti_disponibili = updated_lez.posti_disponibili;
				break;
			}
			else
				i++;
		}
		salva_lezioni(elenco);
	}
}
