import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

public class dataLocale {

    private LocalDate l;

    public dataLocale() {
        l = LocalDate.now();
    }

    public static void main(String[] args) {
        dataLocale d = new dataLocale();
        System.out.println(d.toString());

    } 

    public String toString() {
        String rit = l.format((DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.ITALY)));
        return rit;
    }

    public int comparaData(LocalDate lInquisita) {
        if (l.equals(lInquisita))
            return 0;
        return l.compareTo(lInquisita);
    }

}