import static org.junit.Assert.*;
import java.time.LocalTime;

import org.junit.Test;

public class oraLocaleTest {

	@Test
	public void testComparaOre() {
		oraLocale DoTest = new oraLocale();
		assertTrue(DoTest.comparaOre(LocalTime.of(12, 0)));
	}

}
