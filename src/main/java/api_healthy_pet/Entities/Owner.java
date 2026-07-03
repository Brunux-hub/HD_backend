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

    // Un cliente puede tener una cuenta de acceso (User). Es opcional:
    // los Owners creados por el staff pueden no tener login.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(unique = true)
    private String dni;

    private String names;
    private String lastNames;
    private String email;
    private String phoneNumber;
    private String address;

}
