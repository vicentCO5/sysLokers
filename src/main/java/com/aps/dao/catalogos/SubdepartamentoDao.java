package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Subdepartamento;

public class SubdepartamentoDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public List<Object> getSubdepartamentos() {
		List<Object> subdeptos = new ArrayList<Object>();
		String sql = "SELECT M.*,E.NOMBRE as nombreemp FROM SUBDEPARTAMENTO M INNER JOIN EMPRESA E ON (M.cveempresa=E.ID) ORDER BY ID  ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i+1));
			mapa.put("clave", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("empresa", row.get("nombreemp"));
			String html = "&nbsp;&nbsp;<button onclick=\"deletesubdepto(" + row.get("clave")
					+ ","+ row.get("cveempresa")+ ")\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
			subdeptos.add(mapa);
		}
		return subdeptos;
	}
	/**
	 * Eliminar todOS LOS SUBDEPARTAMENTOS
	 * 
	 * @return
	 */
	public int deleteAllSubDeptos() {
		String sql = "DELETE FROM SUBDEPARTAMENTO";
		return template.update(sql);
	}
	/**
	 * @param idempresa
	 * @param clavesubdeptos
	 * @return 0-> if not exist(insert) and return the clavesubDEPTO TO->(update)
	 */
	public int getSubdeptobyempresaid_clave(int clave, int idempresa) {
		String sql = "SELECT ID FROM SUBDEPARTAMENTO WHERE CLAVE='" + clave + "' AND CVEEMPRESA='" + idempresa + "'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}
	/**
	 * @param SUBDEPARTAMENTO
	 * @return
	 */
	public int save(Subdepartamento l) {
		String sql = "INSERT INTO SUBDEPARTAMENTO( CLAVE, DESCRIPCION, CVEEMPRESA) values";
		sql += "('" + l.getClave() + "','" + l.getDescripcion() + "','" + l.getCveempresa() + "')";
		return template.update(sql);
	}
	/**
	 * @param subdepartamento
	 * @return
	 */
	public int update(Subdepartamento l) {
		String sql = "UPDATE SUBDEPARTAMENTO SET DESCRIPCION='" + l.getDescripcion() + "',CVEEMPRESA='" + l.getCveempresa()
				+ "' WHERE CLAVE='" + l.getClave() + "'";
		return template.update(sql);
	}
	/**
	 * @param CLAVE
	 * @param idempresa
	 *  eliminar subdepartamento
	 */
	public int delete(int id,int idempresa) throws SQLException {
		String sql = "DELETE FROM SUBDEPARTAMENTO WHERE CLAVE='" + id + "' AND CVEEMPRESA="+idempresa+"";

		return template.update(sql);
	}
	/**
	 * @param subdepto
	 * @return
	 */
	public int findByClave(String subdepto) {
		String sql = "SELECT ID FROM SUBDEPARTAMENTO WHERE CLAVE='" + subdepto + "'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));

		}
	}
}
