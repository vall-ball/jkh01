package ru.vallball.jkh01.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.vallball.jkh01.jail.ApartmentValidator;
import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.service.ApartmentService;

@RestController
@RequestMapping(value = "/apartments")
public class ApartmentController {

	@Autowired
	ApartmentService apartmentService;

	@GetMapping
	public List<Apartment> list() {
		return apartmentService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable(value = "id") Long id) {
		try {
			return ResponseEntity.ok(apartmentService.findById(id));
		} catch (Exception e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Apartment apartment) {
		try {
			apartmentService.save(apartment);
			return new ResponseEntity<>("Apartment is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@Valid @PathVariable(value = "id") Long id, @RequestBody Apartment apartment) {
		try {
			Apartment apartmentForUpdate = apartmentService.findById(id);
			apartmentForUpdate.setArea(apartment.getArea());
			apartmentForUpdate.setEntrance(apartment.getEntrance());
			apartmentForUpdate.setHouse(apartment.getHouse());
			apartmentForUpdate.setHowManyRooms(apartment.getHowManyRooms());
			apartmentForUpdate.setHowManyTenants(apartment.getHowManyTenants());
			apartmentForUpdate.setLevel(apartment.getLevel());
			apartmentForUpdate.setNumber(apartment.getNumber());
			apartmentForUpdate.setTenant(apartment.getTenant());
			apartmentService.save(apartmentForUpdate);
		} catch (Exception e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is updated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			apartmentService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is deleted successfully", HttpStatus.ACCEPTED);
	}

	@GetMapping("/{street}/{number}")
	public List<Apartment> listByHouse(@PathVariable(value = "street") String street,
			@PathVariable(value = "number") String number) {
		return apartmentService.listByHome(street, number);
	}
}
