package com.aps.dao.operacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class CalendarioDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Object>  listaEventos(){
		List<Object> listEvent = new ArrayList<Object>();
		
		String sql = "SELECT c.*, al.CLAVE,al.NOMBRE FROM `calendario` c\r\n" + 
				"INNER JOIN ALMACEN al on c.NUMALM_ID = al.ID\r\n" + 
				"WHERE c.EMPRESA_ID='1' AND al.RESURTIDO = 1";
		System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
				 
		for(int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map <?,?> row = rows.get(i);			 
//			 JSONObject eventos = new JSONObject();			
			 mapa.put("title", row.get("NOMBRE"));
			 mapa.put("start", row.get("FECHA"));
			 mapa.put("backgroundColor", "#00c0ef");			
			 listEvent.add(mapa);
		}
		System.out.println(listEvent);
		return listEvent;		
	}
	/*
	 * Lista de almacenes no seleccionados 
	 */
	public List<Map<String,Object>> listaNotAlmacenSeleccionado(int empresa_id, String fecha, String tipomovimiento){
		
		String sql = "SELECT al.* FROM ALMACEN al\r\n" + 
				"WHERE  al.EMPRESA_ID='1' AND al.RESURTIDO = 1 \r\n" + 
				" and al.id NOT IN( \r\n" + 
				"     SELECT  c.NUMALM_ID FROM calendario c WHERE c.EMPRESA_ID='"+empresa_id+"' AND c.FECHA='"+fecha+"' AND c.TIPOMOVIMIENTO='"+tipomovimiento+"' \r\n" + 
				" )";
		System.out.println(sql);
		List<Map<String,Object>> rows = template.queryForList(sql);				
	  return rows;
	}
	/*
	 * Lista de almacenes si seleccionados 
	 */
	public List<Map<String,Object>> listaYesAlmacenSeleccionado(int empresa_id, String fecha, String tipomovimiento){
		
		String sql = "SELECT al.*,c.FECHA FROM ALMACEN al \r\n" + 				
				" INNER JOIN calendario c on c.NUMALM_ID = al.ID  \r\n" + 
				" WHERE al.EMPRESA_ID=c.EMPRESA_ID AND c.EMPRESA_ID='"+empresa_id+"' AND al.RESURTIDO = 1 AND c.FECHA='"+fecha+"' AND c.TIPOMOVIMIENTO='"+tipomovimiento+"' \r\n" + 
				" ";
		System.out.println(sql);
		List<Map<String,Object>> rows = template.queryForList(sql);
		return rows;
	}
	
	
}
