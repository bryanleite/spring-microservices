package br.com.furb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TB_PRODUTO")
public class Produto extends IdentityCommonObject{

	@Column(name = "PRO_DESCRI")
	private String descricao;

}

