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
import org.springframework.validation.annotation.Validated;
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
	public List<House> list() {
		return houseService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable(value = "id") Long id) {
		try {
			return ResponseEntity.ok(houseService.findById(id));
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("House not found", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{street}/{number}")
	public ResponseEntity<Object> getByAddress(@PathVariable(value = "street") String street, @PathVariable(value = "number") String number) {
		try {
			return ResponseEntity.ok(houseService.findByAddress(street, number));
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("House not found", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/street/{street}")
	public List<House> listByStreet(@PathVariable(value = "street") String street) {
		return houseService.listByStreet(street);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody House house) {
		house.setStreet(house.getStreet().toLowerCase());
		house.setNumber(house.getNumber().toLowerCase());
		house.createApartments();
		try {
			houseService.save(house);
			return new ResponseEntity<>("House is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@Valid @PathVariable(value = "id") Long id, @Valid @RequestBody House house)
			throws Exception {
		try {
			House houseForUpdate = houseService.findById(id);
			houseForUpdate.setApartments(house.getApartments());
			houseForUpdate.setApartmentsByLevel(house.getApartmentsByLevel());
			houseForUpdate.setEntrances(house.getEntrances());
			houseForUpdate.setLevels(house.getLevels());
			houseForUpdate.setNumber(house.getNumber().toLowerCase());
			houseForUpdate.setStreet(house.getStreet().toLowerCase());
			houseService.save(houseForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("House not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("House is updated successfully", HttpStatus.ACCEPTED);
	}

	@PutMapping("/{street}/{number}")
	public ResponseEntity<Object> update(@Valid @PathVariable(value = "street") String street,
			@PathVariable(value = "number") String number, @Valid @RequestBody House house) throws Exception {
		try {
			House houseForUpdate = houseService.findByAddress(street, number);
			houseForUpdate.setApartments(house.getApartments());
			houseForUpdate.setApartmentsByLevel(house.getApartmentsByLevel());
			houseForUpdate.setEntrances(house.getEntrances());
			houseForUpdate.setLevels(house.getLevels());
			houseForUpdate.setNumber(house.getNumber().toLowerCase());
			houseForUpdate.setStreet(house.getStreet().toLowerCase());
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
	
	@DeleteMapping("/{street}/{number}") 
	public ResponseEntity<Object> delete(@PathVariable(value = "street") String street,	@PathVariable(value = "number") String number) {
		try {
			houseService.deleteByAddress(street, number);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("House not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("House is deleted successfully", HttpStatus.ACCEPTED);
	}

}
