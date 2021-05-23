package com.aps.dao.catalogos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.aps.beans.catalogos.Url_old;
public class UrlDao_old {
	JdbcTemplate templat;
	public void setTemplate(JdbcTemplate template) {
		this.templat = template;
	}
	public List<Url_old> getUrls() {
		return templat.query("select * from url where parentmenuid=0 AND URL is null", new RowMapper<Url_old>() {
		    
			@Override
			public Url_old mapRow(ResultSet rs, int row) throws SQLException {
				Url_old e = new Url_old();
				e.setId(rs.getInt(1));
				e.setNombre(rs.getString(2));
				e.setTipo(rs.getInt(3));
				e.setParentmenuid(rs.getInt(4));
				e.setUrl(rs.getString(5));
				e.setUrlp_id(rs.getInt(6));				
				return e;
			}
		});
	}
	public List<Url_old> getSubUrls(int id) {
		return templat.query("select * from url where parentmenuid="+id+" order by id asc", new RowMapper<Url_old>() {
			
			@Override
			public Url_old mapRow(ResultSet rs, int row) throws SQLException {
				Url_old e = new Url_old();
				e.setId(rs.getInt(1));
				e.setNombre(rs.getString(2));
				e.setTipo(rs.getInt(3));
				e.setParentmenuid(rs.getInt(4));
				e.setUrl(rs.getString(5));
				e.setUrlp_id(rs.getInt(6));				
				return e;
			}
		});
	}
	public String nameAps() {
		String name="";
		String sql="SELECT VALOR FROM CONFIG WHERE PARAMETRO = 'NAME_APS'";
		List<String> certs = templat.queryForList(sql, String.class);
		name = certs.get(0);
		return name;
	}
	

}
