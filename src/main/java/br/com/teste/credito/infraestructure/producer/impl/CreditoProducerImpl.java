package br.com.teste.credito.infraestructure.producer.impl;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.infraestructure.exception.ProducerException;
import br.com.teste.credito.infraestructure.producer.CreditoProducer;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreditoProducerImpl implements CreditoProducer {

    private final KafkaTemplate<String, CreditoDto> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic ;

    @Override
    public String sendOrder(final CreditoDto order) {
        log.info("Publicando um credito no topico: {}", topic);
        CompletableFuture<SendResult<String, CreditoDto>> future = kafkaTemplate.send(topic, order);

        return future
                .thenApply(result -> result.getProducerRecord().value().toString())
                .exceptionally(e -> {
                    log.error("Erro ao publicar um credito: {}", order, e);
                    throw new ProducerException(e.getMessage());
                })
                .join();
    }
}
