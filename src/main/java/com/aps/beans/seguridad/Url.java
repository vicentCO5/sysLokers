package com.aps.beans.seguridad;

public class Url {
	private int id;
	private String nombre;
	private int tipo;
	private int parentmenuid;
	private String url;
	private int urlp_id;
	
	public int getParentmenuid() {
		return parentmenuid;
	}
	public void setParentmenuid(int parentmenuid) {
		this.parentmenuid = parentmenuid;
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
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public int getUrlp_id() {
		return urlp_id;
	}
	public void setUrlp_id(int urlp_id) {
		this.urlp_id = urlp_id;
	}
}
