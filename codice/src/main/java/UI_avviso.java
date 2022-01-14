import java.io.FilterInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_avviso
{   
    public gestore_avvisi g_avv;
    private avviso avv;

    public UI_avviso(gestore_avvisi g1)
    {	
	//autore: Codetta
	g_avv=g1;
        avv = null;
    }
    
    public void avvio_scrittura_avviso() {
        //autore: Furnari/Gattico RF16

        Scanner scanner = new Scanner(System.in);
        avv = new avviso();
        String check;

        do {
            avv.emissione = LocalDate.now();

            mostra_form_scrittura_avviso();

            mostra_form_inserimento_scadenza();

            System.out.print("Salvataggio avviso (s/n)? ");

            if (new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine())) {
                check = g_avv.controlla_avviso(avv);

                if (check == null) {

                    System.out.print("Conferma salvataggio avviso (s/n)? ");
                    if (new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine())) {
                        g_avv.salva_avviso(avv);
                        System.out.println("Premi INVIO per continuare.");
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.print("Riscrivere avviso (s/n)? ");
                        if (new ArrayList<>(Arrays.asList("n", "N")).contains(scanner.nextLine())) {
                            mostra_avviso_non_salvato();
                            System.out.println("Premi INVIO per continuare.");
                            scanner.nextLine();
                            break;
                        }
                    }
                } else if (check.equals("testo")) {
                    visualizza_errore_testo();
                    System.out.println("Premi INVIO per continuare.");
                    scanner.nextLine();

                    System.out.print("Riscrivere avviso (s/n)? ");
                    if (new ArrayList<>(Arrays.asList("n", "N")).contains(scanner.nextLine())) {
                        mostra_avviso_non_salvato();
                        System.out.println("Premi INVIO per continuare.");
                        scanner.nextLine();
                        break;
                    }
                } else if (check.equals("data")){
                    visualizza_errore_data();
                    System.out.println("Premi INVIO per continuare.");
                    scanner.nextLine();

                    System.out.print("Riscrivere avviso (s/n)? ");
                    if (new ArrayList<>(Arrays.asList("n", "N")).contains(scanner.nextLine())) {
                        mostra_avviso_non_salvato();
                        System.out.println("Premi INVIO per continuare.");
                        scanner.nextLine();
                        break;
                    }
                }
            } else {
                System.out.print("Riscrivere avviso (s/n)? ");
                if (new ArrayList<>(Arrays.asList("n", "N")).contains(scanner.nextLine())) {
                    mostra_avviso_non_salvato();
                    System.out.println("Premi INVIO per continuare.");
                    scanner.nextLine();
                    break;
                }
            }
        } while (true);

    }

    private void mostra_form_inserimento_scadenza() {
        //autore: Furnari/Gattico RF16

        Scanner scanner = new Scanner(System.in);
        boolean formato;

        System.out.print("Inserire data (gg/MM/aaaa): ");
        String data = scanner.nextLine();
        Pattern p = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
        Matcher m = p.matcher(data);

        formato = m.matches();

        if (formato) {
            int giorno = Integer.parseInt(m.group(1));
            int mese = Integer.parseInt(m.group(2));
            int anno = Integer.parseInt(m.group(3));

            avv.scadenza = LocalDate.of(anno, mese, giorno);
        } else {
            System.out.println("ATTENZIONE: formato data errato");
        }
    }

    private void mostra_form_scrittura_avviso() {
        //autore: Furnari/Gattico RF16

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserire il testo dell'avviso");
        avv.testo = scanner.nextLine();
        avv.testo = avv.testo.replace(' ', '_');
    }

    private void mostra_avviso_non_salvato() {
        //autore: Furnari/Gattico RF16

        System.out.println("Avviso non salvato");
    }

    public void visualizza_errore_testo() {
        //autore: Furnari/Gattico RF16

        System.out.println("ERRORE: nessuna testo inserito");
    }

    public void visualizza_errore_data() {
        //autore: Furnari/Gattico RF16

        System.out.println("ERRORE: data inserita non valida");
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
    	
    	//scan.close();
    	
    }
    
    public void visualizza_elenco(ArrayList<avviso> a) {

		// autore: Beccuti/Iabichino RF01

		/*
		Formatter fmt = new Formatter();  
		fmt.format("%1000s %15s %15s\n", "Avviso", "Emissione", "Scadenza");  
		for (avviso b: a)   
		{  
			fmt.format("%1000s %15s %15s\n", b.testo, b.emissione, b.scadenza);  
		}  
		System.out.println(fmt);  
		*/

		System.out.println("Emissione\tScadenza\tAvviso"); 
		for (avviso b: a)   
			System.out.println(b.emissione + "\t" + b.scadenza + "\t" + b.testo);		
    	
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
