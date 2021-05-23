package com.aps.beans.catalogos;

public class Artxempresa {
	/** Clave del articulo	*/
	Articulo articulo;
	
	/** Clave de Empresa */
	Empresa empresa;	
	
	Marca marca ;
	
	Linea linea	;
	
	Sublinea sublinea;
	
	Sublineados sublineados;
	Departamento departamento;
	Subdepartamento subedpartamento;
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
	 * @return the marca
	 */
	public Marca getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	/**
	 * @return the linea
	 */
	public Linea getLinea() {
		return linea;
	}
	/**
	 * @param linea the linea to set
	 */
	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	/**
	 * @return the sublinea
	 */
	public Sublinea getSublinea() {
		return sublinea;
	}
	/**
	 * @param sublinea the sublinea to set
	 */
	public void setSublinea(Sublinea sublinea) {
		this.sublinea = sublinea;
	}
	/**
	 * @return the sublineados
	 */
	public Sublineados getSublineados() {
		return sublineados;
	}
	/**
	 * @param sublineados the sublineados to set
	 */
	public void setSublineados(Sublineados sublineados) {
		this.sublineados = sublineados;
	}
	/**
	 * @return the departamento
	 */
	public Departamento getDepartamento() {
		return departamento;
	}
	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	/**
	 * @return the subedpartamento
	 */
	public Subdepartamento getSubedpartamento() {
		return subedpartamento;
	}
	/**
	 * @param subedpartamento the subedpartamento to set
	 */
	public void setSubedpartamento(Subdepartamento subedpartamento) {
		this.subedpartamento = subedpartamento;
	}
	/**
	 * 
	 */
	public Artxempresa() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param articulo
	 * @param empresa
	 * @param marca
	 * @param linea
	 * @param sublinea
	 * @param sublineados
	 * @param departamento
	 * @param subedpartamento
	 */
	public Artxempresa(Articulo articulo, Empresa empresa, Marca marca, Linea linea, Sublinea sublinea,
			Sublineados sublineados, Departamento departamento, Subdepartamento subedpartamento) {
		super();
		this.articulo = articulo;
		this.empresa = empresa;
		this.marca = marca;
		this.linea = linea;
		this.sublinea = sublinea;
		this.sublineados = sublineados;
		this.departamento = departamento;
		this.subedpartamento = subedpartamento;
	}
	
	
}
