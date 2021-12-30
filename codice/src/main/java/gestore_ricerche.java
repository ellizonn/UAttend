class gestore_ricerche
{
    private DB_utenti db_ut;
    private DB_avvisi db_avv;
    private DB_lezioni db_lez;

    public gestore_ricerche(DB_utenti d1, DB_avvisi d2, DB_lezioni d3)
    {
      //autore: Codetta
      db_ut=d1;
      db_avv=d2;
      db_lez=d3;
    }
}
