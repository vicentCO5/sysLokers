package com.aps.beans.catalogos;

public class UnidadMedida {
	/**
	 * Clave Unidad de Medida 
	 * @String
	 * @MyB_CODUNIDADMEDIDA
	 */
	private String clave;
	
	/**
	 * Descripcion 
	 * @String
	 * @MyB_DESCRIPCION
	 */
	private String descripcion;
	
	/**
	 * Clave de la unidad Minima
	 * @String
	 * @MyB_CODUNIDADMINIMA
	 */
	private String unidadMinima;
	
	/**
	 * Valor Minimo
	 * @Double
	 * @MyB_VALORMINIMO
	 */
	private Double valorMinimo;
	
	/**
	 * Variable de SI (1) NO (0) Es unidad minima
	 * @Short
	 * @MyB_ESUNIDADMINIMA
	 */
	private char esunidadMinima;
	
	/**
	 * Clave del estado
	 * @Short
	 * @MyB_CODESTADO	
	 */
	private char status;	
	
	/**Fecha del Cambio 
	 * @MyB_FECHACAMBIOESTADO
	 * */
	private String fechaCambio;

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
	 * @return the unidadMinima
	 */
	public String getUnidadMinima() {
		return unidadMinima;
	}

	/**
	 * @param unidadMinima the unidadMinima to set
	 */
	public void setUnidadMinima(String unidadMinima) {
		this.unidadMinima = unidadMinima;
	}

	/**
	 * @return the valorMinimo
	 */
	public Double getValorMinimo() {
		return valorMinimo;
	}

	/**
	 * @param valorMinimo the valorMinimo to set
	 */
	public void setValorMinimo(Double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	/**
	 * @return the esunidadMinima
	 */
	public char getEsunidadMinima() {
		return esunidadMinima;
	}

	/**
	 * @param esunidadMinima the esunidadMinima to set
	 */
	public void setEsunidadMinima(char esunidadMinima) {
		this.esunidadMinima = esunidadMinima;
	}

	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * @return the fechaCambio
	 */
	public String getFechaCambio() {
		return fechaCambio;
	}

	/**
	 * @param fechaCambio the fechaCambio to set
	 */
	public void setFechaCambio(String fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	/**
	 * 
	 */
	public UnidadMedida() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param clave
	 * @param descripcion
	 * @param unidadMinima
	 * @param valorMinimo
	 * @param esunidadMinima
	 * @param status
	 * @param fechaCambio
	 */
	public UnidadMedida(String clave, String descripcion, String unidadMinima, Double valorMinimo, char esunidadMinima,
			char status, String fechaCambio) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.unidadMinima = unidadMinima;
		this.valorMinimo = valorMinimo;
		this.esunidadMinima = esunidadMinima;
		this.status = status;
		this.fechaCambio = fechaCambio;
	}
	
}
