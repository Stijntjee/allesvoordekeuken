package be.vdab.allesvoordekeuken.repositories;

import be.vdab.allesvoordekeuken.domain.FoodArtikel;
import be.vdab.allesvoordekeuken.domain.Korting;
import be.vdab.allesvoordekeuken.domain.NonFoodArtikel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/insertArtikel.sql")
@Import(JpaArtikelRepository.class)
public class JpaArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests
{
    @Autowired
    private JpaArtikelRepository repository;
    private static final String ARTIKELS = "artikels";
    private FoodArtikel foodArtikel;
    private NonFoodArtikel nonFoodArtikel;

    @Before
    public void before()
    {
        foodArtikel = new FoodArtikel("testfood2",BigDecimal.ONE,BigDecimal.TEN,7);
        nonFoodArtikel =
                new NonFoodArtikel("testnonfood2", BigDecimal.ONE, BigDecimal.TEN, 30);
    }

    private long idVanTestFoodArtikel() {
        return super.jdbcTemplate.queryForObject(
                "select id from artikels where naam='testfood'", Long.class);
    }
    private long idVanTestNonFoodArtikel() {
        return super.jdbcTemplate.queryForObject(
                "select id from artikels where naam='testnonfood'", Long.class);
    }

    @Test
    public void findFoodArtikelById() {
        assertThat(((FoodArtikel)
                repository.findById(idVanTestFoodArtikel()).get())
                .getHoudbaarheid()).isEqualTo(7);
    }
    @Test
    public void findNonFoodArtikelById() {
        assertThat(((NonFoodArtikel)
                repository.findById(idVanTestNonFoodArtikel()).get())
                .getGarantie()).isEqualTo(30);
    }

    @Test
    public void findOnbestaandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }

    @Test
    public void createFoodArtikel() {
        repository.create(foodArtikel);
        assertThat(super.countRowsInTableWhere(ARTIKELS,
                "id=" + foodArtikel.getId())).isOne();
    }
    @Test
    public void createNonFoodArtikel() {
        repository.create(nonFoodArtikel);
        assertThat(super.countRowsInTableWhere(ARTIKELS,
                "id=" + nonFoodArtikel.getId())).isOne();
    }

    @Test
    public void findBijNaamContains() {
        assertThat(repository.findByNaamContains("es"))
                .hasSize(super.jdbcTemplate.queryForObject(
                        "select count(*) from artikels where naam like '%es%'", Integer.class))
                .extracting(artikel -> artikel.getNaam().toLowerCase())
                .allSatisfy(naam -> assertThat(naam).contains("es"))
                .isSorted();
    }

    @Test
    public void verhoogPrijs() {
        assertThat(repository.verhoogPrijs(BigDecimal.TEN))
                .isEqualTo(super.countRowsInTable("artikels"));
    }

    @Test
    public void kortingenLezen() {
        assertThat(repository.findById(idVanTestFoodArtikel()).get().getKortingen())
                .containsOnly(new Korting(1, BigDecimal.TEN));
    }



}
