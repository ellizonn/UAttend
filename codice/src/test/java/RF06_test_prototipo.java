import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 
 * @author Rosilde Garavoglia, Roberto Aitchison
 *
 */
public class RF06_test_prototipo {

	public static void main(String[] args) {
		UI_lezione l = new UI_lezione (new UI_avviso(new gestore_avvisi(new DB_avvisi())), new gestore_lezioni(new DB_lezioni()));
		aula selezionata = l.avvia_seleziona_aula_libera(LocalDate.of(2021, 10 , 3), LocalTime.of(14, 0));
		System.out.println("Aula selezionata: " + selezionata.numero);
	}

}
