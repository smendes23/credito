package br.com.teste.credito.infraestructure.producer.impl;

import br.com.teste.credito.domain.dto.CreditoDto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditoProducerImplTest {

    @Mock
    private KafkaTemplate<String, CreditoDto> kafkaTemplate;

    @InjectMocks
    private CreditoProducerImpl creditoProducer;

    @Captor
    private ArgumentCaptor<CreditoDto> creditoDtoArgumentCaptor;

    private CreditoDto creditoDto;

    @BeforeEach
    void setUp() throws Exception {
        creditoDto = CreditoDto.builder()
                .id(1L)
                .numeroCredito("12345")
                .numeroNfse("67890")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(100.00))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5.00))
                .valorFaturado(BigDecimal.valueOf(1000.00))
                .valorDeducao(BigDecimal.valueOf(50.00))
                .baseCalculo(BigDecimal.valueOf(950.00))
                .build();

        java.lang.reflect.Field topicField = CreditoProducerImpl.class.getDeclaredField("topic");
        topicField.setAccessible(true);
        topicField.set(creditoProducer, "test-topic");
    }

    @Test
    void testSendOrderWhenKafkaTemplateSendsMessageThenReturnResult() {
        SettableListenableFuture<SendResult<String, CreditoDto>> future = new SettableListenableFuture<>();
        SendResult<String, CreditoDto> sendResult = mock(SendResult.class);
        when(sendResult.getProducerRecord()).thenReturn(mock(org.apache.kafka.clients.producer.ProducerRecord.class));
        when(sendResult.getProducerRecord().value()).thenReturn(creditoDto);
        future.set(sendResult);
        when(kafkaTemplate.send(anyString(), any(CreditoDto.class))).thenReturn(future.completable());

        String result = creditoProducer.sendOrder(creditoDto);

        assertEquals(creditoDto.toString(), result);
        verify(kafkaTemplate).send(eq("test-topic"), creditoDtoArgumentCaptor.capture());
        assertEquals(creditoDto, creditoDtoArgumentCaptor.getValue());
    }

    @Test
    void testSendOrderWhenKafkaTemplateFailsThenThrowProducerException() {
        CompletableFuture<SendResult<String, CreditoDto>> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("Kafka send failed"));
        when(kafkaTemplate.send(anyString(), any(CreditoDto.class))).thenReturn(future);

        CompletionException exception = assertThrows(CompletionException.class, () -> creditoProducer.sendOrder(creditoDto));
        assertEquals("br.com.teste.credito.infraestructure.exception.ProducerException: Erro ao publicar mensagem! Causa: java.lang.RuntimeException: Kafka send failed", exception.getMessage());
        verify(kafkaTemplate).send(eq("test-topic"), creditoDtoArgumentCaptor.capture());
        assertEquals(creditoDto, creditoDtoArgumentCaptor.getValue());
    }
}
