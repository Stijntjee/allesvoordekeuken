package be.vdab.allesvoordekeuken.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
    private static final long serialVersionUID = 1L;
    private int houdbaarheid;

    //CONSTRUCTORS
    public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int houdbaarheid) {
        super(naam, aankoopprijs, verkoopprijs);
        this.houdbaarheid = houdbaarheid;
    }

    protected FoodArtikel() {
    }

    //GETTERS
    public int getHoudbaarheid() {
        return houdbaarheid;
    }
}
