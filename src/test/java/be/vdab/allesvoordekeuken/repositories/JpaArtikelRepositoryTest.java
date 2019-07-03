package be.vdab.allesvoordekeuken.repositories;

import be.vdab.allesvoordekeuken.domain.Artikel;
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
    private Artikel artikel;

    @Before
    public void before()
    {
        artikel = new Artikel("test", BigDecimal.ONE,BigDecimal.valueOf(1.5));
    }

    private int idVanTestArtikel() {
        return super.jdbcTemplate.queryForObject("select id from artikels where naam = 'vork XXL'",Integer.class);
    }

    @Test
    public void findById()
    {
        assertThat(repository.findById(idVanTestArtikel()).get().getNaam()).isEqualTo("vork XXL");
    }

    @Test
    public void create()
    {
        repository.create(artikel);
        assertThat(artikel.getId()).isPositive();
        assertThat(super.countRowsInTableWhere(ARTIKELS, "id=" + artikel.getId())).isOne();
    }
}
