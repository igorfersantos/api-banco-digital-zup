package br.com.igorfersantos.bancodigitalzup.model;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "cpf_foto")
@Entity
public class CpfFoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private User user = null;

}
