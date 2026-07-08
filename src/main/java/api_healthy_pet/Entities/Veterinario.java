package api_healthy_pet.Entities;

import api_healthy_pet.Converters.EspecialidadListConverter;
import api_healthy_pet.Enums.EspecialidadVet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "veterinarios")
@Getter
@Setter
@NoArgsConstructor
public class Veterinario {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String nombre;
    private String apellido;
    private String telefono;

    @Convert(converter = EspecialidadListConverter.class)
    @Column(length = 200)
    private List<EspecialidadVet> especialidades;

    private String licencia;
}
