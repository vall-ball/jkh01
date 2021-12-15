package ru.vallball.jkh01.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.repository.ApartmentRepository;
import ru.vallball.jkh01.repository.HouseRepository;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

	static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);

	@Autowired
	ApartmentRepository apartmentRepository;

	@Autowired
	HouseRepository houseRepository;

	@Autowired
	MyHardApartmentValidator validator;

	@Override
	public void save(Apartment apartment) throws Exception {
		if (!validator.checkEntrance(apartment)) {
			throw new Exception(
					"The number of the entrance must be not more than the quantity of entrances in the house");
		}
		if (!validator.checkLevel(apartment)) {
			throw new Exception("The number of the level must be not more than the max level in the house");
		}
		if (!validator.isUnique(apartment)) {
			throw new Exception("The apartment with the same number exists in this house");
		}
		apartmentRepository.save(apartment);
	}

	@Override
	public List<Apartment> list() {
		return apartmentRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		apartmentRepository.deleteById(id);

	}

	@Override
	public Apartment findById(Long id) {
		return apartmentRepository.findById(id).get();
	}

	@Override
	public List<Apartment> listByHome(String street, String number) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
		return apartmentRepository.findByHouse(house);
	}

	@Override
	public Apartment findByAddress(String street, String numberOfHouse, int numberOfApartment) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, numberOfHouse);
		if (house == null) {
			throw new NoSuchElementException();
		}
		Apartment apartment = apartmentRepository.findByHouseAndNumber(house, numberOfApartment);
		if (apartment == null) {
			throw new NoSuchElementException();
		}
		return apartment;
	}

	@Override
	public void deleteByAddress(String street, String numberOfHouse, int numberOfApartment) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, numberOfHouse);
		if (house == null) {
			throw new EmptyResultDataAccessException(-1);
		}
		long l = apartmentRepository.deleteByHouseAndNumber(house, numberOfApartment);
		if (l == 0) {
			throw new EmptyResultDataAccessException((int) l);
		}
	}

	@Override
	public void update(Long id, Apartment apartment) throws Exception {
		Apartment apartmentForUpdate = findById(id);
		if (!apartment.getHouse().getId().equals(apartmentForUpdate.getHouse().getId())
				|| !(apartment.getNumber() == apartmentForUpdate.getNumber())) {
			if (!validator.isUnique(apartment)) {
				throw new Exception("The number and the house must be unique");
			}
		}
		if (!validator.checkEntrance(apartment)) {
			throw new Exception(
					"The number of the entrance must be not more than the numbers of the entrances of the house");
		}
		if (!validator.checkLevel(apartment)) {
			throw new Exception("The number of the level must be not more than the numbers of the levels of the house");
		}
		apartmentForUpdate.setArea(apartment.getArea());
		apartmentForUpdate.setEntrance(apartment.getEntrance());
		apartmentForUpdate.setHouse(apartment.getHouse());
		apartmentForUpdate.setHowManyRooms(apartment.getHowManyRooms());
		apartmentForUpdate.setHowManyTenants(apartment.getHowManyTenants());
		apartmentForUpdate.setLevel(apartment.getLevel());
		apartmentForUpdate.setNumber(apartment.getNumber());
		apartmentForUpdate.setTenants(apartment.getTenants());
		apartmentRepository.save(apartmentForUpdate);
	}

	@Override
	public void update(String street, String numberOfHouse, int numberOfApartment, Apartment apartment)
			throws Exception {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, numberOfHouse);
		if (house == null) {
			throw new NoSuchElementException();
		}
		Apartment apartmentForUpdate = apartmentRepository.findByHouseAndNumber(house, numberOfApartment);
		if (apartmentForUpdate == null) {
			throw new NoSuchElementException();
		}
		if (!apartment.getHouse().getId().equals(apartmentForUpdate.getHouse().getId())
				|| !(apartment.getNumber() == apartmentForUpdate.getNumber())) {
			if (!validator.isUnique(apartment)) {
				throw new Exception("The number and the house must be unique");
			}
		}
		if (!validator.checkEntrance(apartment)) {
			throw new Exception(
					"The number of the entrance must be not more than the numbers of the entrances of the house");
		}
		if (!validator.checkLevel(apartment)) {
			throw new Exception("The number of the level must be not more than the numbers of the levels of the house");
		}
		apartmentForUpdate.setArea(apartment.getArea());
		apartmentForUpdate.setEntrance(apartment.getEntrance());
		apartmentForUpdate.setHouse(apartment.getHouse());
		apartmentForUpdate.setHowManyRooms(apartment.getHowManyRooms());
		apartmentForUpdate.setHowManyTenants(apartment.getHowManyTenants());
		apartmentForUpdate.setLevel(apartment.getLevel());
		apartmentForUpdate.setNumber(apartment.getNumber());
		apartmentForUpdate.setTenants(apartment.getTenants());
		apartmentRepository.save(apartmentForUpdate);
	}

	@Override
	public void addTenant(Tenant tenant, Long id) {
		Apartment apartment = apartmentRepository.findById(id).get();
		apartment.getTenants().add(tenant);
		apartmentRepository.save(apartment);

	}

	@Override
	public void addTenants(Set<Tenant> tenants, Long id) {
		Apartment apartment = apartmentRepository.findById(id).get();
		apartment.getTenants().addAll(tenants);
		apartmentRepository.save(apartment);
	}

	@Override
	public void removeTenant(Tenant tenant, Long id) {
		Apartment apartment = apartmentRepository.findById(id).get();
		for (Tenant t : apartment.getTenants()) {
			if (t.getId().equals(tenant.getId())) {
				apartment.getTenants().remove(t);
				break;
			}
		}
		apartmentRepository.save(apartment);
	}

}
