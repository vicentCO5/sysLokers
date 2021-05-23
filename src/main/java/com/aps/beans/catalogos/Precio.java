package com.aps.beans.catalogos;

public class Precio {


	private int id;
	private int empresa_id;
	private int numart_id;
	private String numart;
	private int clasificacion_id;
	private String clave;
	private double precio;
	private int estatus;
	private String timecreate;
	private int usuario_id;
	
	public Precio( int id, int empresa_id, int numart_id, String numart,int clasificacion_id, String clave, double precio,
			int estatus, String timecreate, int usuario_id) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.numart_id = numart_id;
		this.numart =  numart;
		this.clasificacion_id =  clasificacion_id;
		this.clave = clave;
		this.precio = precio;
		this.estatus = estatus;
		this.timecreate = timecreate;
		this.usuario_id = usuario_id;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmpresa_id() {
		return empresa_id;
	}
	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}
	public int getNumart_id() {
		return numart_id;
	}
	public void setNumart_id(int numart_id) {
		this.numart_id = numart_id;
	}
	public String getNumart() {
		return numart;
	}
	public void setNumart(String numart) {
		this.numart = numart;
	}
	public int getClasificacion_id() {
		return clasificacion_id;
	}
	public void setClasificacion_id(int clasificacion_id) {
		this.clasificacion_id = clasificacion_id;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public String getTimecreate() {
		return timecreate;
	}
	public void setTimecreate(String timecreate) {
		this.timecreate = timecreate;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public Precio () {
		super();
	}
}
