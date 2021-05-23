package com.aps.dao.catalogos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.jdbc.core.JdbcTemplate;

import com.aps.beans.catalogos.Marca;

public class MarcaDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Object> getMarcas() {
		List<Object> marcas = new ArrayList<Object>();
		String sql = "SELECT M.*,E.NOMBRE as nombreemp FROM MARCA M INNER JOIN EMPRESA E ON (M.empresa_id=E.ID) ORDER BY ID ";
		List<Map<String, Object>> rows = template.queryForList(sql);
		for (int i = 0; i < rows.size(); i++) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			Map<?, ?> row = rows.get(i);
			mapa.put("clave", row.get("clave"));
			mapa.put("descripcion", row.get("descripcion"));
			mapa.put("empresa", row.get("nombreemp"));
			mapa.put("clasificacion", row.get("clasificacion"));
			//String html = "<button onclick=\"editmarca(" + row.get("clave")
			//		+ ")\" class='btn btn-info btn-xs' title='Editar'><i class='ace-icon fa fa-pencil bigger-130'></i></button>";
			String html = "&nbsp;&nbsp;<button onclick=\"deletemarca(" + row.get("clave")
					+ ")\" class='btn btn-danger btn-xs' title='Eliminar'><i class='ace-icon fa fa-trash-o bigger-130'></i></button>";
			mapa.put("accion", html);
			marcas.add(mapa);
		}
		return marcas;
	}

	/**
	 * @param CLAVE
	 *  marca eliminar marca
	 */
	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM MARCA WHERE CLAVE=" + id + "";

		return template.update(sql);
	}

	public int findByClave (String marca) {
		String sql = "SELECT ID FROM MARCA WHERE CLAVE='"+marca+"'";
		List<String> certs = template.queryForList(sql, String.class); 
		
	    if (certs.isEmpty()) {
	    	return 0;
	    } else {
	    	return Integer.parseInt(certs.get(0));
	    	
	    }		
	}
	/**
	 * @param idempresa
	 * @param clavemarca
	 * @return 0-> if not exist(insert) and return the clavemarca(update)
	 */
	public int getmarcaById_Idemp(int idempresa, String clave) {
		String sql = "SELECT ID FROM MARCA WHERE CLAVE=" + clave + " AND EMPRESA_ID=" + idempresa;
		List<String> certs = template.queryForList(sql, String.class);
		if (certs.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(certs.get(0));
		}
	}
	/**
	 * Insert marca
	 * @param m
	 * @return
	 */
	public int save(Marca m) {
		String sql = "INSERT INTO MARCA( clasificacion, clave, descripcion, empresa_id) values";
		sql += "('" + m.getClasificacion() + "'," + m.getClave() + ",'" + m.getDescripcion() + "'," + m.getEmpresa_id()
				+ ")";
		//System.out.println(sql);
		return template.update(sql);
	}
	/**
	 * update Marca
	 * @param m->Marca
	 * @return
	 */
	public int update(Marca m) {
		String sql = "UPDATE MARCA SET CLASIFICACION='" + m.getClasificacion() + "',DESCRIPCION='" + m.getDescripcion()
				+ "',EMPRESA_ID=" + m.getEmpresa_id() + " WHERE CLAVE=" + m.getClave() + "";
		System.out.println(sql);
		return template.update(sql);
	}
	/**
	 * Eliminar todas las marcas
	 * @return
	 */
	public int deleteAll() {
		String sql = "DELETE FROM MARCA";
		return template.update(sql);
	}
}
