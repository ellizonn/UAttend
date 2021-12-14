class gestore_accessi
{
    DB_utenti db_ut;

    public gestore_accessi(DB_utenti d1)
    {
      //autore: Codetta
      db_ut=d1;
    }



    public String controlla_credenziali(int matricola, String password)
    {
        //RF00
        //autore: Codetta

        int L;
        String tipo_utente;
        account a;
        utente u;

        if (matricola<100000 || matricola>999999)
            tipo_utente="error1";
        else
        {
            L=password.length();
            if (L==0)
                tipo_utente="error2";
    	    else
            {
                a=db_ut.cerca_account(matricola, password);
                if (a==null)
                    tipo_utente="error3";
                else
                    if (a.attivo==false)
                        tipo_utente="error4";
                    else
                    {
                        u=db_ut.cerca_utente(matricola);
                        tipo_utente=u.tipo_utente;
                    }
            }
        }

        return tipo_utente;
    }

    public boolean controlla_scelta(int scelta, String tipo_utente)
    {
	//RF00
        //autore: Codetta

        boolean esito;
        
        if (tipo_utente.equals("staff") && scelta>=0 && scelta<=7 ||
        tipo_utente.equals("studente") && scelta>=0 && scelta<=4 ||
        tipo_utente.equals("docente") && scelta>=0 && scelta<=3 ||
        tipo_utente.equals("admin") && scelta>=0 && scelta<=4)
            esito=true;
        else
            esito=false;
        
        return esito;
    }

}

