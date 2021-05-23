package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Linea;

public class LineaDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Object> getLineas() {
		List<Object> lineas = new ArrayList<Object>();
		String sql = "SELECT M.*,E.NOMBRE as nombreemp FROM LINEA M INNER JOIN EMPRESA E ON (M.empresa_id=E.ID) ORDER BY ID  ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i+1));
			mapa.put("clave", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("empresa", row.get("nombreemp"));
			String html = "&nbsp;&nbsp;<button onclick=\"deletelinea(" + row.get("clave")
					+ ")\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
			lineas.add(mapa);
		}
		return lineas;
	}

	/**
	 * Eliminar todas las Lineas
	 * 
	 * @return
	 */
	public int deleteAllLineas() {
		String sql = "DELETE FROM LINEA";
		return template.update(sql);
	}

	/**
	 * @param idempresa
	 * @param clavemarca
	 * @return 0-> if not exist(insert) and return the clavemarca TO->(update)
	 */
	public int getLineabyempresaid_clave(int clave, int idempresa) {
		String sql = "SELECT ID FROM LINEA WHERE CLAVE=" + clave + " AND EMPRESA_ID=" + idempresa;
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	/**
	 * @param linea
	 * @return
	 */
	public int save(Linea l) {
		String sql = "INSERT INTO LINEA( clave, descripcion, empresa_id) values";
		sql += "(" + l.getClave() + ",'" + l.getDescripcion() + "'," + l.getEmpresa_id() + ")";
		return template.update(sql);
	}

	/**
	 * @param linea
	 * @return
	 */
	public int update(Linea l) {
		String sql = "UPDATE LINEA SET DESCRIPCION='" + l.getDescripcion() + "',EMPRESA_ID=" + l.getEmpresa_id()
				+ " WHERE CLAVE=" + l.getClave() + "";
		return template.update(sql);
	}
	/**
	 * @param CLAVE
	 *  eliminar Linea
	 */
	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM LINEA WHERE CLAVE=" + id + "";

		return template.update(sql);
	}
	
	public int findByClave (String linea) {
		String sql = "SELECT ID FROM LINEA WHERE CLAVE='"+linea+"'";
		List<String> certs = template.queryForList(sql, String.class); 
		
	    if (certs.isEmpty()) {
	    	return 0;
	    } else {
	    	return Integer.parseInt(certs.get(0));
	    	
	    }		
	}
}
