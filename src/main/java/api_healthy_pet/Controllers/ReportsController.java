package api_healthy_pet.Controllers;

import api_healthy_pet.Dtos.Response.AppointmentResponse;
import api_healthy_pet.Dtos.Response.MedicalHistoryResponse;
import api_healthy_pet.Enums.AppointmentStatus;
import api_healthy_pet.Services.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportService;

    // RUTA /reports
    @GetMapping()
    public String mostrarJSONReportes(){
        return "Reportes API";
    }

    // Servicios Realizados
    @GetMapping("/completed-services")
    public List<MedicalHistoryResponse> getCompletedServices() {
        return reportService.getCompletedServices();
    }

    // Programación de Citas
    @GetMapping("/appointments")
    public List<AppointmentResponse> getAppointmentsByStatus(
            @RequestParam AppointmentStatus status
    ){
        return reportService.getAppointmentsByStatus(status);
    }

    // Pacientes Atendidos


    // Veterinarios Activos

}
