package br.com.igorfersantos.bancodigitalzup.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
@Table(name = "clientes")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -7793778741900173950L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 100, nullable = false)
    private String sobrenome;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "cpf", unique = true, length = 15, nullable = false)
    private String cpf;

    public User(String nome, String sobrenome, String email, Date dataNascimento, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }
}
