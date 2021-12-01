package ru.vallball.jkh01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.House;

public interface ApartmentRepository extends JpaRepository<Apartment, Long>{
	
	public List<Apartment> findByHouse(House house);
	
	public Apartment findByHouseAndNumber(House house, int number);
	
	public long deleteByHouseAndNumber(House house, int number);
}
