package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.FranjaEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.FranjaRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FranjaDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FranjaDTORespuesta;

@Service
public class FranjaServiceImpl implements IFranjaService {

    private FranjaRepository servicioAccesoBaseDatos;
    private ModelMapper modelMapper;

    public FranjaServiceImpl(FranjaRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
        this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
        this.modelMapper = modelMapper;
    }

    @Override
    public FranjaDTORespuesta save(FranjaDTOPeticion franja) {
        FranjaEntity franjaEntity = this.modelMapper.map(franja, FranjaEntity.class);
        
        // Asigna estado por defecto 
        franjaEntity.setEstado("DISPONIBLE");
        FranjaEntity franjaGuardada = this.servicioAccesoBaseDatos.save(franjaEntity);
        
        return this.modelMapper.map(franjaGuardada, FranjaDTORespuesta.class);
    }

    @Override
    public List<FranjaDTORespuesta> findByMedicoAndFecha(Integer idMedico, LocalDate fecha) {
        Optional<Collection<FranjaEntity>> franjasOpt = 
            this.servicioAccesoBaseDatos.findByMedicoAndFecha(idMedico, fecha);
        
        if (franjasOpt.isEmpty()) {
            return List.of();
        }
        
        return this.modelMapper.map(
            franjasOpt.get(),
            new TypeToken<List<FranjaDTORespuesta>>() {}.getType()
        );
    }
}