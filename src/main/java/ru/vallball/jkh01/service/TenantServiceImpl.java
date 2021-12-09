package ru.vallball.jkh01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.Tenant;
import ru.vallball.jkh01.repository.TenantRepository;

@Service
@Transactional
public class TenantServiceImpl implements TenantService{
	
	static final Logger logger = LoggerFactory.getLogger(TenantService.class);
	
	@Autowired
	TenantRepository tenantRepository;

	@Override
	public void save(Tenant tenant) {
		logger.info("---------------------" + tenant.toString());
		logger.info("---------------------" + tenant.getApartment().toString());
		tenantRepository.save(tenant);
	}

	@Override
	public List<Tenant> list() {
		return tenantRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		tenantRepository.deleteById(id);
	}

	@Override
	public Tenant findById(Long id) {
		return tenantRepository.findById(id).get();
	}
}
