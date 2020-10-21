package br.com.igorfersantos.bancodigitalzup.repository;

import br.com.igorfersantos.bancodigitalzup.data.model.CpfFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CpfFotoRepository extends JpaRepository<CpfFoto, Long> {

    @Query("SELECT c FROM CpfFoto c WHERE c.foto = :path AND c.user.id = :id")
    Optional<CpfFoto> findByPathId(@Param("path") String path, @Param("id") Long id);

}
