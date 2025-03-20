package br.com.teste.credito.infraestructure.controller.mapper;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.infraestructure.controller.dto.CreditoResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditoMapper {

    public static CreditoResponse toResponse(CreditoDto entity){
        return CreditoResponse.builder()
                .id(entity.getId())
                .aliquota(entity.getAliquota())
                .baseCalculo(entity.getBaseCalculo())
                .numeroCredito(entity.getNumeroCredito())
                .tipoCredito(entity.getTipoCredito())
                .dataConstituicao(entity.getDataConstituicao())
                .numeroNfse(entity.getNumeroNfse())
                .simplesNacional(entity.getSimplesNacional())
                .valorDeducao(entity.getValorDeducao())
                .valorFaturado(entity.getValorFaturado())
                .valorIssqn(entity.getValorIssqn())
                .build();
    }
}
