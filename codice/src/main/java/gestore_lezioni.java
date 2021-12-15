class gestore_lezioni
{
    DB_lezioni db_lez;

    public gestore_lezioni(DB_lezioni d1)
    {
      //autore: Codetta
      db_lez=d1;
    }

	public String verifica_nome_corso(String nomeCorso){

		if (nomeCorso == null) return "ABORT";
		else
		if (nomeCorso == "") return "CORSO_VUOTO";
		else
		if (nomeCorso.length() < 5 ) return "CORSO_CORTO";
		else
			{
				if(db_lez.ricerca_nome_corso(nomeCorso) == 0)
					return "OK";
				else
					return "CORSO_PRESENTE";
			}
	}

	public void aggiungi_corso(String nome, int anno, String cognomeDocente)
	{
		corso c = new corso();
		
		c.nome = nome;
		c.anno = anno;
		c.cognome_docente = cognomeDocente;

		db_lez.aggiungi_corso(c);
	}
}
