package com.aps.beans.catalogos;

public class Almacen {
	
	
	private int id;
	private int empresa_id;	
	private String clave;
	private String nombre;	
	private int cp;
	private String direccion; 		 
	private String colonia; 	
	private String poblacion; 		
	private String numInterior;
	private String numExterior;
	private String municipio;	
	private String estado;
	private String pais;
	private char estatus;
	

	public int getEmpresa_id() {
		return empresa_id;
	}
	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getNumInterior() {
		return numInterior;
	}
	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}
	public String getNumExterior() {
		return numExterior;
	}
	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public char getEstatus() {
		return estatus;
	}
	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}
	public Almacen () {
		super();
	}
	public Almacen (int empresa_id, String clave, String nombre, int codigoPostal, String direccion, String colonia, String poblacion, String municipio, String pais, String numExterior, String numInterior, char estatus) {
		super();
		
		this.empresa_id = empresa_id;
		this.clave = clave;
		this.nombre = nombre;
		this.cp = codigoPostal;
		this.direccion = direccion;
		this.colonia = colonia;
		this.poblacion = poblacion;
		this.municipio = municipio;
		this.pais = pais;
		this.numExterior = numExterior;
		this.numInterior = numInterior;
		this.estatus = estatus;	
	}
	
	
	
		
}
