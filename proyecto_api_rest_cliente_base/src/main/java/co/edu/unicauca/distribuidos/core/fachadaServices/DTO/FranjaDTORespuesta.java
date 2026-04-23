package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FranjaDTORespuesta {
    private Integer id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;
    private String estado;
    private Integer idMedico;
}