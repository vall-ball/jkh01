package ru.vallball.jkh01.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.repository.ApartmentRepository;
import ru.vallball.jkh01.repository.HouseRepository;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

	static final Logger logger = LoggerFactory.getLogger(HouseService.class);

	@Autowired
	HouseRepository houseRepository;

	@Autowired
	ApartmentRepository apartmentRepository;

	@Autowired
	MyHardHouseValidator validator;

	@Override
	public void save(House house) throws Exception {
		house.setStreet(house.getStreet().toLowerCase());
		house.setNumber(house.getNumber().toLowerCase());
		if (!validator.isUnique(house)) {
			throw new Exception("The number and the street must be unique");
		}
		house.createApartments();
		houseRepository.save(house);
		for (Apartment a : house.getApartments()) {
			apartmentRepository.save(a);
		}
	}

	@Override
	public void update(Long id, Map<String, Object> changes) throws Exception {
		House houseForUpdate = findById(id);
		if (changes.containsKey("street") && !changes.get("street").equals(houseForUpdate.getStreet())
				|| changes.containsKey("number") && !changes.get("number").equals(houseForUpdate.getNumber())) {
			if (!validator.isUnique(id, changes)) {
				throw new Exception("The number and the street must be unique");
			}
		}
		boolean check = validator.isFieldsChanged(id, changes);
		changes.forEach((change, value) -> {
			switch (change) {
			case "street":
				houseForUpdate.setStreet(((String) value).toLowerCase());
				break;
			case "number":
				houseForUpdate.setNumber((String.valueOf(value)).toLowerCase());
				break;
			case "entrances":
				houseForUpdate.setEntrances(((int) value));
				break;
			case "levels":
				houseForUpdate.setLevels(((int) value));
				break;
			case "apartmentsByLevel":
				houseForUpdate.setApartmentsByLevel(((int) value));
				break;
			}
		});

		if (check) {
			for (Apartment a : houseForUpdate.getApartments()) {
				apartmentRepository.delete(a);
			}
			houseForUpdate.createApartments();
		}

		houseRepository.save(houseForUpdate);
		for (Apartment a : houseForUpdate.getApartments()) {
			apartmentRepository.save(a);
		}

	}

	@Override
	public void update(String street, String number, Map<String, Object> changes) throws Exception {
		House houseForUpdate = findByAddress(street, number);
		if (changes.containsKey("street") && !changes.get("street").equals(houseForUpdate.getStreet())
				|| changes.containsKey("number") && !changes.get("number").equals(houseForUpdate.getNumber())) {
			if (!validator.isUnique(houseForUpdate.getId(), changes)) {
				throw new Exception("The number and the street must be unique");
			}
		}
		boolean check = validator.isFieldsChangedByAddress(street, number, changes);
		changes.forEach((change, value) -> {
			switch (change) {
			case "street":
				houseForUpdate.setStreet(((String) value).toLowerCase());
				break;
			case "number":
				houseForUpdate.setNumber((String.valueOf(value)).toLowerCase());
				break;
			case "entrances":
				houseForUpdate.setEntrances(((int) value));
				break;
			case "levels":
				houseForUpdate.setLevels(((int) value));
				break;
			case "apartmentsByLevel":
				houseForUpdate.setApartmentsByLevel(((int) value));
				break;
			}
		});

		if (check) {
			for (Apartment a : houseForUpdate.getApartments()) {
				apartmentRepository.delete(a);
			}
			houseForUpdate.createApartments();
		}

		houseRepository.save(houseForUpdate);
		for (Apartment a : houseForUpdate.getApartments()) {
			apartmentRepository.save(a);
		}

	}

	@Override
	public List<House> list() {
		return houseRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		houseRepository.deleteById(id);
	}

	@Override
	public House findById(Long id) {
		return houseRepository.findById(id).get();
	}

	@Override
	public House findByAddress(String street, String number) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
		if (house == null) {
			throw new NoSuchElementException();
		}
		return house;
	}

	@Override
	public List<House> listByStreet(String street) {
		return houseRepository.findByStreetIgnoreCase(street);
	}

	@Override
	public void deleteByAddress(String street, String number) {
		long l = houseRepository.deleteByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
		if (l == 0) {
			throw new EmptyResultDataAccessException((int) l);
		}
	}

	@Override
	public void update(Long id, House house) throws Exception {
		House houseForUpdate = findById(id);
		if (!house.getStreet().equals(houseForUpdate.getStreet())
				|| !house.getNumber().equals(houseForUpdate.getNumber())) {
			if (!validator.isUnique(house)) {
				throw new Exception("The number and the street must be unique");
			}
			boolean check = validator.isFieldsNotChanged(id, house);
			houseForUpdate.setApartmentsByLevel(house.getApartmentsByLevel());
			houseForUpdate.setEntrances(house.getEntrances());
			houseForUpdate.setLevels(house.getLevels());
			houseForUpdate.setNumber(house.getNumber());
			houseForUpdate.setStreet(house.getStreet());
			if (check) {
				for (Apartment a : houseForUpdate.getApartments()) {
					apartmentRepository.delete(a);
				}
				houseForUpdate.createApartments();
			}

			houseRepository.save(houseForUpdate);
			for (Apartment a : houseForUpdate.getApartments()) {
				apartmentRepository.save(a);
			}

		}

	}
}
