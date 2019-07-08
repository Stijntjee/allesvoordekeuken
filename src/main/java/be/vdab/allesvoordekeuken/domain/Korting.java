package be.vdab.allesvoordekeuken.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Korting implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int vanafAantal;
    private BigDecimal percentage;

    //CONSTRUCTORS
    public Korting(int vanafAantal, BigDecimal percentage) {
        this.vanafAantal = vanafAantal;
        this.percentage = percentage;
    }

    protected Korting() {
    }

    //METHODS
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Korting)) {
            return false;
        }
        Korting andereKorting = (Korting) object;
        return vanafAantal == andereKorting.vanafAantal;
    }
    @Override
    public int hashCode() {
        return vanafAantal;
    }

    //GETTERS
    public int getVanafAantal() {
        return vanafAantal;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }
}
