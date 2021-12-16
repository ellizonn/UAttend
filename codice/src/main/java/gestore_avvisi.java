package main.java;

import java.util.ArrayList;

public class gestore_avvisi
{
    public DB_avvisi db_avv;

    public gestore_avvisi(DB_avvisi d1)
    {
      //autore: Codetta
      db_avv=d1;
    }
    
    public ArrayList<avviso> richiesta_avvisi_recenti(){

      // autore: Beccuti/Iabichino
		
    	return db_avv.cerca_avvisi();
    }
}
