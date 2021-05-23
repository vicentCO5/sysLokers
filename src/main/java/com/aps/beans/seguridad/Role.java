package com.aps.beans.seguridad;

import java.io.Serializable;

public class Role implements Serializable{
	   private static final long serialVersionUID = 1;

		private String authority;
		private String descripcion;
		private String nombre;
		private int id;
		
		/**
		 * @return the authority
		 */
		public String getAuthority() {
			return authority;
		}


		/**
		 * @param authority the authority to set
		 */
		public void setAuthority(String authority) {
			this.authority = authority;
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
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		public Role(String authority) {
			super();
			this.authority = authority;
		}


		/**
		 * 
		 */
		public Role() {
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
