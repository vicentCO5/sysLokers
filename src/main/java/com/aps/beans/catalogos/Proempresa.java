package com.aps.beans.catalogos;

public class Proempresa {
	Empresa empresa;
	Proveedor proveedor;
	private double porcentajedescuento;
	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
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
	 * @return the porcentajedescuento
	 */
	public double getPorcentajedescuento() {
		return porcentajedescuento;
	}
	/**
	 * @param porcentajedescuento the porcentajedescuento to set
	 */
	public void setPorcentajedescuento(double porcentajedescuento) {
		this.porcentajedescuento = porcentajedescuento;
	}
	/**
	 * 
	 */
	public Proempresa() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param empresa
	 * @param proveedor
	 * @param porcentajedescuento
	 */
	public Proempresa(Empresa empresa, Proveedor proveedor, double porcentajedescuento) {
		super();
		this.empresa = empresa;
		this.proveedor = proveedor;
		this.porcentajedescuento = porcentajedescuento;
	}
	
}
