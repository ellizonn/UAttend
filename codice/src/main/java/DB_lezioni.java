import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DB_lezioni {
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
    private UI_prenotazione ui_pren;

    public ArrayList<lezione> carica_lezioni() {
        // autore: Codetta
        // metodo condiviso

        ArrayList<lezione> elenco;
        lezione l;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco = new ArrayList<lezione>();
        try {
            sc = new Scanner(new File("dati/lezioni.txt"));
            while ((sc.hasNext())) {
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
        } catch (FileNotFoundException e) {
            System.out.println("ERRORE apertura file lezioni.txt");
        }

        return elenco;
    }

	public int ricerca_nome_corso(String nomeCorso){

		for(corso corso : this.carica_corsi())
			if(corso.nome.equals(nomeCorso)) return 1;
		
		return 0; 
	}


    public void salva_lezioni(ArrayList<lezione> elenco) {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        lezione l;

        L = elenco.size();

        // SALVA TUTTO IL FILE
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/lezioni.txt"), false);
            for (i = 0; i < L; i++) {
                l = elenco.get(i);
                fw.write(l.nome_corso + "\t" + l.cognome_docente + "\t" + l.anno + "\t" +
                        l.numero_aula + "\t" + l.posti_disponibili + "\t" +
                        l.giorno.format(formatter) + "\t" + l.ora_inizio.format(formatter2)
                        + "\t" + l.ora_fine.format(formatter2) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("ERRORE apertura file lezioni.txt");
        }

    }


    public void aggiungi_lezione(lezione l) {
        // autore: Codetta
        // metodo condiviso
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/lezioni.txt"), true);
            fw.write(l.nome_corso + "\t" + l.cognome_docente + "\t" + l.anno + "\t" +
                    l.numero_aula + "\t" + l.posti_disponibili + "\t" +
                    l.giorno.format(formatter) + "\t" + l.ora_inizio.format(formatter2) + "\t" +
                    l.ora_fine.format(formatter2) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("ERRORE apertura file lezioni.txt");
        }
    }


    /**
     * Cancella la lezione
     * @author Davide Ceci - 20033793
     * @author Luca Tamone - 20034235
     * @param l la lezione da cancellare
     */
    public void elimina_lezione(lezione l) {
        ArrayList<lezione> elenco_lezioni = carica_lezioni();
        int eliminare = -1;

        for(int i = 0; i < elenco_lezioni.size() && eliminare == -1; i++) {
            if( l.nome_corso.equals(elenco_lezioni.get(i).nome_corso) && 
                l.cognome_docente.equals(elenco_lezioni.get(i).cognome_docente) &&
                l.anno == elenco_lezioni.get(i).anno &&
                l.numero_aula == elenco_lezioni.get(i).numero_aula &&
                l.giorno.equals(elenco_lezioni.get(i).giorno) &&
                l.ora_inizio.equals(elenco_lezioni.get(i).ora_inizio) &&
                l.ora_fine.equals(elenco_lezioni.get(i).ora_fine) ) {
                    eliminare = i;
            }
        }

        if(eliminare != -1) {
            elenco_lezioni.remove(eliminare);
            salva_lezioni(elenco_lezioni);
        }
    }

// =======================================================================

    public ArrayList<aula> carica_aule() {
        // autore: Codetta
        // metodo condiviso

        ArrayList<aula> elenco;
        aula a;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco = new ArrayList<aula>();
        try {
            sc = new Scanner(new File("dati/aule.txt"));
            while ((sc.hasNext())) {
                a = new aula();
                a.numero = sc.nextInt();
                a.capienza = sc.nextInt();

                elenco.add(a);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRORE apertura file aule.txt");
        }

        return elenco;
    }


    public void salva_aule(ArrayList<aula> elenco) {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        aula a;

        L = elenco.size();

        // SALVA TUTTO IL FILE
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/aule.txt"), false);
            for (i = 0; i < L; i++) {
                a = elenco.get(i);
                fw.write(a.numero + "\t" + a.capienza + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("ERRORE apertura file aule.txt");
        }

    }


    public void aggiungi_aula(aula a) {
        // autore: Codetta
        // metodo condiviso
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/aule.txt"), true);
            fw.write(a.numero + "\t" + a.capienza + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("ERRORE apertura file aule.txt");
        }
    }

     // autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    public ArrayList<aula> restituisci_elenco_aule_occupate(LocalDate date, LocalTime startHour, LocalTime endHour) {
    	ArrayList<lezione> lezioni = this.carica_lezioni();
    	ArrayList<lezione> lezioni_corrispondenti = new ArrayList<lezione>();
    	for (lezione l: lezioni) {
    		if (l.giorno.equals(date) 
    				&& (((startHour.equals(l.ora_inizio)) || endHour.equals(l.ora_fine)) 
    				|| (endHour.isBefore(l.ora_fine) && endHour.isAfter(l.ora_inizio)) 
    				|| (startHour.isBefore(l.ora_fine) && startHour.isAfter(l.ora_inizio)) 
    				|| (l.ora_inizio.isAfter(startHour) && l.ora_fine.isBefore(endHour)))) {
    			lezioni_corrispondenti.add(l);
    		}
    			
    	}
    	
    	ArrayList<aula> tot_aule = this.carica_aule();
    	ArrayList<aula> aule_occupate = new ArrayList<aula> ();
    	for (lezione l : lezioni_corrispondenti) {
    		aula a = new aula();
    		a.numero = l.numero_aula;
    		for (aula a2 : tot_aule) {
    			if (a.numero == a2.numero) {
    				a.capienza = a2.capienza;
    				break;
    			}
    		}
    		aule_occupate.add(a);
    	}
    	return aule_occupate;
    }


// =======================================================================

    public ArrayList<corso> carica_corsi() {
        // autore: Codetta
        // metodo condiviso

        ArrayList<corso> elenco;
        corso c;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco = new ArrayList<corso>();
        try {
            sc = new Scanner(new File("dati/corsi.txt"));
            while ((sc.hasNext())) {
                c = new corso();
                c.nome = sc.next();
                c.anno = sc.nextInt();
                c.cognome_docente = sc.next();

                elenco.add(c);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRORE apertura file corsi.txt");
        }

        return elenco;
    }


    public void salva_corsi(ArrayList<corso> elenco) {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        corso c;

        L = elenco.size();

        // SALVA TUTTO IL FILE
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/corsi.txt"), false);
            for (i = 0; i < L; i++) {
                c = elenco.get(i);
                fw.write(c.nome + "\t" + c.anno + "\t" + c.cognome_docente + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file corsi");
        }

    }


    public void aggiungi_corso(corso c) {
        // autore: Codetta
        // metodo condiviso
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/corsi.txt"), true);
            fw.write(c.nome + "\t" + c.anno + "\t" + c.cognome_docente + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file corsi.txt");
        }
    }

// =======================================================================

    public ArrayList<prenotazione> carica_prenotazioni() {
        // autore: Codetta
        // metodo condiviso

        ArrayList<prenotazione> elenco;
        prenotazione p;
        Scanner sc;
        int i;

        // CARICA TUTTO IL FILE
        elenco = new ArrayList<prenotazione>();
        try {
            sc = new Scanner(new File("dati/prenotazioni.txt"));
            while ((sc.hasNext())) {
                p = new prenotazione();
                p.matricola_studente = sc.nextInt();
                p.nome_corso = sc.next();
                p.cognome_docente = sc.next();
				p.aula = sc.nextInt();
                p.giorno = LocalDate.parse(sc.next(), formatter);
                p.ora_inizio = LocalTime.parse(sc.next(), formatter2);
				p.ora_fine = LocalTime.parse(sc.next(), formatter2);
                p.presente = sc.next();

                elenco.add(p);
            }
            sc.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("ERRORE apertura file prenotazioni.txt");
        }

        return elenco;
    }


    public void salva_prenotazioni(ArrayList<prenotazione> elenco) {
        // autore: Codetta
        // metodo condiviso

        int i, L;
        prenotazione p;

        L = elenco.size();

        // SALVA TUTTO IL FILE
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/prenotazioni.txt"), false);
            for (i=0; i<L; i++)
            {
                p=(prenotazione)elenco.get(i);
                fw.write(p.matricola_studente + "\t" + p.nome_corso + "\t" + p.cognome_docente + "\t" + p.aula + "\t" + p.giorno.format(formatter) + "\t" + p.ora_inizio.format(formatter2) + "\t" + p.ora_fine.format(formatter2) + "\t" + p.presente + "\n");
            }
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file prenotazioni");
        }

    }


    public void aggiungi_prenotazione(prenotazione p) {
        // autore: Codetta
        // metodo condiviso
        try {
            // false per modalita' write, true per modalita' append
            FileWriter fw = new FileWriter(new File("dati/prenotazioni.txt"), true);
            fw.write(p.matricola_studente + "\t" + p.nome_corso + "\t" + p.cognome_docente + "\t" + p.aula + "\t" + p.giorno.format(formatter) + "\t" + p.ora_inizio.format(formatter2) + "\t" + p.ora_fine.format(formatter2) + "\t" + p.presente + "\n");
            fw.close();
        }
        catch (IOException e) 
        {
            System.out.println("ERRORE apertura file prenotazioni.txt");
        }
    }

   

    // ----------------------------------------------------------------------------

    public ArrayList<lezione> cerca_lezioni(LocalDate data_inizio, LocalDate data_fine) {

        // autore: Masino, Spina
        // RF12 - Ricerca lezioni per data

        ArrayList<lezione> elenco_lezioni_in;
        ArrayList<lezione> elenco_lezioni = new ArrayList<>();
        elenco_lezioni_in = carica_lezioni();
        lezione lez = null;

        for(lezione l : elenco_lezioni_in) {
            lez = l;
            if(((lez.giorno.isAfter(data_inizio) && lez.giorno.isBefore(data_fine)) || lez.giorno.equals(data_inizio) || lez.giorno.equals(data_fine)) && (lez.giorno.isEqual(LocalDate.now()) || lez.giorno.isAfter(LocalDate.now()))) {
                elenco_lezioni.add(lez);
            }
        }

        return elenco_lezioni;


    }
  
  
  // =======================================================================
  
    /**
     * Aggiorna entry nel db secondo updated_lez
     * @author RF13 prenota_posto
     * @author Paolo Rossari 20034882
     * @author Elia Marisio 20036782
     * @param updated_lez oggetto lezione con posti disponibili decrementati
     */
    public void modifica_lezione(lezione updated_lez){
		
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

    /**
     * Cerca se la prenotazione obj_preno e' gia' presente sul db (l'utente si e' gia' prenotato)
     * @author RF13 prenota_posto
     * @author Paolo Rossari 20034882
     * @author Elia Marisio 20036782
     * @param obj_preno oggetto prenotazione
     * @return l'oggetto cercato (prenotazione) se presente, altrimenti null
     */
    public prenotazione cerca_prenotazione(prenotazione obj_preno) {
        
        prenotazione obj_cercato = null, preno_ricerca = null;
        ArrayList elenco = carica_prenotazioni();

        for (int i=0; i<elenco.size() && obj_cercato==null; i++)
        {
            preno_ricerca = (prenotazione)elenco.get(i);
            if (preno_ricerca.matricola_studente == obj_preno.matricola_studente && preno_ricerca.nome_corso.equals(obj_preno.nome_corso) && preno_ricerca.aula == obj_preno.aula && preno_ricerca.giorno.compareTo(obj_preno.giorno)==0 && preno_ricerca.ora_inizio.compareTo(obj_preno.ora_inizio)==0)
            {
                obj_cercato = preno_ricerca;
            }

        }

        return obj_cercato;	
    }

    /**
     * RF11 Registra_presenza
     * Aggiorna il campo "presente" della prenotazione.
     * @author Almasio, Borova
     * @param p : prenotazione
     * @param scelta_opzione : scelta dell'utente
     */
    public void inserisci_scelta(prenotazione p, String scelta_opzione){
        ArrayList<prenotazione> pren = carica_prenotazioni();
        int len = pren.size();
        int index = 0;
        while(index<len){
            if(pren.get(index).matricola_studente == p.matricola_studente &&
                    pren.get(index).giorno.equals(p.giorno) &&
                    pren.get(index).ora_inizio.equals(p.ora_inizio)){
                pren.get(index).presente = p.presente;
                break;
            }
        }
        salva_prenotazioni(pren);
        ui_pren.mostra_messaggio_conferma(scelta_opzione);
    }

}



