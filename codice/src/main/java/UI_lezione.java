import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_lezione {
    UI_avviso ui_avv;
    gestore_lezioni g_lez;

    String nome_corso;
    String cognome_docente;
    int anno;
    int numero_aula;
    LocalDate giorno;
    LocalTime ora_inizio;
    LocalTime ora_fine;

    public UI_lezione(UI_avviso ui1, gestore_lezioni g1) {
        //autore: Codetta
        ui_avv = ui1;
        g_lez = g1;
    }

    /**
     *
     */
    public void visualizza_elenco_corsi() {
        // autori: Simone Garau, Filiberto Melis
        ArrayList<corso> elencoCorsi = g_lez.richiesta_elenco_corsi();

        if (elencoCorsi != null) {
            System.out.println("Selezionare una lezione");
            for (int i = 0; i < elencoCorsi.size(); i++) {
                String nome = elencoCorsi.get(i).nome;
                String cognome_docente = elencoCorsi.get(i).cognome_docente;
                int anno = elencoCorsi.get(i).anno;
                System.out.println("Lezione " + (i+1) + ": " + nome + " " + anno + " " + cognome_docente);
            }
        }
    }

    /**
     *
     */
    public void form_data() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        String conferma = "annulla";
        boolean formato;

        do {
            System.out.print("Inserire data (gg/MM/aaaa): ");
            String data = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
            Matcher m = p.matcher(data);

            if (formato = m.matches()) {
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
                conferma = scanner.nextLine();

                if (conferma.equals("s") | conferma.equals("S"))
                    conferma = "conferma";
                else conferma = "annulla";
            }
        } while (!formato || conferma.equals("annulla"));
    }

    /**
     *
     */
    public void form_orario() {
        // autori: Simone Garau, Filiberto Melis
        Scanner scanner = new Scanner(System.in);
        String conferma = "annulla";
        boolean formato;

        do {
            System.out.print("Inserire orario di inizio e di fine lezione (h24) (HH:mm-HH:mm): ");
            String orario = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2}):(\\d{2})-(\\d{2}):(\\d{2})");
            Matcher m = p.matcher(orario);

            if (formato = m.matches()) {
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
                conferma = scanner.nextLine();

                if (conferma.equals("s") | conferma.equals("S"))
                    conferma = "conferma";
                else conferma = "annulla";
            }
        } while (!formato || conferma.equals("annulla"));
    }

    /**
     *
     * @param data data di cui mostrare l'errore
     */
    public void mostra_errore_data(LocalDate data) {
        // autori: Simone Garau, Filiberto Melis
        if (data == null)
            System.out.println("ERRORE: nessuna data inserita");
        else {
            if (data.isBefore(LocalDate.now()))
                System.out.println("ERRORE: la data deve essere successiva alla data odierna");
            if (data.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                System.out.println("ERRORE: non ci possono essere lezioni il sabato");
            if (data.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                System.out.println("ERRORE: non ci possono essere lezioni la domenica");
            if (data.getMonth().equals(Month.AUGUST))
                System.out.println("ERRORE: non ci possono essere lezioni ad agosto");
        }
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    /**
     *
     * @param ora_inizio orario d'inizio lezione di cui mostrare l'errore
     * @param ora_fine orario di fine lezione di cui mostrare l'errore
     */
    public void mostra_errore_orario(LocalTime ora_inizio, LocalTime ora_fine) {
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

    /**
     *
     */
    public void mostra_errore_aula() {
        // autori: Simone Garau, Filiberto Melis
        System.out.println("ERRORE: nessuna aula disponibile");
        System.out.print("Premi INVIO per conferma");
        new Scanner(System.in).nextLine();
    }

    /**
     *
     */
    public void avvio_aggiungi_lezione() {
        this.visualizza_elenco_corsi();
        this.form_data();
        this.form_orario();

        if (!g_lez.verifica_correttezza_data(this.giorno))
            this.mostra_errore_data(this.giorno);
        if (!g_lez.verifica_correttezza_orario(this.ora_inizio, this.ora_fine))
            this.mostra_errore_orario(this.ora_inizio, this.ora_fine);
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
