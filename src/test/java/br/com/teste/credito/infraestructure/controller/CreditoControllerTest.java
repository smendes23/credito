package br.com.teste.credito.infraestructure.controller;

import br.com.teste.credito.application.service.CreditoService;
import br.com.teste.credito.domain.dto.CreditoDto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService service;

    @Test
    void testConsultarPorNfseWhenServiceReturnsListThenReturnList() throws Exception {
        CreditoDto creditoDto = CreditoDto.builder()
                .id(1L)
                .numeroCredito("123")
                .numeroNfse("456")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(100))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5))
                .valorFaturado(BigDecimal.valueOf(1000))
                .valorDeducao(BigDecimal.valueOf(100))
                .baseCalculo(BigDecimal.valueOf(900))
                .build();
        List<CreditoDto> creditoList = List.of(creditoDto);

        BDDMockito.given(service.buscarPorNfse(ArgumentMatchers.anyString())).willReturn(creditoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/creditos/456"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numeroCredito").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numeroNfse").value("456"));
    }

    @Test
    void testConsultarPorNfseWhenServiceReturnsEmptyListThenReturnEmptyList() throws Exception {
        BDDMockito.given(service.buscarPorNfse(ArgumentMatchers.anyString())).willReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/creditos/456"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void testConsultarPorNfseWhenServiceThrowsExceptionThenReturnInternalServerError() throws Exception {
        BDDMockito.given(service.buscarPorNfse(ArgumentMatchers.anyString())).willThrow(new RuntimeException("Service exception"));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/creditos/456"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testConsultarPorNumeroCreditoWhenServiceReturnsCreditoThenReturnCredito() throws Exception {
        CreditoDto creditoDto = CreditoDto.builder()
                .id(1L)
                .numeroCredito("123")
                .numeroNfse("456")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(100))
                .tipoCredito("Tipo1")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5))
                .valorFaturado(BigDecimal.valueOf(1000))
                .valorDeducao(BigDecimal.valueOf(100))
                .baseCalculo(BigDecimal.valueOf(900))
                .build();

        BDDMockito.given(service.buscarPorNumeroCredito(ArgumentMatchers.anyString())).willReturn(creditoDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/creditos/123/credito"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroCredito").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroNfse").value("456"));
    }

    @Test
    void testConsultarPorNumeroCreditoWhenServiceThrowsExceptionThenReturnInternalServerError() throws Exception {
        BDDMockito.given(service.buscarPorNumeroCredito(ArgumentMatchers.anyString())).willThrow(new RuntimeException("Service exception"));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/creditos/123/credito"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
