package com.aps.beans.catalogos;

public class Clasificador {
	private int id;
	private int empresa;
	private String codigo;
	private String descripcion;
	private int nivel;
	private int forecast;
	private int forecastpv;
	private int reabasto;
	private int resurtido;
	private int extraordinario;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the empresa
	 */
	public int getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return the forecast
	 */
	public int getForecast() {
		return forecast;
	}
	/**
	 * @param forecast the forecast to set
	 */
	public void setForecast(int forecast) {
		this.forecast = forecast;
	}
	/**
	 * @return the forecastpv
	 */
	public int getForecastpv() {
		return forecastpv;
	}
	/**
	 * @param forecastpv the forecastpv to set
	 */
	public void setForecastpv(int forecastpv) {
		this.forecastpv = forecastpv;
	}
	/**
	 * @return the reabasto
	 */
	public int getReabasto() {
		return reabasto;
	}
	/**
	 * @param reabasto the reabasto to set
	 */
	public void setReabasto(int reabasto) {
		this.reabasto = reabasto;
	}
	/**
	 * @return the resurtido
	 */
	public int getResurtido() {
		return resurtido;
	}
	/**
	 * @param resurtido the resurtido to set
	 */
	public void setResurtido(int resurtido) {
		this.resurtido = resurtido;
	}
	/**
	 * @return the extraordinario
	 */
	public int getExtraordinario() {
		return extraordinario;
	}
	/**
	 * @param extraordinario the extraordinario to set
	 */
	public void setExtraordinario(int extraordinario) {
		this.extraordinario = extraordinario;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * 
	 */
	public Clasificador() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param empresa
	 * @param codigo
	 * @param descripcion
	 * @param forecast
	 * @param forecastpv
	 * @param reabasto
	 * @param resurtido
	 * @param extraordinario
	 */
	public Clasificador(int id, int empresa, String codigo, String descripcion, int nivel,int forecast, int forecastpv,
			int reabasto, int resurtido, int extraordinario) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.forecast = forecast;
		this.forecastpv = forecastpv;
		this.reabasto = reabasto;
		this.resurtido = resurtido;
		this.extraordinario = extraordinario;
	}
	
	
}
