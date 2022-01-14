class gestore_accessi
{
    private DB_utenti db_ut;

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
    
    public String controlla_credenziali(int matricola, String current_psw, String new_psw, String confirm_new_psw)
    {
        //RF02
        //autore: Miglio -Mazzarino

    	String tipo_esito;
        account a;

        int l_current = current_psw.length();
        int l_new = new_psw.length();
        int l_confirm_new = confirm_new_psw.length();
        
        //verifica che i campi non siano vuoti
        if (l_current == 0 || l_new == 0 || l_confirm_new == 0)
        	tipo_esito = "error1";
        else
        {
          //verifica che la password inserita sia corretta
          a = db_ut.cerca_account(matricola, current_psw);
          if (a == null)
        	  tipo_esito = "error2";
          
          //verifica che la nuova password sia diversa da quella attuale 
          else if (current_psw.equals(new_psw))
        	  tipo_esito = "error3";
          
          //verifica che la nuova password rispetti i requisiti ----        
          else if (l_new < 8)
        	  tipo_esito = "error4";
          
          else
          {
        	  char[] temp = new_psw.toCharArray();
      		  boolean spc,num,low,upp;
      	
      		  spc = false;
      		  num = false;
      		  low = false;
      		  upp = false;

      		  char[] special = {'.',',','#','@','$','%','%'};
      		
      		  for (int i = 0; i < special.length; i++)
      		  {
      			for(int j = 0; j < l_new; j++)
      				if(temp[j] == special[i])
      					spc = true;
      		  }  
      	
      		  for(int j = 0; j < l_new; j++)
      			{
      			 if(Character.isDigit(temp[j]))
      				 num = true;
      			 if(Character.isLowerCase(temp[j]))
      				 low = true;
      			 if(Character.isUpperCase(temp[j]))
      				 upp = true;
      			}
      		  
      		  if(spc == false || num == false || low == false || upp == false)
      			  tipo_esito = "error4";
      		
      		 //-----
      		  
      		 //verifica che la nuova password sia stata confermata
      		  else if (!new_psw.equals(confirm_new_psw))
        	    tipo_esito = "error5";
      		  else
      		  {
      			  //se le password inserite rispettano i controlli viene invocato il metodo per modificare la password
      			  tipo_esito = "ok";
      			  db_ut.modifica_account(matricola, new_psw);
      		  }
      	  }
        	   
        }

        return tipo_esito;
    }
    

    public boolean generazione_credenziali()
    {
    	//RF17: crea utente
    	//autori: La Spisa & Frasson
    	int M=db_ut.ricerca_ultima_matricola();
    	M=M+1;
    	String password="miapassword";
    	account A=new account();
    	A.matricola=M;
    	A.password=password;
        A.attivo=true;
    	db_ut.aggiungi_account(A);
    	boolean esito=true;
    	return esito;
    }

}

