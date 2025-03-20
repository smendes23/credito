package br.com.teste.credito.domain.entity;

import br.com.teste.credito.annotations.SqlSetUp;
import br.com.teste.credito.annotations.SqlTearDown;
import br.com.teste.credito.infraestructure.persistence.CreditoRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class CreditoRepositoryTest {

    @Autowired
    private CreditoRepository creditoRepository;

    @Test
    @SqlSetUp("/db/scripts/insert-creditos.sql")
    @SqlTearDown("/db/scripts/remove-creditos.sql")
    void consultarPorNfse() {
        List<Credito> foundCredito = creditoRepository.findByNumeroNfse("7891011");

        assertThat(foundCredito.stream().findFirst().get().getNumeroCredito()).isEqualTo("123456");
        assertThat(foundCredito.stream().findFirst().get().getNumeroNfse()).isEqualTo("7891011");
        assertThat(foundCredito.stream().findFirst().get().getTipoCredito()).isEqualTo("ISSQN");

        assertThat(foundCredito.get(1).getNumeroCredito()).isEqualTo("789012");
        assertThat(foundCredito.get(1).getNumeroNfse()).isEqualTo("7891011");
        assertThat(foundCredito.get(1).getTipoCredito()).isEqualTo("ISSQN");
    }

    @Test
    void consultarPorNumeroCredito() {
        Optional<Credito> foundCredito = creditoRepository.findByNumeroCredito("123456");

        assertThat(foundCredito.stream().findFirst().get().getNumeroCredito()).isEqualTo("123456");
        assertThat(foundCredito.stream().findFirst().get().getNumeroNfse()).isEqualTo("7891011");
        assertThat(foundCredito.stream().findFirst().get().getTipoCredito()).isEqualTo("ISSQN");
    }
}