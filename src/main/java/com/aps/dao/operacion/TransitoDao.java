package com.aps.dao.operacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.operacion.Transito;

public class TransitoDao {
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
    
	/*
	 * Guardar datos 
	 * */
	public int save(Transito t) {
		String sql = "INSERT INTO `transitos`(`EMPRESA_ID`,`NUMEROFOLIO`, `NUMALM_ID`, `NUMART_ID`, `CANTIDADINICIAL`, `CANTIDADFALTANTE`, `UNIDAD`, `FECHALLEGADA`, `TIMECREATE`, `AGENTE_ID`) VALUES ";
		       sql +="('"+t.getEmpresa()+"','"+t.getNumerofolio()+"','"+t.getAlmacen()+"','"+t.getArticulo()+"','"+t.getCantidadinicial()+"','"+t.getCantidadfaltante()+"','"+t.getUnidad()+"','"+t.getFechallegada()+"','"+t.getTimecreate()+"','"+t.getAgente()+"');";
		System.out.println(sql);
		 return template.update(sql);
	}
	
	public List <Object> listaTransito(){
		List<Object> listTransito = new ArrayList<Object>();
		
		String sql = "SELECT t.*, a.NUMART,a.NUMARTALTERNO,a.NOMART, al.CLAVE,al.NOMBRE\r\n" + 
				"FROM `transitos` t\r\n" + 
				"INNER JOIN maearticulo a  on t.NUMART_ID = a.ID\r\n" + 
				"INNER JOIN almacen as al on t.NUMALM_ID = al.ID\r\n" + 
				"WHERE t.EMPRESA_ID = '1'";
		System.out.println(sql);
		List<Map <String, Object>> rows = template.queryForList(sql);
		for(int i = 0; i < rows.size(); i++) {	   
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map <?,?> row = rows.get(i);
			mapa.put("indice", i+1);
			mapa.put("numeroFolio", row.get("NUMEROFOLIO"));
			mapa.put("codalmacen", row.get("CLAVE"));
			mapa.put("nombreAlmacen", row.get("NOMBRE"));
			mapa.put("codSKU", row.get("NUMART"));
			mapa.put("nombreSKU", row.get("NOMART"));
			mapa.put("cantidadInicial",row.get("CANTIDADINICIAL"));
			mapa.put("cantidadFaltante", row.get("CANTIDADFALTANTE"));
			mapa.put("unidad", row.get("UNIDAD"));
			mapa.put("fechaLlegada", row.get("FECHALLEGADA"));
			mapa.put("agente", row.get("AGENTE_ID"));
			
			listTransito.add(mapa);
		}
		return listTransito;				
	}
	/*
	 * Eliminar lista */
	public int deleteTransito( int empresa_id) throws SQLException {
		String sql = "DELETE FROM transitos where EMPRESA_ID ='"+empresa_id+"' ";
		System.out.println(sql);
		return template.update(sql);
	}
}
