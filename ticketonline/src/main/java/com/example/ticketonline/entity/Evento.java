package com.example.ticketonline.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "evento")
public class Evento implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 255)
	@Length(min = 3, max = 255)
	private String nombre;
	private Date fecha;
	private Time hora;
	@NotBlank
	@NotNull
	@NotEmpty
	@Length(min = 3, max = 255)
	private String recinto;
	@NotBlank
	@NotNull
	@NotEmpty
	@Length(min = 3, max = 255)
	private String descripcion;
	@Column(name = "cantidadasiento")
	private int cantidadasiento;
	private int cantidaddisponible;
	private String tipoevento;
	private String foto;
	public Evento(int id,
			@NotBlank @NotNull @NotEmpty @Size(min = 3, max = 255) @Length(min = 3, max = 255) String nombre,
			Date fecha, Time hora, @NotBlank @NotNull @NotEmpty @Length(min = 3, max = 255) String recinto,
			@NotBlank @NotNull @NotEmpty @Length(min = 3, max = 255) String descripcion, int cantidadasiento,
			int cantidaddisponible, String tipoevento, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.recinto = recinto;
		this.descripcion = descripcion;
		this.cantidadasiento = cantidadasiento;
		this.cantidaddisponible = cantidaddisponible;
		this.tipoevento = tipoevento;
		this.foto = foto;
	}
	public Evento() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getRecinto() {
		return recinto;
	}
	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidadasiento() {
		return cantidadasiento;
	}
	public void setCantidadasiento(int cantidadasiento) {
		this.cantidadasiento = cantidadasiento;
	}
	public int getCantidaddisponible() {
		return cantidaddisponible;
	}
	public void setCantidaddisponible(int cantidaddisponible) {
		this.cantidaddisponible = cantidaddisponible;
	}
	public String getTipoevento() {
		return tipoevento;
	}
	public void setTipoevento(String tipoevento) {
		this.tipoevento = tipoevento;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", hora=" + hora + ", recinto="
				+ recinto + ", descripcion=" + descripcion + ", cantidadasiento=" + cantidadasiento
				+ ", cantidaddisponible=" + cantidaddisponible + ", tipoevento=" + tipoevento + ", foto=" + foto + "]";
	}
	
	
	
	

}
