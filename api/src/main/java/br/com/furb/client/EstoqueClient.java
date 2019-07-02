package br.com.furb.client;

import br.com.furb.domain.dto.EstoqueProdutoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class EstoqueClient {

	@Value("${client.estoque.host}")
	private String host;

	@Value("${client.estoque.port}")
	private Integer port;

	public EstoqueProdutoDTO debitarEstoque(Integer codEmpresa, Integer codProduto) {
		String uri = getFormattedHost() + "/debitarEstoque/{codEmp}/{codPro}";
		Map<String, Object> params = new HashMap<>();
		params.put("codEmp", codEmpresa);
		params.put("codPro", Long.valueOf(codProduto));

		return new RestTemplate().postForObject(uri, null, EstoqueProdutoDTO.class, params);
	}

	private String getFormattedHost(){
		return String.format("http://%s:%d", this.host, this.port);
	}

}