package com.aps.beans.seguridad;

import java.io.Serializable;

public class UsuarioRole implements Serializable{

	private static final long serialVersionUID = 1;
	Usuario usuario;
	Role role;
	private int id;
	public UsuarioRole(Usuario u, Role r) {
		super();
		usuario = u;
		role = r;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
