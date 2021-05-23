package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Sublineados;

public class SublineadosDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Object> getSublineasdos() {
		List<Object> lineas = new ArrayList<Object>();
		String sql = "SELECT M.*,E.NOMBRE as nombreemp FROM SUBLINEADOS M INNER JOIN EMPRESA E ON (M.empresa_id=E.ID) ORDER BY ID  ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("clave", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("empresa", row.get("nombreemp"));
			String html = "&nbsp;&nbsp;<button onclick=\"deleteSublineados(" + row.get("clave") + ","
					+ row.get("empresa_id")
					+ ")\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
			lineas.add(mapa);
		}
		return lineas;
	}

	/**
	 * Eliminar todO SUBLINEADOS
	 * 
	 * @return
	 */
	public int deleteAllSublineados() {
		String sql = "DELETE FROM SUBLINEADOS";
		return template.update(sql);
	}

	/**
	 * @param idempresa
	 * @param clave
	 *            sublineados
	 * @return 0-> if not exist(insert) and return the clavemarca TO->(update)
	 */
	public int getSublineadosbyclave_empresaid(int clave, int idempresa) {
		String sql = "SELECT ID FROM SUBLINEADOS WHERE CLAVE=" + clave + " AND EMPRESA_ID=" + idempresa;
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	/**
	 * @param sublineados
	 * @return
	 */
	public int save(Sublineados l) {
		String sql = "INSERT INTO SUBLINEADOS( clave, descripcion, empresa_id) values";
		sql += "(" + l.getClave() + ",'" + l.getDescripcion() + "'," + l.getEmpresa_id() + ")";
		return template.update(sql);
	}

	/**
	 * @param sublineados
	 * @return
	 */
	public int update(Sublineados l) {
		String sql = "UPDATE SUBLINEADOS SET DESCRIPCION='" + l.getDescripcion() + "',EMPRESA_ID=" + l.getEmpresa_id()
				+ " WHERE CLAVE=" + l.getClave() + "";
		return template.update(sql);
	}

	/**
	 * @param CLAVE
	 *            eliminar sublineados
	 */
	public int delete(int id, int idempresa) throws SQLException {
		String sql = "DELETE FROM SUBLINEADOS WHERE CLAVE=" + id + " and EMPRESA_ID=" + idempresa + "";

		return template.update(sql);
	}

	public int findByClave(String sublineados) {
		String sql = "SELECT ID FROM SUBLINEADOS WHERE CLAVE='" + sublineados + "'";
		List<String> certs = template.queryForList(sql, String.class);
		
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));

		}
	}
}
