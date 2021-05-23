package com.aps.beans.catalogos;

public class Proart {
	Proveedor proveedor;
	Articulo articulo;
	private double costo;
	private double descuento;
	/** Clave Unidad de Medidad del Proveedor
	 */
	private String unidadProveedor;
	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}
	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	/**
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}
	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	/**
	 * @return the costo
	 */
	public double getCosto() {
		return costo;
	}
	/**
	 * @param costo the costo to set
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}
	/**
	 * @return the descuento
	 */
	public double getDescuento() {
		return descuento;
	}
	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	/**
	 * @return the unidadProveedor
	 */
	public String getUnidadProveedor() {
		return unidadProveedor;
	}
	/**
	 * @param unidadProveedor the unidadProveedor to set
	 */
	public void setUnidadProveedor(String unidadProveedor) {
		this.unidadProveedor = unidadProveedor;
	}

	/**
	 * @param proveedor
	 * @param articulo
	 * @param costo
	 * @param descuento
	 * @param unidadProveedor
	 */
	public Proart(Proveedor proveedor, Articulo articulo, double costo, double descuento,
			String unidadProveedor) {
		super();
		this.proveedor = proveedor;
		this.articulo = articulo;
		this.costo = costo;
		this.descuento = descuento;
		this.unidadProveedor = unidadProveedor;
	}
	/**
	 * 
	 */
	public Proart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
