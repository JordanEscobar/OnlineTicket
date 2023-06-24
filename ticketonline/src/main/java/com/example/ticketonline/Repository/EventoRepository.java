package com.example.ticketonline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.ticketonline.entity.Evento;


public interface EventoRepository extends CrudRepository<Evento, Integer>{
@Query(value = "SELECT * FROM evento p WHERE p.nombre LIKE %?1% OR p.recinto LIKE %?1% OR p.tipoevento LIKE %?1%", nativeQuery = true)
public List<Evento> findAll(String nombre);

@Query(value = "SELECT * FROM evento", nativeQuery = true)
public List<Evento> encontrarTodos();
}
