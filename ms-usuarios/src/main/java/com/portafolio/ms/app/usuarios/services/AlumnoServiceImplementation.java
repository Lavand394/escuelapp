package com.portafolio.ms.app.usuarios.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portafolio.ms.app.usuarios.models.entity.Alumno;
import com.portafolio.ms.app.usuarios.models.repository.AlumnoRepository;
@Service
public class AlumnoServiceImplementation implements AlumnoService {

	@Autowired
	private AlumnoRepository aRepository;
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return aRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return aRepository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return aRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		aRepository.deleteById(id);

	}

}
