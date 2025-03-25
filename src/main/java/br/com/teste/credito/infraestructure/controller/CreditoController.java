package br.com.teste.credito.infraestructure.controller;

import br.com.teste.credito.application.service.CreditoService;
import br.com.teste.credito.infraestructure.controller.dto.CreditoResponse;
import br.com.teste.credito.infraestructure.controller.mapper.CreditoMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/api/creditos")
public class CreditoController {

    private final CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{numeroNfse}")
    public List<CreditoResponse> consultarPorNfse(@PathVariable String numeroNfse) {
        log.info("Consultando uma lista de creditos pela NFSE: {}", numeroNfse);

        return service.buscarPorNfse(numeroNfse)
                .stream().map(CreditoMapper::toResponse).toList();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{numeroCredito}/credito")
    public CreditoResponse consultarPorNumeroCredito(@PathVariable String numeroCredito) {
        log.info("Consulta de credito pelo numeroCredito: {}", numeroCredito);

        var credito = service.buscarPorNumeroCredito(numeroCredito);
        return CreditoMapper.toResponse(credito);
    }
}
