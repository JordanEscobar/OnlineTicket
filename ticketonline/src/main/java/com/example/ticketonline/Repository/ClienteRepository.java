package com.example.ticketonline.Repository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ticketonline.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

}
