package br.com.furb.repository;

import br.com.furb.domain.EstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueProdutoRepository extends JpaRepository<EstoqueProduto, Long> {

	@Query("select epr from EstoqueProduto epr where epr.produto.id = :codProduto and epr.codEmpresa = :codEmpresa")
	Optional<EstoqueProduto> findByProdutoAndCodEmpresa(@Param("codProduto") Long codProduto, @Param("codEmpresa") Integer codEmpresa);

}
