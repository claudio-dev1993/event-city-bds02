package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exceptions.DatabaseException;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	CityRepository repository;

	@Transactional(readOnly = true)
	public List<CityDTO> findAllSortedByName() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public CityDTO insert(CityDTO dto) {
		City city = new City(null, dto.getName());
		city = repository.save(city);
		return new CityDTO(city);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("id not found! " + id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation");
			
		}
	}
}
