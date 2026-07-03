package api_healthy_pet.Entities;

import api_healthy_pet.Enums.PetGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long idPet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner")
    private Owner owner;

    private String name;

    private String species;

    private String race;

    private Date birthdate;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private PetGender sex;
}
