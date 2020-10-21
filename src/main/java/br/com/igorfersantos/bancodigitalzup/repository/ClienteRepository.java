package br.com.igorfersantos.bancodigitalzup.repository;

import br.com.igorfersantos.bancodigitalzup.data.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    Optional<Cliente> findUserByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT c FROM Cliente c WHERE c.email = :email")
    Optional<Cliente> findUserByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE Cliente c SET c.endereco.id = :idEndereco WHERE c.id = :id")
    void updateEndereco(@Param("idEndereco") Long idEndereco, @Param("id") Long id);

}
