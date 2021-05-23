package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.UnidadMedida;

public class UnidadMedidaDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	/**
	 * @param CLAVE
	 * @return 0-> if not exist(insert) and return the claveUNIDAD_MEDIDA
	 *         TO->(update)
	 */
	public int getundByCodigo(String clave) {
		String sql = "SELECT ID FROM UNIDAD_MEDIDA WHERE CLAVE='" + clave + "'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	/**
	 * @param UnidadMedida
	 * @return
	 */
	public int save(UnidadMedida l) {
		String sql = "INSERT INTO UNIDAD_MEDIDA( CLAVE, DESCRIPCION, ESUNIDAD_MINIMA,FECHA_CAMBIO,STATUS, UNIDAD_MINIMA, VALOR_MINIMO) values";
		sql += "('" + l.getClave() + "','" + l.getDescripcion() + "'," + l.getEsunidadMinima() + ",'"
				+ l.getFechaCambio() + "'," + l.getStatus() + ",'" + l.getUnidadMinima() + "'," + l.getValorMinimo()
				+ ")";
		return template.update(sql);
	}

	public int update(UnidadMedida l) {
		String sql = "UPDATE UNIDAD_MEDIDA SET DESCRIPCION='" + l.getDescripcion() + "',ESUNIDAD_MINIMA="
				+ l.getEsunidadMinima() + ",FECHA_CAMBIO='" + l.getFechaCambio() + "',STATUS=" + l.getStatus()
				+ ",UNIDAD_MINIMA='" + l.getUnidadMinima() + "',VALOR_MINIMO=" + l.getValorMinimo() + " WHERE CLAVE='"
				+ l.getClave() + "'";
		return template.update(sql);
	}
	public List<Object> getUndMeds() {
		List<Object> undmeds = new ArrayList<Object>();
		String sql = "SELECT * FROM UNIDAD_MEDIDA ORDER BY ID ASC";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("codigo", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("undmin", row.get("valor_minimo"));
			String est="";
			if( row.get("status").equals(1)) {
				est = "ACTIVO";
			}else
				est ="INACTIVO";
			mapa.put("estatus", est);
			String html = "&nbsp;&nbsp;<button onclick=\"deleteUndMedida('" + row.get("clave")
			+ "')\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
	
			undmeds.add(mapa);
		}
		return undmeds;
	}
	/**
	 * Eliminar todas las unidades de medida
	 * 
	 * @return
	 */
	public int deleteAllUndMed() {
		String sql = "DELETE FROM UNIDAD_MEDIDA";
		return template.update(sql);
	}
	/**
	 * @param CLAVE
	 *  eliminar sublineados
	 */
	public int delete(String clave) throws SQLException {
		String sql = "DELETE FROM UNIDAD_MEDIDA WHERE CLAVE='" + clave + "' ";

		return template.update(sql);
	}
}
