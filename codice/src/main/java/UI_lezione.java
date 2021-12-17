import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_lezione {
    UI_avviso ui_avv;
    gestore_lezioni g_lez;

    private String nome_corso;
    private String cognome_docente;
    private int anno;
    private int numero_aula;
    private int posti_disponibili;
    private LocalDate giorno;
    private LocalTime ora_inizio;
    private LocalTime ora_fine;

    public UI_lezione(UI_avviso ui1, gestore_lezioni g1) {
        //autore: Codetta
        ui_avv = ui1;
        g_lez = g1;
    }

    private void visualizza_elenco_corsi() {
        // autori: Simone Garau, Filiberto Melis
        ArrayList<corso> elencoCorsi = this.g_lez.richiesta_elenco_corsi();

        if (elencoCorsi != null) {
            System.out.println("Elenco corsi disponibili");
            for (int i = 0; i < elencoCorsi.size(); i++) {
                String nome = elencoCorsi.get(i).nome;
                String cognome_docente = elencoCorsi.get(i).cognome_docente;
                int anno = elencoCorsi.get(i).anno;
                System.out.println("Corso " + (i+1) + ": " + nome + " " + anno + " " + cognome_docente);
            }
        }
    }

    private void mostra_form_data() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        boolean conferma = false;
        boolean formato;

        do {
            System.out.print("Inserire data (gg/MM/aaaa): ");
            String data = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
            Matcher m = p.matcher(data);

            formato = m.matches();
            if (formato) {
                int giorno = Integer.parseInt(m.group(1));
                int mese = Integer.parseInt(m.group(2));
                int anno = Integer.parseInt(m.group(3));
                try {
                    this.giorno = LocalDate.of(anno, mese, giorno);
                } catch (DateTimeException e) {
                    System.out.println("ATTENZIONE: data inesistente");
                    formato = false;
                }
            } else
                System.out.println("ATTENZIONE: formato data errato");

            if (formato) {
                System.out.print("Confermi la data (s/n)? ");
                if(new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine()))
                    conferma = true;
            }
        } while (!formato || !conferma);
    }

    private void mostra_form_orario() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        boolean conferma = false;
        boolean formato;

        do {
            System.out.print("Inserire orario di inizio e di fine lezione (h24) (HH:mm-HH:mm): ");
            String orario = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2}):(\\d{2})-(\\d{2}):(\\d{2})");
            Matcher m = p.matcher(orario);

            formato = m.matches();
            if (formato) {
                int ora_inizio = Integer.parseInt(m.group(1));
                int minuto_inizio = Integer.parseInt(m.group(2));
                int ora_fine = Integer.parseInt(m.group(3));
                int minuto_fine = Integer.parseInt(m.group(4));
                try {
                    this.ora_fine = LocalTime.of(ora_fine, minuto_fine);
                    this.ora_inizio = LocalTime.of(ora_inizio, minuto_inizio);
                } catch (DateTimeException e) {
                    System.out.println("ATTENZIONE: orario inesistente");
                    formato = false;
                }
            } else
                System.out.println("ATTENZIONE: formato ora errato");

            if (formato) {
                System.out.print("Confermi l'orario (s/n)? ");
                if(new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine()))
                    conferma = true;
            }
        } while (!formato || !conferma);
    }

    private void mostra_errore_data(LocalDate data) {
        // autori: Simone Garau, Filiberto Melis
        if (data == null)
            System.out.println("ERRORE: nessuna data inserita");
        else {
            if (data.isBefore(LocalDate.now()))
                System.out.println("ERRORE: la data deve essere successiva alla data odierna");
            else {
                if (data.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                    System.out.println("ERRORE: non ci possono essere lezioni il sabato");
                if (data.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                    System.out.println("ERRORE: non ci possono essere lezioni la domenica");
                if (data.getMonth().equals(Month.AUGUST))
                    System.out.println("ERRORE: non ci possono essere lezioni ad agosto");
            }
        }
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    private void mostra_errore_orario(LocalTime ora_inizio, LocalTime ora_fine) {
        // autori: Simone Garau, Filiberto Melis
        if (ora_inizio == null || ora_fine == null) {
            if (ora_inizio == null)
                System.out.println("ERRORE: nessun orario di inizio inserito");
            else
                System.out.println("ERRORE: nessun orario di fine inserito");
        } else {
            if (ora_inizio.isAfter(ora_fine) || ora_inizio.isAfter(ora_fine.minusHours(1))) {
                if (ora_inizio.isAfter(ora_fine))
                    System.out.println("ERRORE: l'inizio della lezione deve precedere la fine");
                else
                    System.out.println("ERRORE: la lezione deve durare almeno un'ora");
            }
            if (ora_inizio.getHour() < 9)
                System.out.println("ERRORE: la lezione non deve iniziare prima delle 9");
            if (ora_inizio.getHour() > 17)
                System.out.println("ERRORE: la lezione non deve iniziare dopo le 17");
            if (ora_fine.getHour() < 10)
                System.out.println("ERRORE: la lezione non deve finire prima delle 10");
            if (ora_fine.getHour() > 18)
                System.out.println("ERRORE: la lezione non deve finire dopo le 18");
        }
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    private void mostra_errore_aula() {
        // autori: Simone Garau, Filiberto Melis
        System.out.println("ERRORE: nessuna aula disponibile");
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    private void mostra_dati_lezione_da_aggiungere() {
        // autori: Simone Garau, Filiberto Melis
        System.out.println("Dati lezione");
        System.out.println("Corso: " + this.nome_corso);
        System.out.println("Docente: " + this.cognome_docente);
        System.out.println("Anno: " + this.anno);
        System.out.println("Aula: " + this.numero_aula);
        System.out.println("Capienza: " + this.posti_disponibili);
        System.out.println("Data: " + this.giorno);
        System.out.println("Ora inizio: " + this.ora_inizio);
        System.out.println("Ora fine: " + this.ora_fine);
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    public void avvio_aggiungi_lezione() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        boolean risposta = false;

        do {
            boolean selezione = false;
            do {
                this.visualizza_elenco_corsi();
                System.out.print("Selezionare un corso indicandone il numero: ");
                try {
                    int numero_corso = Integer.parseInt(scanner.nextLine());
                    corso c = this.g_lez.richiesta_elenco_corsi().get(numero_corso-1);
                    this.nome_corso = c.nome;
                    this.anno = c.anno;
                    this.cognome_docente = c.cognome_docente;
                    selezione = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("ATTENZIONE: numero di corso non presente");
                } catch (NumberFormatException e) {
                    System.out.println("ATTENZIONE: digitare un numero");
                }
            } while (!selezione);

            boolean errore_aula;
            do {
                boolean errore_data;
                do {
                    this.mostra_form_data();
                    errore_data = !this.g_lez.verifica_correttezza_data(this.giorno);
                    if (errore_data)
                        this.mostra_errore_data(this.giorno);
                } while (errore_data);

                boolean errore_orario;
                do {
                    this.mostra_form_orario();
                    errore_orario = !this.g_lez.verifica_correttezza_orario(this.ora_inizio, this.ora_fine);
                    if (errore_orario)
                        this.mostra_errore_orario(this.ora_inizio, this.ora_fine);
                } while (errore_orario);

                // aula a = richiamo a RF06 per selezione aula libera;
                aula a = new aula(); a.capienza = 50; a.numero = 7; // aula di prova (da rimuovere con l'arrivo di RF06)
                errore_aula = (a == null);
                if (errore_aula) {
                    this.mostra_errore_aula();
                } else {
                    this.numero_aula = a.numero;
                    this.posti_disponibili = a.capienza;
                }
            } while (errore_aula);

            this.mostra_dati_lezione_da_aggiungere();
            System.out.print("Sei sicuro di voler salvare questa lezione (s/n)? ");
            if(new ArrayList<>(Arrays.asList("s", "S")).contains(scanner.nextLine()))
                risposta = true;

        } while (!risposta);

        this.g_lez.richiesta_salvataggio_lezione(this.nome_corso, this.cognome_docente,
                this.anno, this.numero_aula, this.posti_disponibili, this.giorno,
                this.ora_inizio, this.ora_fine);
    }

    public static void main(String[] args) {
        DB_avvisi da = new DB_avvisi();
        gestore_avvisi ga = new gestore_avvisi(da);
        UI_avviso ua = new UI_avviso(ga);
        DB_lezioni dl = new DB_lezioni();
        gestore_lezioni gl = new gestore_lezioni(dl);
        UI_lezione ul = new UI_lezione(ua, gl);

        ul.avvio_aggiungi_lezione();
    }
}
