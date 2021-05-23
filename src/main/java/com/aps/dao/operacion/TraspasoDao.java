package com.aps.dao.operacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.operacion.Traspaso;

public class TraspasoDao {
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int save(Traspaso t) {
		String sql = "INSERT INTO `traspasos`( `EMPRESA_ID`, `NUMEROFOLIO`, `ALMACENORIGEN_ID`, `ALMACENDESTINO_ID`, `NUMART_ID`, `CANTIDAD`, `UNIDAD`, `FECHAMOVIMIENTO`, `TIMECREATE`, `ESTATUS`, `NUMAGT`) VALUES ";
		       sql += "('"+t.getEmpresa()+"','"+t.getNumerofolio()+"','"+t.getAlmacenorigen()+"','"+t.getAlmacendestino()+"','"+t.getArticulo()+"','"+t.getCantidad()+"','"+t.getUnidad()+"','"+t.getFechamovimiento()+"','"+t.getTimecreate()+"','"+t.getEstatus()+"','"+t.getAgente()+"');";
		 System.out.println(sql);
		 return template.update(sql);
	}
	/*
	 * Lista de traspasos */
	public List<Object> listaTraspasos(){
		List<Object> listtraspaso = new ArrayList<Object>();
		
		String sql = "SELECT t.*,a.NUMART,a.NUMARTALTERNO,a.NOMART,\r\n" + 
				"(SELECT al.NOMBRE  FROM almacen al WHERE al.id = T.ALMACENORIGEN_ID ) AS NOMBRE_ORIGEN,\r\n" + 
				"(SELECT al.NOMBRE  FROM almacen al WHERE al.id = T.ALMACENDESTINO_ID ) AS NOMBRE_DESTINO\r\n" + 
				"FROM `traspasos` t\r\n" + 
				"INNER JOIN maearticulo as a on t.NUMART_ID = a.ID";
		System.out.println(sql);
		List<Map<String, Object> > rows = template.queryForList(sql);
		
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map <?,?> row = rows.get(i);
			mapa.put("indice", (i+1));
			mapa.put("numeroFolio", row.get("NUMEROFOLIO"));
			mapa.put("almacenOrigen",row.get("NOMBRE_ORIGEN"));
			mapa.put("almacenDestino",row.get("NOMBRE_DESTINO"));
			mapa.put("codSKU", row.get("NUMART"));
			mapa.put("nombreSKU", row.get("NOMART"));
			mapa.put("cantidad", row.get("CANTIDAD"));
			mapa.put("unidad", row.get("UNIDAD"));
			mapa.put("fechaMovimiento", row.get("FECHAMOVIMIENTO"));
			mapa.put("estatus", row.get("ESTATUS"));
			mapa.put("agente", row.get("NUMAGT"));
		    
			listtraspaso.add(mapa);
		}
		return listtraspaso;
	}
	/*
	 * @param empresa_id
	 * Eliminar lista de traspasos 
	 * - Falta agregar la condicion de eliminar por empresa
	 * */
	public int deleteTraspaso(int empresa_id) throws SQLException  {
		String sql = "DELETE FROM traspasos where EMPRESA_ID ='"+empresa_id+"' ";
		System.out.println(sql);
		return template.update(sql);
	}
	

}
