package com.example.ticketonline.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketonline.Repository.EventoRepository;
import com.example.ticketonline.entity.Evento;

import javax.transaction.Transactional;

@Service
public class EventoServiceImpl  implements EventoService{
	
	@Autowired
	private final EventoRepository eventoRepo;	

	public EventoServiceImpl(EventoRepository eventoRepo) {
		super();
		this.eventoRepo = eventoRepo;
	}

	@Override
	@Transactional
	public Evento CrearEvento(Evento evento) {
		return eventoRepo.save(evento);
	}

	@Override
	@Transactional
	public Evento getEventoById(Integer id) {
		return eventoRepo.findById(id).get();
	}
	
	@Override
	@Transactional
	public List<Evento> findAll(String palabraClave){
		List<Evento> list = (List<Evento>) eventoRepo.findAll();
		List<Evento> listN = new ArrayList<>();				
		if(palabraClave != null) {
			if(palabraClave.trim().length()<=0) {
				return list;
			}
			if(palabraClave.trim().length()>0) {
				
				for (int i = 0; i < list.size(); i++) {
					if(palabraClave.equalsIgnoreCase(list.get(i).getNombre()) || 
							palabraClave.equalsIgnoreCase(list.get(i).getRecinto()) ||
							palabraClave.equalsIgnoreCase(list.get(i).getTipoevento())) 
					{
						Evento e = new Evento();
						e.setCantidadasiento(list.get(i).getCantidadasiento());
						e.setCantidaddisponible(list.get(i).getCantidaddisponible());
						e.setDescripcion(list.get(i).getDescripcion());
						e.setNombre(list.get(i).getNombre());
						e.setFecha(list.get(i).getFecha());
						e.setHora(list.get(i).getHora());
						e.setId(list.get(i).getId());
						e.setTipoevento(list.get(i).getTipoevento());
						e.setRecinto(list.get(i).getRecinto());
						e.setFoto(list.get(i).getFoto());
						listN.add(e);
					}
				}
				if(listN.isEmpty() || listN==null) {
					return list;
				}
				return listN;
			}		
		}		
		return list;	
	}

	@Override
	@Transactional
	public void deleteEvento(Integer id) {
		eventoRepo.deleteById(id);
	}

	@Override
	@Transactional
	public void actualizarEvento(Evento evento, Integer id) {
		Optional<Evento> eventoId = eventoRepo.findById(id);
		Evento eventoN = eventoId.get();
		eventoN.setCantidadasiento(evento.getCantidadasiento());
		eventoN.setDescripcion(evento.getDescripcion());
		eventoN.setFecha(evento.getFecha());
		eventoN.setHora(evento.getHora());
		eventoN.setNombre(evento.getNombre());
		eventoN.setRecinto(evento.getRecinto());
		eventoN.setTipoevento(evento.getTipoevento());
		eventoRepo.save(eventoN);
	}

	@Override
	@Transactional
	public Evento encontrarEvento(Evento evento) {
		return eventoRepo.findById(evento.getId()).orElse(null);
	}

	@Override
	@Transactional
	public List<Evento> findByFecha(Date fechaFiltro) {
		List<Evento> eventos = (List<Evento>) eventoRepo.findAll();
		if(fechaFiltro!=null) {
			List<Evento> listN = new ArrayList<>();	
			for (int i = 0; i < eventos.size(); i++) {
				if(eventos.get(i).getFecha() == fechaFiltro) {
					Evento e = new Evento();
					e.setCantidadasiento(eventos.get(i).getCantidadasiento());
					e.setCantidaddisponible(eventos.get(i).getCantidaddisponible());
					e.setDescripcion(eventos.get(i).getDescripcion());
					e.setNombre(eventos.get(i).getNombre());
					e.setFecha(eventos.get(i).getFecha());
					e.setHora(eventos.get(i).getHora());
					e.setId(eventos.get(i).getId());
					e.setTipoevento(eventos.get(i).getTipoevento());
					e.setRecinto(eventos.get(i).getRecinto());
					listN.add(e);
				}
				
			}
			return listN;
		}
		return eventos;
	}

	@Override
	public List<Evento> encontrarTodos() {
		return eventoRepo.encontrarTodos();
	}
	
}
