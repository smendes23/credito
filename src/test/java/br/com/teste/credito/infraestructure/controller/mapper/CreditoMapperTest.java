package br.com.teste.credito.infraestructure.controller.mapper;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.infraestructure.controller.dto.CreditoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;

class CreditoMapperTest {

    @Test
    void testToResponseWhenCreditoDtoProvidedThenReturnCreditoResponseWithExpectedValues() {
        CreditoDto creditoDto = CreditoDto.builder()
                .id(1L)
                .numeroCredito("12345")
                .numeroNfse("67890")
                .dataConstituicao(LocalDate.of(2023, 10, 1))
                .valorIssqn(new BigDecimal("100.00"))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(new BigDecimal("0.05"))
                .valorFaturado(new BigDecimal("200.00"))
                .valorDeducao(new BigDecimal("50.00"))
                .baseCalculo(new BigDecimal("150.00"))
                .build();

        CreditoResponse creditoResponse = CreditoMapper.toResponse(creditoDto);

        Assertions.assertEquals(creditoDto.getId(), creditoResponse.getId());
        Assertions.assertEquals(creditoDto.getNumeroCredito(), creditoResponse.getNumeroCredito());
        Assertions.assertEquals(creditoDto.getNumeroNfse(), creditoResponse.getNumeroNfse());
        Assertions.assertEquals(creditoDto.getDataConstituicao(), creditoResponse.getDataConstituicao());
        Assertions.assertEquals(creditoDto.getValorIssqn(), creditoResponse.getValorIssqn());
        Assertions.assertEquals(creditoDto.getTipoCredito(), creditoResponse.getTipoCredito());
        Assertions.assertEquals(creditoDto.getSimplesNacional(), creditoResponse.getSimplesNacional());
        Assertions.assertEquals(creditoDto.getAliquota(), creditoResponse.getAliquota());
        Assertions.assertEquals(creditoDto.getValorFaturado(), creditoResponse.getValorFaturado());
        Assertions.assertEquals(creditoDto.getValorDeducao(), creditoResponse.getValorDeducao());
        Assertions.assertEquals(creditoDto.getBaseCalculo(), creditoResponse.getBaseCalculo());
    }
}
