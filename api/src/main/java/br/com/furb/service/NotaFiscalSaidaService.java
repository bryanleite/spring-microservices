package br.com.furb.service;

import br.com.furb.client.EstoqueClient;
import br.com.furb.domain.NotaFiscal;
import br.com.furb.domain.dto.EstoqueProdutoDTO;
import br.com.furb.repository.NotaFiscalRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalSaidaService extends AbstractService<NotaFiscal> {

	@Autowired
	private EstoqueClient estoqueClient;

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	public NotaFiscal gerarNotaFiscalSaida(NotaFiscal notaFiscal) {
		EstoqueProdutoDTO estoqueAtualizado = estoqueClient.debitarEstoque(notaFiscal.getCodEmpresa(), notaFiscal.getCodProduto());

		if(estoqueAtualizado != null) {
			return save(notaFiscal);
		}

		return null;
	}

	@Override
	protected JpaRepository<NotaFiscal, Long> getRepository() {
		return notaFiscalRepository;
	}
}
