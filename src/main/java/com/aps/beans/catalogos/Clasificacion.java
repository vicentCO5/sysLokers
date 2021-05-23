package com.aps.beans.catalogos;

import org.springframework.format.annotation.DateTimeFormat;

public class Clasificacion {
	
	private int empresa_id;	
	private String clave;
	private String nombre;
	private int estatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private String timeCreate;
	private int usuario_id;
	
	public int getEmpresa_id() {
		return empresa_id;
	}
	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public String getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	
	/* cons ...! */
	public Clasificacion() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*contrucctor
	 * @param */
	public Clasificacion(int empresa_id,String clave, String nombre, int estatus, String timeCreate, int usuario_id) {
		this.empresa_id = empresa_id;
		this.clave   = clave;
		this.nombre  =  nombre;
		this.estatus =  estatus;
		this.timeCreate = timeCreate;
		this.usuario_id = usuario_id;
	}
	
	
}
