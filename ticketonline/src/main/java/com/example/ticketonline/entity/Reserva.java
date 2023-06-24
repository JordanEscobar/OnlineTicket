package com.example.ticketonline.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int id_evento;
	private Integer cantidadentradas;
	private Integer preciounitario;
	private Integer totalprecio;
	private Date fechacompra;
	private int id_cliente;
	@NotBlank
	@NotNull
	@NotEmpty
	private String estado;
	public Reserva() {
		super();
	}
	public Reserva(Integer id, int id_evento, int cantidadentradas, int preciounitario, int totalprecio, Date fechacompra,
			int id_cliente, @NotBlank @NotNull @NotEmpty String estado) {
		super();
		this.id = id;
		this.id_evento = id_evento;
		this.cantidadentradas = cantidadentradas;
		this.preciounitario = preciounitario;
		this.totalprecio = totalprecio;
		this.fechacompra = fechacompra;
		this.id_cliente = id_cliente;
		this.estado = estado;
	}
	public Integer getId() {
		return id;
		
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getId_evento() {
		return id_evento;
	}
	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}
	public Integer getCantidadentradas() {
		return cantidadentradas;
	}
	public void setCantidadentradas(Integer cantidadentradas) {
		this.cantidadentradas = cantidadentradas;
	}
	public Integer getPreciounitario() {
		return preciounitario;
	}
	public void setPreciounitario(Integer preciounitario) {
		this.preciounitario = preciounitario;
	}
	public Integer getTotalprecio() {
		return totalprecio;
	}
	public void setTotalprecio(Integer totalprecio) {
		this.totalprecio = totalprecio;
	}
	public Date getFechacompra() {
		return fechacompra;
	}
	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", id_evento=" + id_evento + ", cantidadentradas=" + cantidadentradas
				+ ", preciounitario=" + preciounitario + ", totalprecio=" + totalprecio + ", fechacompra=" + fechacompra
				+ ", id_cliente=" + id_cliente + ", estado=" + estado + "]";
	}
	
	
	
	
	

}
