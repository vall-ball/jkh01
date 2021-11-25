package ru.vallball.jkh01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.jkh01.model.House;

public interface HouseRepository extends JpaRepository<House, Long>{
	
	public House findByStreetIgnoreCaseAndNumberIgnoreCase(String street, String number);
	
	public List<House> findByStreetIgnoreCase(String street);
	
	public long deleteByStreetIgnoreCaseAndNumberIgnoreCase(String street, String number);
}
