package be.vdab.allesvoordekeuken.domain;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;

// enkele imports
public class KortingTest {
    private Korting korting1, nogEensKorting1, korting2;
    @Before
    public void before() {
        korting1 = new Korting(1, BigDecimal.ONE);
        nogEensKorting1 = new Korting(1, BigDecimal.ONE);
        korting2 = new Korting(2, BigDecimal.TEN);
    }
    @Test
    public void kortingenMetDezelfdeVanafAantallenZijnGelijk() {
        assertThat(korting1).isEqualTo(nogEensKorting1);
    }
    @Test
    public void kortingenMetVerschillendeVanafAantallenZijnVerschillend() {
        assertThat(korting1).isNotEqualTo(korting2);
    }
    @Test
    public void eenKortingVerschiltVanNull() {
        assertThat(korting1).isNotEqualTo(null);
    }
    @Test
    public void eenKortingVerschiltVanEenAnderTypeObject() {
        assertThat(korting1).isNotEqualTo("");
    }
    @Test
    public void gelijkeKortingenGevenDezelfdeHashCode() {
        assertThat(korting1).hasSameHashCodeAs(nogEensKorting1);
    }
}