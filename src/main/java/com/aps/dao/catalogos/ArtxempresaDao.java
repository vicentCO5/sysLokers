package com.aps.dao.catalogos;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Gem
 *
 */
public class ArtxempresaDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int getArtxempresabyId(int articuloid, int empresaid) {
		String sql = "SELECT ARTICULO_ID FROM ARTXEMPRESA WHERE articulo_id=" + articuloid + "  AND empresa_id="
				+ empresaid + "";
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}

	/**
	 * @param articuloid
	 * @param empresaid
	 * @param lineaid
	 * @param sublineaid
	 * @param sublineadosid
	 * @param marcaid
	 * @param departamentoid
	 * @param subdepartamentoid
	 * @param unidadmedidaid
	 * @param stockminimo
	 * @param abc
	 * @param costo
	 * @param costofob
	 * @param tamaniopallet
	 * @param tipopallet
	 * @param estado
	 * @return
	 */
	public boolean saveArt(Integer articuloid, Integer empresaid, Integer lineaid, Integer sublineaid,
			Integer sublineadosid, Integer marcaid, Integer departamentoid, Integer subdepartamentoid,
			Integer unidadmedidaid, double stockminimo, String abc, double costo, double costofob, String tamaniopallet,
			String tipopallet, String estado) {

		String sql = "INSERT INTO artxempresa(`articulo_id`, `empresa_id`, `linea_id`, `sublinea_id`, `sublineados_id`, `marca_id`, `departamento_id`, `subdepartamento_id`, `unidad_medida_id`, `stock_minimo`, `abc`, `costo`, `costofob`, `tamano_pallet`, `tipo_pallet`, `estado`)values";
		sql += "(" + articuloid + "," + empresaid + "," + lineaid + "," + sublineaid + "," + sublineadosid + ","
				+ marcaid + "," + departamentoid + "," + subdepartamentoid + "," + unidadmedidaid + "," + stockminimo
				+ ",'" + abc + "'," + costo + "," + costofob + ",'" + tamaniopallet + "','" + tipopallet + "','"
				+ estado + "')";
		System.out.println(sql);
		return template.update(sql) > 0;
	}

	public boolean updateArt(Integer articuloid, Integer empresaid, Integer lineaid, Integer sublineaid,
			Integer sublineadosid, Integer marcaid, Integer departamentoid, Integer subdepartamentoid,
			Integer unidadmedidaid, double stockminimo, String abc, double costo, double costofob, String tamaniopallet,
			String tipopallet, String estado) {

		String sql = "UPDATE ARTXEMPRESA SET `linea_id`=" + lineaid + ", `sublinea_id`=" + sublineaid
				+ ", `sublineados_id`=" + sublineadosid + ", `marca_id`=" + marcaid + ", `departamento_id`="
				+ departamentoid + ", `subdepartamento_id`=" + subdepartamentoid + ", `unidad_medida_id`="
				+ unidadmedidaid + ", `stock_minimo`=" + stockminimo + ", `abc`='" + abc + "', `costo`=" + costo
				+ ", `costofob`=" + costofob + ", `tamano_pallet`='" + tamaniopallet + "', `tipo_pallet`='" + tipopallet
				+ "', `estado`='" + estado + "' WHERE articulo_id=" + articuloid + " AND empresa_id=" + empresaid + "";

		System.out.println(sql);
		return template.update(sql) > 0;
	}

}
