package com.example.ticketonline.Service;

import java.util.List;

import com.example.ticketonline.entity.Reserva;

public interface ReservaService {
	
	public Reserva CrearReserva(Reserva reserva);

	public Reserva getReservaById(Integer id);

	public List<Reserva> findAll();

	public void deleteReserva(Integer id);
	
	public void actualizarReserva(Reserva reserva, Integer id);
	
    public Reserva encontrarReserva(Reserva reserva);

}
