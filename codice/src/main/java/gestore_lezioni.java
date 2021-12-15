class gestore_lezioni
{
    DB_lezioni db_lez;

    public gestore_lezioni(DB_lezioni d1)
    {
      //autore: Codetta
      db_lez=d1;
    }

    /**
     * @author: Simone Garau & Filiberto Melis
     */
    public ArrayList<corso> richiesta_elenco_corsi(){
        return db_lez.carica_corsi();
    }
}
