import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI_lezione {
    UI_avviso ui_avv;
    gestore_lezioni g_lez;
    LocalDate giorno;
    LocalTime ora_inizio;
    LocalTime ora_fine;

    public UI_lezione(UI_avviso ui1, gestore_lezioni g1) {
        //autore: Codetta
        ui_avv = ui1;
        g_lez = g1;
    }

    /**
     * @author: Simone Garau
     * @author: Filiberto Melis
     */
    public void visualizza_elenco_corsi() {
        ArrayList<corso> elencoCorsi = g_lez.richiesta_elenco_corsi();

        if (elencoCorsi != null) {
            System.out.println("Selezionare una lezione");
            for (int i = 0; i < elencoCorsi.size(); i++) {
                System.out.printf("Lezione %d" + elencoCorsi.get(i).toString() + "%n", i + 1);
            }
        }
    }

    public void form_data() {
        Scanner scanner = new Scanner(System.in);
        boolean formato = false;

        do {
            System.out.print("Inserisci data (gg/MM/aaaa): ");
            String data = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
            Matcher m = p.matcher(data);

            if (m.matches()) {
                int giorno = Integer.parseInt(m.group(1));
                int mese = Integer.parseInt(m.group(2));
                int anno = Integer.parseInt(m.group(3));
                this.giorno = LocalDate.of(anno, mese, giorno);
                formato = true;
            } else
                System.out.println("Formato data errato");
        }
        while (!formato);
    }

    public void form_orario() {
        Scanner scanner = new Scanner(System.in);
        boolean formato = false;

        do {
            System.out.print("Inserisci orario inizio - orario fine (h24) (HH:mm-HH:mm): ");
            String orario = scanner.nextLine();
            Pattern p = Pattern.compile("(\\d{2}):(\\d{2}-(\\d{2}):(\\d{2}))");
            Matcher m = p.matcher(orario);

            if (m.matches()) {
                int ora_inizio = Integer.parseInt(m.group(1));
                int minuto_inizio = Integer.parseInt(m.group(2));
                int ora_fine = Integer.parseInt(m.group(3));
                int minuto_fine = Integer.parseInt(m.group(4));
                this.ora_inizio = LocalTime.of(ora_inizio, minuto_inizio);
                this.ora_fine = LocalTime.of(ora_fine, minuto_fine);
                formato = true;
            } else
                System.out.println("Formato ora errato");
        }
        while (!formato);
    }

    public void avvio_aggiungi_lezione() {
    }
}
