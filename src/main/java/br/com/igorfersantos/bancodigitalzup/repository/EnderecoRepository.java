package br.com.igorfersantos.bancodigitalzup.repository;

import br.com.igorfersantos.bancodigitalzup.data.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
