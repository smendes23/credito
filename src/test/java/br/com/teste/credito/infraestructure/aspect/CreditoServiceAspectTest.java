package br.com.teste.credito.infraestructure.aspect;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.infraestructure.producer.CreditoProducer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreditoServiceAspectTest {

    @Mock
    private CreditoProducer creditoProducer;

    @Test
    void testAfterReturningAdviceWhenResultIsCreditoDtoThenSendOrderCalled() {
        CreditoServiceAspect creditoServiceAspect = new CreditoServiceAspect(creditoProducer);
        CreditoDto creditoDto = CreditoDto.builder()
                .id(1L)
                .numeroCredito("12345")
                .numeroNfse("67890")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(100.00))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5.00))
                .valorFaturado(BigDecimal.valueOf(200.00))
                .valorDeducao(BigDecimal.valueOf(50.00))
                .baseCalculo(BigDecimal.valueOf(150.00))
                .build();

        creditoServiceAspect.afterReturningAdvice(creditoDto);

        verify(creditoProducer).sendOrder(creditoDto);
    }

    @Test
    void testAfterReturningAdviceWhenResultIsListOfCreditoDtoThenSendOrderCalledForEach() {
        CreditoServiceAspect creditoServiceAspect = new CreditoServiceAspect(creditoProducer);
        CreditoDto creditoDto1 = CreditoDto.builder()
                .id(1L)
                .numeroCredito("12345")
                .numeroNfse("67890")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(100.00))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5.00))
                .valorFaturado(BigDecimal.valueOf(200.00))
                .valorDeducao(BigDecimal.valueOf(50.00))
                .baseCalculo(BigDecimal.valueOf(150.00))
                .build();

        CreditoDto creditoDto2 = CreditoDto.builder()
                .id(2L)
                .numeroCredito("54321")
                .numeroNfse("09876")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(200.00))
                .tipoCredito("Tipo2")
                .simplesNacional(false)
                .aliquota(BigDecimal.valueOf(10.00))
                .valorFaturado(BigDecimal.valueOf(400.00))
                .valorDeducao(BigDecimal.valueOf(100.00))
                .baseCalculo(BigDecimal.valueOf(300.00))
                .build();

        List<CreditoDto> creditoDtoList = Arrays.asList(creditoDto1, creditoDto2);

        creditoServiceAspect.afterReturningAdvice(creditoDtoList);

        verify(creditoProducer).sendOrder(creditoDto1);
        verify(creditoProducer).sendOrder(creditoDto2);
    }
}
