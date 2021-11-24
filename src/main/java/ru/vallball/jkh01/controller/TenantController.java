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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.service.TenantService;

@RestController
@RequestMapping(value = "/tenants")
public class TenantController {

	@Autowired
	TenantService tenantService;

	@GetMapping
	@ResponseBody
	public List<Tenant> list() {
		return tenantService.list();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Tenant get(@PathVariable(value = "id") Long id) {
		return tenantService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Tenant tenant) {
		try {
			tenantService.save(tenant);
			return new ResponseEntity<>("Tenant is created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity("Tenant is exist", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody Tenant tenant) {
		try {
			Tenant tenantForUpdate = tenantService.findById(id);
			tenantForUpdate.setApartment(tenant.getApartment());
			tenantForUpdate.setLastname(tenant.getLastname());
			tenantForUpdate.setName(tenant.getName());
			tenantService.save(tenantForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Tenant not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tenant is updated successfully", HttpStatus.ACCEPTED);
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


}
