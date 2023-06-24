package com.example.ticketonline.Service;

import java.util.List;

import com.example.ticketonline.entity.Cliente;


public interface ClienteService {
	
	public Cliente CrearCliente(Cliente cliente);

	public Cliente getClienteById(Integer id);

	public List<Cliente> findAll();

	public void deleteCliente(Integer id);
	
	public void actualizarCliente(Cliente cliente, Integer id);
	
    public Cliente encontrarCliente(Cliente cliente);

}
