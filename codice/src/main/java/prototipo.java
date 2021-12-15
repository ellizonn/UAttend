public class prototipo
{
    public static void main(String[] args) 
    {
        DB_utenti db_ut = new DB_utenti();
        DB_avvisi db_avv = new DB_avvisi();
        DB_lezioni db_lez = new DB_lezioni();
		
        gestore_accessi g_acc = new gestore_accessi(db_ut);
        gestore_utenti g_ut = new gestore_utenti(db_ut);
        gestore_ricerche g_ric = new gestore_ricerche(db_ut, db_avv, db_lez);
        gestore_avvisi g_avv = new gestore_avvisi(db_avv);
        gestore_lezioni g_lez = new gestore_lezioni(db_lez);
       
        UI_account ui_acc = new UI_account(g_acc);	
        UI_utente ui_ut = new UI_utente(ui_acc, g_ut);
        UI_avviso ui_avv = new UI_avviso(g_avv);
        UI_lezione ui_lez = new UI_lezione(ui_avv, g_lez, g_ut);
        UI_prenotazione ui_pren = new UI_prenotazione(g_lez);
        UI_ricerca ui_ric = new UI_ricerca(ui_acc, ui_lez, ui_pren, g_ric);
	UI_login ui_log = new UI_login(ui_avv, ui_ric, ui_acc, ui_lez, ui_pren, ui_ut, g_acc);

        ui_log.avvio_login();	
    }
}
