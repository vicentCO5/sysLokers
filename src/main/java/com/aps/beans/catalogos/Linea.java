package com.aps.beans.catalogos;

public class Linea {
	/**Clave de la marca
	 * codigo */
	private Integer clave;
		
	/** Decripcion de la linea
	 * DESCRIPCION */
	private String descripcion;
	
	/**
	 * clave empresa
	 */
	private int empresa_id;
	
	public Linea() {
		super();
	}
	/**
	 * @param clave
	 * @param descripcion
	 * @param empresa_id
	 */
	public Linea(Integer clave, String descripcion, int empresa_id) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.empresa_id = empresa_id;
	}
	/**
	 * @return the clave
	 */
	public Integer getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(Integer clave) {
		this.clave = clave;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the empresa_id
	 */
	public int getEmpresa_id() {
		return empresa_id;
	}
	/**
	 * @param empresa_id the empresa_id to set
	 */
	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}
	
}
