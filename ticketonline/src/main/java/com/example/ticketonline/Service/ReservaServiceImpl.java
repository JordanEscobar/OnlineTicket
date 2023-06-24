package com.example.ticketonline.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketonline.Repository.ReservaRepository;
import com.example.ticketonline.entity.Reserva;

import javax.transaction.Transactional;

@Service
public class ReservaServiceImpl implements ReservaService{
	@Autowired
	private final ReservaRepository reservaRepo;

	public ReservaServiceImpl(ReservaRepository reservaRepo) {
		super();
		this.reservaRepo = reservaRepo;
	}

	@Override
	@Transactional
	public Reserva CrearReserva(Reserva reserva) {
		return reservaRepo.save(reserva);
	}

	@Override
	public Reserva getReservaById(Integer id) {
		return reservaRepo.findById(id).get();
	}

	@Override
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaRepo.findAll();
	}

	@Override
	public void deleteReserva(Integer id) {
		if(Objects.isNull(id)) {
			System.out.println("El id es nulo y no puede ser eliminado");		
		}else {
			reservaRepo.deleteById(id);
		}
		
	}

	@Override
	public void actualizarReserva(Reserva reserva, Integer id) {
		Optional<Reserva> reservaId = reservaRepo.findById(id);
		Reserva reservaN = reservaId.get();
		reservaN.setCantidadentradas(reserva.getCantidadentradas());
		reservaN.setEstado(reserva.getEstado());
		reservaN.setFechacompra(reserva.getFechacompra());
		reservaN.setPreciounitario(reserva.getPreciounitario());
		reservaN.setTotalprecio(reserva.getTotalprecio());
		reservaRepo.save(reservaN);
	}

	@Override
	public Reserva encontrarReserva(Reserva reserva) {
		return reservaRepo.findById(reserva.getId()).orElse(null);
	}
}
