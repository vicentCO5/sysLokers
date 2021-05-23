package com.aps.beans.seguridad;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1;
	private int id;
	private String username;
	private String password;
	private int empresa_id;
	private int almacen_id;
	private String nombre;
	private String roles;
	private boolean enabled = true;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean passwordExpired;
	private int perfil;
	
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the empresa
	 */
	public int getEmpresa_id() {
		return empresa_id;
	}



	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}



	/**
	 * @return the almacen
	 */
	public int getAlmacen_id() {
		return almacen_id;
	}



	/**
	 * @param almacen the almacen to set
	 */
	public void setAlmacen_id(int almacen_id) {
		this.almacen_id = almacen_id;
	}



	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}



	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}



	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}



	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}



	/**
	 * @param accountExpired the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}



	/**
	 * @return the accountLocked
	 */
	public boolean isAccountLocked() {
		return accountLocked;
	}



	/**
	 * @param accountLocked the accountLocked to set
	 */
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}



	/**
	 * @return the passwordExpired
	 */
	public boolean isPasswordExpired() {
		return passwordExpired;
	}



	/**
	 * @param passwordExpired the passwordExpired to set
	 */
	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}



	/**
	 * @return the perfil
	 */
	public int getPerfil() {
		return perfil;
	}



	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}



	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	/**
	 * 
	 */
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
