package com.aps.beans.catalogos;

public class Subdepartamento {
	/** Clave de la sublinea
	 * @String
	 * @MyB_CODSUBLINEA*/
	private String clave;
	
	/** Descripcion 
	 * @String
	 * @MyB_DESCRIPCION*/
	private String descripcion;
	
	/** Orde de presentacion
	 * @String 
	 * @MyB_ORDENPRESENTACION*/ 
	private String ordenSublinea;
	
	/** Clave de la empresa Sublinea
	 * @MyB_CODEMPRESASUBLINEA 	*/
	private String cveempresa;

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
	 * @return the ordenSublinea
	 */
	public String getOrdenSublinea() {
		return ordenSublinea;
	}

	/**
	 * @param ordenSublinea the ordenSublinea to set
	 */
	public void setOrdenSublinea(String ordenSublinea) {
		this.ordenSublinea = ordenSublinea;
	}

	/**
	 * @return the cveempresa
	 */
	public String getCveempresa() {
		return cveempresa;
	}

	/**
	 * @param cveempresa the cveempresa to set
	 */
	public void setCveempresa(String cveempresa) {
		this.cveempresa = cveempresa;
	}

	/**
	 * @param clave
	 * @param descripcion
	 * @param ordenSublinea
	 * @param cveempresa
	 */
	public Subdepartamento(String clave, String descripcion, String ordenSublinea, String cveempresa) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.ordenSublinea = ordenSublinea;
		this.cveempresa = cveempresa;
	}

	/**
	 * 
	 */
	public Subdepartamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
