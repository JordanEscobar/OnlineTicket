package com.example.ticketonline.Web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ticketonline.Exception.RequestException;
import com.example.ticketonline.Service.AsientoService;
import com.example.ticketonline.Service.ClienteService;
import com.example.ticketonline.Service.EmailService;
import com.example.ticketonline.Service.EventoService;
import com.example.ticketonline.Service.ReservaService;
import com.example.ticketonline.entity.Asiento;
import com.example.ticketonline.entity.Cliente;
import com.example.ticketonline.entity.Evento;
import com.example.ticketonline.entity.Reserva;

import com.example.ticketonline.entity.CrearTokenModel;
import com.example.ticketonline.entity.RequestModel;
import com.example.ticketonline.entity.RespuestaVerificacionModel;
import com.example.ticketonline.Util.Constantes;

@Controller
public class HomeController {
	@Autowired
	private final EventoService eventoService;
	@Autowired
	private final AsientoService asientoService;
	@Autowired
	private final ClienteService clienteS;

	private final EmailService emailS;
	@Autowired
	private final ReservaService reservaS;

	public HomeController(EventoService eventoService, AsientoService asientoService, ClienteService clienteS,
			EmailService emailS, ReservaService reservaS) {
		super();
		this.eventoService = eventoService;
		this.asientoService = asientoService;
		this.clienteS = clienteS;
		this.emailS = emailS;
		this.reservaS = reservaS;
	}

	@GetMapping("/")
	public String listaEvento(Model model,@Param("palabraClave") String palabraClave,HttpSession sesion) {		
		var listaEvento = eventoService.findAll(palabraClave);		
		model.addAttribute("listaEvento", listaEvento);
		model.addAttribute("palabraClave", palabraClave);
		if(sesion!=null)
		{
			model.addAttribute("usuario",sesion.getAttribute("usuario"));
		}
		
		return "index";
	}
	
	@GetMapping("/perfil")
	public String perfil(Cliente cliente, Model model,HttpSession sesion) {
		if(sesion!=null)
		{
			Cliente clienteSesion = (Cliente) sesion.getAttribute("usuario");
			if(clienteSesion!=null) {
				List<Reserva> listaReserva = new ArrayList<>();//lista nueva para llenar
				var reservas = reservaS.findAll();//encuentra todas las reservas
				for (int i = 0; i < reservas.size(); i++) {//las recorre
					if (reservas.get(i).getId_cliente()==clienteSesion.getId()) {//pregunta si el id de la sesion es igual a alguna reserva
						Reserva r = new Reserva();//crea nueva reserva y settea
						r.setId(reservas.get(i).getId());
						r.setId_cliente(reservas.get(i).getId_cliente());
						r.setId_evento(reservas.get(i).getId_evento());
						r.setCantidadentradas(reservas.get(i).getCantidadentradas());
						r.setFechacompra(reservas.get(i).getFechacompra());
						r.setPreciounitario(reservas.get(i).getPreciounitario());
						r.setTotalprecio(reservas.get(i).getTotalprecio());
						r.setEstado(reservas.get(i).getEstado());
						listaReserva.add(r);//finalmente se guarda en la lista vacia
					}				
				}
			model.addAttribute("reservas",listaReserva);//se envia a traves del model las reservas a la vista
			model.addAttribute("usuario",clienteSesion);//enviamos la sesion del usuario
			
			return "perfil";//retornamos a la vista perfil
			}
		}
		return "login";
		
	}
	
	@PostMapping(path = "/ingresar", consumes = "application/x-www-form-urlencoded")
	public String ingresar(Cliente cliente,RedirectAttributes flash,Model model,HttpSession sesion) {		
		List<Cliente> listaCliente = clienteS.findAll();
		List<Reserva> listaReserva = new ArrayList<>();
		var reservas = reservaS.findAll();
		
		for (int i = 0; i < listaCliente.size(); i++) {
			if (cliente.getCorreo().equalsIgnoreCase(listaCliente.get(i).getCorreo())
					&& cliente.getPassword().equals(listaCliente.get(i).getPassword())) {

				cliente.setApellidos(listaCliente.get(i).getApellidos());
				cliente.setCelular(listaCliente.get(i).getCelular());
				cliente.setDireccion(listaCliente.get(i).getDireccion());
				cliente.setNombre(listaCliente.get(i).getNombre());
				cliente.setRut(listaCliente.get(i).getRut());
				cliente.setId(listaCliente.get(i).getId());
				cliente.setPassword(listaCliente.get(i).getPassword());
				cliente.setUsername(listaCliente.get(i).getUsername());
				sesion.setAttribute("usuario", cliente);
				model.addAttribute("usuario",sesion.getAttribute("usuario"));			
				
				for (int e = 0; e < reservas.size(); e++) {
					if (cliente.getId()==reservas.get(e).getId_cliente()) {
						Reserva r = new Reserva();
						r.setCantidadentradas(reservas.get(e).getCantidadentradas());
						r.setFechacompra(reservas.get(e).getFechacompra());
						r.setPreciounitario(reservas.get(e).getPreciounitario());
						r.setTotalprecio(reservas.get(e).getTotalprecio());
						listaReserva.add(r);						
					}				
				}							
				model.addAttribute("reservas",listaReserva);							
				return "perfil";
			}else {
				flash.addFlashAttribute("warning","Debe ingresar credenciales válidas");
				return "redirect:/login";
			}
		}
		return "redirect:/login";
	}

	@GetMapping("/reservar/{id}")
	public String reservar(Evento evento, Model model,HttpSession sesion) {
		var eventos = eventoService.encontrarEvento(evento);
		var asiento = asientoService.findAll();
		List<Asiento> asientoN = new ArrayList<>();
		for (var a : asiento) {
			if (a.getId_evento() == eventos.getId()) {
				asientoN.add(a);
			}
		}
		model.addAttribute("asientoN", asientoN);
		model.addAttribute("eventos", eventos);
		
		if(sesion!=null)
		{
			model.addAttribute("usuario",sesion.getAttribute("usuario"));
		}
		
		return "reservar";
	}

	@GetMapping("/reservaEvento/{id}")
	public String reservaEvento(Evento evento, Model model, @Param("cantidadasientos") Integer cantidadasientos,Cliente cliente,HttpSession sesion) {
		Cliente clienteSesion = (Cliente) sesion.getAttribute("usuario");
		if(clienteSesion==null)
		{
			return "login";
		}

		if(sesion!=null)
		{
			model.addAttribute("usuario",sesion.getAttribute("usuario"));
		}
		
		var eventos = eventoService.encontrarEvento(evento);
		var asientoL = asientoService.findAll();
		List<Asiento> asientoN = new ArrayList<>();
		for (var a : asientoL) {
			if (a.getId_evento() == eventos.getId()) {
				asientoN.add(a);
			}
		}
		Asiento asientos = new Asiento();
		asientos.setId(asientoN.get(0).getId());
		asientos.setEstado(asientoN.get(0).getEstado());
		asientos.setPrecio(asientoN.get(0).getPrecio());
		asientos.setId_evento(asientoN.get(0).getId_evento());
		if (cantidadasientos != null && cantidadasientos>0) {
			Integer total = cantidadasientos * asientos.getPrecio();
			if (total != null && total > 0) {
				model.addAttribute("total", total);
			}

		}
		model.addAttribute("asientos", asientos);
		model.addAttribute("eventos", eventos);
		return "reservaEvento";
	}

	@GetMapping("/resumenReserva/{id}/{precio}/{total}")
	public String resumenReserva(Evento evento, @PathVariable int id, @PathVariable int precio,@PathVariable String total,RedirectAttributes flash,
			Model model,HttpSession sesion) {		
		
		Cliente clienteSesion = (Cliente) sesion.getAttribute("usuario");
		
		if(clienteSesion==null)
		{
			return "login";
		}
		
		Asiento asientos = new Asiento();
		List<Asiento> listaAsiento = asientoService.findAll();
		var eventos = eventoService.encontrarEvento(evento);
		for (int i = 0; i < listaAsiento.size(); i++) {
			if (listaAsiento.get(i).getId_evento() == eventos.getId()) {
				asientos.setEstado(listaAsiento.get(i).getEstado());
				asientos.setPrecio(listaAsiento.get(i).getPrecio());
				asientos.setId(listaAsiento.get(i).getId());
				asientos.setId_evento(listaAsiento.get(i).getId_evento());
			}
		}
		
		Integer cantidad = 0;
		if(total != null) {
			cantidad = Integer.parseInt(total) / precio;
			model.addAttribute("cantidad", cantidad);
		}
		model.addAttribute("asientos", asientos);
		model.addAttribute("eventos", eventos);

		model.addAttribute("cantidadasientos", cantidad);

		if(total == null ) {
				System.out.println("Total: "+total );
				flash.addFlashAttribute("danger","Ingrese cantidad y presione en Calcular");
				return "redirect:/reservaEvento/" + id;			
		}

		Reserva reserva = new Reserva();
		reserva.setId_evento(id);
		reserva.setCantidadentradas(cantidad);
		reserva.setPreciounitario(precio);
		reserva.setTotalprecio(Integer.parseInt(total));	
		reserva.setFechacompra(new Date());
		reserva.setId_cliente(clienteSesion.getId());
		reserva.setEstado("pendiente");

		reservaS.CrearReserva(reserva);

		Integer entradasDisponibles = eventos.getCantidaddisponible() - cantidad;
		eventos.setCantidaddisponible(entradasDisponibles);

		Evento e = new Evento();
		e.setCantidadasiento(eventos.getCantidadasiento());
		e.setCantidaddisponible(entradasDisponibles);
		e.setDescripcion(eventos.getDescripcion());
		e.setFecha(eventos.getFecha());
		e.setHora(eventos.getHora());
		e.setId(id);
		e.setNombre(eventos.getNombre());
		e.setRecinto(eventos.getRecinto());
		e.setTipoevento(eventos.getTipoevento());
		eventoService.actualizarEvento(e, e.getId());

		List<Reserva> listaR = new ArrayList<>();
		List<Evento> listaE = new ArrayList<>();
		listaE.add(eventos);
		listaR.add(reserva);
		model.addAttribute("listaE", listaE);
		model.addAttribute("listaR", listaR);
		model.addAttribute("reserva", reserva);
		
		if(sesion!=null)
		{
			model.addAttribute("usuario",sesion.getAttribute("usuario"));
		}
		return "resumenReserva";
	}

	@GetMapping("/login")
	public String login(Cliente cliente,HttpSession sesion) {
		return "login";
	}

	@PostMapping(path = "/modificar", consumes = "application/x-www-form-urlencoded")
	public String modificar(@Valid Cliente cliente,Errors errores,RedirectAttributes flash, Model model,HttpSession sesion) {
		Cliente clienteSesion = (Cliente) sesion.getAttribute("usuario");
		var clientes = clienteS.findAll();
		Cliente c = new Cliente();
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getId()==cliente.getId()) {				
				c.setId(clientes.get(i).getId());
				c.setApellidos(clientes.get(i).getApellidos());
				c.setCelular(clientes.get(i).getCelular());
				c.setCorreo(clientes.get(i).getCorreo());
				c.setDireccion(clientes.get(i).getDireccion());
				c.setNombre(clientes.get(i).getNombre());
				c.setPassword(clientes.get(i).getPassword());
				c.setRut(clientes.get(i).getRut());
				c.setUsername(clientes.get(i).getUsername());				
			}		
		}		
		cliente.setUsername(c.getUsername());
		
		if (errores.hasErrors()) {
			flash.addFlashAttribute("warning","Valores Inválidos");
			return "redirect:/perfil";
		}
		
		clienteS.actualizarCliente(cliente, cliente.getId());
		
		flash.addFlashAttribute("success","Modificado Correctamente");
		sesion.setAttribute("usuario", cliente);
		model.addAttribute("usuario",sesion.getAttribute("usuario"));
		return "redirect:/perfil";
	}

	@GetMapping("/registroCliente")
	public String registroCliente(Cliente client,HttpSession sesion) {
		return "registroCliente";
	}

	@PostMapping(path = "/guardar", consumes = "application/x-www-form-urlencoded")
	public String guardar(@Valid Cliente cliente,Errors errores,RedirectAttributes flash, Model model,HttpSession sesion) {	
		
		if (errores.hasErrors()) {
			return "registroCliente";
		}
		Cliente clienteN = new Cliente();
		clienteN.setApellidos(cliente.getApellidos());
		clienteN.setCelular(cliente.getCelular());
		clienteN.setDireccion(cliente.getDireccion());
		clienteN.setNombre(cliente.getNombre());
		clienteN.setUsername(cliente.getUsername());
		clienteN.setPassword(cliente.getPassword());
		String rutExistente = "";
		for (int i = 0; i < clienteS.findAll().size(); i++) {
			if (cliente.getRut().equalsIgnoreCase(clienteS.findAll().get(i).getRut())) {
				rutExistente = clienteS.findAll().get(i).getRut();
			}
			if (cliente.getRut().equals(rutExistente)) {
				throw new RequestException("P-300", "El Rut es inválido");
			} else {
				clienteN.setRut(cliente.getRut());
			}
		}
		String correoExistente = "";
		for (int i = 0; i < clienteS.findAll().size(); i++) {
			if (cliente.getCorreo().equals(clienteS.findAll().get(i).getCorreo())) {
				correoExistente = clienteS.findAll().get(i).getCorreo();
			}
		}
		System.out.println("El correo es: " + correoExistente);
		if (cliente.getCorreo().equals(correoExistente)) {
			throw new RequestException("P-300", "El correo es inválido");

		} else {
			clienteN.setCorreo(cliente.getCorreo());
		}
		clienteS.CrearCliente(clienteN);
		emailS.sendSimpleMessage(clienteN.getCorreo(), "Bienvenido " + clienteN.getNombre(),
				"Gracias por preferirnos.");
		
		flash.addFlashAttribute("success","Cliente registrado exitosamente!!");
		return "redirect:/registroCliente";
	}
	
	

	@GetMapping("/cerrarSesion")
	public String cerrarSesion(Model model,HttpSession sesion) throws ServletException {
		sesion.invalidate();
		return "redirect:/";
	}

	@GetMapping("/eliminar/{id}/{eventosId}/{cantidad}")
	public String eliminar(Reserva reserva, @PathVariable int eventosId, @PathVariable int cantidad,HttpSession sesion) {
		if(sesion!=null) {
			if(reserva!=null ) {
				reservaS.deleteReserva(reserva.getId());	
			}
		}
		return "redirect:/";
	}
	
	@GetMapping("/registroeventos")
	public String formumarioEvento(Model model) {
		model.addAttribute("eventos",new Evento());
		return "registroeventos";
	}
	
	@PostMapping("/registroeventos")
	public String guardarEvento(@RequestParam(name = "file",required = false) MultipartFile foto, Evento evento,RedirectAttributes flash) {
		Evento ev = new Evento();
		ev.setCantidadasiento(evento.getCantidadasiento());
		ev.setCantidaddisponible(evento.getCantidaddisponible());
		ev.setDescripcion(evento.getDescripcion());
		ev.setFecha(new Date());
		ev.setHora(new Time(21));
		ev.setId(evento.getId());
		ev.setNombre(evento.getNombre());
		ev.setRecinto(evento.getRecinto());
		ev.setTipoevento(evento.getTipoevento());
		if(!foto.isEmpty()) {
			String ruta = "c://Temp//uploads";
			
			try {
				byte[] bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				evento.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ev.setFoto(evento.getFoto());
			eventoService.CrearEvento(ev);
			flash.addFlashAttribute("success","Foto subida");
		}
		return "redirect:/registroeventos";
	}
	
/**
	@GetMapping("/pagado/{id}/{eventosId}/{precio}/{total}")
	public String pagar(Reserva reserva,@PathVariable int eventosId, @PathVariable int precio,@PathVariable int total,Model model,HttpSession sesion) {
		Cliente clienteSesion = (Cliente) sesion.getAttribute("usuario");
		
		if(clienteSesion==null)
		{
			return "login";
		}
			
		var r = reservaS.encontrarReserva(reserva);
		System.out.println(r);

		Reserva reservaNueva = new Reserva();
		reservaNueva.setId(r.getId());
		reservaNueva.setCantidadentradas(r.getCantidadentradas());
		reservaNueva.setEstado("pagado");
		reservaNueva.setFechacompra(r.getFechacompra());
		reservaNueva.setId_cliente(r.getId_cliente());
		reservaNueva.setId_evento(r.getId_evento());
		reservaNueva.setPreciounitario(r.getPreciounitario());
		reservaNueva.setTotalprecio(r.getTotalprecio());
		reservaS.actualizarReserva(reservaNueva, reservaNueva.getId());
		System.out.println(reservaNueva);
		
		var eventos = eventoService.encontrarTodos();
		Evento e = new Evento();
		for (int i = 0; i < eventos.size(); i++) {
			if (eventos.get(i).getId() == r.getId_evento()) {				
				e.setCantidadasiento(eventos.get(i).getCantidadasiento());
				e.setCantidaddisponible(eventos.get(i).getCantidaddisponible());
				e.setDescripcion(eventos.get(i).getDescripcion());
				e.setFecha(eventos.get(i).getFecha());
				e.setHora(eventos.get(i).getHora());
				e.setId(eventos.get(i).getId());
				e.setNombre(eventos.get(i).getNombre());
				e.setRecinto(eventos.get(i).getRecinto());
				e.setTipoevento(eventos.get(i).getTipoevento());
			}			
		}
			
		model.addAttribute("eventos",e);
		model.addAttribute("reserva",reserva);
		
		emailS.sendSimpleMessage(clienteSesion.getCorreo(), "Compra realizada exitosamente en Online Ticket.",
				"Compra realizada exitosamente, Gracias por preferirnos, Por favor no responder a este mail.  "
				+ "Tus ticket online ya se encuentran reservados.");
		
		return "Pagado";
	}**/

	//Cada 180 segundos se eliminan las reservas que tienen un estado pendiente PRODUCE ERROR AL PAGAR O CANCELAR UNA RESERVA
	//@Scheduled(cron = "*/20 * * * * *")
	/**public void revisarEstadoReserva() {
		var listaReservas = reservaS.findAll();
		String pendiente = "pendiente";
		var contador = 0;
		for (int i = 0; i < listaReservas.size(); i++) {				
					if (listaReservas.get(i).getEstado().equals(pendiente)) {
							reservaS.deleteReserva(listaReservas.get(i).getId());
							contador = contador + 1;
						}	
		}
		System.out.println("Se eliminaron " + contador + " reservas pendientes.");
	}**/
		
	
	
	@GetMapping("/pagarWebpay/{id}/{eventosId}/{precio}/{total}")
    public String pagarWebpay(Reserva reserva,@PathVariable int eventosId, @PathVariable int precio,@PathVariable int total,Model model,HttpSession sesion){
        
        //Desacoplar esto... en este ejemplo todo estara aqui.
        
        RestTemplate restTemplate = new RestTemplate();

        //Configurando los cabeceros
        HttpHeaders headers = new HttpHeaders();
		headers.set("Tbk-Api-Key-Id", Constantes.WEBPAY_CODIGO_COMERCIO);
		headers.set("Tbk-Api-Key-Secret", Constantes.WEBPAY_CODIGO_SECRETO);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        //Generamos la peticion
        RequestModel post =new RequestModel("ordenCompra12rrdd", "sesion1234557545", total, "http://localhost:8080/respuesta/"+reserva.getId() +"/"+eventosId+ "/"+precio+"/"+total);
        //Construimos el json request
        //Asi pasamos los parametros como un jsonRequest.
        HttpEntity<RequestModel> request = new HttpEntity<>(post, headers);
        //enviamos la peticion post
        ResponseEntity<CrearTokenModel> response = restTemplate.postForEntity(Constantes.WEBPAY_URL, request, CrearTokenModel.class);
        
        model.addAttribute("response", response.getBody());

        return "pagar";
    }

    @GetMapping("/respuesta/{id}/{eventosId}/{precio}/{total}")
	public String respuesta(Reserva reserva,@PathVariable int eventosId, @PathVariable int precio,@PathVariable int total,HttpSession sesion,Model model, @RequestParam("token_ws") String token_ws) /*token_ws TIENE QUE LLAMARSE ASI */
	{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Tbk-Api-Key-Id", Constantes.WEBPAY_CODIGO_COMERCIO);
		headers.set("Tbk-Api-Key-Secret", Constantes.WEBPAY_CODIGO_SECRETO);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<RespuestaVerificacionModel> response = restTemplate.exchange(Constantes.WEBPAY_URL +"/"+token_ws, HttpMethod.PUT, entity, RespuestaVerificacionModel.class);
		
		RespuestaVerificacionModel respuesta = response.getBody();
		model.addAttribute("respuesta", respuesta);
		model.addAttribute("token_ws", token_ws);
		
		//
				
			Cliente clienteSesion = (Cliente) sesion.getAttribute("usuario");
				
			var r = reservaS.encontrarReserva(reserva);
			r.setEstado("pagado");
			reservaS.actualizarReserva(r, r.getId());
			
			var eventos = eventoService.encontrarTodos();
			Evento e = new Evento();
			for (int i = 0; i < eventos.size(); i++) {
				if (eventos.get(i).getId() == r.getId_evento()) {				
					e.setCantidadasiento(eventos.get(i).getCantidadasiento());
					e.setCantidaddisponible(eventos.get(i).getCantidaddisponible());
					e.setDescripcion(eventos.get(i).getDescripcion());
					e.setFecha(eventos.get(i).getFecha());
					e.setHora(eventos.get(i).getHora());
					e.setId(eventos.get(i).getId());
					e.setNombre(eventos.get(i).getNombre());
					e.setRecinto(eventos.get(i).getRecinto());
					e.setTipoevento(eventos.get(i).getTipoevento());
				}			
			}
				
			model.addAttribute("eventos",e);
			model.addAttribute("reserva",reserva);
			
			emailS.sendSimpleMessage(clienteSesion.getCorreo(), "Compra realizada exitosamente en Online Ticket.",
					"Compra realizada exitosamente, Gracias por preferirnos, Por favor no responder a este mail.  "
					+ "Tus ticket online ya se encuentran reservados.");
					
		return "respuesta";
	}
	
}
