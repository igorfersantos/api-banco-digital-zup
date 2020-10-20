package br.com.igorfersantos.bancodigitalzup.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cpf_foto")
@Entity
public class CpfFoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String foto;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", nullable = false)
    private User user = null;

    public CpfFoto(String foto, User user){
        this.foto = foto;
        this.user = user;
    }

}
