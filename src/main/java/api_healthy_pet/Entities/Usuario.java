package api_healthy_pet.Entities;

import api_healthy_pet.Configuration.DatabaseConstants;
import api_healthy_pet.Enums.RolUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(schema = DatabaseConstants.SCHEMA, name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "contrasenia_hash", nullable = false)
    private String contraseniaHash;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "rol", nullable = false, columnDefinition = "healthy_pets_bd_2.rol_usuario")
    private RolUsuario rol;

    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado = Boolean.TRUE;
}
