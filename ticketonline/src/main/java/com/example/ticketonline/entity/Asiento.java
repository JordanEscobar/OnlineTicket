package com.example.ticketonline.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "asiento")
public class Asiento {
	@Id
	private String id;
	private int id_evento;
	private int precio;
	@NotBlank
	@NotNull
	@NotEmpty
	private String estado;

	public Asiento() {
		super();
	}

	public Asiento(String id, int id_evento, int precio, String estado) {
		super();
		this.id = id;
		this.id_evento = id_evento;
		this.precio = precio;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getId_evento() {
		return id_evento;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Asiento [id=" + id + ", id_evento=" + id_evento + ", precio=" + precio + ", estado=" + estado + "]";
	}

}
