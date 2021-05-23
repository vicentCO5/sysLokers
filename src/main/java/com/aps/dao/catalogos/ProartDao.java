package com.aps.dao.catalogos;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class ProartDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public int getproveedorClave(String claveproveedor) {
		String sql = "SELECT ID FROM PROVEEDOR WHERE CLAVE='"+claveproveedor+"' ";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}
	
	public boolean save(int articuloid, Integer proveedorid) {
		String sql = "INSERT INTO PROART (numart_id,numpro_id)VALUES";
		sql +="("+articuloid+","+proveedorid+")";
		System.out.println(sql);
		return template.update(sql) > 0;
	}
	public boolean findByIndex(int articuloid,int proveedorid) {
		String sql = "SELECT ID FROM PROART WHERE numart_id="+articuloid+" AND numpro_id="+proveedorid+"";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
