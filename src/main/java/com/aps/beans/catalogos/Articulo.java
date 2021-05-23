package com.aps.beans.catalogos;

/**
 * @author Gem
 *
 */
public class Articulo {
	/**Clave del articulo*/
	private int empresa_id;
	private String numart;	
	private String numartAlterno;	
	private String nomart;
	private String uniart;
	private int clasificacion_id;		
	private String color;
	private double peso;
	private double volumen;
	private double alto;
	private double largo;
	private double ancho;
    private int estatus; // 0 activo 1 inactivo
	private String timecreate ;
	private String timechange;
	private int usuario_id;

	
		/**
	 * @param empresa_id
	 * @param numart
	 * @param numartAlterno
	 * @param nomart
	 * @param uniart
	 * @param clasificacion_id
	 * @param color
	 * @param peso
	 * @param volumen
	 * @param alto
	 * @param largo
	 * @param ancho
	 * @param estatus
o
	 */
	public Articulo(int empresa_id,String numart, String numartAlterno, String nomart, String uniart, 
			 int clasificacion_id,String color, double peso,double volumenn,
			 double alto,double largo,double ancho, int estatus,String timecreate, String timechage, int usuario_id ) {
		super();
		this.empresa_id = empresa_id;
		this.numart = numart;
		this.numartAlterno = numartAlterno;
		this.nomart = nomart;
		this.uniart = uniart;
		this.clasificacion_id = clasificacion_id;
		this.color = color;
		this.peso = peso;
		this.volumen = volumenn;
		this.alto = alto;
		this.largo =  largo;
		this.ancho =  ancho;
		this.estatus = estatus;
		this.timecreate = timecreate;
		this.timechange = timechage;
		this.usuario_id =  usuario_id;
				
		
	}
	public int getEmpresa_id() {
			return empresa_id;
		}
		public void setEmpresa_id(int empresa_id) {
			this.empresa_id = empresa_id;
		}
		public String getNumart() {
			return numart;
		}
		public void setNumart(String numart) {
			this.numart = numart;
		}
		public String getNumartAlterno() {
			return numartAlterno;
		}
		public void setNumartAlterno(String numartAlterno) {
			this.numartAlterno = numartAlterno;
		}
		public String getNomart() {
			return nomart;
		}
		public void setNomart(String nomart) {
			this.nomart = nomart;
		}
		public String getUniart() {
			return uniart;
		}
		public void setUniart(String uniart) {
			this.uniart = uniart;
		}
		public int getClasificacion_id() {
			return clasificacion_id;
		}
		public void setClasificacion_id(int clasificacion_id) {
			this.clasificacion_id = clasificacion_id;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public double getPeso() {
			return peso;
		}
		public void setPeso(double peso) {
			this.peso = peso;
		}
		public double getVolumen() {
			return volumen;
		}
		public void setVolumen(double volumen) {
			this.volumen = volumen;
		}
		public double getAlto() {
			return alto;
		}
		public void setAlto(double alto) {
			this.alto = alto;
		}
		public double getLargo() {
			return largo;
		}
		public void setLargo(double largo) {
			this.largo = largo;
		}
		public double getAncho() {
			return ancho;
		}
		public void setAncho(double ancho) {
			this.ancho = ancho;
		}
		public int getEstatus() {
			return estatus;
		}
		public void setEstatus(int estatus) {
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
	/**
	 * 
	 */
	public Articulo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
		
	
		
	
	
	
}
