package com.aps.dao.catalogos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import com.aps.beans.catalogos.Clasificacion;

public class ClasificacionDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}	
	
	/* Lista de folios
	 * @param empresa_id
	*/
	public List<Map<String, Object>> listCalsificacion(int empresa_id){
		String sql = "SELECT c.*,e.CLAVE,e.nombre  FROM `config_clasificacion` c \r\n" + 
				"INNER JOIN empresa e ON c.EMPRESA_ID = e.id\r\n" + 
				"WHERE c.empresa_id = 1";
		System.out.println(sql);
		List<Map<String,Object>> rows = template.queryForList(sql);				
	  return rows;
	}
	
	public Map<String,Map<String,Object>> getByListClasificacion(int idempresa) {
		String sql="select * from config_clasificacion WHERE EMPRESA_ID="+idempresa + " AND ESTATUS=0 ORDER BY ID";	
		List<Map<String, Object>> rows = template.queryForList(sql);
		
		Map<String, Map<String, Object>> lineas = new HashMap<String,Map<String,Object>>();
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("id",  row.get("ID"));
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("descripcion", row.get("DESCRIPCION"));
			
			lineas.put(row.get("ID").toString(), mapa);
		}
		
		return lineas;
	}
	
	public Map<String,Map<String,Object>> getByListIdentificacion() {
		String sql="SELECT `IDI`, `CLAVE`, `IDENTIFICACION` FROM `config_identificacion`";	
		List<Map<String, Object>> rows = template.queryForList(sql);
		
		Map<String, Map<String, Object>> lineas = new HashMap<String,Map<String,Object>>();
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("id",  row.get("IDI"));
			mapa.put("clave", row.get("CLAVE"));
			mapa.put("descripcion", row.get("IDENTIFICACION"));
			
			lineas.put(row.get("IDI").toString(), mapa);
		}
		
		return lineas;
	}
	
	
	/*Eliminar folios
	 * @param id
	 * */
	public int deleteCalsificacion(int id) throws SQLException  {
		String sql = "DELETE FROM config_clasificacion WHERE ID="+id+" ";
		return template.update(sql);
	}
	/* changedEstatusClasificacion(id,status) */
	/*
	 * Modificar estatus
	 * @param id folio
	 * @param status 
	 * */
	public int changedEstatusClasificacion(int id,int status) {
		String sql = "UPDATE config_clasificacion SET ESTATUS="+status+"  WHERE ID="+id+" ";
		return template.update(sql);
	}
	public boolean getByClave(int empresa_id , String clave) {
		String sql = "SELECT * FROM config_clasificacion WHERE CLAVE = '"+clave+"' and EMPRESA_ID='"+empresa_id+"'  ";
		List<String> certs = template.queryForList(sql, String.class);
		System.out.println(certs.isEmpty() );
		if (certs.size() > 0 ) {
			return false;
		} else {
			return true;			
		}
	}
	
	public boolean getByDescripcion(int empresa_id , String descripcion) {
		String sql = "SELECT * FROM config_clasificacion WHERE DESCRIPCION = '"+descripcion+"' and EMPRESA_ID='"+empresa_id+"'  ";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.size() > 0 ) {
			return false;
		} else {
			return true;			
		}
	}
	public void saveClasificacion(int empresa_id, String clave,String descripcion, int estatus, String timeCreate, int usuario_id ) {
		String sql = "INSERT INTO `config_clasificacion`( `EMPRESA_ID`, `CLAVE`, `DESCRIPCION`, `ESTATUS`, `TIMECREATE`, `USUARIO_ID`)"
				+ " VALUES ('"+empresa_id+"','"+clave+"','"+descripcion+"','"+estatus+"','"+timeCreate+"','"+usuario_id+"' ) ";
		System.out.println(sql);
		
		template.update(sql);
	} 
	
	public int findByClave (String clave) {
		String sql = "SELECT ID FROM config_clasificacion WHERE CLAVE='"+clave+"'";
		List<String> certs = template.queryForList(sql, String.class); 
		
	    if (certs.isEmpty()) {
	    	return 0;
	    } else {
	    	return Integer.parseInt(certs.get(0));
	    	
	    }		
	}
	
	
	
	
}
