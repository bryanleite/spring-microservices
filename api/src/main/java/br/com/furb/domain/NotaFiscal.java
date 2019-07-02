package br.com.furb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TB_NOTA_FISCAL")
public class NotaFiscal extends IdentityCommonObject{

	@Column(name = "NFS_CODEMP")
	private Integer codEmpresa;

	@Column(name = "NFS_CODCLI")
	private Integer codCliente;

	@Column(name = "NFS_CODPRO")
	private Integer codProduto;

	@Column(name = "NFS_VALTOT")
	private Double valorTotal;

	@Column(name = "NFS_VALCON")
	private Double valorContabil;

	@Column(name = "NFS_CODREI")
	private Integer codReducaoImpostos;

	@Column(name = "NFS_PERICM")
	private Double percentualIcms;

	@Column(name = "NFS_PERIRR")
	private Double percentualIrrf;

	@Column(name = "NFS_VALIRR")
	private Double valorIrrf;

	@Column(name = "NFS_CODFPA")
	private Integer codFormaPagamento;

}
