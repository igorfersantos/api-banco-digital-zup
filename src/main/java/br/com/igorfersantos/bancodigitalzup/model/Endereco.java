package br.com.igorfersantos.bancodigitalzup.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Table(name = "enderecos")
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = -7793778741900173950L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "bairro", length = 100, nullable = false)
    private String bairro;

    @Column(name = "complemento", length = 40, unique = true, nullable = false)
    private String complemento;

    @Column(name = "cidade", length = 45, nullable = false)
    private String cidade;

    @Column(name = "estado", unique = true, length = 20, nullable = false)
    private String estado;

    public Endereco(String cep, String bairro, String complemento, String cidade, String estado) {
        this.cep = cep;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }
}
