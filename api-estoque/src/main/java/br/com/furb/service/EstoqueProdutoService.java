package br.com.furb.service;

import br.com.furb.domain.EstoqueProduto;
import br.com.furb.repository.EstoqueProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueProdutoService {

	@Autowired
	private EstoqueProdutoRepository estoqueProdutoRepository;

	public EstoqueProduto debitarEstoque(Long codProduto, Integer codEmpresa) throws Exception {
		Optional<EstoqueProduto> estoqueProduto = estoqueProdutoRepository.findByProdutoAndCodEmpresa(codProduto, codEmpresa);
		estoqueProduto.orElseThrow(() ->
			new Exception(String.format("Não foi encontrado registro de estoque para o produto %d e empresa %d.", codProduto, codEmpresa))
		);

		estoqueProduto.get().setQuantidade(estoqueProduto.get().getQuantidade() - 1);
		return estoqueProdutoRepository.save(estoqueProduto.get());
	}

}
