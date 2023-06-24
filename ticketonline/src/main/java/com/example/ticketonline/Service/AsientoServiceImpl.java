package com.example.ticketonline.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketonline.Repository.AsientoRepository;
import com.example.ticketonline.entity.Asiento;

import javax.transaction.Transactional;

@Service
public class AsientoServiceImpl implements AsientoService {
	@Autowired
	private final AsientoRepository asientoRepo;

	public AsientoServiceImpl(AsientoRepository asientoRepo) {
		super();
		this.asientoRepo = asientoRepo;
	}

	@Override
	@Transactional
	public Asiento CrearAsiento(Asiento asiento) {
		return asientoRepo.save(asiento);
	}

	@Override
	@Transactional
	public Asiento getAsientoById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Asiento> findAll() {
		return (List<Asiento>) asientoRepo.findAll();
	}

	@Override
	@Transactional
	public void deleteAsiento(String id) {
		asientoRepo.deleteById(id);
	}

	@Override
	@Transactional
	public void actualizarAsiento(Asiento asiento, String id) {

	}

	@Override
	@Transactional
	public Asiento encontrarAsiento(Asiento asiento) {
		return asientoRepo.findById(asiento.getId()).orElse(null);
	}

}
