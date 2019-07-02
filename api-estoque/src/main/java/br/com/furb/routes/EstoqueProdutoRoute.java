package br.com.furb.routes;

import br.com.furb.service.EstoqueProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value = "/debitarEstoque")
public class EstoqueProdutoRoute {

	@Autowired
	private EstoqueProdutoService estoqueProdutoService;

	@PostMapping(value = "/{codEmp}/{codPro}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> debitarEstoque(@PathVariable("codEmp") Integer codEmpresa, @PathVariable("codPro") Long codProduto) throws Exception {
		return ResponseEntity.ok(estoqueProdutoService.debitarEstoque(codProduto, codEmpresa));
	}

}
