package ru.vallball.jkh01.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.service.HouseService;

@RestController
@RequestMapping(value = "/houses")
public class HouseController {
	
	static final Logger logger = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	HouseService houseService;

	@GetMapping
	@ResponseBody
	public List<House> list() {
		return houseService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public House get(@PathVariable(value = "id") Long id) {
		return houseService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody House house) {
		house.createApartments();
		logger.info(house.toString());
		try {
			houseService.save(house);
			return new ResponseEntity<>("House is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "House is exist", e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody House house) {
		try {
			House houseForUpdate = houseService.findById(id);
			houseForUpdate.setApartments(house.getApartments());
			houseForUpdate.setApartmentsByLevel(house.getApartmentsByLevel());
			houseForUpdate.setEntrances(house.getEntrances());
			houseForUpdate.setLevels(house.getLevels());
			houseForUpdate.setNumber(house.getNumber());
			houseForUpdate.setStreet(house.getStreet());
			houseService.save(houseForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("House not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("House is updated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			houseService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("House not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("House is deleted successfully", HttpStatus.ACCEPTED);
	}

}
