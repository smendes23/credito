package br.com.teste.credito.infraestructure.producer;

import br.com.teste.credito.domain.dto.CreditoDto;

public interface CreditoProducer {
    String sendOrder(CreditoDto credito);
}
