package com.aps.dao.operacion;

import org.springframework.jdbc.core.JdbcTemplate;

public class ResurtidoDao {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
}
