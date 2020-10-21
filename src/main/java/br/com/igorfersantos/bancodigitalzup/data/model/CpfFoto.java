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

    @OneToOne(targetEntity = Cliente.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente = null;

    public CpfFoto(String foto, Cliente cliente){
        this.foto = foto;
        this.cliente = cliente;
    }

}
