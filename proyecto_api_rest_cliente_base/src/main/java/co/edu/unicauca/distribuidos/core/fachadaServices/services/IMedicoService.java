package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.MedicoDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.MedicoDTORespuesta;

public interface IMedicoService {
    public List<MedicoDTORespuesta> findAll();

	public MedicoDTORespuesta findById(Integer id);

	public MedicoDTORespuesta save(MedicoDTOPeticion medico);
}
