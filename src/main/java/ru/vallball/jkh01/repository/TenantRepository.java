package ru.vallball.jkh01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.jkh01.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long>{
	
	public Tenant findByNameAndLastname(String name, String lastname);
	
	public long deleteByNameAndLastname(String name, String lastname);

}
