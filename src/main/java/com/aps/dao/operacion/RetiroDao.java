package com.aps.dao.operacion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class RetiroDao {
	
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Object>  listaRetiros(int almacen_id, int usuario_id){
		List<Object> lineas = new ArrayList<Object>();
		String sql = "SELECT r.*,a.CLAVE,a.NOMBRE,u.nombre  FROM `retiros` r\r\n" + 
				" INNER JOIN almacen a on r.ALMACEN_ID = a.ID \r\n" + 
				" INNER JOIN usuario u ON  r.USUARIO_ID = u.id" +
				" WHERE  r.USUARIO_ID='"+usuario_id+"' AND r.ALMACEN_ID='"+almacen_id+"' ";
		System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
		
		DecimalFormat df = new DecimalFormat("#.00");
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			double importe = Double.parseDouble(df.format(row.get("IMPORTE")));
			
			mapa.put("indice", (i+1));			
			mapa.put("serie", row.get("SERIE"));
			mapa.put("folio", row.get("FOLIO"));
			mapa.put("fecha", row.get("FECHA"));			
			mapa.put("motivo", row.get("MOTIVO"));
			mapa.put("receptor", row.get("RECEPTOR"));
			mapa.put("importe", "$ "+importe );			
			mapa.put("tiempo", row.get("TIMECREATE").toString());
			mapa.put("nombre", row.get("nombre"));
			
			lineas.add(mapa);
		}
		
		return lineas;
	}
	
	public List<Map<String, Object>> getOptionsRetiros(){
		List<Object> lineas = new ArrayList<Object>();
		String sql = " SELECT * FROM `config_retiros` WHERE ESTATUS=0 ";
		System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);						
		
		return rows;
	}
	
	public int saveRetiro(String serie, int folio, String fecha, double importe, String motivo, String receptor, int usuario_id, int almacen_id, int empresa_id, String timeCreate ) {
		String sql = "INSERT INTO `retiros`( `SERIE`, `FOLIO`, `FECHA`, `IMPORTE`, `MOTIVO`, `RECEPTOR`, `USUARIO_ID`, `ALMACEN_ID`, `EMPRESA_ID`, `TIMECREATE`) VALUES "
				+ "('"+serie+"','"+folio+"','"+fecha+"','"+importe+"','"+motivo+"','"+receptor+"','"+usuario_id+"','"+almacen_id+"','"+empresa_id+"','"+timeCreate+"' ) ";
		return template.update(sql);
	}
	
	
}
