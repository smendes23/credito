package br.com.teste.credito.infraestructure.aspect;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.infraestructure.producer.CreditoProducer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class CreditoServiceAspect {

    private final CreditoProducer creditoProducer;

    @AfterReturning(pointcut = "execution(* br.com.teste.credito.application.service.CreditoService.*(..))", returning = "result")
    public void afterReturningAdvice(Object result) {
        if (result instanceof CreditoDto) {
            CreditoDto creditoDto = (CreditoDto) result;
            log.info("interceptando e enviando o credito: {}", creditoDto);
            creditoProducer.sendOrder(creditoDto);
        } else if (result instanceof List) {
            log.info("interceptando e enviando  a lista de creditos");
            List<?> resultList = (List<?>) result;
            resultList.stream()
                    .filter(CreditoDto.class::isInstance)
                    .map(CreditoDto.class::cast)
                    .forEach(creditoProducer::sendOrder);
        }
    }
}
