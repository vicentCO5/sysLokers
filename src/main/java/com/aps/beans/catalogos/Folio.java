package com.aps.beans.catalogos;

import org.springframework.format.annotation.DateTimeFormat;

public class Folio {

	private int empresa_id;
	private int almacen_id;
	private String serie;
	private int folio;
	private int folioiInicial;
	private String documento;
	private String estatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private String timecreate;
	private String timechange;
	private int usuario_id;
	
	public int getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}

	public int getAlmacen_id() {
		return almacen_id;
	}

	public void setAlmacen_id(int almacen_id) {
		this.almacen_id = almacen_id;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getFolioiInicial() {
		return folioiInicial;
	}

	public void setFolioiInicial(int folioiInicial) {
		this.folioiInicial = folioiInicial;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTimecreate() {
		return timecreate;
	}

	public void setTimecreate(String timecreate) {
		this.timecreate = timecreate;
	}

	public String getTimechange() {
		return timechange;
	}

	public void setTimechange(String timechange) {
		this.timechange = timechange;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	public Folio() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* contructor */
	public Folio ( int empresa_id, int almacen_id, String serie,int folio, int folioinicial,String documento, String estatus, String fechacreacion,String fechamodificar, int usuarioid) {
		this.empresa_id = empresa_id;
		this.almacen_id = almacen_id;
		this.serie = serie;
		this.folio = folio;
		this.folioiInicial = folioinicial;
		this.documento = documento;
		this.estatus = estatus;
		this.timecreate = fechacreacion; 
		this.timechange =  fechamodificar;
		this.usuario_id =  usuarioid;
		
	}
}
