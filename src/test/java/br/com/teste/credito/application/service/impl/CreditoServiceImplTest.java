package br.com.teste.credito.application.service.impl;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.domain.entity.Credito;
import br.com.teste.credito.infraestructure.exception.NotFoundException;
import br.com.teste.credito.infraestructure.persistence.CreditoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditoServiceImplTest {

    @Mock
    private CreditoRepository repository;

    @InjectMocks
    private CreditoServiceImpl service;

    private Credito credito;
    private CreditoDto creditoDto;

    @BeforeEach
    void setUp() {
        credito = Credito.builder()
                .id(1L)
                .numeroCredito("123")
                .numeroNfse("456")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(100))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(10))
                .valorFaturado(BigDecimal.valueOf(200))
                .valorDeducao(BigDecimal.valueOf(50))
                .baseCalculo(BigDecimal.valueOf(150))
                .build();

        creditoDto = CreditoDto.builder()
                .id(1L)
                .numeroCredito("123")
                .numeroNfse("456")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(100))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(10))
                .valorFaturado(BigDecimal.valueOf(200))
                .valorDeducao(BigDecimal.valueOf(50))
                .baseCalculo(BigDecimal.valueOf(150))
                .build();
    }

    @Test
    void testBuscarPorNfseWhenRepositoryReturnsListOfCreditosThenReturnListOfCreditos() {
        when(repository.findByNumeroNfse("456")).thenReturn(List.of(credito));

        List<CreditoDto> result = service.buscarPorNfse("456");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(creditoDto, result.get(0));
    }

    @Test
    void testBuscarPorNfseWhenRepositoryReturnsEmptyListThenThrowNotFoundException() {
        when(repository.findByNumeroNfse("456")).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> service.buscarPorNfse("456"));
    }

    @Test
    void testBuscarPorNumeroCreditoWhenRepositoryReturnsCreditoThenReturnCredito() {
        when(repository.findByNumeroCredito("123")).thenReturn(Optional.of(credito));

        CreditoDto result = service.buscarPorNumeroCredito("123");

        assertNotNull(result);
        assertEquals(creditoDto, result);
    }

    @Test
    void testBuscarPorNumeroCreditoWhenRepositoryReturnsEmptyOptionalThenThrowNotFoundException() {
        when(repository.findByNumeroCredito("123")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarPorNumeroCredito("123"));
    }
}
