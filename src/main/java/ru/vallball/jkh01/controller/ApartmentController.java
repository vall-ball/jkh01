package ru.vallball.jkh01.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.service.ApartmentService;

@RestController
@RequestMapping(value = "/apartments")
public class ApartmentController {

	static final Logger logger = LoggerFactory.getLogger(ApartmentController.class);

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

	@GetMapping("/{street}/{number}")
	public List<Apartment> listByHouse(@PathVariable(value = "street") String street,
			@PathVariable(value = "number") String number) {
		return apartmentService.listByHome(street, number);
	}

	@GetMapping("/{street}/{numberOfHouse}/{numberOfApartment}")
	public ResponseEntity<Object> get(@PathVariable(value = "street") String street,
			@PathVariable(value = "numberOfHouse") String numberOfHouse,
			@PathVariable(value = "numberOfApartment") int numberOfApartment) {
		try {
			return ResponseEntity.ok(apartmentService.findByAddress(street, numberOfHouse, numberOfApartment));
		} catch (Exception e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		}
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

	@DeleteMapping("/{street}/{numberOfHouse}/{numberOfApartment}")
	public ResponseEntity<Object> delete(@PathVariable(value = "street") String street,
			@PathVariable(value = "numberOfHouse") String numberOfHouse,
			@PathVariable(value = "numberOfApartment") int numberOfApartment) {
		try {
			apartmentService.deleteByAddress(street, numberOfHouse, numberOfApartment);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is deleted successfully", HttpStatus.ACCEPTED);
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
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Apartment apartment) {
		try {
			apartmentService.update(id, apartment);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is updated successfully", HttpStatus.ACCEPTED);
	}

	@PutMapping("/{street}/{numberOfHouse}/{numberOfApartment}")
	public ResponseEntity<Object> update(@PathVariable(value = "street") String street,
			@PathVariable(value = "numberOfHouse") String numberOfHouse,
			@PathVariable(value = "numberOfApartment") int numberOfApartment, @Valid @RequestBody Apartment apartment) {
		try {
			apartmentService.update(street, numberOfHouse, numberOfApartment, apartment);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is updated successfully", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/addTenant/{id}")
	public ResponseEntity<Object> addTenant(@PathVariable(value = "id") Long id, @RequestBody Tenant tenant) {
		try {
			apartmentService.addTenant(tenant, id);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is updated successfully", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/addTenants/{id}")
	public ResponseEntity<Object> addTenants(@PathVariable(value = "id") Long id, @RequestBody Set<Tenant> tenants) {
		try {
			apartmentService.addTenants(tenants, id);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is updated successfully", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/removeTenant/{id}")
	public ResponseEntity<Object> removeTenant(@PathVariable(value = "id") Long id, @RequestBody Tenant tenant) {
		try {
			apartmentService.removeTenant(tenant, id);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Apartment not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Apartment is updated successfully", HttpStatus.ACCEPTED);
	}
	
}
