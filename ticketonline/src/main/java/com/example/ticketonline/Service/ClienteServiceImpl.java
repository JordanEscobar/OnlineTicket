 package com.example.ticketonline.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketonline.Repository.ClienteRepository;
import com.example.ticketonline.entity.Cliente;

import javax.transaction.Transactional;
@Service
public class ClienteServiceImpl implements ClienteService{
	@Autowired
	private final ClienteRepository clienteRepo;
	
	public ClienteServiceImpl(ClienteRepository clienteRepo) {
		super();
		this.clienteRepo = clienteRepo;
	}

	@Override
	@Transactional
	public Cliente CrearCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	@Override
	@Transactional
	public Cliente getClienteById(Integer id) {
		return clienteRepo.findById(id).get();
	}

	@Override
	@Transactional
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepo.findAll();
	}

	@Override
	@Transactional
	public void deleteCliente(Integer id) {
		clienteRepo.deleteById(id);
	}

	@Override
	@Transactional
	public void actualizarCliente(Cliente cliente, Integer id) {
		Optional<Cliente> clienteId = clienteRepo.findById(id);
		Cliente clienteN = clienteId.get();
		clienteN.setId(cliente.getId());
		clienteN.setApellidos(cliente.getApellidos());
		clienteN.setCelular(cliente.getCelular());
		clienteN.setCorreo(cliente.getCorreo());
		clienteN.setDireccion(cliente.getDireccion());
		clienteN.setNombre(cliente.getNombre());
		clienteN.setRut(cliente.getRut());
		clienteN.setUsername(cliente.getUsername());
		clienteN.setPassword(cliente.getPassword());
		clienteRepo.save(clienteN);
	}

	@Override
	@Transactional
	public Cliente encontrarCliente(Cliente cliente) {
		return clienteRepo.findById(cliente.getId()).orElse(null);
	}

}
