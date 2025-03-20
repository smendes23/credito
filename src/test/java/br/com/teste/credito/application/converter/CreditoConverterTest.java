package br.com.teste.credito.application.converter;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.domain.entity.Credito;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CreditoConverterTest {

    @Test
    void testToDtoWhenEntityIsFullyPopulatedThenDtoIsCorrectlyConverted() {
        // Arrange
        Credito entity = Credito.builder()
                .id(1L)
                .numeroCredito("12345")
                .numeroNfse("67890")
                .dataConstituicao(LocalDate.of(2023, 10, 1))
                .valorIssqn(new BigDecimal("100.00"))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(new BigDecimal("5.00"))
                .valorFaturado(new BigDecimal("1000.00"))
                .valorDeducao(new BigDecimal("50.00"))
                .baseCalculo(new BigDecimal("950.00"))
                .build();

        // Act
        CreditoDto dto = CreditoConverter.toDto(entity);

        // Assert
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNumeroCredito(), dto.getNumeroCredito());
        assertEquals(entity.getNumeroNfse(), dto.getNumeroNfse());
        assertEquals(entity.getDataConstituicao(), dto.getDataConstituicao());
        assertEquals(entity.getValorIssqn(), dto.getValorIssqn());
        assertEquals(entity.getTipoCredito(), dto.getTipoCredito());
        assertEquals(entity.getSimplesNacional(), dto.getSimplesNacional());
        assertEquals(entity.getAliquota(), dto.getAliquota());
        assertEquals(entity.getValorFaturado(), dto.getValorFaturado());
        assertEquals(entity.getValorDeducao(), dto.getValorDeducao());
        assertEquals(entity.getBaseCalculo(), dto.getBaseCalculo());
    }

    @Test
    void testToDtoWhenEntityHasNullFieldsThenDtoIsCorrectlyConverted() {
        // Arrange
        Credito entity = Credito.builder()
                .id(null)
                .numeroCredito(null)
                .numeroNfse(null)
                .dataConstituicao(null)
                .valorIssqn(null)
                .tipoCredito(null)
                .simplesNacional(null)
                .aliquota(null)
                .valorFaturado(null)
                .valorDeducao(null)
                .baseCalculo(null)
                .build();

        // Act
        CreditoDto dto = CreditoConverter.toDto(entity);

        // Assert
        assertNull(dto.getId());
        assertNull(dto.getNumeroCredito());
        assertNull(dto.getNumeroNfse());
        assertNull(dto.getDataConstituicao());
        assertNull(dto.getValorIssqn());
        assertNull(dto.getTipoCredito());
        assertNull(dto.getSimplesNacional());
        assertNull(dto.getAliquota());
        assertNull(dto.getValorFaturado());
        assertNull(dto.getValorDeducao());
        assertNull(dto.getBaseCalculo());
    }

    @Test
    void testToDtoWhenEntityIsGivenThenDtoIsReturned() {
        // Arrange
        Credito entity = Credito.builder()
                .id(1L)
                .aliquota(new BigDecimal("0.15"))
                .baseCalculo(new BigDecimal("1000.00"))
                .numeroCredito("12345")
                .tipoCredito("Tipo1")
                .dataConstituicao(LocalDate.of(2023, 10, 1))
                .numeroNfse("NF12345")
                .simplesNacional(true)
                .valorDeducao(new BigDecimal("100.00"))
                .valorFaturado(new BigDecimal("900.00"))
                .valorIssqn(new BigDecimal("135.00"))
                .build();

        // Act
        CreditoDto dto = CreditoConverter.toDto(entity);

        // Assert
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getAliquota(), dto.getAliquota());
        assertEquals(entity.getBaseCalculo(), dto.getBaseCalculo());
        assertEquals(entity.getNumeroCredito(), dto.getNumeroCredito());
        assertEquals(entity.getTipoCredito(), dto.getTipoCredito());
        assertEquals(entity.getDataConstituicao(), dto.getDataConstituicao());
        assertEquals(entity.getNumeroNfse(), dto.getNumeroNfse());
        assertEquals(entity.getSimplesNacional(), dto.getSimplesNacional());
        assertEquals(entity.getValorDeducao(), dto.getValorDeducao());
        assertEquals(entity.getValorFaturado(), dto.getValorFaturado());
        assertEquals(entity.getValorIssqn(), dto.getValorIssqn());
    }
}