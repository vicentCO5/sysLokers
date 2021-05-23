package com.aps.beans.operacion;

public class Transito {
	
	private int articulo;
	private int almacen;
	private int empresa;
	

	private double cantidadinicial;
	private double cantidadfaltante;
	private String unidad;
	private String numerofolio;
	private String fechallegada;
	private String timecreate;
	private String agente;
	
	public int getEmpresa() {
		return empresa;
	}
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	public String getTimecreate() {
		return timecreate;
	}
	public void setTimecreate(String timecreate) {
		this.timecreate = timecreate;
	}
	public String getAgente() {
		return agente;
	}
	public void setAgente(String agente) {
		this.agente = agente;
	}
	public int getArticulo() {
		return articulo;
	}
	public void setArticulo(int articulo) {
		this.articulo = articulo;
	}
	public int getAlmacen() {
		return almacen;
	}
	public void setAlmacen(int almacen) {
		this.almacen = almacen;
	}
	public double getCantidadinicial() {
		return cantidadinicial;
	}
	public void setCantidadinicial(double cantidadinicial) {
		this.cantidadinicial = cantidadinicial;
	}
	public double getCantidadfaltante() {
		return cantidadfaltante;
	}
	public void setCantidadfaltante(double cantidadfaltante) {
		this.cantidadfaltante = cantidadfaltante;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getNumerofolio() {
		return numerofolio;
	}
	public void setNumerofolio(String numerofolio) {
		this.numerofolio = numerofolio;
	}
	public String getFechallegada() {
		return fechallegada;
	}
	public void setFechallegada(String fechallegada) {
		this.fechallegada = fechallegada;
	}

	/**
	 * 
	 */
	public Transito() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param empresa_id
	 * @param numerofolio
	 * @param articulo
	 * @param almacen
	 * @param cantidadinicial
	 * @param cantidadfaltante
	 * @param unidad	 
	 * @param fechallegada
	 * @param timecreate
	 * @param agente
	 *  */
	public Transito( int empresa_id,  String numeroFolio,int articuloo, int almacenn, double cantidadInicial, double cantidadFaltante, String unidadd,String fechaLlegada, String timeCreate,String agentee) {
		super();
		this.empresa = empresa_id;
		numerofolio  = numeroFolio;
		this.articulo =  articuloo;
		this.almacen  = almacenn;
		cantidadinicial  = cantidadInicial;
		cantidadfaltante = cantidadFaltante;
		this.unidad  =  unidadd;		
		fechallegada =  fechaLlegada;
		timecreate      = timeCreate;
		agente = agentee; 
	}
	
}
