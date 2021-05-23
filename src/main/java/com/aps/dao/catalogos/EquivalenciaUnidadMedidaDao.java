package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.EquivalenciaUnidadMedida;

public class EquivalenciaUnidadMedidaDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Object> getEquivUndMeds() {
		List<Object> undmeds = new ArrayList<Object>();
		String sql = "SELECT * FROM EQUIVALENCIA_UNIDAD_MEDIDA ORDER BY ID ASC";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("indice", (i + 1));
			mapa.put("codori", row.get("unidad_medida_origen"));
			mapa.put("coddes", row.get("unidad_medida_destino"));
			mapa.put("factor", row.get("factor_conversion"));

			String html = "&nbsp;&nbsp;<button onclick=\"deleteEquivUndMedida('" + row.get("unidad_medida_origen")
					+ "','"+row.get("unidad_medida_destino")+"')\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);

			undmeds.add(mapa);
		}
		return undmeds;
	}

	/**
	 * @param CLAVE
	 * @return 0-> if not exist(insert) and return the
	 *         claveEQUIVALENCIAUNIDAD_MEDIDA TO->(update)
	 */
	public int getEundByCodigo(String claveori, String clavedes) {
		String sql = "SELECT ID FROM EQUIVALENCIA_UNIDAD_MEDIDA WHERE UNIDAD_MEDIDA_ORIGEN='" + claveori
				+ "' AND UNIDAD_MEDIDA_DESTINO='" + clavedes + "'";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	public int save(EquivalenciaUnidadMedida l) {
		String sql = "INSERT INTO EQUIVALENCIA_UNIDAD_MEDIDA (UNIDAD_MEDIDA_ORIGEN, UNIDAD_MEDIDA_DESTINO, FACTOR_CONVERSION, FECHA_MODIFICACION) VALUES";
		sql += "('" + l.getUnidadMedidaOrigen() + "','" + l.getUnidadMedidaDestino() + "'," + l.getFactorConversion()
				+ ",'" + l.getFechaModificacion() + "')";
		return template.update(sql);
	}

	public int update(EquivalenciaUnidadMedida l) {
		String sql = "UPDATE EQUIVALENCIA_UNIDAD_MEDIDA SET FACTOR_CONVERSION=" + l.getFactorConversion()
				+ ",FECHA_MODIFICACION='" + l.getFechaModificacion() + "' WHERE UNIDAD_MEDIDA_ORIGEN='"
				+ l.getUnidadMedidaOrigen() + "' AND UNIDAD_MEDIDA_DESTINO='" + l.getUnidadMedidaDestino() + "'";
		return template.update(sql);
	}
	/**
	 * Eliminar todas las unidades de medida
	 * 
	 * @return
	 */
	public int deleteAllEquivalencias() {
		String sql = "DELETE FROM EQUIVALENCIA_UNIDAD_MEDIDA";
		return template.update(sql);
	}
	/**
	 * @param CLAVE
	 *  eliminar sublineados
	 */
	public int delete(String codori, String coddes) throws SQLException {
		String sql = "DELETE FROM EQUIVALENCIA_UNIDAD_MEDIDA WHERE UNIDAD_MEDIDA_ORIGEN='" + codori + "' AND UNIDAD_MEDIDA_DESTINO='"+coddes+"'";

		return template.update(sql);
	}
}
