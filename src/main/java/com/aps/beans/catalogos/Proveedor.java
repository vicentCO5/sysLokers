package com.aps.beans.catalogos;

public class Proveedor {
	/**Atributo que Almacena la Clave del proveedor
	 * @String
	 * @MyB_CODPROVEEDOR*/
	private String clave;
	
	/**Atributo que Almacena el Nombre del proveedor
	 * @String
	 * @MyB_NOMBRES @MyB_APELLIDOS*/
	private String descripcion;
	
	/**Atributo que almacena la Razon Social del proveedor
	 * @String*/	
	private String razonSocial;
	
	/** Direccion del proveedor
	 * @String 
	 * @MyB_DIRECCION*/
	private String direccion;						
	
	/** Colonia o Provincia*/
	private String colonia;			
	
	/** Poblacion 
	 * @MyB_CODCIUDAD */
	private String poblacion;				
	
	/**Estado del Proveedorss */
	private String estado;					
	
	/** Pais del Proveedor */
	private String pais;		
	
	/** @MyB_TELEFONOFIJO1*/	
	private String telefono;				
			
	
	/** Observaciones del Proveedor */
	private String observaciones;			
	
	
	/** RFC del Proveedor */
	private String rfc;					
	
	/** Correo electronico del Proveedor
	 * @MyB_EMAIL
	 */
	private String mail;			
	
	/** Pagina del proveedor 
	 * */
	private String web;
	
	/** Indicar el tipo de proveedor 
	 * Puede Utilizarse para indicar si Proveedor Local o IMPORTADO*/	
	private String tipo;			    
	
	/**0 deshabilitado , 1 habilitado */
	private String status;
		
	/** Celular del proveedor
	 * @MyB_TELEFONOCELULAR*/
	private String celular;
	
	private int empresa_id;
	private double descuento;
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
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the colonia
	 */
	public String getColonia() {
		return colonia;
	}

	/**
	 * @param colonia the colonia to set
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the web
	 */
	public String getWeb() {
		return web;
	}

	/**
	 * @param web the web to set
	 */
	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public int getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
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
	 * @param clave
	 * @param descripcion
	 * @param razonSocial
	 * @param direccion
	 * @param colonia
	 * @param poblacion
	 * @param estado
	 * @param pais
	 * @param telefono
	 * @param observaciones
	 * @param rfc
	 * @param mail
	 * @param web
	 * @param tipo
	 * @param status
	 * @param celular
	 * @param empresa_id
	 */
	public Proveedor(String clave, String descripcion, String razonSocial, String direccion, String colonia,
			String poblacion, String estado, String pais, String telefono, String observaciones, String rfc,
			String mail, String web, String tipo, String status, String celular,int empresa_id,double descuento) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
		this.colonia = colonia;
		this.poblacion = poblacion;
		this.estado = estado;
		this.pais = pais;
		this.telefono = telefono;
		this.observaciones = observaciones;
		this.rfc = rfc;
		this.mail = mail;
		this.web = web;
		this.tipo = tipo;
		this.status = status;
		this.celular = celular;
		this.empresa_id = empresa_id;
		this.descuento = descuento;
	}

	/**
	 * 
	 */
	public Proveedor() {
		super();
	}

							
	     
}
