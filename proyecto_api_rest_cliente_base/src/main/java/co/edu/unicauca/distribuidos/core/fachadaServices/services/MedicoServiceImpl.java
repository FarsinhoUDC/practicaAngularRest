
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;


import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.MedicoEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.MedicoRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.MedicoDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.MedicoDTORespuesta;

@Service//El objeto creado se almacena en el contenedor de Spring
public class MedicoServiceImpl implements IMedicoService {

	
	private MedicoRepository servicioAccesoBaseDatos;	
	private ModelMapper modelMapper;

	//El contructor inyecta los objetos que se encuentran en el contenedor de Spring
	public MedicoServiceImpl(MedicoRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<MedicoDTORespuesta> findAll() {
		List<MedicoDTORespuesta> listaRetornar;
		Optional<Collection<MedicoEntity>> medicosEntityOpt = this.servicioAccesoBaseDatos.findAll();
		
		// Si el Optional está vacío, devolvemos una lista vacía
		if (medicosEntityOpt.isEmpty()) {
			listaRetornar=List.of(); // Retorna una lista inmutable vacía
		}
		else{
			// Convertimos la colección a una lista y la mapeamos a ClienteDTO
			Collection<MedicoEntity> medicosEntity = medicosEntityOpt.get();
			listaRetornar= this.modelMapper.map(medicosEntity, new TypeToken<List<MedicoDTORespuesta>>() {}.getType());
			
		}
	
		
		return listaRetornar;
	}
	
	@Override
	public MedicoDTORespuesta findById(Integer id) {
		MedicoDTORespuesta medicoRetornar=null;
		Optional<MedicoEntity> optionalMedico = this.servicioAccesoBaseDatos.findById(id);
		if(optionalMedico.isPresent())
		{
			MedicoEntity medicoEntity=optionalMedico.get();
			medicoRetornar= this.modelMapper.map(medicoEntity, MedicoDTORespuesta.class);
		}


		return medicoRetornar;
		
	}

	@Override
	public MedicoDTORespuesta save(MedicoDTOPeticion medico) {
		MedicoEntity medicoEntity = this.modelMapper.map(medico, MedicoEntity.class);
		medicoEntity.setEstado(true);
		medicoEntity.setCreateAt(new Date());
		MedicoEntity objMedicoEntity = this.servicioAccesoBaseDatos.save(medicoEntity);
		System.out.println(objMedicoEntity);
		MedicoDTORespuesta medicoDTO = this.modelMapper.map(objMedicoEntity, MedicoDTORespuesta.class);
		return medicoDTO;
	}
}
