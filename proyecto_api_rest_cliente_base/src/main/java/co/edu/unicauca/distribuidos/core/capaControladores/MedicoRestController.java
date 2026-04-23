
package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.MedicoDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.MedicoDTORespuesta;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IMedicoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MedicoRestController {

	@Autowired	//Otra forma de inyectar un objeto del contenedor de Spring
	private IMedicoService medicoService;

	@GetMapping("/medicos")
	public List<MedicoDTORespuesta> listarClientes() {			
		return medicoService.findAll();
	}

	@GetMapping("/medicos/{id}")
	public MedicoDTORespuesta consultarCliente(@PathVariable Integer id) {
		MedicoDTORespuesta objMedico = null;
		objMedico = medicoService.findById(id);
		return objMedico;
	}
	
	@PostMapping("/medicos")
	public MedicoDTORespuesta crearCliente(@RequestBody MedicoDTOPeticion medico) {
		MedicoDTORespuesta objMedico = null;
		objMedico = medicoService.save(medico);
		return objMedico;
	}
}
