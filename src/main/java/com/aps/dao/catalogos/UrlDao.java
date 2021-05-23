package com.aps.dao.catalogos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.aps.beans.seguridad.Url;
public class UrlDao {
	JdbcTemplate templat;
	public void setTemplate(JdbcTemplate template) {
		this.templat = template;
	}
	public List<Url> getUrls() {
		return templat.query("select * from url where parentmenuid=0 AND URL is null", new RowMapper<Url>() {
		
			public Url mapRow(ResultSet rs, int row) throws SQLException {
				Url e = new Url();
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
	public List<Url> getSubUrls(int id) {
		return templat.query("select * from url where parentmenuid="+id+" order by id asc", new RowMapper<Url>() {
			
			public Url mapRow(ResultSet rs, int row) throws SQLException {
				Url e = new Url();
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
	
	public List<Object> getByPerfil(int perfil_id) {		
		List<Object> url = new ArrayList<Object>();      
		String sql = "SELECT url_id FROM PERFIL_URL WHERE perfil_urls_id="+perfil_id;
		List<Map<String, Object>> rows = templat.queryForList(sql);		
		 for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();	
			 		mapa.put("id", row.get("url_id"));			 		
			 		url.add(mapa);
			 }
		  
		  return url;
	}
}
