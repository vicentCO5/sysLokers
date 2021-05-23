package com.aps.dao.seguridad;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.seguridad.Role;

public class RoleDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public Role findByAuthority(String authority) {
		String sql = "SELECT * FROM ROLE WHERE authority=?";
		return template.queryForObject(sql, new Object[] { authority }, new BeanPropertyRowMapper<Role>(Role.class));
	}
}
