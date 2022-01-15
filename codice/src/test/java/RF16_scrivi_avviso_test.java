import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;


public class RF16_scrivi_avviso_test {

    gestore_avvisi g_avv;

    @Before
    public void setUp() {
        g_avv = new gestore_avvisi(new DB_avvisi());
    }

    @Test
    public void controlla_avviso_testo() {
        String expected = "testo";
        avviso avv = new avviso();

        avv.testo = null;
        avv.emissione = LocalDate.now();
        avv.scadenza = LocalDate.of(2022, 12, 9);

        Assert.assertEquals(expected, g_avv.controlla_avviso(avv));
    }

    @Test
    public void controlla_avviso_data() {
        String expected = "data";
        avviso avv = new avviso();

        avv.testo = "le_lezioni_finiscono_il_15_gennaio";
        avv.emissione = LocalDate.now();
        avv.scadenza = LocalDate.of(2019, 12, 9);

        Assert.assertEquals(expected, g_avv.controlla_avviso(avv));
    }

    @Test
    public void controlla_avviso_ok() {
        avviso avv = new avviso();

        avv.testo = "le_lezioni_finiscono_il_15_gennaio";
        avv.emissione = LocalDate.now();
        avv.scadenza = LocalDate.of(2022, 12, 9);

        Assert.assertNull(g_avv.controlla_avviso(avv));
    }
}
