package be.vdab.allesvoordekeuken.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel {
    private static final long serialVersionUID = 1L;
    private int garantie;

    //CONSTRUCTORS
    public NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int garantie) {
        super(naam, aankoopprijs, verkoopprijs);
        this.garantie = garantie;
    }

    protected NonFoodArtikel() {
    }

    //GETTERS
    public int getGarantie() {
        return garantie;
    }
}
