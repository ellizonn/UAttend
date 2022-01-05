public class aula
{
    public int numero;
    public int capienza;

    //autore: RF06 Rosilde Garavoglia, Roberto Aitchison
    @Override
    public boolean equals (Object o) { 
    	if (!(o instanceof aula)) return false;
    	else {
    		aula a = (aula) o;
    		if (this.numero == a.numero && this.capienza == a.capienza) return true;
    		else return false;
    	}
    }
}
