package br.com.furb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_ESTOQUE_PRODUTO")
public class EstoqueProduto extends IdentityCommonObject {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EPR_PROID", nullable = false)
	private Produto produto;

	@Column(name = "EPR_CODEMP")
	private Integer codEmpresa;

	@Column(name = "EPR_CODLOTE")
	private Integer codLote;

	@Column(name = "ERP_QTD")
	private Integer quantidade;

}
