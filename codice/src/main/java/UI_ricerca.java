import java.util.Scanner;

public class UI_ricerca
{   
    private UI_account ui_acc;
    private UI_lezione ui_lez;
    private UI_prenotazione ui_pren;

    private gestore_ricerche g_ric;

    public UI_ricerca(UI_account ui1, UI_lezione ui2, UI_prenotazione ui3, gestore_ricerche g1)
    {	
	//autore: Codetta
        ui_acc=ui1;
        ui_lez=ui2;
        ui_pren=ui3;

        g_ric=g1;
    }
}
