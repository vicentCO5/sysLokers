package com.aps.beans.catalogos;
public class Empresa {
	private int id;
	

	/**
	 * Clave de la empresa
	 */
	private String clave;
	
	/**
	 * Nombre de la empresa
	 */
	private String  nombre;
	
	/**
	 * Logotipo de la empresa
	 */
//	private String logo;
	
	/**
	 * Pais
	 */
	private String pais;
	
	/**
	 * RUC
	 */
	private String ruc;
	
//	/**
//	 * Tipo de Empresa
//	 */
//	private Character tipo;
//	
	
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public String getLogo() {
//		return logo;
//	}
//
//	public void setLogo(String logo) {
//		this.logo = logo;
//	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

//	public Character getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(Character tipo) {
//		this.tipo = tipo;
//	}
}
