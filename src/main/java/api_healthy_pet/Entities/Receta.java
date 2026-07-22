package api_healthy_pet.Entities;

import api_healthy_pet.Configuration.DatabaseConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(schema = DatabaseConstants.SCHEMA, name = "recetas")
@Getter
@Setter
@NoArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Long idReceta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_registro_medico", nullable = false)
    private RegistroMedico registroMedico;

    @Column(name = "numero_receta", unique = true, length = 20)
    private String numeroReceta;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;
}
