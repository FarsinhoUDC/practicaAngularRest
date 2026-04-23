package co.edu.unicauca.distribuidos.core.capaControladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FranjaDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FranjaDTORespuesta;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IFranjaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FranjaRestController {

    @Autowired
    private IFranjaService franjaHorariaService;

    @PostMapping("/franjas")
    public FranjaDTORespuesta registrarFranja(@RequestBody FranjaDTOPeticion franja) {
        return franjaHorariaService.save(franja);
    }

    @GetMapping("/franjas/medico/{idMedico}/fecha/{fecha}")
    public List<FranjaDTORespuesta> obtenerFranjasPorMedicoYFecha(
            @PathVariable Integer idMedico,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        return franjaHorariaService.findByMedicoAndFecha(idMedico, fecha);
    }
}