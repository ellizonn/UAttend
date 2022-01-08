import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class RF05_aggiungi_lezione_test {

    gestore_lezioni g_lez;

    @Before
    public void setUp() {
        g_lez = new gestore_lezioni(new DB_lezioni());
    }

    @Test
    public void richiesta_elenco_corsi_01() {
        Assert.assertNotNull("L'elenco dei corsi non deve essere null", g_lez.richiesta_elenco_corsi());
    }

    @Test
    public void richiesta_elenco_corsi_02() {
        corso expected = new corso();
        expected.nome = "Programmazione_1";
        expected.anno = 1;
        expected.cognome_docente = "Codetta";
        Assert.assertEquals("Il nome del corso deve essere Programmazione_1", expected.nome, g_lez.richiesta_elenco_corsi().get(0).nome);
        Assert.assertEquals("L'anno del corso deve essere 1", expected.anno, g_lez.richiesta_elenco_corsi().get(0).anno);
        Assert.assertEquals("Il cognome del docente deve essere Codetta", expected.cognome_docente, g_lez.richiesta_elenco_corsi().get(0).cognome_docente);
    }

    @Test
    public void richiesta_elenco_corsi_03() {
        corso expected = new corso();
        expected.nome = "Ingegneria_del_Software";
        expected.anno = 3;
        expected.cognome_docente = "Codetta";
        Assert.assertEquals("Il nome del corso deve essere Ingegneria_del_Software", expected.nome, g_lez.richiesta_elenco_corsi().get(4).nome);
        Assert.assertEquals("L'anno del corso deve essere 3", expected.anno, g_lez.richiesta_elenco_corsi().get(4).anno);
        Assert.assertEquals("Il cognome del docente deve essere Codetta", expected.cognome_docente, g_lez.richiesta_elenco_corsi().get(4).cognome_docente);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void richiesta_elenco_corsi_04() {
        g_lez.richiesta_elenco_corsi().get(5);
    }

    @Test
    public void verifica_correttezza_data_01() {
        Assert.assertTrue("La data deve essere corretta", g_lez.verifica_correttezza_data(LocalDate.of(2022, 1, 12)));
    }

    @Test
    public void verifica_correttezza_data_02() {
        Assert.assertFalse("La data non deve essere corretta", g_lez.verifica_correttezza_data(null));
    }

    @Test
    public void verifica_correttezza_data_03() {
        Assert.assertFalse("La data non deve essere corretta", g_lez.verifica_correttezza_data(LocalDate.of(2020, 1, 12)));
    }

    @Test
    public void verifica_correttezza_data_04() {
        Assert.assertFalse("La data non deve essere corretta", g_lez.verifica_correttezza_data(LocalDate.of(2023, 1, 7)));
    }

    @Test
    public void verifica_correttezza_data_05() {
        Assert.assertFalse("La data non deve essere corretta", g_lez.verifica_correttezza_data(LocalDate.of(2023, 1, 8)));
    }

    @Test
    public void verifica_correttezza_data_06() {
        Assert.assertFalse("La data non deve essere corretta", g_lez.verifica_correttezza_data(LocalDate.of(2022, 8, 4)));
    }

    @Test
    public void verifica_correttezza_orario_01() {
        Assert.assertTrue("L'ora deve essere corretta", g_lez.verifica_correttezza_orario(LocalTime.of(9, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void verifica_correttezza_orario_02() {
        Assert.assertFalse("L'ora non deve essere corretta", g_lez.verifica_correttezza_orario(null, LocalTime.of(10, 0)));
    }

    @Test
    public void verifica_correttezza_orario_03() {
        Assert.assertFalse("L'ora non deve essere corretta", g_lez.verifica_correttezza_orario(LocalTime.of(9, 0), null));
    }

    @Test
    public void verifica_correttezza_orario_04() {
        Assert.assertFalse("L'ora non deve essere corretta", g_lez.verifica_correttezza_orario(LocalTime.of(11, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void verifica_correttezza_orario_05() {
        Assert.assertFalse("L'ora non deve essere corretta", g_lez.verifica_correttezza_orario(LocalTime.of(10, 0), LocalTime.of(10, 30)));
    }

    @Test
    public void verifica_correttezza_orario_06() {
        Assert.assertFalse("L'ora non deve essere corretta", g_lez.verifica_correttezza_orario(LocalTime.of(8, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void verifica_correttezza_orario_07() {
        Assert.assertFalse("L'ora non deve essere corretta", g_lez.verifica_correttezza_orario(LocalTime.of(18, 0), LocalTime.of(19, 0)));
    }

    @Test
    public void verifica_correttezza_orario_08() {
        Assert.assertFalse("L'ora non deve essere corretta", g_lez.verifica_correttezza_orario(LocalTime.of(16, 0), LocalTime.of(20, 0)));
    }
}