package com.example.ticketonline.Service;

import java.util.List;

import com.example.ticketonline.entity.Asiento;

public interface AsientoService {
	
	public Asiento CrearAsiento(Asiento asiento);

	public Asiento getAsientoById(Integer id);

	public List<Asiento> findAll();

	public void deleteAsiento(String id);
	
	public void actualizarAsiento(Asiento asiento, String id);
	
    public Asiento encontrarAsiento(Asiento asiento);

}
