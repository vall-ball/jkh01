package ru.vallball.jkh01.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.service.TenantService;

@RestController
@RequestMapping(value = "/tenants")
public class TenantController {

	@Autowired
	TenantService tenantService;

	@GetMapping
	public List<Tenant> list() {
		return tenantService.list();
	}

	@GetMapping("/{id}")
	public Tenant get(@PathVariable(value = "id") Long id) {
		return tenantService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Tenant tenant) {
		try {
			tenantService.save(tenant);
			return new ResponseEntity<>("Tenant is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Tenant is exist", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Tenant tenant) {
		try {
			tenantService.update(id, tenant);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Tenant not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("The tenant is updated successfully", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
		try {
			tenantService.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Tenant not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tenant is deleted successfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{name}/{lastname}")
	public ResponseEntity<Object> get(@PathVariable(value = "name") String name,
			@PathVariable(value = "lastname") String lastname) {
		try {
			return ResponseEntity.ok(tenantService.findByName(name, lastname));
		} catch (Exception e) {
			return new ResponseEntity<>("The tenant not found", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{name}/{lastname}")
	public ResponseEntity<Object> delete(@PathVariable(value = "name") String name,
			@PathVariable(value = "lastname") String lastname) {
		try {
			tenantService.deleteByName(name, lastname);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("The tenant not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("The tenant is deleted successfully", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{name}/{lastname}")
	public ResponseEntity<Object> update(@PathVariable(value = "name") String name,
			@PathVariable(value = "lastname") String lastname, @Valid @RequestBody Tenant tenant) {
		try {
			tenantService.update(name, lastname, tenant);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("The tenant not found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("The tenant is updated successfully", HttpStatus.ACCEPTED);
	}

}
