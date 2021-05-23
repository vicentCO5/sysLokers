package com.aps.dao.seguridad;

import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;

public class UsuarioRoleDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public int findByUsuario(int usuario) {
		String sql = "SELECT USUARIO_ID FROM USUARIO_ROLE WHERE USUARIO_ID="+usuario ;		
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}
	public boolean delete(int usuario) {
		String sql = "DELETE FROM USUARIO_ROLE WHERE USUARIO_ID="+usuario ;		
		 return template.update(sql)>0;
	
	}
	
	public void create(int usuario,int role) {
		String sql="INSERT USUARIO_ROLE(USUARIO_ID,ROLE_ID)VALUES("+usuario+","+role+")";
		template.update(sql);
	}
	

}
