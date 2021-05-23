package com.aps.beans.catalogos;

public class Cliente {

	private int empresa_id;
	private String codigo;
	private String nombre;
	private String apellidos;
	
	private String direccion;
	private String colonia;
	private String municipio;
	private String estado;
	private String pais;
	private String telefono;
	private String email;
	private String timecreate;
	
	public int getEmpresa_id() {
		return empresa_id;
	}
	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTimecreate() {
		return timecreate;
	}
	public void setTimecreate(String timecreate) {
		this.timecreate = timecreate;
	}
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente ( int empresa_idd, String codigoo, String nombree,String apellidos, String direccionn
	, String coloniaa, String municipioo, String estadoo	, String paiss	, String telefonoo	,
	String emaill	, String timecreatee) {
		this.empresa_id =  empresa_idd;
		this.codigo =  codigoo;
		this.nombre = nombree;
		this.apellidos = apellidos;
		this.direccion = direccionn;
		this.colonia = coloniaa;
		this.municipio = municipioo;
		this.estado = estadoo;
		this.pais =  paiss;
		this.telefono = telefonoo;
		this.email = emaill;
		this.timecreate = timecreatee;
		
		
	}
	
}
