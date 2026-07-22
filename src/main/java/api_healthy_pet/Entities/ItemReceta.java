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

@Entity
@Table(schema = DatabaseConstants.SCHEMA, name = "items_receta")
@Getter
@Setter
@NoArgsConstructor
public class ItemReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_receta")
    private Long idItemReceta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @Column(name = "medicamento", nullable = false, length = 150)
    private String medicamento;

    @Column(name = "cantidad", length = 50)
    private String cantidad;

    @Column(name = "dosis", length = 100)
    private String dosis;

    @Column(name = "indicaciones")
    private String indicaciones;
}
