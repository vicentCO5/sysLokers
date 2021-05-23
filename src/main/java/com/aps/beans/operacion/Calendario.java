package com.aps.beans.operacion;

public class Calendario {
	
	private int empresa;
	private int almacen;	
	private String fecha;
	private int tipomovimiento;
	private int agente;
	private String timecreate;
	
	
	public int getEmpresa() {
		return empresa;
	}
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	public int getAlmacen() {
		return almacen;
	}
	public void setAlmacen(int almacen) {
		this.almacen = almacen;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getTipomovimiento() {
		return tipomovimiento;
	}
	public void setTipomovimiento(int tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}
	public int getAgente() {
		return agente;
	}
	public void setAgente(int agente) {
		this.agente = agente;
	}
	public String getTimecreate() {
		return timecreate;
	}
	public void setTimecreate(String timecreate) {
		this.timecreate = timecreate;
	}
	
	/**
	 * 
	 */
	public Calendario() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*
	 * @param empresa_id
	 * @param almacen_id
	 * @param fechaMovimiento
	 * @param tipoMovimiento
	 * @param agente_id
	 * @param timeCreate
	 * */
	public Calendario( int empresa_id,int almacen_id, String fechaMovimiento, int tipoMovimiento, int agente_id, String timeCreate ) {
		this.empresa = empresa_id;
		this.almacen = almacen_id;
		fecha = fechaMovimiento;
		tipomovimiento =  tipoMovimiento;
		this.agente    = agente_id;
		timecreate     = timeCreate;
	}
	
	
}
