package com.example.ticketonline.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	@NotNull
	@NotEmpty
	@Length(min = 6, max = 11)
	@Pattern(regexp = "^[0-9]+-[0-9kK]{1}$", message = "El Rut debe ser sin puntos y con guión")
	private String rut;
	@NotBlank
	@NotNull
	@NotEmpty
	private String nombre;
	@NotBlank
	@NotNull
	@NotEmpty
	private String apellidos;
	@NotBlank
	@NotNull
	@NotEmpty
	@Length(min = 3, max = 255)
	private String direccion;
	@NotBlank
	@NotNull
	@NotEmpty
	@Email
	private String correo;
	@NotBlank
	@NotNull
	@NotEmpty
	private String celular;
	@NotBlank
	@NotNull
	@NotEmpty
	private String username;
	@NotBlank
	@NotNull
	@NotEmpty
	private String password;

	public Cliente() {
		super();
	}

	public Cliente(int id, String rut, String nombre, String apellidos, String direccion, String correo,
			String celular, String username, String password) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.correo = correo;
		this.celular = celular;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion="
				+ direccion + ", correo=" + correo + ", celular=" + celular + ", username=" + username + ", password="
				+ password + "]";
	}

}
