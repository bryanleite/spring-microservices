package br.com.furb.routes;

import br.com.furb.domain.NotaFiscal;
import br.com.furb.service.NotaFiscalSaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value = "/nota-fiscal-saida")
public class NotaFiscalSaidaRoute {

	@Autowired
	private NotaFiscalSaidaService notaFiscalSaidaService;

	@PostMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> save(@RequestBody NotaFiscal notaFiscal) {
		return ResponseEntity.ok(notaFiscalSaidaService.gerarNotaFiscalSaida(notaFiscal));
	}

}
