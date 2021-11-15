package ru.vallball.jkh01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.jkh01.model.House;

public interface HouseRepository extends JpaRepository<House, Long>{
	
}
