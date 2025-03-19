package br.com.teste.credito.application.service.impl;

import br.com.teste.credito.application.converter.CreditoConverter;
import br.com.teste.credito.application.service.CreditoService;
import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.infraestructure.exception.NotFoundException;
import br.com.teste.credito.infraestructure.persistence.CreditoRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository repository;

    public CreditoServiceImpl(CreditoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CreditoDto> buscarPorNfse(String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse).stream()
                .map(CreditoConverter::toDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), listaCreditos -> {
                    if (listaCreditos.isEmpty()) {
                        throw new NotFoundException("Creditos não encontrados!");
                    }
                    return listaCreditos;
                }));
    }

    @Override
    public CreditoDto buscarPorNumeroCredito(String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito)
                .map(CreditoConverter::toDto)
                .orElseThrow(() -> new NotFoundException("Credito não encontrado!"));
    }
}
