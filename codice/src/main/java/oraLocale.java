import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

import java.util.Locale;

public class oraLocale {

    private LocalTime l;

    public oraLocale() {
        l = LocalTime.now();
    }

    public static void main(String[] args) {
        oraLocale ora = new oraLocale();
        System.out.println(ora.toSTring());

    }

    public String toSTring() {
        String rit = l.format((DateTimeFormatter.ofPattern("hh:mm", Locale.ITALY)));
        return rit;
    }

    public boolean comparaOre(LocalTime oraInquisita) {
        if ((l.toSecondOfDay()/60)-(oraInquisita.toSecondOfDay()/60) < 60)
            return false;
        return true;
    }
}
