package com.aps.beans.catalogos;

public class Marca {
	/**Clave de la marca
	 *  */
	private int clave;
		
	/** Decripcion de la marca
	 *  */
	private String descripcion;	
	
	/** Orden de Presentacion
	 * */
	private String clasificacion;
	
	/*
	 * id de la empresa
	 * */
	private int empresa_id;
	
	/**
	 * 
	 */
	public Marca() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param clave
	 * @param descripcion
	 * @param clasificacion
	 * @param empresa_id
	 */
	public Marca(int clave, String descripcion, String clasificacion, int empresa_id) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.clasificacion = clasificacion;
		this.empresa_id = empresa_id;
	}

	/**
	 * @return the clave
	 */
	public int getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(int clave) {
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
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * @param clasificacion the clasificacion to set
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
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
