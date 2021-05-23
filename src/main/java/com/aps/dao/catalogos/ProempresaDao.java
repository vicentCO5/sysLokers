package com.aps.dao.catalogos;

import org.springframework.jdbc.core.JdbcTemplate;


public class ProempresaDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public int save(int idempresa, int idproveedor, double porcentajedescuento) {
		String sql = "INSERT INTO PROEMPRESA (PROVEEDOR_ID,EMPRESA_ID,PORC_DESCUENTO) VALUES";
		sql += "('"+idempresa+"','"+idproveedor+"',"+porcentajedescuento+")";
		//System.out.println(sql);
		return template.update(sql);
	}
}
