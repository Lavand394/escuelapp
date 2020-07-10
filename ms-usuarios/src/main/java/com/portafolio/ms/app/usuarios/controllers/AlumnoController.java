package com.portafolio.ms.app.usuarios.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portafolio.ms.app.usuarios.models.entity.Alumno;
import com.portafolio.ms.app.usuarios.services.AlumnoService;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService aService;

	@GetMapping
	public ResponseEntity<?> listarAlumnos() {
		return ResponseEntity.ok().body(aService.findAll()); // Se pasa al cuerpo de la respuesta si está correcto.
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerAlumnoId(@PathVariable Long id) {
		Optional<Alumno> o = aService.findById(id);

		if (!(o.isPresent())) { // Preguntamos si es no existe el alumno, si es true Reponse entity tendrá un
								// valor notFound
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());// De lo contrario vamos a obtener el alumno con .get()
	}

	@PostMapping
	public ResponseEntity<?> crearAlumno(@RequestBody Alumno alumno) {
		Alumno alumnoDb = aService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> modificarAlumno(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> o = aService.findById(id);

		if (!(o.isPresent())) { // Preguntamos si es no existe el alumno, si es true Reponse entity tendrá un
								// valor notFound
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(aService.save(alumnoDb));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id){
		aService.deleteById(id);
		return ResponseEntity.noContent().build(); //contruye la respuesta sin contenido
	}

}
