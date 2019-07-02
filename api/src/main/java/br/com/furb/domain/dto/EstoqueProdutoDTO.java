package br.com.furb.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueProdutoDTO {

	private ProdutoDTO produto;
	private Integer codEmpresa;
	private Integer codLote;
	private Integer quantidade;

}
