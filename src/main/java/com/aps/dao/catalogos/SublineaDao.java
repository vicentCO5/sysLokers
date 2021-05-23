package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Sublinea;

public class SublineaDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public List<Object> getSubLineas() {
		List<Object> lineas = new ArrayList<Object>();
		String sql = "SELECT M.*,E.NOMBRE as nombreemp FROM SUBLINEA M INNER JOIN EMPRESA E ON (M.empresa_id=E.ID) ORDER BY ID  ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i+1));
			mapa.put("clave", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("empresa", row.get("nombreemp"));
			String html = "&nbsp;&nbsp;<button onclick=\"deletesublinea(" + row.get("clave")
					+ ")\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
			lineas.add(mapa);
		}
		return lineas;
	}
	/**
	 * Eliminar todas las SubLineas
	 * 
	 * @return
	 */
	public int deleteAllSublineas() {
		String sql = "DELETE FROM SUBLINEA";
		return template.update(sql);
	}
	/**
	 * @param idempresa
	 * @param clavesublinea
	 * @return 0-> if not exist(insert) and return the clavesublinea TO->(update)
	 */
	public int getSublineabyidclaveempresa(int clave, int idempresa) {
		String sql = "SELECT ID FROM SUBLINEA WHERE CLAVE=" + clave + " AND EMPRESA_ID=" + idempresa;
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
	public int save(Sublinea s) {
		String sql = "INSERT INTO SUBLINEA( clave, descripcion, empresa_id) values";
		sql += "(" + s.getClave() + ",'" + s.getDescripcion() + "'," + s.getEmpresa_id() + ")";
		return template.update(sql);

	}
	/**
	 * @param linea
	 * @return
	 */
	public int update(Sublinea s) {
		String sql = "UPDATE SUBLINEA SET DESCRIPCION='" + s.getDescripcion() + "',EMPRESA_ID=" + s.getEmpresa_id()
		+ " WHERE CLAVE=" + s.getClave() + "";
		return template.update(sql);

	}
	/**
	 * @param CLAVE
	 *  eliminar Linea
	 */
	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM SUBLINEA WHERE CLAVE=" + id + "";
		return template.update(sql);
	}
	public int findByClave (String sublinea) {
		String sql = "SELECT ID FROM SUBLINEA WHERE CLAVE='"+sublinea+"'";
		List<String> certs = template.queryForList(sql, String.class); 
		
	    if (certs.isEmpty()) {
	    	return 0;
	    } else {
	    	return Integer.parseInt(certs.get(0));
	    	
	    }		
	}
}
