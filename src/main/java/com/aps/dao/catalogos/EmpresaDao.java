package com.aps.dao.catalogos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.aps.beans.catalogos.Empresa;

public class EmpresaDao {
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	/**
	 * guardar empresa
	 * */
	public int save(Empresa e) {
//		 template.update(
//			      "insert into empresa (clave,nombre,pais,ruc) VALUES (?,?,?,?)",
//			        new Object[] {
//			        e.getClave(),
//			        e.getNombre(),
//			        e.getPais(),
//			        e.getRuc()
//			      }
//			    );
		String sql="insert into empresa (clave,nombre,pais,ruc) values ('"+e.getClave()+"','"+e.getNombre()+"','"+e.getPais()+"','"+e.getRuc()+"')";
		//System.out.println(sql);
		return template.update(sql);		
	}
	/**
	 * lista de empresas
	 * */
	public List<Empresa>getEmpresas() {
		return template.query("select * from empresa", new RowMapper<Empresa>() {
			public Empresa mapRow(ResultSet rs, int row) throws SQLException {
				Empresa e = new Empresa();
				e.setId(rs.getInt(1));
				e.setClave(rs.getString(2));
				e.setNombre(rs.getString(4));
				e.setPais(rs.getString(5));
				e.setRuc(rs.getString(6));
				return e;
			}
		});
	}
	/**
	 * lista de almacenes
	 * */
	public List<Object> getempresas() {
		List<Object> almacenes = new ArrayList<Object>();
		String sql="select * from EMPRESA";		      
		List<Map<String, Object>> rows = template.queryForList(sql);		
		 for (Map<?, ?> row : rows) {
			 Map<String, Object> mapa = new HashMap<String, Object>();
			 		mapa.put("id", row.get("id"));
			 		mapa.put("clave", row.get("clave"));
			 		mapa.put("nombre", row.get("nombre"));
			 		almacenes.add(mapa);
		
		        }
		        return almacenes;
	}
	/**
	 * select empresa by id
	 * */
	public Empresa getEmpresaById(int id) {
		String sql = "select * from empresa where id=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Empresa>(Empresa.class));
	}
	/**
	 * actualizar empresa
	 * */
	
	public int update(Empresa p) {
		String sql = "update empresa set nombre='" + p.getNombre() + "', pais='" + p.getPais() + "', ruc='"
				+ p.getRuc() + "' where id=" + p.getId()+ "";
		//System.out.println(sql);
		return template.update(sql);
	}
	/**
	 * eliminar empresa
	 * */
	public int delete(int id) throws SQLException  {
		String sql = "delete from empresa where id=" + id + "";
		
		return template.update(sql);
	}
	
	

}
