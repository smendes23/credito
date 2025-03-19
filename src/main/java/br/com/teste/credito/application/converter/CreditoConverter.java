package br.com.teste.credito.application.converter;

import br.com.teste.credito.domain.dto.CreditoDto;
import br.com.teste.credito.domain.entity.Credito;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditoConverter {

    public static CreditoDto toDto(final Credito entity){
        return CreditoDto.builder()
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
