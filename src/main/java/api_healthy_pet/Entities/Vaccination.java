package api_healthy_pet.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vaccinations")
@Getter
@Setter
@NoArgsConstructor
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaccination")
    private Long idVaccination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medical_history")
    private MedicalHistory medicalHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vaccine")
    private Vaccine vaccine;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate nextApplicationDate;

    private String observation;

}
