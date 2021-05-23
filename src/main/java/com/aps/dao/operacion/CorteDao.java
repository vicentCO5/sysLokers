package com.aps.dao.operacion;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class CorteDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Map<String, Object>> sessionHome( int empresa_id, int almacen_id, int usuario_id){
		String sql = "SELECT u.*, p.id,p.account_expired,p.nombre as nombreusuario,p.perfil_id,p.username, a.CLAVE,a.NOMBRE,a.DIRECCION FROM `usuario_registro` u \r\n" + 
				"INNER join usuario p ON u.USUARIO_ID =  p.id\r\n" + 
				"INNER JOIN ALMACEN a ON u.ALMACEN_ID = a.ID \r\n" + 
				"WHERE u.EMPRESA_ID='"+empresa_id+"' and u.ALMACEN_ID ='"+almacen_id+"'  AND u.USUARIO_ID ='"+usuario_id+"' AND u.CORTE IS NULL  ";
		System.out.println(sql);
		List<Map<String, Object>> rows = template.queryForList(sql);
	    
		return rows;
	}
	
	public double montoCaja(int empresa_id, int almacen_id) {
		String sql ="SELECT IMPORTE FROM `caja` WHERE EMPRESA_ID=1 AND ALMACEN_ID=1 AND FECHA = (SELECT MAX(FECHA) AS FECHA FROM caja WHERE EMPRESA_ID='"+empresa_id+"' AND ALMACEN_ID='"+almacen_id+"' )\r\n" + 
				"AND CORTE IS NULL";
		double total = 0;
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.get(0) == null) {
			total = 0;
		} else {
			total = Double.parseDouble(certs.get(0));			
		} 
		return total;
	}
	
	public double totalRetiros(int empresa_id, int almacen_id) {
		String sql ="SELECT SUM(IMPORTE) AS IMPORTE FROM `retiros` WHERE EMPRESA_ID='"+empresa_id+"' AND ALMACEN_ID='"+almacen_id+"' \r\n" + 
				"AND CORTE IS NULL";
		double total = 0;
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			total = 0;
		} else {
			total = Double.parseDouble(certs.get(0));			
		} 
		return total;
	}
	
}
