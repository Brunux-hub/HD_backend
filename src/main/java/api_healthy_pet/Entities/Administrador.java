package api_healthy_pet.Entities;

import api_healthy_pet.Configuration.DatabaseConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = DatabaseConstants.SCHEMA, name = "administradores")
@Getter
@Setter
@NoArgsConstructor
public class Administrador {

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;
}
