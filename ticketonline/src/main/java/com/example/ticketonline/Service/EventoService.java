package com.example.ticketonline.Service;

import java.util.Date;
import java.util.List;

import com.example.ticketonline.entity.Evento;

public interface EventoService {
	
	public Evento CrearEvento(Evento evento);

	public Evento getEventoById(Integer id);
	
	public List<Evento> findAll(String palabraClave);
	
	public List<Evento> findByFecha(Date fechaFiltro);

	public void deleteEvento(Integer id);
	
	public void actualizarEvento(Evento evento, Integer id);
	
    public Evento encontrarEvento(Evento evento);
    
    public List<Evento> encontrarTodos();

}
