package br.com.igorfersantos.bancodigitalzup.repository;

import br.com.igorfersantos.bancodigitalzup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.cpf = :cpf")
    User findUserByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
}
