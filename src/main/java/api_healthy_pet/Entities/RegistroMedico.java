package api_healthy_pet.Entities;

import api_healthy_pet.Configuration.DatabaseConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(schema = DatabaseConstants.SCHEMA, name = "registros_medicos")
@Getter
@Setter
@NoArgsConstructor
public class RegistroMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_medico")
    private Long idRegistroMedico;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_cita", nullable = false, unique = true)
    private Cita cita;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "medicamentos_recetados")
    private String medicamentosRecetados;

    @Column(name = "observaciones")
    private String observaciones;
}
