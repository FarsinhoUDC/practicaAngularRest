package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.time.LocalDate;
import java.util.List;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FranjaDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FranjaDTORespuesta;

public interface IFranjaService {
    public FranjaDTORespuesta save(FranjaDTOPeticion franja);
    public List<FranjaDTORespuesta> findByMedicoAndFecha(Integer idMedico, LocalDate fecha);
}