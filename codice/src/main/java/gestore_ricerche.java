class gestore_ricerche
{
    DB_utenti db_ut;
    DB_avvisi db_avv;
    DB_lezioni db_lez;

    public gestore_ricerche(DB_utenti d1, DB_avvisi d2, DB_lezioni d3)
    {
      //autore: Codetta
      db_ut=d1;
      db_avv=d2;
      db_lez=d3;
    }
}
