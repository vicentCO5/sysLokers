package com.aps.dao.seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.seguridad.Perfil;

public class PerfilDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	/**
	 * @return lista de perfiles existentes
	 */
	public List<Object> finfAll() {
		List<Object> perfiles = new ArrayList<Object>();
		String sql = "SELECT * from PERFIL";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("DT_RowId", "pi_" + row.get("ID"));
			mapa.put("nombre", row.get("nombre"));
			mapa.put("tipo", row.get("tipo"));
			String html="<span class='btn btn-primary btn-xs'><i class='ace-icon glyphicon glyphicon-pencil'></i> </span>";
            
	 		mapa.put("accion", html);
			perfiles.add(mapa);
		}
		return perfiles;
	}
	public int saveperfil(Perfil p) {
		 return template.update("INSERT INTO PERFIL(DESCRIPCION,NOMBRE,TIPO) VALUES(?,?,?)", p.getDescripcion(), p.getNombre(),p.getTipo());		
	}
	
	public Perfil findByName(String name) {
		String sql = "SELECT * FROM PERFIL WHERE NOMBRE=?";
		return template.queryForObject(sql, new Object[] { name }, new BeanPropertyRowMapper<Perfil>(Perfil.class));
	}
	public Perfil findById(int id) {
		String sql = "SELECT * FROM PERFIL WHERE ID=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Perfil>(Perfil.class));
	}
	
	public void saveperfilUrl(int perfil_id, JSONArray arrayvalues) {
		String sql = "INSERT INTO PERFIL_URL(perfil_urls_id,url_id)values";
		String query = "";
		for(int i=0;i<arrayvalues.size();i++) {
			query += "("+perfil_id+","+arrayvalues.get(i)+"),";
		}
		String queryinsert =sql+query.substring(0, query.length()-1);
		//System.out.println(queryinsert);
		template.update(queryinsert);
	}

	public boolean deleteurlperfil(int id) {
		String sql = "DELETE FROM PERFIL_URL WHERE perfil_urls_id="+id;
		return template.update(sql)>0;
		
	}

	public void update(int id, String nombre, String descripcion, String tipo) {
		String sql = "UPDATE PERFIL SET NOMBRE='"+nombre+"',DESCRIPCION='"+descripcion+"',TIPO='"+tipo+"' WHERE ID="+id;
		template.update(sql);
	}
}
