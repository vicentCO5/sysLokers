package com.aps.beans.catalogos;

/**
 * @author Gem
 *
 */
public class Sublinea {
	/**Clave de la Sublinea
	 * @MyB_codigo */
	private Integer clave;
		
	/** Decripcion de la sublinea
	 * @MyB_DESCRIPCION */
	private String descripcion;
	
	private int empresa_id;

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
	 * @return the empresa_did
	 */
	public int getEmpresa_id() {
		return empresa_id;
	}

	/**
	 * @param empresa_did the empresa_did to set
	 */
	public void setEmpresa_id(int empresa_did) {
		this.empresa_id = empresa_did;
	}

	/**
	 * @param clave
	 * @param descripcion
	 * @param empresa_id
	 */
	public Sublinea(Integer clave, String descripcion, int empresa_id) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.empresa_id = empresa_id;
	}

	/**
	 * 
	 */
	public Sublinea() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
