package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Departamento;

public class DepartamentoDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Object> getDepartamentos() {
		List<Object> deptos = new ArrayList<Object>();
		String sql = "SELECT M.*,E.NOMBRE as nombreemp FROM DEPARTAMENTO M INNER JOIN EMPRESA E ON (M.empresa_id=E.ID) ORDER BY ID  ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("clave", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("empresa", row.get("nombreemp"));
			String html = "&nbsp;&nbsp;<button onclick=\"deletedepto(" + row.get("clave") + "," + row.get("empresa_id")
					+ ")\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
			deptos.add(mapa);
		}
		return deptos;
	}

	/**
	 * Eliminar todOS LOS DEPARTAMENTOS
	 * 
	 * @return
	 */
	public int deleteAllDeptos() {
		String sql = "DELETE FROM DEPARTAMENTO";
		return template.update(sql);
	}

	/**
	 * @param idempresa
	 * @param claveDEPTO
	 * @return 0-> if not exist(insert) and return the claveDEPTO TO->(update)
	 */
	public int getDeptobyempresaid_clave(int clave, int idempresa) {
		String sql = "SELECT ID FROM DEPARTAMENTO WHERE CLAVE='" + clave + "' AND EMPRESA_ID=" + idempresa;
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	/**
	 * @param DEPARTAMENTO
	 * @return
	 */
	public int save(Departamento l) {
		String sql = "INSERT INTO DEPARTAMENTO( CLAVE, DESCRIPCION, EMPRESA_ID) values";
		sql += "('" + l.getClave() + "','" + l.getDescripcion() + "'," + l.getEmpresa_id() + ")";
		return template.update(sql);
	}

	/**
	 * @param departamento
	 * @return
	 */
	public int update(Departamento l) {
		String sql = "UPDATE DEPARTAMENTO SET DESCRIPCION='" + l.getDescripcion() + "',EMPRESA_ID=" + l.getEmpresa_id()
				+ " WHERE CLAVE='" + l.getClave() + "'";
		return template.update(sql);
	}

	/**
	 * @param CLAVE
	 *            eliminar Departamento
	 */
	public int delete(int id, int idempresa) throws SQLException {
		String sql = "DELETE FROM DEPARTAMENTO WHERE CLAVE='" + id + "' AND EMPRESA_ID=" + idempresa + "";

		return template.update(sql);
	}

	/**
	 * @param depto
	 * @return
	 */
	public int findByClave(String depto) {
		String sql = "SELECT ID FROM DEPARTAMENTO WHERE CLAVE='" + depto + "'";
		List<String> certs = template.queryForList(sql, String.class);
		
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));

		}
	}

}
