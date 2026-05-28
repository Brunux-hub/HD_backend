package api_healthy_pet.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private Long idOwner;

    private String names;
    private String lastNames;
    private String email;
    private String phoneNumber;
    private String address;

}
