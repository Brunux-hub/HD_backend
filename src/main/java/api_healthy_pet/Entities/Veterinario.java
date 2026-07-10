package api_healthy_pet.Entities;

import api_healthy_pet.Configuration.DatabaseConstants;
import api_healthy_pet.Enums.EspecialidadVeterinario;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = DatabaseConstants.SCHEMA, name = "veterinarios")
@Getter
@Setter
@NoArgsConstructor
public class Veterinario {

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

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "numero_licencia", nullable = false, unique = true, length = 50)
    private String numeroLicencia;

    @ElementCollection(targetClass = EspecialidadVeterinario.class, fetch = FetchType.EAGER)
    @CollectionTable(
            schema = DatabaseConstants.SCHEMA,
            name = "veterinario_especialidades",
            joinColumns = @JoinColumn(name = "id_usuario_veterinario")
    )
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "especialidad", nullable = false, columnDefinition = "healthy_pets_bd_2.especialidad_veterinario")
    private Set<EspecialidadVeterinario> especialidades = new HashSet<>();
}
