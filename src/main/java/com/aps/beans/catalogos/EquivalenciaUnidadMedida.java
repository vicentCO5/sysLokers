package com.aps.beans.catalogos;

public class EquivalenciaUnidadMedida {
	/** 
	 * Clave de unidad de Origen
	 * @String 
	 * @MyB_CODUNIDADORIGEN
	 */
	private String unidadMedidaOrigen;
	
	/**
	 * Clave de unidad de Destino
	 * @String
	 * @MyB_CODUNIDADDESTINO
	 */
	private String unidadMedidaDestino;
	
	/** Factor de Convercion
	 * @Double
	 * @MyB_FACTORCONVERSION
	 */
	private double factorConversion;
	
	/** Fecha
	 * Date
	 * @MyB_FECHA
	 */
	private String   fecha;
	
	/** Fecha de Modificacion
	 * @Date
	 * @MyB_FECHAMODIFICACION
	 */
	private String  fechaModificacion;

	/**
	 * @return the unidadMedidaOrigen
	 */
	public String getUnidadMedidaOrigen() {
		return unidadMedidaOrigen;
	}

	/**
	 * @param unidadMedidaOrigen the unidadMedidaOrigen to set
	 */
	public void setUnidadMedidaOrigen(String unidadMedidaOrigen) {
		this.unidadMedidaOrigen = unidadMedidaOrigen;
	}

	/**
	 * @return the unidadMedidaDestino
	 */
	public String getUnidadMedidaDestino() {
		return unidadMedidaDestino;
	}

	/**
	 * @param unidadMedidaDestino the unidadMedidaDestino to set
	 */
	public void setUnidadMedidaDestino(String unidadMedidaDestino) {
		this.unidadMedidaDestino = unidadMedidaDestino;
	}

	/**
	 * @return the factorConvercion
	 */
	public double getFactorConversion() {
		return factorConversion;
	}

	/**
	 * @param factorConvercion the factorConvercion to set
	 */
	public void setFactorConvercion(double factorConversion) {
		this.factorConversion = factorConversion;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the fechaModificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * 
	 */
	public EquivalenciaUnidadMedida() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param unidadMedidaOrigen
	 * @param unidadMedidaDestino
	 * @param factorConvercion
	 * @param fecha
	 * @param fechaModificacion
	 */
	public EquivalenciaUnidadMedida(String unidadMedidaOrigen, String unidadMedidaDestino, double factorConversion,
			 String fechaModificacion) {
		super();
		this.unidadMedidaOrigen = unidadMedidaOrigen;
		this.unidadMedidaDestino = unidadMedidaDestino;
		this.factorConversion = factorConversion;
		this.fechaModificacion = fechaModificacion;
	}
	
}
