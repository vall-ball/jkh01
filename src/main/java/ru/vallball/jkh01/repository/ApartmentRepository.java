package ru.vallball.jkh01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.jkh01.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long>{

}
