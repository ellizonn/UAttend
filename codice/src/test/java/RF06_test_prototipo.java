import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * 
 * @author Rosilde Garavoglia, Roberto Aitchison
 *
 */
public class RF06_test_prototipo {

	public static void main(String[] args) {
		UI_lezione l = new UI_lezione (new UI_avviso(new gestore_avvisi(new DB_avvisi())), new gestore_lezioni(new DB_lezioni()));
		System.out.println ("Inserire ora inizio:");
		Scanner in = new Scanner (System.in);
		String s = in.nextLine();
		Integer sh = Integer.parseInt(s);
		
		System.out.println ("Inserire ora fine:");
		in = new Scanner (System.in);
		s = in.nextLine();
		Integer eh = Integer.parseInt(s);
		System.out.println ("");
		
		aula selezionata = l.avvia_seleziona_aula_libera(LocalDate.of(2021, 10 , 3), LocalTime.of(sh, 0), LocalTime.of(eh,0));
		System.out.println("Aula selezionata: " + selezionata.numero);
	}

}
