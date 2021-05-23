package com.aps.beans.catalogos;

public class Departamento {
	/** Numero del Departamento o linea
	 * @String 
	 * @MyB_CODLINEA */
	private String clave;		
	
	/** Nombre del Departamento
	 * @String 
	 * @MyB_DESCRIPCION */
	private String descripcion;
	
	/** Orden del Departamento o linea
	 * @String 
	 * @MyB_ORDENPRESENTACION */
	private String ordenPresentacion;
	private int empresa_id;
	private int departamento_id;
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
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
	 * @return the ordenPresentacion
	 */
	public String getOrdenPresentacion() {
		return ordenPresentacion;
	}
	/**
	 * @param ordenPresentacion the ordenPresentacion to set
	 */
	public void setOrdenPresentacion(String ordenPresentacion) {
		this.ordenPresentacion = ordenPresentacion;
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
	/**
	 * @return the departamento_id
	 */
	public int getDepartamento_id() {
		return departamento_id;
	}
	/**
	 * @param departamento_id the departamento_id to set
	 */
	public void setDepartamento_id(int departamento_id) {
		this.departamento_id = departamento_id;
	}
	/**
	 * @param clave
	 * @param descripcion
	 * @param ordenPresentacion
	 * @param empresa_id
	 * @param departamento_id
	 */
	public Departamento(String clave, String descripcion, String ordenPresentacion, int empresa_id) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.ordenPresentacion = ordenPresentacion;
		this.empresa_id = empresa_id;
	}
	/**
	 * 
	 */
	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
