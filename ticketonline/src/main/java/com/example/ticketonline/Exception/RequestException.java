package com.example.ticketonline.Exception;

public class RequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String codigo;
	
	public RequestException(String codigo,String mensaje) {	
		super(mensaje);
		this.codigo = codigo;
	}

	public RequestException() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "RequestException [codigo=" + codigo + "]";
	}
	
	
	

}
