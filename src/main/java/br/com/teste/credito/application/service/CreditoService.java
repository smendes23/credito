package br.com.teste.credito.application.service;

import br.com.teste.credito.domain.dto.CreditoDto;
import java.util.List;

public interface CreditoService {

    List<CreditoDto> buscarPorNfse(final String numeroNfse);

    CreditoDto buscarPorNumeroCredito(final String numeroCredito);
}
