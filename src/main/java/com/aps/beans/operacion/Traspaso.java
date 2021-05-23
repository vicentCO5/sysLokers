package com.aps.beans.operacion;

public class Traspaso {
		
    
	private int empresa;
    private String numerofolio;
	private int almacenorigen;
	private int almacendestino;
	private int articulo;	
	private double cantidad;	
	private String unidad;	
	private String fechamovimiento;	
	private String timecreate;
	private String estatus;
	private String agente;
	
	
	
	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	
	public String getFechamovimiento() {
		return fechamovimiento;
	}

	public void setFechamovimiento(String fechamovimiento) {
		this.fechamovimiento = fechamovimiento;
	}

	public String getTimecreate() {
		return timecreate;
	}

	public void setTimecreate(String timecreate) {
		this.timecreate = timecreate;
	}
	
	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getAgente() {
		return agente;
	}
	
	public void setAgente(String agente) {
		this.agente = agente;
	}	

	public String getNumerofolio() {
		return numerofolio;
	}


	public void setNumerofolio(String numerofolio) {
		this.numerofolio = numerofolio;
	}


	public int getAlmacenorigen() {
		return almacenorigen;
	}


	public void setAlmacenorigen(int almacenorigen) {
		this.almacenorigen = almacenorigen;
	}


	public int getAlmacendestino() {
		return almacendestino;
	}


	public void setAlmacendestino(int almacendestino) {
		this.almacendestino = almacendestino;
	}


	public int getArticulo() {
		return articulo;
	}


	public void setArticulo(int articulo) {
		this.articulo = articulo;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}


	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	

	/**
	 * @param codEmpresa
	 * @param numerofolio
	 * @param almacenorigen
	 * @param almacendestino
	 * @param articulo
	 * @param cantidad
	 * @param unidad
	 * @param fechamovimiento
	 * @param fechacreate
	 * @param estatus
	 * @param agente
	 *  */
	
	public Traspaso( int codEmpresa, String numeroFolio, int almacenOrigen,int almacenDestino,int articulo, double cantidadd, String unidad, 
			String fechaMovimiento,String timeCreate, String estatuss, String agentee) {
		super();
		
		empresa    = codEmpresa;
		numerofolio         = numeroFolio;
		this.almacenorigen  = almacenOrigen;
		this.almacendestino =  almacenDestino;
		this.articulo =  articulo;
		cantidad = cantidadd;
		this.unidad   =  unidad;		
		fechamovimiento = fechaMovimiento;
		timecreate = timeCreate;
		estatus = estatuss;
		agente  = agentee;		
		
	}
	

}
